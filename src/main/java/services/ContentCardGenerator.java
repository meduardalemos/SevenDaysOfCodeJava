package services;

import abstractions.Content;

public class ContentCardGenerator {

    public String generateContentCard(Content content, int ranking){

        // Tratamento do tamanho do título para adequação ao html
        String longTitle = content.getTitle();
        String shortTitle;
        if (longTitle.length() > 40) {
            shortTitle = longTitle.substring(0, 20) + "...";
        } else {
            shortTitle = longTitle;
        }

        // Gera o html do contentcard
        return """
                                    <div class="content-card">
                                        <div class="content-title-container">
                                            <h4 class="content-position"># %d</h4>
                                            <div class="content-title">
                                                <h5 class="content-title-text">%s (%d)</h5>
                                            </div>
                                        </div>
                                        <img src="%s" class="content-image" alt="%s poster">
                                        <div class="content-rate-container">
                                            <h5 class="rate-text">Rate: %.1f</h5>
                                        </div>
                                    </div>
                """.formatted(ranking, shortTitle, content.getYear(),
                content.getUrlImage(),
                content.getTitle(), content.getRating());
    }

}

