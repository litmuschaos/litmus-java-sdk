package io.litmuschaos.request;

import io.litmuschaos.generated.types.SaveChaosExperimentRequest;
import io.litmuschaos.util.Builder;

public class ChaosExperimentSaveRequest {

    private final String projectID;
    private final SaveChaosExperimentRequest request;

    private ChaosExperimentSaveRequest(ChaosExperimentSaveRequestBuilder builder) {
        this.projectID = builder.projectID;
        this.request = builder.request;
    }

    public String getProjectID() {
        return projectID;
    }

    public SaveChaosExperimentRequest getRequest() {
        return request;
    }

    public static ChaosExperimentSaveRequestBuilder builder() {
        return new ChaosExperimentSaveRequestBuilder();
    }

    public static class ChaosExperimentSaveRequestBuilder implements Builder<ChaosExperimentSaveRequest> {
        private String projectID;
        private SaveChaosExperimentRequest request;

        public ChaosExperimentSaveRequestBuilder projectID(String projectID) {
            this.projectID = projectID;
            return this;
        }

        public ChaosExperimentSaveRequestBuilder request(SaveChaosExperimentRequest request) {
            this.request = request;
            return this;
        }

        public ChaosExperimentSaveRequest build() {
            return new ChaosExperimentSaveRequest(this);
        }
    }
}
