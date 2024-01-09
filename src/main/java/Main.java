import model.Movie;
import util.HtmlGenerator;
import util.TmdbApiCallerTopRatedMovies;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpClient;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Definição da ApiKey
        String apiKey = System.getenv("TMDB_API_KEY");

        // Requisição a API retorna lista do top 250 filmes
        HttpClient httpClient = HttpClient.newHttpClient();
        TmdbApiCallerTopRatedMovies tmdbApiCallerTopRatedMovies = new TmdbApiCallerTopRatedMovies(apiKey, httpClient);
        List<Movie> top250movies = tmdbApiCallerTopRatedMovies.getTop250Movies();

        // Gera Htlm com os top 250 filmes
        try(PrintWriter writer = new PrintWriter("src/main/frontend/index.html")){
            HtmlGenerator htmlGenerator = new HtmlGenerator(writer);
            htmlGenerator.generateHTML(top250movies);
            System.out.println("Arquivo html salvo com sucesso!");
        } catch (IOException ex) {
            System.err.println("Erro ao salvar o arquivo html: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}
