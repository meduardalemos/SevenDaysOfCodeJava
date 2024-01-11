package abstractions;

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

    public abstract String getTitle();

    public abstract String getUrlImage();

    public abstract Integer getYear();

    public abstract Double getRating();

    @Override
    public String toString() {
        return title + " (" + year + ")";
    }
}
