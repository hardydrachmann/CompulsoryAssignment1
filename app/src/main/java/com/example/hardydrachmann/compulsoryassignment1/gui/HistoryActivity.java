package com.example.hardydrachmann.compulsoryassignment1.gui;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hardydrachmann.compulsoryassignment1.R;
import com.example.hardydrachmann.compulsoryassignment1.bll.DiceEntry;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends ListActivity {

    private final int NUMBER_OF_DICES = 6;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showHistory();
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        state.getParcelableArrayList("diceEntryParcelableArrayList");
    }

    private void showHistory() {
        Bundle extras = getIntent().getExtras();
        int i = 0;
        int[] faces = extras.getIntArray("dice" + i++);
        ArrayList<DiceEntry> rollHistory = new ArrayList<>();

        while (faces != null) {
            rollHistory.add(new DiceEntry(faces));
            faces = extras.getIntArray("dice" + i++);
        }
        myAdapter = new MyAdapter(getApplicationContext(), R.layout.activity_history, rollHistory);
        setListAdapter(myAdapter);
    }


    // Start på ny inner-class
    // ADAPTER KLASSEN BRUGES TIL AT ADAPTERE DATA TIL LIST-ACTIVITIEN (HVORDAN SKAL DATAEN VISES I LISTEN)
    public class MyAdapter extends ArrayAdapter<DiceEntry> {
        private List<DiceEntry> diceEntryList;

        public MyAdapter(Context context, int resource, List<DiceEntry> entries) {
            super(context, resource, entries);
            this.diceEntryList = entries;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.activity_history, null);

            TextView tView = (TextView) view.findViewById(R.id.historyView);
            StringBuilder sb = new StringBuilder("Dice faces thrown: ");
            DiceEntry e = diceEntryList.get(position);
            for (int i = 0; i < NUMBER_OF_DICES; i++) {
                sb.append(e.getDiceFaces()[i]);
            }
            tView.setText("" + sb.toString());

            return view;
        }
    }
}

