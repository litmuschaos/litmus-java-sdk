package io.litmuschaos;

import org.junit.jupiter.api.Assumptions;

final class IntegrationTestConfig {

    private static final String HOST_ENV = "LITMUS_TEST_HOST";
    private static final String TOKEN_ENV = "LITMUS_TEST_TOKEN";
    private static final String USER_ID_ENV = "LITMUS_TEST_USER_ID";
    private static final String DELETE_TOKEN_ENV = "LITMUS_TEST_DELETE_TOKEN";
    private static final String PROJECT_ID_ENV = "LITMUS_TEST_PROJECT_ID";
    private static final String EXPERIMENT_RUN_ID_ENV = "LITMUS_TEST_EXPERIMENT_RUN_ID";

    private IntegrationTestConfig() {
    }

    static void assumeConfigured() {
        Assumptions.assumeTrue(
                hasText(hostUrl()) && hasText(token()),
                "Set " + HOST_ENV + " and " + TOKEN_ENV + " to run integration tests."
        );
    }

    static String hostUrl() {
        return System.getenv(HOST_ENV);
    }

    static String token() {
        return System.getenv(TOKEN_ENV);
    }

    static String userId() {
        String userId = System.getenv(USER_ID_ENV);
        Assumptions.assumeTrue(hasText(userId), "Set " + USER_ID_ENV + " to run this integration test.");
        return userId;
    }

    static String projectId() {
        String projectId = System.getenv(PROJECT_ID_ENV);
        Assumptions.assumeTrue(hasText(projectId), "Set " + PROJECT_ID_ENV + " to run this integration test.");
        return projectId;
    }

    static String experimentRunId() {
        String experimentRunId = System.getenv(EXPERIMENT_RUN_ID_ENV);
        Assumptions.assumeTrue(
                hasText(experimentRunId),
                "Set " + EXPERIMENT_RUN_ID_ENV + " to run this integration test."
        );
        return experimentRunId;
    }

    static String deleteToken() {
        String deleteToken = System.getenv(DELETE_TOKEN_ENV);
        Assumptions.assumeTrue(
                hasText(deleteToken),
                "Set " + DELETE_TOKEN_ENV + " to run this integration test."
        );
        return deleteToken;
    }

    private static boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}
