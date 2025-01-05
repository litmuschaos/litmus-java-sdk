package io.litmuschaos.response;

import java.util.Objects;

public class GetInfraManifestResponse {
    private String manifest;

    public GetInfraManifestResponse(String manifest) {
        this.manifest = manifest;
    }

    public String getManifest() {
        return manifest;
    }

    @Override
    public String toString() {
        return "GetInfraManifestResponse{" +
                "manifest='" + manifest + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GetInfraManifestResponse that = (GetInfraManifestResponse) o;
        return Objects.equals(manifest, that.manifest);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(manifest);
    }
}
