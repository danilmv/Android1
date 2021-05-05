package com.andriod.lesson6notes.data;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class NotesManager {
    private static final String TAG = "NotesManager";

    public interface DataChangedListener {
        void onChanged();
    }

    private final List<Note> notes = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public List<Note> getNotes() {
        return notes;
    }

    public NotesManager() {
    }

    public void loadNotes(DataChangedListener listener) {
        db.collection("notes")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots)
                        notes.add(NoteMapping.toNote(doc.getId(), doc.getData()));

                    int size = notes.size();
                    if (notes.size() < 5)
                        for (int i = 0; i < 5 - size; i++)
                            notes.add(Note.getEmptyNote());

                    if (listener != null) listener.onChanged();
                });

    }

    public Note get(int index) {
        if (index >= 0 && index < notes.size())
            return notes.get(index);

        return null;
    }

    public void updateNote(int index, Note note) {
//        Note note = notes.get(index);
        String id = note.getId();

        if (id == null || id.isEmpty())
            db.collection("notes")
                    .add(NoteMapping.toDocument(note))
                    .addOnSuccessListener(documentReference -> {
                        note.setId(documentReference.getId());
                        Log.i(TAG, String.format("Note#%s was added", note.getId()));
                    });
        else
            db.collection("notes")
                    .document(id)
                    .set(NoteMapping.toDocument(note))
                    .addOnSuccessListener((OnSuccessListener<Void>) unused -> Log.i(TAG, String.format("Note#%s was changed", note.getId())));
    }
}
