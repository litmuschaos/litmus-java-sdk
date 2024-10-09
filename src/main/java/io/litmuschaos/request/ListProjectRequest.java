package io.litmuschaos.request;

public class ListProjectRequest {
    private final Integer page;
    private final Integer limit;
    private final String sortField;
    private final Boolean createdByMe;

    private ListProjectRequest(Builder builder) {
        this.page = builder.page;
        this.limit = builder.limit;
        this.sortField = builder.sortField;
        this.createdByMe = builder.createdByMe;
    }

    public static class Builder {
        private Integer page;
        private Integer limit;
        private String sortField;
        private Boolean createdByMe;

        public Builder page(Integer page) {
            this.page = page;
            return this;
        }

        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder sortField(String sortField) {
            this.sortField = sortField;
            return this;
        }

        public Builder createdByMe(Boolean createdByMe) {
            this.createdByMe = createdByMe;
            return this;
        }

        public ListProjectRequest build() {
            return new ListProjectRequest(this);
        }
    }

    public Integer getPage() {
        return page;
    }

    public Integer getLimit() {
        return limit;
    }

    public String getSortField() {
        return sortField;
    }

    public Boolean getCreatedByMe() {
        return createdByMe;
    }
}

