package com.example.note0107;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NotesFirebaseRepository implements NotesRepository{
    public static final NotesRepository INSTANCE = new NotesFirebaseRepository();
    private final FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private final static String NOTES = "notes";
    private final static String TITLE = "title";
    private final static String DATE = "date";
    private final static String DESCRIPTION = "description";


    @Override
    public void getNotes(CallBack<List<Note>> callback) {
        firebaseFirestore.collection(NOTES)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    ArrayList<Note> result = new ArrayList<>();
                    for (QueryDocumentSnapshot queryDocumentSnapshot: task.getResult()){
                        String title = (String) queryDocumentSnapshot.get(TITLE);
                        String date = (String) queryDocumentSnapshot.get(DATE);
                        String description = (String) queryDocumentSnapshot.get(DESCRIPTION);
                        result.add(new Note(queryDocumentSnapshot.getId(), title, date, description));
                    }

                    callback.onSucess(result);
                }else {
                    task.getException();
                }

            }
        });
    }

    @Override
    public void update(Note note, String title, String data, String desc) {

    }

    @Override
    public void remove(Note note, CallBack<Object> callback) {
        firebaseFirestore.collection(NOTES)
                .document(note.getId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            callback.onSucess(note);
                        }
                    }
                });
    }

    @Override
    public void add(String title, String date, String description, CallBack<Note> callback) {
        HashMap<String, Object> data = new HashMap<>();
        //Date date1 = new Date();
        data.put(TITLE, title);
        data.put(DATE, date);
        data.put(DESCRIPTION, description);

        firebaseFirestore.collection(NOTES)
                .add(data)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()){
                            Note note = new Note(task.getResult().getId(), title, date, description);
                            callback.onSucess(note);
                        }
                    }
                });
    }
}
