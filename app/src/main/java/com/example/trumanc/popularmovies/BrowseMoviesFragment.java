package com.example.trumanc.popularmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.trumanc.popularmovies.lib.NetworkTools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class BrowseMoviesFragment extends Fragment {
    /******************* MEMBER VARIABLES *******************/
    private MovieArrayAdapter mMovieListAdapter;


    /****************** API REQUEST CONSTANTS ***************/
    // Request constants
    final static String TMDB_API_BASE = "https://api.themoviedb.org/3/movie/";
    final static String TMDB_API_KEY_PARAM = "api_key";

    // Response constants
    final static String TMDB_RESULTS_ARRAY_NAME = "results";
    final static String TMDB_RESULTS_TITLE_NAME = "title";
    final static String TMDB_RESULTS_POSTER_PATH_NAME = "poster_path";
    final static String TMDB_RESULTS_OVERVIEW_NAME = "overview";
    final static String TMDB_RESULTS_RELEASE_DATE_NAME = "release_date";
    final static String TMDB_RESULTS_RATING_NAME = "vote_average";



    final static String LOG_TAG = BrowseMoviesFragment.class.getSimpleName();


    public BrowseMoviesFragment() {
    }

    ArrayList<Movie> getMovies(String sortBy) {

        Uri apiReq = new Uri.Builder().encodedPath(TMDB_API_BASE)
                .appendPath(sortBy)
                .appendQueryParameter(TMDB_API_KEY_PARAM, BuildConfig.TMDB_API_KEY)
                .build();



        String raw_data = NetworkTools.downloadUri(apiReq);
        Log.d(LOG_TAG, "downloadUri result: " + raw_data);

        if (raw_data == null) return null;

        try {
            JSONObject root = new JSONObject(raw_data);
            JSONArray results = root.getJSONArray(TMDB_RESULTS_ARRAY_NAME);

            ArrayList<Movie> list = new ArrayList<Movie>();

            int len = results.length();
            for (int index = 0; index < len; index++) {
                list.add(extractEntry(results.getJSONObject(index)));
            }

            Log.d(LOG_TAG, "Movie list: " + list.toString());
            return list;
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse JSON response.", e);
            return null;
        }
    }

    private static Movie extractEntry(JSONObject entry) {
        Movie movie = new Movie();
        movie.setTitle(entry.optString(TMDB_RESULTS_TITLE_NAME, "<NOT FOUND>"));
        movie.setPosterPath(entry.optString(TMDB_RESULTS_POSTER_PATH_NAME, "<NOT FOUND>"));
        movie.setOverview(entry.optString(TMDB_RESULTS_OVERVIEW_NAME, "<NOT FOUND>"));
        movie.setReleaseDate(entry.optString(TMDB_RESULTS_RELEASE_DATE_NAME, "<NOT FOUND>"));
        movie.setRating(entry.optDouble(TMDB_RESULTS_RATING_NAME, -1.0));
        return movie;
    }

    @Override
    public void onStart() {
        super.onStart();

        new DownloadMovieInfoTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_browse, container, false);

        GridView grid = (GridView) rootView.findViewById(R.id.movie_grid);
        mMovieListAdapter = new MovieArrayAdapter(getContext(), new ArrayList<Movie>());
        grid.setAdapter(mMovieListAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent detailIntent = new Intent(getContext(), DetailActivity.class);
                detailIntent.putExtra(Movie.INTENT_EXTRA_NAME, mMovieListAdapter.getItem(position));

                startActivity(detailIntent);
            }
        });
        return rootView;
    }

    class DownloadMovieInfoTask extends AsyncTask<Void, Void, ArrayList<Movie>> {

        @Override
        protected ArrayList<Movie> doInBackground(Void... voids) {
            // TODO: Make this a setting with two options: popular and top_rated
            String sortBy = "popular";

            return getMovies(sortBy);
        }


        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {

            Log.d("ON_POST_EXECUTE", movies.toString());
            mMovieListAdapter.clear();
            mMovieListAdapter.addAll(movies);

            super.onPostExecute(movies);
        }
    }
}
