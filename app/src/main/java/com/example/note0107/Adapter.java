package com.example.note0107;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private final ArrayList<Note> notes = new ArrayList<>();
    private OnNoteClickedListener listener;
    public Fragment fragment;
    private OnNoteLongClickedListener longClickedListener;

    public Adapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setData(List<Note> toSet) {
        notes.clear();
        notes.addAll(toSet);
    }

    public int add(Note addedNote){
        notes.add(addedNote);
        return notes.size()-1;
    }

    public void delteNote(Note longClickedNote){
        notes.remove(longClickedNote);
    }

    public interface OnNoteClickedListener{
        void onOnNoteClickedListener(@NonNull Note note);
    }

    public OnNoteLongClickedListener getLongClickedListener() {
        return longClickedListener;
    }

    public void setLongClickedListener(OnNoteLongClickedListener longClickedListener) {
        this.longClickedListener = longClickedListener;
    }

    public interface OnNoteLongClickedListener{
        void OnNoteLongClickedListener(@NonNull Note note, int index);
    }

    public OnNoteClickedListener getListener() {
        return listener;
    }

    public void setListener(OnNoteClickedListener listener) {
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView title;
        private final TextView date;

        public MyViewHolder(@NonNull final View view){
            super(view);
            fragment.registerForContextMenu(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getListener() != null){
                        getListener().onOnNoteClickedListener(notes.get(getAdapterPosition()));
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemView.showContextMenu();
                    if (getLongClickedListener() != null){
                        int index = getAdapterPosition();
                        getLongClickedListener().OnNoteLongClickedListener(notes.get(index), index);
                    }
                    return true;
                }
            });
            title = view.findViewById(R.id.title);
            date = view.findViewById(R.id.date);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
