package com.example.trumanc.popularmovies;

import java.io.Serializable;

/**
 * This class encapsulates all the data for a single movie. It allows us to package all the info
 * into a single serializable object when we want to communicate that information across intents.
 *
 * Created by truman on 7/1/16.
 */
public class Movie implements Serializable {
    /* Member variables used when displaying a movie */

    public static final String INTENT_EXTRA_NAME = "movie_extra";

    private String title;
    private String posterPath;
    private String releaseDate;
    private double rating;
    private String overview;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    static final String POSTER_PATH_BASE_URL = "http://image.tmdb.org/t/p/";
    static final String POSTER_PATH_SIZE =
    // There are several sizes to choose from in the API. Uncomment the one size you want to use.
    // TODO: Make this a setting, or an enum, or simply eliminate the option if this size works.
            //"w92";
            //"w154";
            //"w185";
            "w342";
            //"w500";
            //"w780";

    public String getPosterUrl() {
        return POSTER_PATH_BASE_URL + POSTER_PATH_SIZE + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }



}
