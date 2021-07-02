package com.example.note0107;

import java.util.List;

import javax.security.auth.callback.Callback;

public interface NotesRepository {

    void getNotes(CallBack<List<Note>> callback);
    Note update(Note note, String title, String data, String desc);
    void remove(Note note, CallBack<Object> callback);
    void add(String title, String date, String description, CallBack<Note> callback);
}
