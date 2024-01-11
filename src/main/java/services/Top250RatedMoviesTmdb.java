package services;

import abstractions.ApiClient;
import abstractions.Content;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Top250RatedMoviesTmdb implements ApiClient {
    private final String apiKey;
    private final HttpClient httpClient;


    public Top250RatedMoviesTmdb(String apiKey, HttpClient httpClient) {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalArgumentException("A ApiKey não pode ser nula!");
        }
        this.apiKey = apiKey;
        this.httpClient = httpClient;
    }

    // Método que retorna a lista com os top 250 movies ordenados
    public List<Content> getContentList() {

        // Variáveis para controle da quantidade de resultados obtidos
        int desiredResults = 250;
        int currentPage = 1;

        // Cria a lista para armazenar os top 250 filmes
        List<Content> top250RatedMovies = new ArrayList<>();

        while (top250RatedMovies.size() < desiredResults) {

            // Faz chamada a API do TMDB top rated movies e retorna a resposta da requisição
            String responseBody = callTmdbApi(httpClient, currentPage);

            // Processa a resposta JSON em movie e adiciona a lista
            JsonParserTmdbMovies jsonParserTmdbMovies = new JsonParserTmdbMovies(responseBody, desiredResults);
            jsonParserTmdbMovies.parseJsonToListOfContents(top250RatedMovies);

            currentPage++;

            }

        return top250RatedMovies;
    }

    private String callTmdbApi(HttpClient httpClient, int currentPage) {

        try {
            URI uri = new URI("https://api.themoviedb.org/3/movie/top_rated?api_key=" +
                    apiKey + "&page=" + currentPage);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

            return response.body();
        } catch (URISyntaxException | IOException | InterruptedException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
