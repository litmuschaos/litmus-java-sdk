package io.litmuschaos.response;

public class CapabilityResponse {
    private Dex dex;

    public Dex getDex() {
        return dex;
    }

    private class Dex {
        private boolean enabled;

        public boolean isEnabled() {
            return enabled;
        }
    }
}
