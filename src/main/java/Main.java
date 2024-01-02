import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    public static void main(String[] args) {

        String apiKey = System.getenv("TMDB_API_KEY");

        if (apiKey == null) {
            System.out.println("A variável de ambiente TMDB_API_KEY não está configurada no sistema.");
            return;
        }


        try {
            URI uri = new URI("https://api.themoviedb.org/3/movie/top_rated?api_key=" + apiKey);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println(response.body());
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao tentar acessar a API: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
