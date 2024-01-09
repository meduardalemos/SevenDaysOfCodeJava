package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;

public class JSONParserMovies {

    private String responseBody;
    private int desiredResults;

    public JSONParserMovies(String responseBody, int desiredResults) {
        this.responseBody = responseBody;
        this.desiredResults = desiredResults;
    }

    public void parseJsonToListOfContents(List<Movie> movieList) {
        try {
            // Converte a string responseBody em um JsonArray de JsonObjects
            JSONArray resultsArray = parseResponseBodeyIntoJsonArray(responseBody, "results");

            // Converte o JsonArray em movies e adiciona a lista
            parseJsonArrayMoviesIntoMoviesList(resultsArray, movieList);
        } catch (JSONException ex) {
            System.err.println("Erro ao tentar converter o JSON em filmes: "
                    + ex.getMessage());
            ex.printStackTrace();
        }

    }

    private JSONArray parseResponseBodeyIntoJsonArray (String responseBody, String key) {
        JSONObject jsonResponseBody = new JSONObject(responseBody);
        return jsonResponseBody.getJSONArray(key);
    }

    private void parseJsonArrayMoviesIntoMoviesList (JSONArray resultsArray, List<Movie> movieList) {

        for (int i = 0; i < resultsArray.length(); i++) {

            if (movieList.size() >= desiredResults) {
                break;
            }

            JSONObject movieJson = resultsArray.getJSONObject(i);

            String title = movieJson.getString("title");
            String urlImage = movieJson.getString("poster_path");
            Integer year = Integer.parseInt(movieJson.getString("release_date")
                    .substring(0, 4));
            Double rating = movieJson.getDouble("vote_average");

            Movie currentMovie = new Movie(title, urlImage, year, rating);
            currentMovie.setRanking(movieList.size() + 1);

            movieList.add(currentMovie);
        }
    }
}
