package com.example.note0107;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
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
        firebaseFirestore.collection(NOTES).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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
}
