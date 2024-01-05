public class Movie {
    private final String title;
    private final String posterPath;
    private final Integer releaseYear;
    private final Double voteAverage;
    private final int ranking;

    public Movie(String title, String posterPath, Integer releaseYear, Double voteAverage,
                 int ranking) {
        this.title = title;
        this.posterPath = posterPath;
        this.releaseYear = releaseYear;
        this.voteAverage = voteAverage;
        this.ranking = ranking;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w154" + posterPath;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public int getRanking() {
        return ranking;
    }

    @Override
    public String toString() {
        return "#" + ranking + " " + title + " (" + releaseYear + ") ";
    }
}
