package util;

import model.Movie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class TmdbApiCaller {

    private final String apiKey;

    private final HttpClient httpClient;

    // Construtor
    public TmdbApiCaller(String apiKey, HttpClient httpClient) {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalArgumentException("A ApiKey não pode ser nula!");
        }
        this.apiKey = apiKey;
        this.httpClient = httpClient;
    }

    // Método que retorna a lista com os top 250 movies
    public List<Movie> getTop250Movies () {

        // Variáveis para controle da quantidade de resultados obtidos
        int desiredResults = 250;
        int currentPage = 1;
        int moviePosition = 1;

        // Cria a lista para armazenar os top 250 filmes
        List<Movie> top250Movies = new ArrayList<>();

        while (top250Movies.size() < desiredResults) {

            String responseBody = callTmdbApi(httpClient, currentPage);
            JSONObject json = new JSONObject(responseBody);

            try {
                JSONArray resultsArray = json.getJSONArray("results");
                processResults(resultsArray, desiredResults, moviePosition, top250Movies);
            } catch (JSONException ex) {
                System.err.println("Erro ao tentar converter o JSON em filmes: "
                + ex.getMessage());
                ex.printStackTrace();
            }

            currentPage++;

        }

        return top250Movies;
    }

    private String callTmdbApi (HttpClient httpClient, int currentPage) {

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

    private void processResults (JSONArray resultsArray, int desiredResults, int moviePosition,
                                 List<Movie> top250Movies) {
        for (int i = 0; i < resultsArray.length(); i++) {

            if (top250Movies.size() >= desiredResults) {
                break;
            }

            JSONObject movie = resultsArray.getJSONObject(i);

            String title = movie.getString("title");
            String posterPath = movie.getString("poster_path");
            Integer releaseYear = Integer.parseInt(movie.getString("release_date")
                    .substring(0, 4));
            Double voteAverage = movie.getDouble("vote_average");
            int ranking = moviePosition;
            moviePosition++;

            Movie currentMovie = new Movie(title, posterPath, releaseYear, voteAverage,
                    ranking);
            top250Movies.add(currentMovie);
        }
    }
}
