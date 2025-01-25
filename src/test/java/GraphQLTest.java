import io.litmuschaos.LitmusClient;
import io.litmuschaos.generated.client.*;
import io.litmuschaos.generated.types.*;

import java.util.Random;

import org.assertj.core.api.Assertions;
import io.litmuschaos.generated.client.ListInfrasGraphQLQuery;
import io.litmuschaos.generated.client.ListInfrasProjectionRoot;
import io.litmuschaos.generated.types.ListInfraResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraphQLTest {

    private static final String HOST_URL = "http://127.0.0.1:3000";
    private static final String TEST_TOKEN = "Bearer token"; // Put your token here

    private LitmusClient litmusClient;

    @BeforeEach
    public void setup() {
        this.litmusClient = new LitmusClient(HOST_URL, TEST_TOKEN);
    }

    // ChaosEnvironment

//    @Test
//    public void getEnvironmentTest() {
//
//        String projectId = "3f397b8c-292f-4d4d-b5dd-447e3205acd1";
//        int random = new Random().nextInt();
//        String environmentID = "TEST-ENVIRONMENT-ID-" + random;
//        String environmentName = "TEST-ENVIRONMENT-NAME-" + random;
//
//        Environment result = litmusClient.createEnvironment(
//                CreateEnvironmentGraphQLQuery.newRequest()
//                        .projectID(projectId)
//                        .request(CreateEnvironmentRequest
//                                .newBuilder()
//                                .environmentID(environmentID)
//                                .name(environmentName)
//                                .type(EnvironmentType.NON_PROD)
//                                .build()
//                        )
//                        .build(),
//                new CreateEnvironmentProjectionRoot<>()
//                        .environmentID()
//        );
//
//        Environment environment = litmusClient.getEnvironment(
//                GetEnvironmentGraphQLQuery.newRequest()
//                        .projectID(projectId)
//                        .environmentID(result.getEnvironmentID())
//                        .build(),
//                new GetEnvironmentProjectionRoot<>()
//                        .name());
//
//        Assertions.assertThat(environment.getName()).isEqualTo(environmentName);
//    }
//
//    @Test
//    public void listEnvironmentsTest() {
//
//        String projectId = "3f397b8c-292f-4d4d-b5dd-447e3205acd1";
//        ListEnvironmentResponse result = litmusClient.listEnvironments(
//                ListEnvironmentsGraphQLQuery.newRequest()
//                        .projectID(projectId)
//                        .build(),
//                new ListEnvironmentsProjectionRoot()
//                        .totalNoOfEnvironments()
//        );
//    }
//
//    @Test
//    public void deleteEnvironmentTest() {
//
//        String projectId = "3f397b8c-292f-4d4d-b5dd-447e3205acd1";
//        int random = new Random().nextInt();
//        String environmentID = "TEST-ENVIRONMENT-ID-" + random;
//        String environmentName = "TEST-ENVIRONMENT-NAME-" + random;
//
//        litmusClient.createEnvironment(
//                CreateEnvironmentGraphQLQuery.newRequest()
//                        .projectID(projectId)
//                        .request(CreateEnvironmentRequest
//                                .newBuilder()
//                                .environmentID(environmentID)
//                                .name(environmentName)
//                                .type(EnvironmentType.NON_PROD)
//                                .build()
//                        )
//                        .build(),
//                new CreateEnvironmentProjectionRoot<>()
//                        .environmentID()
//        );
//
//        String result = litmusClient.deleteEnvironment(
//                DeleteEnvironmentGraphQLQuery.newRequest()
//                        .projectID(projectId)
//                        .environmentID(environmentID)
//                        .build()
//        );
//
//        Assertions.assertThat(result).isEqualTo("successfully deleted environment");
//    }
//
//    @Test
//    public void createEnvironmentTest() {
//
//        String projectId = "3f397b8c-292f-4d4d-b5dd-447e3205acd1";
//        int random = new Random().nextInt();
//        String environmentID = "TEST-ENVIRONMENT-ID-" + random;
//        String environmentName = "TEST-ENVIRONMENT-NAME-" + random;
//
//
//        Environment result = litmusClient.createEnvironment(
//                CreateEnvironmentGraphQLQuery.newRequest()
//                        .projectID(projectId)
//                        .request(CreateEnvironmentRequest
//                                .newBuilder()
//                                .environmentID(environmentID)
//                                .name(environmentName)
//                                .type(EnvironmentType.NON_PROD)
//                                .build()
//                        )
//                        .build(),
//                new CreateEnvironmentProjectionRoot<>()
//                        .environmentID()
//        );
//
//        Assertions.assertThat(result.getEnvironmentID()).isEqualTo(environmentID);
//    }
//
//    @Test
//    public void updateEnvironmentTest() {
//
//        String projectId = "3f397b8c-292f-4d4d-b5dd-447e3205acd1";
//        int random = new Random().nextInt();
//        String environmentID = "TEST-ENVIRONMENT-ID-" + random;
//        String environmentName = "TEST-ENVIRONMENT-NAME-" + random;
//
//        Environment result = litmusClient.createEnvironment(
//                CreateEnvironmentGraphQLQuery.newRequest()
//                        .projectID(projectId)
//                        .request(CreateEnvironmentRequest
//                                .newBuilder()
//                                .environmentID(environmentID)
//                                .name(environmentName)
//                                .type(EnvironmentType.NON_PROD)
//                                .build()
//                        )
//                        .build(),
//                new CreateEnvironmentProjectionRoot<>()
//                        .environmentID()
//        );
//
//        String updatedResult = litmusClient.updateEnvironment(
//                UpdateEnvironmentGraphQLQuery.newRequest()
//                        .projectID(projectId)
//                        .request(UpdateEnvironmentRequest
//                                .newBuilder()
//                                .environmentID(result.getEnvironmentID())
//                                .name("updated environment")
//                                .type(EnvironmentType.NON_PROD)
//                                .description("Updated environment description")
//                                .build()
//                        )
//                        .build()
//        );
//
//        Environment updatedEnvironment = litmusClient.getEnvironment(
//                GetEnvironmentGraphQLQuery.newRequest()
//                        .projectID(projectId)
//                        .environmentID(environmentID)
//                        .build(),
//                new GetEnvironmentProjectionRoot<>()
//                        .name()
//        );
//
//        Assertions.assertThat(updatedResult).isEqualTo("environment updated successfully");
//        Assertions.assertThat(updatedEnvironment.getName()).isEqualTo("updated environment");
//    }
}