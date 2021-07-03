package com.example.note0107;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DetailFragment extends Fragment {
    private final NotesRepository repository = NotesFirebaseRepository.INSTANCE;
    private final Adapter notesAdapter = new Adapter(this);
    public static final String TAG = "DetailFragment";
    private static final String ARG_NOTE = "ARG_NOTE";

    public static DetailFragment newInstance(Note note) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);
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
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView title = view.findViewById(R.id.newTitles);
        TextView data = view.findViewById(R.id.newDates);
        TextView description = view.findViewById(R.id.newDescriptions);
        if (getArguments() != null) {
            Note note = getArguments().getParcelable(ARG_NOTE);
            title.setText(note.getTitle());
            title.setTextSize(50);
            data.setText(note.getDate());
            description.setText(note.getDescription());
            description.setTextSize(30);
            Button buttonSave = getActivity().findViewById(R.id.saveNote);

            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            String date = format.format(Calendar.getInstance().getTime());
            EditText titleUp = getActivity().findViewById(R.id.newTitles);
            EditText descriptionUp = getActivity().findViewById(R.id.newDescriptions);
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //continue
                    repository.remove(note, new CallBack<Object>() {
                        @Override
                        public void onSucess(Object result) {
                            notesAdapter.delteNote(note);

                        }
                    });
                    repository.add(titleUp.getText().toString(), date, descriptionUp.getText().toString(), new CallBack<Note>() {
                        @Override
                        public void onSucess(Note result) {
                            int index = notesAdapter.add(result);
                            notesAdapter.notifyItemRemoved(index);
                        }
                    });
                    notesAdapter.notifyDataSetChanged();
                }
            });

        }
    }
}