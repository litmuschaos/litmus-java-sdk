package io.litmuschaos.request;

import io.litmuschaos.util.Builder;

public class ListProjectRequest {

    private final Integer page;
    private final Integer limit;
    private final String sortField;
    private final Boolean createdByMe;

    private ListProjectRequest(ListProjectRequestBuilder builder) {
        this.page = builder.page;
        this.limit = builder.limit;
        this.sortField = builder.sortField;
        this.createdByMe = builder.createdByMe;
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

    public static ListProjectRequestBuilder builder() {
        return new ListProjectRequestBuilder();
    }

    public static class ListProjectRequestBuilder implements Builder<ListProjectRequest> {

        private Integer page;
        private Integer limit;
        private String sortField;
        private Boolean createdByMe;

        public ListProjectRequestBuilder page(Integer page) {
            this.page = page;
            return this;
        }

        public ListProjectRequestBuilder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public ListProjectRequestBuilder sortField(String sortField) {
            this.sortField = sortField;
            return this;
        }

        public ListProjectRequestBuilder createdByMe(Boolean createdByMe) {
            this.createdByMe = createdByMe;
            return this;
        }

        @Override
        public ListProjectRequest build() {
            return new ListProjectRequest(this);
        }
    }
}

