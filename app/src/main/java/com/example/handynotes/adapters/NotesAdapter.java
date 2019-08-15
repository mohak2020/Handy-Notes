package com.example.handynotes.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.handynotes.R;
import com.example.handynotes.model.Note;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    ArrayList<Note> mNote = new ArrayList<>();

    public NotesAdapter(ArrayList<Note> note) {
        this.mNote = note;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.titleView.setText(mNote.get(i).getTitle());
        viewHolder.timestampView.setText(mNote.get(i).getTimeStamp());
    }

    @Override
    public int getItemCount() {
        return mNote.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleView, timestampView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.note_title);
            timestampView = itemView.findViewById(R.id.note_timestamp);
        }
    }
}
