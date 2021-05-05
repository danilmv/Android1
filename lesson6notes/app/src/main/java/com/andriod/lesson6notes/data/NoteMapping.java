package com.andriod.lesson6notes.data;

import java.util.HashMap;
import java.util.Map;

public class NoteMapping {

    public static class Fields {
        public final static String DATE = "date";
        public final static String HEADER = "header";
        public final static String MESSAGE = "message";
    }

    public static Note toNote(String id, Map<String, Object> doc) {
        Note note = new Note((String) doc.get(Fields.DATE),
                (String) doc.get(Fields.HEADER),
                (String) doc.get(Fields.MESSAGE));
        note.setId(id);

        return note;
    }

    public static Map<String, Object> toDocument(Note note) {
        Map<String, Object> doc = new HashMap<>();
        doc.put(Fields.DATE, note.getDate());
        doc.put(Fields.HEADER, note.getHeader());
        doc.put(Fields.MESSAGE, note.getMessage());
        return doc;
    }
}
