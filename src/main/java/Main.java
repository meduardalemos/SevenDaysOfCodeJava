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
        String responseBody = null;

        try {
            URI uri = new URI("https://api.themoviedb.org/3/movie/top_rated?api_key=" + apiKey);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            responseBody = response.body();
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao tentar acessar a API: " + e.getMessage());
            e.printStackTrace();
        }

        // Verifica se a resposta não está vazia antes de tentar processar o Json
        if (responseBody != null && !responseBody.isEmpty()) {
            // Converte a resposta em um objeto JSON
            JSONObject json = new JSONObject(responseBody);

            // Obtem a matriz "results" do JSON
            JSONArray resultsArray = json.getJSONArray("results");

            // Cria listas para armazenar títulos e caminho dos posters
            List<String> titles = new ArrayList<>();
            List<String> posterPaths = new ArrayList<>();
            List<Integer> releaseYears = new ArrayList<>();
            List<Double> voteAverages = new ArrayList<>();

            // Itera sobre os 250 primeiros 250 filmes ou até o final da matriz
            int limit = Math.min(resultsArray.length(), 250);
            for (int i = 0; i < limit; i++) {
                // Obtém o objeto JSON do filme atual
                JSONObject movie = resultsArray.getJSONObject(i);

                // Obtém o título e caminho do poster do filme atual
                String title = movie.getString("title");
                String posterPath = movie.getString("poster_path");
                Integer releaseYear = Integer.parseInt(movie.getString("release_date").substring(0,
                        4));
                Double voteAverage = movie.getDouble("vote_average");

                // Adiciona o titulo e caminho do poster às listas respectivas
                titles.add(title);
                posterPaths.add(posterPath);
                releaseYears.add(releaseYear);
                voteAverages.add(voteAverage);
            }

            System.out.println("Lista de titulos: ");
            titles.forEach(System.out::println);

            System.out.println("\nLista de caminhos dos posteres: ");
            posterPaths.forEach(System.out::println);

            System.out.println("\nLista de anos de lancamento: ");
            releaseYears.forEach(System.out::println);

            System.out.println("\nLista de medias de notas: ");
            voteAverages.forEach(System.out::println);
        } else {
            System.out.println("A resposta da API está vazia.");
        }
    }
}
