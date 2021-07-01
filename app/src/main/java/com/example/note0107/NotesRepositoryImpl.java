package com.example.note0107;

import java.util.ArrayList;
import java.util.List;

public class NotesRepositoryImpl implements NotesRepository {

    private final ArrayList<Note> notes = new ArrayList<>();

    public NotesRepositoryImpl() {
        notes.add(new Note("0", "Заметка 1", "01-01-2021", "Заметка 1 Заметка 1"));
        notes.add(new Note("1", "Заметка 2", "02-01-2021", "Заметка 2 Заметка 1"));
        notes.add(new Note("2", "Заметка 3", "03-01-2021", "Заметка 3 Заметка 1"));
        notes.add(new Note("3", "Заметка 4", "04-01-2021", "Заметка 4"));
        notes.add(new Note("4", "Заметка 5", "05-01-2021", "Заметка 5"));
        notes.add(new Note("5", "Заметка 6", "06-01-2021", "Заметка 6"));
        notes.add(new Note("6", "Заметка 7", "07-01-2021", "Заметка 7"));
        notes.add(new Note("7", "Заметка 8", "08-01-2021", "Заметка 8"));
        notes.add(new Note("8", "Заметка 9", "09-01-2021", "Заметка 9"));
        notes.add(new Note("9", "Заметка 10", "10-01-2021", "Заметка 10"));
    }



    public static final NotesRepository INSTANCE  = new NotesRepositoryImpl();




    @Override
    public void getNotes(CallBack<List<Note>> callback) {
        callback.onSucess(notes);
    }

}
