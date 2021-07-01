package com.example.note0107;

import java.util.List;

public interface NotesRepository {

    void getNotes(CallBack<List<Note>> callback);
}
