package com.example.hardydrachmann.compulsoryassignment1.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hardydrachmann.compulsoryassignment1.R;
import com.example.hardydrachmann.compulsoryassignment1.bll.Dice;
import com.example.hardydrachmann.compulsoryassignment1.bll.DiceEntry;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int NUMBER_OF_DICES = 6;
    private static final int MAX_DICE_VAULE = 6;
    private static final int MIN_DICE_VALUE = 1;
    private static final String PARCELABLE_DICE_ENTRY = "diceEntryParcelableArrayList";

    private Button btnRollDice, btnHistory, btnClear;
    private TextView[] txtDiceFace;
    private ArrayList<DiceEntry> rollHistory;
    private NumberPicker numberPicker;
    private Dice dice;
    private String diceLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRollDice = (Button) findViewById(R.id.btnRollDice);
        btnHistory = (Button) findViewById(R.id.btnHistory);
        btnClear = (Button) findViewById(R.id.btnClear);

        populateDiceFaceArray();

        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMinValue(MIN_DICE_VALUE);
        numberPicker.setMaxValue(MAX_DICE_VAULE);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        dice = new Dice();

        rollHistory = new ArrayList<>();

        btnRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RollDice();
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHistory();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearHistory();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        for (int i = 0; i < txtDiceFace.length; i++) {
            diceLabel = txtDiceFace[i].getText().toString();
            state.putString("sequence" + i, diceLabel);
        }
        state.putParcelableArrayList(PARCELABLE_DICE_ENTRY, rollHistory);
        Log.d("onSaveInstanceState", " has been called");
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        for (int i = 0; i < txtDiceFace.length; i++) {
            txtDiceFace[i].setText(state.getString("sequence" + i));
        }
        rollHistory = state.getParcelableArrayList(PARCELABLE_DICE_ENTRY);
        Log.d("onRestoreInstanceState", " has been called");
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
        DiceEntry diceEntry = new DiceEntry(NUMBER_OF_DICES);
        for (int i = 0; i < numberOfDices; i++) {
            dice.rollDice();
            int face = dice.getFace();
            txtDiceFace[i].setText("" + face);
            diceEntry.setDiceFace(face);
        }
        rollHistory.add(diceEntry);
    }

    private void viewHistory() {
        if (!rollHistory.isEmpty()) {
            Intent intentHistory = new Intent(getApplicationContext(), HistoryActivity.class);

            for (int i = 0; i < rollHistory.size(); i++) {
                int[] diceFaces = rollHistory.get(i).getDiceFaces();
                intentHistory.putExtra("dice" + i, diceFaces);
            }
            startActivity(intentHistory);
        } else {
            historyUserMessage();
        }
    }

    private void clearHistory() {
        if (!rollHistory.isEmpty())
            rollHistory.clear();
        historyUserMessage();
    }

    private void historyUserMessage() {
        Toast toast = Toast.makeText(getApplicationContext(), "History is empty...\n" +
                "Roll dices to build a history", Toast.LENGTH_LONG);
        toast.show();
    }
}