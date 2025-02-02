package io.litmuschaos.graphql;
import com.jayway.jsonpath.TypeRef;
import com.netflix.graphql.dgs.client.GraphQLClient;
import com.netflix.graphql.dgs.client.GraphQLResponse;
import com.netflix.graphql.dgs.client.HttpResponse;
import java.io.IOException;
import io.litmuschaos.constants.HttpStatus;
import io.litmuschaos.exception.LitmusApiException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static io.litmuschaos.constants.RequestHeaders.*;


public class LitmusGraphQLClient {

    public final GraphQLClient client;

    public LitmusGraphQLClient(OkHttpClient okHttpClient, String host, String token) {
        client = GraphQLClient.createCustom(host, (url, headers, body) -> {
            Request request = new Request.Builder()
                .url(url)
                .addHeader(AUTHORIZATION, BEARER + " " + token)
                .post(RequestBody.create(body, MediaType.parse(APPLICATION_JSON))
            ).build();

            try (Response response = okHttpClient.newCall(request).execute()) {
                return new HttpResponse(HttpStatus.OK, response.body().string());
            }catch (IOException e) {
                // requestExecutor cannot throw a checked exception, so wrapped it with unchecked exception.
                throw new RuntimeException(e);
            }
        });
    }

    public <T> T query(String query, String operationName, TypeRef<T> type) throws LitmusApiException {
        GraphQLResponse response = client.executeQuery(query);
        return handleResponse(response, operationName, type);
    }

    private <T> T handleResponse(GraphQLResponse response, String operationName, TypeRef<T> type) throws LitmusApiException{
        validateResponse(response);
        return response.extractValueAsObject(operationName, type);
    }

    private void validateResponse(GraphQLResponse response) throws LitmusApiException {
        if (response.hasErrors()){
            throw new LitmusApiException(response.getErrors().get(0).getMessage());
        }
    }
}
