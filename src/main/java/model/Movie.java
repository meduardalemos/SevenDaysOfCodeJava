package model;

import abstractions.Content;

public class Movie extends Content {

    public Movie(String title, String urlImage, Integer year, Double rating) {
        super(title, urlImage, year, rating);
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getUrlImage() {
        return "https://image.tmdb.org/t/p/w154" + urlImage;
    }

    @Override
    public Integer getYear() {
        return null;
    }

    @Override
    public Double getRating() {
        return null;
    }
}

