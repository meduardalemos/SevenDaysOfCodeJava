import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator {
    private final PrintWriter printWriter;

    public HTMLGenerator(PrintWriter printWriter) {
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
                    <!--<link rel="" href="">-->
                </head>
                    <body>
                        <h2 id="page-title">Top 250 Movies</h2>
                """
        );

        for (Movie movie:moviesList) {
            generateMovieCard(movie);
        }

        printWriter.println("""
                    </body>
                </html>
                """
        );

    }

    private void generateMovieCard(Movie movie){
        String html = """
                        <div class="movie-card"
                            <h5>#%d %s (%d)</h5>
                            <img src="%s" class="movie-image" alt="%s poster">
                            <h5>%.1f</h5>
                        </div>
                """.formatted(movie.getRanking(), movie.getTitle(), movie.getReleaseYear(),
                movie.getPosterPath(),
                movie.getTitle(), movie.getVoteAverage());
        printWriter.write(html);
    }
}
