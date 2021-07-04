package com.example.note0107;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class NotesFragment extends Fragment {
    public static final String TAG = "NotesFragment";
    private Adapter adapter;
    private final NotesRepository repository = NotesFirebaseRepository.INSTANCE;
    private int longClickedIndex;
    private Note longClickedNote;
    private Router router;

    public static NotesFragment newInstance() {
        return new NotesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new Adapter(this);
        router =new Router(getActivity().getSupportFragmentManager());
        repository.getNotes(new CallBack<List<Note>>() {
            @Override
            public void onSucess(List<Note> result) {
                adapter.setData(result);
                adapter.notifyDataSetChanged();
            }
        });

        adapter.setListener(new Adapter.OnNoteClickedListener() {
            @Override
            public void onOnNoteClickedListener(@NonNull Note note) {


                if (NotesFragment.this.requireActivity() instanceof  MainActivity) {
                    View view = LayoutInflater.from(requireContext()).inflate(R.layout.detail_note_dialog, null, false);
                    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                    String date = format.format(Calendar.getInstance().getTime());
                    EditText textDialTitle = view.findViewById(R.id.newTitlesD);
                    TextView textDialDesc = view.findViewById(R.id.newDescriptionsD);
                    textDialDesc.setText(note.getDescription());
                    textDialTitle.setText(note.getTitle());
                    AlertDialog.Builder builderDetail = new AlertDialog.Builder(requireContext())
                            .setTitle(R.string.title)
                            .setView(view)
                            .setPositiveButton(R.string.Ssave, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    repository.remove(note, new CallBack<Object>() {
                                        @Override
                                        public void onSucess(Object result) {
                                            adapter.delteNote(note);
                                        }
                                    });
                                    repository.add(textDialTitle.getText().toString(), date, textDialDesc.getText().toString(), new CallBack<Note>() {
                                        @Override
                                        public void onSucess(Note result) {
                                            int index = adapter.add(result);
                                            adapter.notifyItemRemoved(index);
                                        }
                                    });
                                    adapter.notifyDataSetChanged();
                                   router.showNotes();
                                }
                            });
                    builderDetail.show();
                    //MainActivity mainActivity = (MainActivity) NotesFragment.this.requireActivity();
                    //mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.container, DetailFragment.newInstance(note)).addToBackStack(null).commit();
                }
            }
        });

        adapter.setLongClickedListener(new Adapter.OnNoteLongClickedListener() {
            @Override
            public void OnNoteLongClickedListener(@NonNull Note note, int index) {
                longClickedIndex = index;
                longClickedNote = note;
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

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.menu_notes_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext())
                    .setTitle(R.string.titleDelete)
                    .setMessage(R.string.deleteMessage)
                    .setPositiveButton(R.string.btn_positive, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            repository.remove(longClickedNote, new CallBack<Object>() {
                                @Override
                                public void onSucess(Object result) {
                                    adapter.delteNote(longClickedNote);
                                    adapter.notifyItemRemoved(longClickedIndex);
                                }
                            });
                        }
                    })
                    .setNegativeButton(R.string.btn_negative, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

            builder.show();
        }
        return super.onContextItemSelected(item);
    }
}