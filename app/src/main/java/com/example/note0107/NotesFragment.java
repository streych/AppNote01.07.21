package com.example.note0107;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment {
    public static final String TAG = "NotesFragment";
    private Adapter adapter;
    private NotesRepository repository = NotesRepositoryImpl.INSTANCE;


    public static NotesFragment newInstance() {
        return new NotesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new Adapter();
        repository.getNotes(new CallBack<List<Note>>() {
            @Override
            public void onSucess(List<Note> result) {
                adapter.setData(result);
                adapter.notifyDataSetChanged();
            }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView notesList = view.findViewById(R.id.notes_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        notesList.setLayoutManager(linearLayoutManager);
        notesList.setAdapter(adapter);
    }
}