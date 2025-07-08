package com.example.rag.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collectors;

public class OllamaClient {

    public static String callOllama(String prompt) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        String safePrompt = prompt.replace("\"", "\\\"").replace("\n", "\\n");
        String body = String.format("""
                {
                    "model": "mistral",
                    "prompt": "%s"
                }
                """, safePrompt);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:11434/api/generate"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        HttpResponse<java.util.stream.Stream<String>> response = client.send(
                request, HttpResponse.BodyHandlers.ofLines());

        ObjectMapper mapper = new ObjectMapper();
        String result = response.body()
                .map(line -> {
                    try {
                        JsonNode node = mapper.readTree(line);
                        return node.has("response") ? node.get("response").asText() : "";
                    } catch (Exception e) {
                        return "";
                    }
                })
                .collect(Collectors.joining());

        return result;
    }
}