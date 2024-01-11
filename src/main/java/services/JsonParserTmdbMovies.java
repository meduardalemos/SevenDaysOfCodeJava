package services;

import abstractions.Content;
import abstractions.JsonParser;
import model.Movie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

public class JsonParserTmdbMovies implements JsonParser {

    private final String responseBody;
    private final int desiredResults;

    public JsonParserTmdbMovies(String responseBody, int desiredResults) {
        this.responseBody = responseBody;
        this.desiredResults = desiredResults;
    }

    public void parseJsonToListOfContents(List<Content> contentList) {
        try {
            // Converte a string responseBody em um JsonArray de JsonObjects
            JSONArray resultsArray = parseResponseBodyIntoJsonArray(responseBody);

            // Converte o JsonArray em movies e adiciona a lista
            parseJsonArrayMoviesIntoContentList(resultsArray, contentList);
        } catch (JSONException ex) {
            System.err.println("Erro ao tentar converter o JSON em filmes: "
                    + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private JSONArray parseResponseBodyIntoJsonArray(String responseBody) {
        JSONObject jsonResponseBody = new JSONObject(responseBody);
        return jsonResponseBody.getJSONArray("results");
    }

    private void parseJsonArrayMoviesIntoContentList(JSONArray resultsArray,
                                                     List<Content> contentList) {

        for (int i = 0; i < resultsArray.length(); i++) {

            if (contentList.size() >= desiredResults) {
                break;
            }

            JSONObject movieJson = resultsArray.getJSONObject(i);

            String title = movieJson.getString("title");
            String urlImage = movieJson.getString("poster_path");
            Integer year = Integer.parseInt(movieJson.getString("release_date")
                    .substring(0, 4));
            Double rating = movieJson.getDouble("vote_average");

            Movie currentMovie = new Movie(title, urlImage, year, rating);

            contentList.add(currentMovie);
        }
    }
}
