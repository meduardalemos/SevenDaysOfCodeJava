import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {

        String apiKey = System.getenv("TMDB_API_KEY");

        // Verifica se a chave API está configurada como varivável de ambiente chamada TMDB_API_KEY
        if (apiKey == null) {
            System.out.println("A variável de ambiente TMDB_API_KEY não está configurada no sistema.");
            return;
        }

        // Requisição a API
        HttpClient client = HttpClient.newHttpClient();
        // String responseBody = null;

        // Variáveis para controle da quantidade de resultados obtidos
        int desiredResults = 250;
        int currentPage = 1;
        int moviePosition = 1;

        // Cria lista para armazenar os filmes
        List<Movie> top250Movies = new ArrayList<>();

        do {
            try {
                URI uri = new URI("https://api.themoviedb.org/3/movie/top_rated?api_key=" +
                        apiKey + "&page=" + currentPage);

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(uri)
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request,
                        HttpResponse.BodyHandlers.ofString());

                JSONObject json = new JSONObject(response.body());
                JSONArray resultsArray = json.getJSONArray("results");

                for (int i = 0; i < resultsArray.length(); i++) {

                    if(top250Movies.size() >= desiredResults) {
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

                currentPage++;

            } catch (Exception e) {
                System.err.println("Ocorreu um erro ao tentar acessar a API: " + e.getMessage());
                e.printStackTrace();
            }
        } while (top250Movies.size() < desiredResults);

        System.out.println(top250Movies);
    }
}
