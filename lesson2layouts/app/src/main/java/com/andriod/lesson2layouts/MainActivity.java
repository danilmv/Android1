package com.andriod.lesson2layouts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String KEY = "CALC";

    private final int[] buttonIds = {R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
            R.id.buttonPoint,
            R.id.buttonC,
            R.id.buttonSign, R.id.buttonPercent,
            R.id.buttonDiv, R.id.buttonMult, R.id.buttonSubt, R.id.buttonAdd, R.id.buttonEq};

    private final Action[] actions = {Action.ZERO, Action.ONE, Action.TWO, Action.THREE,
            Action.FOUR, Action.FIVE, Action.SIX, Action.SEVEN, Action.EIGHT, Action.NINE,
            Action.DECIMAL,
            Action.CANCEL,
            Action.SIGN, Action.PERCENT,
            Action.DIVIDE, Action.MULTIPLY, Action.SUBTRACT, Action.ADD, Action.EQUAL};

    private Calculate calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null)
            calculate = new Calculate(findViewById(R.id.textView));

        Button button;
        for (int i = 0; i < buttonIds.length; i++) {
            final int index = i;
            button = findViewById(buttonIds[i]);
            if (button != null) {
                button.setOnClickListener((View.OnClickListener) v -> {
                    calculate.process(actions[index]);
                });
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(KEY, calculate);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        calculate = (Calculate) savedInstanceState.getSerializable(KEY);
        calculate.setTextView(findViewById(R.id.textView));
        calculate.show();

        super.onRestoreInstanceState(savedInstanceState);
    }
}