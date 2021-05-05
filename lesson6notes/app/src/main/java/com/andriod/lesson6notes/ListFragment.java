package com.andriod.lesson6notes;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andriod.lesson6notes.data.Note;

import java.util.List;
import java.util.Map;

public class ListFragment extends Fragment {

    private int currentItem;


    public ListFragment() {
    }

    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout layout = (LinearLayout) view;

        List<Note> notes = MainActivity.getNotesManager().getNotes();
        for (int i = 0; i < notes.size(); i++) {
            TextView textView = new TextView(view.getContext());
            textView.setText(notes.get(i).toString());
            layout.addView(textView);

            final int index = i;
            textView.setOnClickListener(v -> {
                currentItem = index;
                if (MainActivity.isIsLandscape()) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.detailsContainer, DetailsFragment.newInstance(currentItem, notes.get(currentItem)))
                            .addToBackStack(null)
                            .commit();
                } else {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.mainContainer, DetailsFragment.newInstance(currentItem, notes.get(currentItem)))
                            .addToBackStack(null)
                            .commit();
                }
            });
        }
    }
}