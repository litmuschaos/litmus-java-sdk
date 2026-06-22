package io.litmuschaos.util;

import io.litmuschaos.exception.ExperimentRunTimeoutException;
import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.generated.types.ExperimentRun;
import io.litmuschaos.generated.types.ExperimentRunStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayDeque;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExperimentRunPollerTest {

    private final MutableClock clock = new MutableClock();

    @AfterEach
    void clearInterruptedFlag() {
        Thread.interrupted();
    }

    @Test
    void returnsFinalRunAfterPollingUntilTerminalPhase() throws LitmusApiException, IOException {
        Queue<ExperimentRun> runs = new ArrayDeque<>();
        runs.add(run(ExperimentRunStatus.Queued));
        runs.add(run(ExperimentRunStatus.Running));
        ExperimentRun completedRun = run(ExperimentRunStatus.Completed);
        runs.add(completedRun);
        ExperimentRunPoller poller = pollerAdvancingClock();

        ExperimentRun result = poller.waitFor(runs::remove, Duration.ofSeconds(5), Duration.ofMillis(100));

        assertThat(result).isSameAs(completedRun);
    }

    @Test
    void returnsImmediatelyWhenFirstRunIsTerminal() throws LitmusApiException, IOException {
        ExperimentRun completedRun = run(ExperimentRunStatus.Completed);
        ExperimentRunPoller poller = pollerAdvancingClock();

        ExperimentRun result = poller.waitFor(
                () -> completedRun,
                Duration.ofSeconds(5),
                Duration.ofMillis(100)
        );

        assertThat(result).isSameAs(completedRun);
        assertThat(clock.instant()).isEqualTo(Instant.EPOCH);
    }

    @Test
    void throwsWhenRunDoesNotReachTerminalPhaseBeforeTimeout() {
        ExperimentRunPoller poller = pollerAdvancingClock();

        assertThatThrownBy(() -> poller.waitFor(
                () -> run(ExperimentRunStatus.Running),
                Duration.ofMillis(250),
                Duration.ofMillis(100)
        )).isInstanceOf(ExperimentRunTimeoutException.class)
                .hasMessageContaining("PT0.25S");
    }

    @Test
    void returnsNonSuccessfulTerminalRun() throws LitmusApiException, IOException {
        ExperimentRun erroredRun = run(ExperimentRunStatus.Completed_With_Error);
        ExperimentRunPoller poller = pollerAdvancingClock();

        ExperimentRun result = poller.waitFor(
                () -> erroredRun,
                Duration.ofSeconds(5),
                Duration.ofMillis(100)
        );

        assertThat(result).isSameAs(erroredRun);
    }

    @Test
    void rejectsInvalidDurations() {
        ExperimentRunPoller poller = pollerAdvancingClock();

        assertThatThrownBy(() -> poller.waitFor(
                () -> run(ExperimentRunStatus.Running),
                Duration.ZERO,
                Duration.ofMillis(100)
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("timeout must be positive");

        assertThatThrownBy(() -> poller.waitFor(
                () -> run(ExperimentRunStatus.Running),
                Duration.ofSeconds(1),
                Duration.ZERO
        )).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("interval must be positive");
    }

    @Test
    void restoresInterruptedFlagWhenSleepIsInterrupted() {
        ExperimentRunPoller poller = new ExperimentRunPoller(clock, duration -> {
            throw new InterruptedException("interrupted");
        });

        assertThatThrownBy(() -> poller.waitFor(
                () -> run(ExperimentRunStatus.Running),
                Duration.ofSeconds(1),
                Duration.ofMillis(100)
        )).isInstanceOf(LitmusApiException.class)
                .hasMessage("Interrupted while waiting for experiment run.");
        assertThat(Thread.currentThread().isInterrupted()).isTrue();
    }

    private ExperimentRunPoller pollerAdvancingClock() {
        return new ExperimentRunPoller(clock, clock::advance);
    }

    private static ExperimentRun run(ExperimentRunStatus phase) {
        ExperimentRun run = new ExperimentRun();
        run.setPhase(phase);
        return run;
    }

    private static class MutableClock extends Clock {
        private Instant instant = Instant.EPOCH;

        @Override
        public ZoneId getZone() {
            return ZoneId.of("UTC");
        }

        @Override
        public Clock withZone(ZoneId zone) {
            return this;
        }

        @Override
        public Instant instant() {
            return instant;
        }

        void advance(Duration duration) {
            instant = instant.plus(duration);
        }
    }
}
