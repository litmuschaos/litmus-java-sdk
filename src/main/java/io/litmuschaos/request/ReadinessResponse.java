package io.litmuschaos.request;

import java.util.Objects;

public class ReadinessResponse {
    private final String database;
    private final String collections;

    public ReadinessResponse(String database, String collections) {
        this.database = database;
        this.collections = collections;
    }

    public String getDatabase() {
        return database;
    }

    public String getCollections() {
        return collections;
    }

    @Override
    public String toString() {
        return "ReadinessResponse{" +
                "database='" + database + '\'' +
                ", collections='" + collections + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReadinessResponse that = (ReadinessResponse) o;
        return Objects.equals(database, that.database) && Objects.equals(collections, that.collections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(database, collections);
    }
}
