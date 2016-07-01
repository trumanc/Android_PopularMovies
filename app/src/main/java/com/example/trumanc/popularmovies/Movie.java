package com.example.trumanc.popularmovies;

import java.io.Serializable;

/**
 * This class encapsulates all the data for a single movie. It allows us to package all the info
 * into a single serializable object when we want to communicate that information across intents.
 *
 * Created by truman on 7/1/16.
 */
public class Movie implements Serializable {

    private String title;
    private String posterPath;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }



}
