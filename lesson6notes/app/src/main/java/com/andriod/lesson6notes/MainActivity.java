package com.andriod.lesson6notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

import com.andriod.lesson6notes.data.Note;
import com.andriod.lesson6notes.data.NotesManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static NotesManager notesManager = new NotesManager();
    private static boolean isLandscape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainContainer, new RecyclerFragment())
                .commit();
        if (isIsLandscape())
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detailsContainer, DetailsFragment.newInstance(0, null))
                    .commit();

    }

    public static NotesManager getNotesManager() {
        return notesManager;
    }

    public static boolean isIsLandscape() {
        return isLandscape;
    }

    ;

}