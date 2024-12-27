package io.litmuschaos.response;

import java.util.Objects;

public class GetManifestWithInfraIDResponse {
    private String manifest;

    public GetManifestWithInfraIDResponse(String manifest) {
        this.manifest = manifest;
    }

    public String getManifest() {
        return manifest;
    }

    @Override
    public String toString() {
        return "GetManifestWithInfraIDResponse{" +
                "manifest='" + manifest + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GetManifestWithInfraIDResponse that = (GetManifestWithInfraIDResponse) o;
        return Objects.equals(manifest, that.manifest);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(manifest);
    }
}
