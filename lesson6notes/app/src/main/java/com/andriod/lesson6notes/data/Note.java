package com.andriod.lesson6notes.data;

import androidx.annotation.NonNull;

public class Note {
    private String date;
    private String header;
    private String message;

    private static int ID = 0;

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
        note.setHeader("Header #" + ID++);
        note.setDate("01.01.2001");
        return note;
    }

    @NonNull
    @Override
    public String toString() {
        return header;
    }
}
