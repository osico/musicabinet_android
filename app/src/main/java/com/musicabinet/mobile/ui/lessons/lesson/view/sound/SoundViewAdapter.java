package com.musicabinet.mobile.ui.lessons.lesson.view.sound;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.musicabinet.mobile.R;
import com.musicabinet.mobile.model.lesson.remote.Accompaniment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirchhoff-
 */

public class SoundViewAdapter extends ArrayAdapter<Accompaniment> {

    // declaring our ArrayList of items
    private ArrayList<Accompaniment> accompaniments;

    public SoundViewAdapter(@NonNull Context context, int resource, @NonNull List<Accompaniment> objects) {
        super(context, resource, objects);
        this.accompaniments = new ArrayList<>(objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_sound_spinner, null);
        }

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText(getItem(position).getName());

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_sound_spinner, null);
        }

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        tvTitle.setText(getItem(position).getName());

        return view;
    }
}
