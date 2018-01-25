package com.musicabinet.mobile.ui.lessons.lesson.note.adapter.spinner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.musicabinet.mobile.R;
import com.musicabinet.mobile.model.lesson.machine.note.NoteItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirchhoff-
 */

public class NoteSpinnerAdapter extends BaseAdapter {

    private List<NoteItem> list;

    NoteSpinnerAdapter(List<NoteItem> list) {
        this.list = new ArrayList<>(list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public NoteItem getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemView = LayoutInflater.from(view.getContext()).inflate(R.layout.item_note_spinner, null);
        TextView tvTitle = itemView.findViewById(R.id.tvTitle);
        tvTitle.setText(getItem(position).getName());
        return itemView;
    }
}
