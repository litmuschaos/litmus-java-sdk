package io.litmuschaos.util;

import io.litmuschaos.exception.ExperimentRunTimeoutException;
import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.generated.types.ExperimentRun;
import io.litmuschaos.generated.types.ExperimentRunStatus;

import java.io.IOException;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class ExperimentRunPoller {

    private static final Set<ExperimentRunStatus> TERMINAL_PHASES = EnumSet.of(
            ExperimentRunStatus.Completed,
            ExperimentRunStatus.Completed_With_Error,
            ExperimentRunStatus.Stopped,
            ExperimentRunStatus.Skipped,
            ExperimentRunStatus.Error,
            ExperimentRunStatus.Timeout,
            ExperimentRunStatus.Terminated
    );

    private final Clock clock;
    private final Sleeper sleeper;

    public ExperimentRunPoller() {
        this(Clock.systemUTC(), duration -> Thread.sleep(duration.toMillis()));
    }

    ExperimentRunPoller(Clock clock, Sleeper sleeper) {
        this.clock = Objects.requireNonNull(clock, "clock must not be null");
        this.sleeper = Objects.requireNonNull(sleeper, "sleeper must not be null");
    }

    /**
     * Waits until an experiment run reaches a terminal phase.
     *
     * @param fetcher fetches the latest experiment run state
     * @param timeout maximum time to wait
     * @param interval delay between state checks
     * @return the latest experiment run once it reaches a terminal phase
     * @throws IOException when fetching the experiment run fails
     * @throws LitmusApiException when fetching fails, waiting is interrupted, or the timeout expires
     */
    public ExperimentRun waitFor(ExperimentRunFetcher fetcher, Duration timeout, Duration interval)
            throws IOException, LitmusApiException {
        validate(fetcher, timeout, interval);

        Instant deadline = clock.instant().plus(timeout);
        while (true) {
            ExperimentRun experimentRun = fetcher.fetch();
            if (experimentRun == null) {
                throw new LitmusApiException("Experiment run fetcher returned null.");
            }
            if (isTerminal(experimentRun.getPhase())) {
                return experimentRun;
            }

            Instant now = clock.instant();
            if (!now.isBefore(deadline)) {
                throw timeoutException(timeout);
            }

            sleepUntilNextPoll(now, deadline, interval);
        }
    }

    public static boolean isTerminal(ExperimentRunStatus phase) {
        return TERMINAL_PHASES.contains(phase);
    }

    private void sleepUntilNextPoll(Instant now, Instant deadline, Duration interval) throws LitmusApiException {
        Duration remaining = Duration.between(now, deadline);
        Duration sleepDuration = interval.compareTo(remaining) < 0 ? interval : remaining;
        try {
            sleeper.sleep(sleepDuration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new LitmusApiException("Interrupted while waiting for experiment run.", e);
        }
    }

    private void validate(ExperimentRunFetcher fetcher, Duration timeout, Duration interval) {
        Objects.requireNonNull(fetcher, "fetcher must not be null");
        Objects.requireNonNull(timeout, "timeout must not be null");
        Objects.requireNonNull(interval, "interval must not be null");
        if (timeout.isZero() || timeout.isNegative()) {
            throw new IllegalArgumentException("timeout must be positive");
        }
        if (interval.isZero() || interval.isNegative()) {
            throw new IllegalArgumentException("interval must be positive");
        }
    }

    private ExperimentRunTimeoutException timeoutException(Duration timeout) {
        return new ExperimentRunTimeoutException(
                "Experiment run did not reach a terminal phase within " + timeout + "."
        );
    }

    @FunctionalInterface
    public interface ExperimentRunFetcher {
        ExperimentRun fetch() throws IOException, LitmusApiException;
    }

    @FunctionalInterface
    interface Sleeper {
        void sleep(Duration duration) throws InterruptedException;
    }
}
