package com.andriod.lesson6notes.data;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Note implements Serializable {
    private String id;
    private String date;
    private String header;
    private String message;

    private static int ID = 0;

    public Note() {
    }

    public Note(String date, String header, String message) {
        this.date = date;
        this.header = header;
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Note getEmptyNote() {
        Note note = new Note();
        note.setHeader("Header #" + ID);
        note.setDate("01.01.2001");
        note.setMessage("Test #" + ID++);
        return note;
    }

    @NonNull
    @Override
    public String toString() {
        return header;
    }

    public int compare(Note n) {
        int result = 0;
        result = header.compareTo(n.header);
        if (result == 0)
            result = message.compareTo(n.getMessage());
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
