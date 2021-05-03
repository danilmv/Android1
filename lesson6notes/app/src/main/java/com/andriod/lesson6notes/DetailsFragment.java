package com.andriod.lesson6notes;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.andriod.lesson6notes.data.Note;
import com.andriod.lesson6notes.data.NotesManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {

    private static final String INDEX = "index";

    private TextView textHeader;
    private TextView textDate;
    private EditText editMessage;

    private int index = 0;
    private Note note;

    final Calendar myCalendar = Calendar.getInstance();

    public DetailsFragment setListener(OnNotesChangedListener listener) {
        this.listener = listener;
        return this;
    }

    private OnNotesChangedListener listener;

    public DetailsFragment() {
    }

    public static DetailsFragment newInstance(int index) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt(INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(INDEX);
        }

        note = MainActivity.getNotesManager().get(index);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textHeader = view.findViewById(R.id.textHeader);
        textDate = view.findViewById(R.id.textDate);
        editMessage = view.findViewById(R.id.editMessage);

        showData();
    }

    private void showData() {
        textHeader.setText(note.getHeader());
        textDate.setText(note.getDate());
        editMessage.setText(note.getMessage());

        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String myFormat = "dd.MM.YYYY"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

            textDate.setText(sdf.format(myCalendar.getTime()));
        };

        textDate.setOnClickListener(v -> {
            new DatePickerDialog(DetailsFragment.this.getContext(), date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });
    }

    @Override
    public void onStop() {
        super.onStop();

//        saveNote();
    }

    @Override
    public void onPause() {
        super.onPause();

        saveNote();
    }

    private void saveNote() {
        if (note != null) {
            note.setHeader(textHeader.getText().toString());
            note.setMessage(editMessage.getText().toString());
            note.setDate(textDate.getText().toString());

            if (listener != null) listener.onChange(index);
        }
    }

    interface OnNotesChangedListener {
        void onChange(int listIndex);
    }
}