package com.example.hackathon2019;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;

public class mainActivity extends AppCompatActivity {

    EditText etSubmitSong;
    TableLayout tl;
    Button bAdd;

    ArrayList<String> queue = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ConnectActivity connect = new ConnectActivity(getIntent().getStringExtra("USER"));

        etSubmitSong = findViewById(R.id.et_song);
        tl = findViewById(R.id.table);
        bAdd = findViewById(R.id.bt_add);


        queue.add("song1 7");
        queue.add("song2 4");
        queue.add("song3 123");
        queue.add("song4 0");

        addRows();

        //add song button
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (new Thread(new ConnectActivity.sendDataThread("song " + etSubmitSong.getText().toString()))).start();
            }
        });

//        (new Thread(new ConnectActivity.waitForResponseThread())).start();
    }

    private void addRows() {
        tl.removeAllViews();
        for (int i = 0; i < queue.size(); i++) {
            // ROW
            TableRow tr = new TableRow(this);
            tr.setPadding(0, 50, 0, 50);
            tr.setBackground(getDrawable(R.drawable.cell_shape));

            // SONG NAME
            final TextView songName = new TextView(this);
            songName.setText(getSongName(queue.get(i)));
            songName.setTextSize(15);
            songName.setTextColor(getResources().getColor(R.color.colorAccent));
            songName.setPadding(30, 0, 50, 0);

            // VOTE NUMBER
            TextView vote = new TextView(this);
            vote.setText(getVoteCount(queue.get(i)));
            vote.setTextSize(15);
            vote.setTextColor(getResources().getColor(R.color.colorAccent));
            vote.setPadding(20, 0, 20, 0);

            // UP VOTE
            Button up = new Button(this);
            up.setText("UP");
            up.setLayoutParams(new TableRow.LayoutParams(150, TableRow.LayoutParams.WRAP_CONTENT));

            up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    (new Thread(new ConnectActivity.sendDataThread("vote "+ songName.getText().toString() + " 1"))).start();
                }
            });

            // DOWN VOTE
            Button down = new Button(this);
            down.setText("DOWN");
            down.setLayoutParams(new TableRow.LayoutParams(180, TableRow.LayoutParams.WRAP_CONTENT));

            down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    (new Thread(new ConnectActivity.sendDataThread("vote "+ songName.getText().toString() + " -1"))).start();
                }
            });

            tr.addView(songName);
            tr.addView(down);
            tr.addView(vote);
            tr.addView(up);

            tl.addView(tr);
        }
    }

//    private void updateQueue(String s) {
//        String[] songs = s.split(",");
//        ArrayList<String> arrListSongs = new ArrayList<>();
//
//        for (int i = 0; i <= songs.length; i++) {
//            arrListSongs.add(songs[i]);
//        }
//
//        Collections.copy(queue, arrListSongs);
//
//        addRows();
//    }


    private String getSongName(String string) {
        String[] strings = string.split(" ");
        String name = strings[0];

        name = name.replaceAll("_", " ");

        if (name.length() > 20) {
            name = name.substring(0, 18);
            name += "...";
        }

        return name;
    }

    private String getVoteCount (String string) {
        String[] strings = string.split(" ");
        String vote = strings[1];

        return vote;
    }
}
