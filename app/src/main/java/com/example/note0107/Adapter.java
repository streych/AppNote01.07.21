package com.example.note0107;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private final ArrayList<Note> notes = new ArrayList<>();

    public void setData(List<Note> toSet) {
        notes.clear();
        notes.addAll(toSet);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView title;
        private final TextView date;

        public MyViewHolder(final View view){
            super(view);
            title = view.findViewById(R.id.title);
            date = view.findViewById(R.id.date);
        }
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_note, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
       Note note = notes.get(position);
       holder.title.setText(note.getTitle());
       holder.date.setText(note.getDate());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
