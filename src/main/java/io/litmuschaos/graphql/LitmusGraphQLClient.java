package io.litmuschaos.graphql;
import com.netflix.graphql.dgs.client.GraphQLClient;
import com.netflix.graphql.dgs.client.HttpResponse;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LitmusGraphQLClient {

    public final GraphQLClient graphQLClient;

    public LitmusGraphQLClient(OkHttpClient okHttpClient, String host) {
        graphQLClient = GraphQLClient.createCustom(host, ((url, headers, body) -> {
            Request.Builder requestBuilder = new Request.Builder().url(url).post(RequestBody.create(body, MediaType.parse("application/json"))
            );
            headers.forEach((key, values) -> values.forEach(value -> requestBuilder.addHeader(key, value)));

            Request request = requestBuilder.build();

            try (Response response = okHttpClient.newCall(request).execute()) {
                if(!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                return new HttpResponse(response.code(), response.body().string());
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
    }
}
