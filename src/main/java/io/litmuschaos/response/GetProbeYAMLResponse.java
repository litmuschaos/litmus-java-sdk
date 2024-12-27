package io.litmuschaos.response;

import java.util.Objects;

public class GetProbeYAMLResponse {
    private String probeYAML;

    public GetProbeYAMLResponse(String probeYAML) {
        this.probeYAML = probeYAML;
    }

    public String getProbeYAML() {
        return probeYAML;
    }

    @Override
    public String toString() {
        return "GetProbeYAMLResponse{" +
                "probeYAML='" + probeYAML + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GetProbeYAMLResponse that = (GetProbeYAMLResponse) o;
        return Objects.equals(probeYAML, that.probeYAML);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(probeYAML);
    }
}
