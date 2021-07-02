package com.example.note0107.menu_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.note0107.Adapter;
import com.example.note0107.CallBack;
import com.example.note0107.Note;
import com.example.note0107.NotesFirebaseRepository;
import com.example.note0107.NotesRepository;
import com.example.note0107.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NotesFragmentAdd extends Fragment {
    private final NotesRepository repository = NotesFirebaseRepository.INSTANCE;
    private final Adapter notesAdapter = new Adapter(this);

    public static NotesFragmentAdd newInstance() {
        NotesFragmentAdd fragment = new NotesFragmentAdd();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText editTextTitle = getActivity().findViewById(R.id.input_title);
        EditText editTextDescription = getActivity().findViewById(R.id.input_description);
        Button buttonAdd = getActivity().findViewById(R.id.add_note);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String date = format.format(Calendar.getInstance().getTime());

        buttonAdd.setOnClickListener(v -> {
            repository.add(String.valueOf(editTextTitle.getText()), date, String.valueOf(editTextDescription.getText()), new CallBack<Note>() {
                @Override
                public void onSucess(Note result) {
                    int index = notesAdapter.add(result);
                    notesAdapter.notifyItemInserted(index);
                }
            });
            notesAdapter.notifyDataSetChanged();
        });
    }
}