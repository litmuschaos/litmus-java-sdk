package io.litmuschaos.response;

public class CapabilityResponse {
    private Dex dex;

    public Dex getDex() {
        return dex;
    }
}

class Dex {
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }
}
