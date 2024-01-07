package util;

import model.Movie;

import java.io.PrintWriter;
import java.util.List;

public class HtmlGenerator {
    private final PrintWriter printWriter;

    public HtmlGenerator(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void generateHTML(List<Movie> moviesList){
        printWriter.println("""
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Top 250 Movies</title>
                        <link rel="stylesheet" href="reset.css">
                        <link rel="stylesheet" href="style.css">
                </head>
                    <body>
                        <div id="title-container">
                                    <h2 id="title">Top 250 Movies</h2>
                                </div>
                                <div id="movies-grid">
                """
        );

        for (Movie movie:moviesList) {
            generateMovieCard(movie);
        }

        printWriter.println("""
                               </div>
                               <footer>Developed by Maria Eduarda Lemos for #7DaysOfCode Alura </footer>
                           </body>
                       </html>
                """
        );

    }

    private void generateMovieCard(Movie movie){
        String html = """
                                    <div class="movie-card">
                                        <div class="movie-title-container">
                                            <h4 class="movie-position"># %d</h4>
                                            <div class="movie-title">
                                                <h5 class="movie-title-text">%s (%d)</h5>
                                            </div>
                                        </div>
                                        <img src="%s" class="movie-image" alt="%s poster">
                                        <div class="movie-rate-container">
                                            <h5 class="rate-text">Rate: %.1f</h5>
                                        </div>
                                    </div>
                """.formatted(movie.getRanking(), movie.getTitle(), movie.getReleaseYear(),
                movie.getPosterPath(),
                movie.getTitle(), movie.getVoteAverage());
        printWriter.write(html);
    }
}
