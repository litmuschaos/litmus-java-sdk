package io.litmuschaos.request;

import io.litmuschaos.generated.client.ListExperimentProjectionRoot;
import io.litmuschaos.generated.types.ListExperimentRequest;
import io.litmuschaos.util.Builder;

public class ChaosExperimentListRequest {

    private final String projectID;
    private final ListExperimentRequest request;
    private final ListExperimentProjectionRoot projectionRoot;

    private ChaosExperimentListRequest(ChaosExperimentListRequestBuilder builder) {
        this.projectID = builder.projectID;
        this.request = builder.request;
        this.projectionRoot = builder.projectionRoot;
    }

    public String getProjectID() {
        return projectID;
    }

    public ListExperimentRequest getRequest() {
        return request;
    }

    public ListExperimentProjectionRoot getProjectionRoot() {
        return projectionRoot;
    }

    public static ChaosExperimentListRequestBuilder builder() {
        return new ChaosExperimentListRequestBuilder();
    }

    public static class ChaosExperimentListRequestBuilder implements Builder<ChaosExperimentListRequest> {
        private String projectID;
        private ListExperimentRequest request;
        private ListExperimentProjectionRoot projectionRoot;

        public ChaosExperimentListRequestBuilder projectID(String projectID) {
            this.projectID = projectID;
            return this;
        }

        public ChaosExperimentListRequestBuilder request(ListExperimentRequest request) {
            this.request = request;
            return this;
        }

        public ChaosExperimentListRequestBuilder projectionRoot(ListExperimentProjectionRoot projectionRoot) {
            this.projectionRoot = projectionRoot;
            return this;
        }

        public ChaosExperimentListRequest build() {
            return new ChaosExperimentListRequest(this);
        }
    }
}
