package com.example.hardydrachmann.compulsoryassignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;

import bll.Dice;

public class MainActivity extends AppCompatActivity {

    private Button btnRollDice, btnHistory;
    private TextView[] txtDiceFace;
    private NumberPicker numberPicker;
    private Dice dice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRollDice = (Button) findViewById(R.id.btnRollDice);
        btnHistory = (Button) findViewById(R.id.btnHistory);

        populateDiceFaceArray();

        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(6);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        dice = new Dice();

        btnRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RollDice();
            }
        });
    }

    private void populateDiceFaceArray() {
        txtDiceFace = new TextView[]{
                (TextView) findViewById(R.id.diceFace1),
                (TextView) findViewById(R.id.diceFace2),
                (TextView) findViewById(R.id.diceFace3),
                (TextView) findViewById(R.id.diceFace4),
                (TextView) findViewById(R.id.diceFace5),
                (TextView) findViewById(R.id.diceFace6)
        };
    }

    private void RollDice() {
        for (TextView textView : txtDiceFace) {
            textView.setText("");
        }
        int numberOfDices = numberPicker.getValue();
        for (int i = 0; i < numberOfDices; i++) {
            dice.rollDice();
            int face = dice.getFace();
            txtDiceFace[i].setText("" + face);
        }
    }
}