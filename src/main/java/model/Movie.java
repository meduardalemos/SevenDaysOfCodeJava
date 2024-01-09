package model;

public class Movie extends Content {
    private int ranking;

    public Movie(String title, String urlImage, Integer year, Double rating) {
        super(title, urlImage, year, rating);
    }

    @Override
    public String getUrlImage() {
        return "https://image.tmdb.org/t/p/w154" + urlImage;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }


    @Override
    public String toString() {
        return "#" + ranking + " " + title + " (" + year + ") ";
    }
}
