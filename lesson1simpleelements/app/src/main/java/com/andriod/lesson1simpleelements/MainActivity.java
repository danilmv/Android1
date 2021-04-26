package com.andriod.lesson1simpleelements;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView calendarView = findViewById(R.id.calendar);
        TextView textView = findViewById(R.id.textView);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            textView.setText(String.format(Locale.getDefault(), "%02d.%02d.%d", dayOfMonth, month+1, year));
        });

        EditText editText = findViewById(R.id.editText);

        editText.setOnEditorActionListener((v, actionId, event) -> {
            textView.setText(editText.getText());

            //hide keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            return true; //if we don't need multiline
        });
    }
}