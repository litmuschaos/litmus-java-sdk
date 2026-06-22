package io.litmuschaos;

import io.litmuschaos.exception.LitmusApiException;
import io.litmuschaos.generated.types.ExperimentRun;
import io.litmuschaos.generated.types.ExperimentRunStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.EnumSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("integration")
class ExperimentRunLiveTest {

    private static final Set<ExperimentRunStatus> TERMINAL_STATUSES = EnumSet.of(
            ExperimentRunStatus.Completed,
            ExperimentRunStatus.Completed_With_Error,
            ExperimentRunStatus.Stopped,
            ExperimentRunStatus.Skipped,
            ExperimentRunStatus.Error,
            ExperimentRunStatus.Timeout,
            ExperimentRunStatus.Terminated
    );

    private LitmusClient litmusClient;

    @BeforeEach
    void setup() {
        IntegrationTestConfig.assumeConfigured();
        litmusClient = new LitmusClient(IntegrationTestConfig.hostUrl(), IntegrationTestConfig.token());
    }

    @Test
    void waitForExperimentRunReturnsTerminalExperimentRunFromLiveServer()
            throws LitmusApiException, IOException {
        ExperimentRun experimentRun = litmusClient.waitForExperimentRun(
                IntegrationTestConfig.projectId(),
                IntegrationTestConfig.experimentRunId(),
                Duration.ofSeconds(30),
                Duration.ofSeconds(2)
        );

        assertThat(experimentRun.getExperimentRunID()).isEqualTo(IntegrationTestConfig.experimentRunId());
        assertThat(experimentRun.getPhase()).isIn(TERMINAL_STATUSES);
    }
}
