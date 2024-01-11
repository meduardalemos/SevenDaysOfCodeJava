package services;

import abstractions.Content;
import abstractions.HtmlGenerator;

import java.io.PrintWriter;
import java.util.List;

public class HtmlGeneratorTop250RatedMovies implements HtmlGenerator {
    private final PrintWriter printWriter;

    public HtmlGeneratorTop250RatedMovies(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public void generateHtml(List<Content> contentList){
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

        int ranking = 1;
        ContentCardGenerator contentCardGenerator = new ContentCardGenerator();
        for (Content content:contentList) {
            String htmlContentCard = contentCardGenerator.generateContentCard(content, ranking);
            printWriter.write(htmlContentCard);
            ranking++;
        }

        printWriter.println("""
                               </div>
                               <footer>Developed by Maria Eduarda Lemos for #7DaysOfCode Alura </footer>
                           </body>
                       </html>
                """
        );
    }
}
