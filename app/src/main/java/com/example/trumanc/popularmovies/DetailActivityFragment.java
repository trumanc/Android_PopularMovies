package com.example.trumanc.popularmovies;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    private void setTextViewById(View root, int id, String value) {
        TextView textView = (TextView) root.findViewById(id);
        textView.setText(value);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail, container, false);

        Intent intent = getActivity().getIntent();
        Movie movie = (Movie) intent.getSerializableExtra(Movie.INTENT_EXTRA_NAME);

        ImageView posterView = (ImageView) root.findViewById(R.id.detail_poster);
        Picasso.with(getContext()).load(movie.getPosterUrl()).into(posterView);

        setTextViewById(root, R.id.detail_overview, movie.getOverview());
        setTextViewById(root, R.id.detail_release_date, movie.getReleaseDate());
        setTextViewById(root, R.id.detail_rating, "" + movie.getRating() + "/10");
        return root;
    }
}
