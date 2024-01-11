import abstractions.Content;
import services.HtmlGeneratorTop250RatedMovies;
import services.Top250RatedMoviesTmdb;

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
        Top250RatedMoviesTmdb top250RatedMoviesTmdb = new Top250RatedMoviesTmdb(apiKey, httpClient);
        List<Content> top250movies = top250RatedMoviesTmdb.getContentList();

        // Gera Htlm com os top 250 filmes
        try(PrintWriter writer = new PrintWriter("src/main/frontend/index.html")){
            HtmlGeneratorTop250RatedMovies htmlGeneratorTop250RatedMovies = new HtmlGeneratorTop250RatedMovies(writer);
            htmlGeneratorTop250RatedMovies.generateHtml(top250movies);
            System.out.println("Arquivo html salvo com sucesso!");
        } catch (IOException ex) {
            System.err.println("Erro ao salvar o arquivo html: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}
