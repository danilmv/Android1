package com.andriod.lesson6notes.data;

import java.util.ArrayList;
import java.util.List;

public class NotesManager {
    private final List<Note> notes = new ArrayList<>();

    public List<Note> getNotes() {
        return notes;
    }

    public NotesManager() {
        loadNotes();
    }

    private void loadNotes() {
        for (int i = 0; i < 5; i++)
            notes.add(Note.getEmptyNote());
    }

    public Note get(int index){
        if (index >=0 && index < notes.size())
            return notes.get(index);

        return null;
    }
}
