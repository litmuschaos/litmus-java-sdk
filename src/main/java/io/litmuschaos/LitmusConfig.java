package io.litmuschaos;

/**
 * The basic configuration of this SDK.
 */
public class LitmusConfig {

    public static final LitmusConfig DEFAULT = new LitmusConfig() {

        void throwException() {
            throw new UnsupportedOperationException("This config is immutable");
        }

        @Override
        public void setHttpClientReadTimeoutMillis(Long httpClientReadTimeoutMillis) {
            throwException();
        }

        @Override
        public void setHttpClientWriteTimeoutMillis(Long httpClientWriteTimeoutMillis) {
            throwException();
        }

        @Override
        public void setHttpClientCallTimeoutMillis(Long httpClientCallTimeoutMillis) {
            throwException();
        }

        @Override
        public void setHttpClientConnectTimeoutMillis(Long httpClientConnectTimeoutMillis) {
            throwException();
        }
    };

    /**
     * The underlying HTTP client's read timeout (in milliseconds). The default is 10 seconds.
     */
    private Long httpClientReadTimeoutMillis;

    /**
     * The underlying HTTP client's write timeout (in milliseconds). The default is 10 seconds.
     */
    private Long httpClientWriteTimeoutMillis;

    /**
     * The underlying HTTP client's call timeout (in milliseconds).
     * By default, there is no timeout for complete calls while there is for connect/write/read actions within a call.
     */
    private Long httpClientCallTimeoutMillis;

    /**
     * The underlying HTTP client's connect timeout (in milliseconds). The default is 10 seconds.
     */
    private Long httpClientConnectTimeoutMillis;

    public void setHttpClientReadTimeoutMillis(Long httpClientReadTimeoutMillis) {
        this.httpClientReadTimeoutMillis = httpClientReadTimeoutMillis;
    }

    public void setHttpClientWriteTimeoutMillis(Long httpClientWriteTimeoutMillis) {
        this.httpClientWriteTimeoutMillis = httpClientWriteTimeoutMillis;
    }

    public void setHttpClientCallTimeoutMillis(Long httpClientCallTimeoutMillis) {
        this.httpClientCallTimeoutMillis = httpClientCallTimeoutMillis;
    }

    public void setHttpClientConnectTimeoutMillis(Long httpClientConnectTimeoutMillis) {
        this.httpClientConnectTimeoutMillis = httpClientConnectTimeoutMillis;
    }

    public Long getHttpClientReadTimeoutMillis() {
        return httpClientReadTimeoutMillis;
    }

    public Long getHttpClientWriteTimeoutMillis() {
        return httpClientWriteTimeoutMillis;
    }

    public Long getHttpClientCallTimeoutMillis() {
        return httpClientCallTimeoutMillis;
    }

    public Long getHttpClientConnectTimeoutMillis() {
        return httpClientConnectTimeoutMillis;
    }
}
