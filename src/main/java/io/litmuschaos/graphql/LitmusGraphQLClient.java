package io.litmuschaos.graphql;
import com.netflix.graphql.dgs.client.GraphQLClient;
import com.netflix.graphql.dgs.client.GraphQLResponse;
import com.netflix.graphql.dgs.client.HttpResponse;
import java.io.IOException;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LitmusGraphQLClient {

    public final GraphQLClient client;

    public LitmusGraphQLClient(OkHttpClient okHttpClient, String host, String token) {
        client = GraphQLClient.createCustom(host, ((url, headers, body) -> {
            Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", token)
                .post(RequestBody.create(body, MediaType.parse("application/json"))
            ).build();

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

    public GraphQLResponse query(String query, Map<String,Object> variables){
        return client.executeQuery(query, variables);
    }

    public GraphQLResponse query(String query){
        return client.executeQuery(query);
    }
}
