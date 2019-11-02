package com.example.hackathon2019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class mainActivity extends AppCompatActivity {

    EditText submitSong;
    TableLayout tl;

    ArrayList<String> queue = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitSong = findViewById(R.id.et_song);
        tl = findViewById(R.id.table);

        queue.add("poop");

        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

//        TextView songName = new TextView(this);
//
//        songName.setText(queue.get(0));
//        songName.setTextSize(25);
//        songName.setTextColor(getResources().getColor(R.color.colorAccent));
//        songName.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//
//        tr.addView(songName);
//        tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));


        for (int i = 0; i <= queue.size(); i++) {
            TextView songName = new TextView(this);
            songName.setText(queue.get(i));

            songName.setTextSize(25);
            songName.setTextColor(getResources().getColor(R.color.colorAccent));
            songName.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            tr.addView(songName);
            tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        }


    }
}
