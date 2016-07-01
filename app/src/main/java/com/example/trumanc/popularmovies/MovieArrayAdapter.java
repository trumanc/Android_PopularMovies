package com.example.trumanc.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by truman on 7/1/16.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie> {
    public MovieArrayAdapter(Context context, List<Movie> movies) {
        /* Second param is used to identify the single text view in the normal case.
         * Since we aren't using a single textview, we set it to 0
         */
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        if (convertView == null) {
            // No old view to recycle, so inflate a new one
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.browse_entry, parent, false);
        }

        TextView textView = (TextView)convertView;
        textView.setText(movie.getTitle());

        return convertView;
    }
}
