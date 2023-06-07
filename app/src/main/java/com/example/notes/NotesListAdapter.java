package com.example.notes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NotesListAdapter extends ArrayAdapter<Note> {

    private final Context context;
    private final int layoutResourceId;
    private final List<Note> data;

    public NotesListAdapter(Context context, int layoutResourceId, List<Note> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.tvNoteTitle = row.findViewById(R.id.tv_note_title);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Note note = data.get(position);
        holder.tvNoteTitle.setText(note.getTitle());

        return row;
    }

    static class ViewHolder {
        TextView tvNoteTitle;
    }
}