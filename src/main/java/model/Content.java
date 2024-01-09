package model;

public abstract class Content {

    protected final String title;
    protected final String urlImage;
    protected final Integer year;
    protected final Double rating;

    public Content(String title, String urlImage, Integer year, Double rating) {
        this.title = title;
        this.urlImage = urlImage;
        this.year = year;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public Integer getYear() {
        return year;
    }

    public Double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return title + " (" + year + ")";
    }
}
