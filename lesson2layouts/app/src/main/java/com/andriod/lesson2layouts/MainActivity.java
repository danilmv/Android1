package com.andriod.lesson2layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final int MAX_LENGTH = 15;

    private TextView textView;
    private final int[] buttonIds = {R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.buttonPoint};
    private final String[] values = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "."};
    private int length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        for (int i = 0; i < buttonIds.length; i++) {
            ((Button) findViewById(buttonIds[i])).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (length >= MAX_LENGTH)return;
        for (int i = 0; i < buttonIds.length; i++) {
            if (v.getId() == buttonIds[i]) {
                textView.append(values[i]);
                length++;
            }
        }
    }
}