package intra.service.crawler;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Http {
    private static final HttpClient client = HttpClient.newHttpClient();
    public static String request(String uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
        return send.body();
    }
}
