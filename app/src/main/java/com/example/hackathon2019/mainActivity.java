package com.example.hackathon2019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class mainActivity extends AppCompatActivity {

    EditText submitSong;
    TableLayout tl;
    String IP = "10.27.248.205";
    int Port = 53312;
    SocketClass socketClass =  new SocketClass(IP, Port);
    ArrayList<String> queue = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitSong = findViewById(R.id.et_song);
        tl = findViewById(R.id.table);

        queue.add("po_oppppppppppppppppppp 7");
        queue.add("pdsoop 4");
        queue.add("poods_p 123");
        queue.add("poosdp 0");
//        queue.add("poop");
//        queue.add("poop");
//        queue.add("poop");
//        queue.add("poop");
//        queue.add("poop");
//        queue.add("poop");
//        queue.add("poop");
//        queue.add("poop");
//        queue.add("poop");
//        queue.add("poop");
//        queue.add("poop");
//        queue.add("poop");
//        queue.add("poop");
//        queue.add("poop");
//        queue.add("poop");
//        queue.add("poop");
//        queue.add("poop");


        addRows();

    }

    private void addRows() {
        for (int i = 0; i < queue.size(); i++) {
            // ROW
            TableRow tr = new TableRow(this);
            tr.setPadding(0, 50, 0, 50);
            tr.setBackground(getDrawable(R.drawable.cell_shape));

            // SONG NAME
            TextView songName = new TextView(this);
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

            Button down = new Button(this);
            down.setText("DOWN");
            down.setLayoutParams(new TableRow.LayoutParams(180, TableRow.LayoutParams.WRAP_CONTENT));



//            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

            tr.addView(songName);
            tr.addView(down);
            tr.addView(vote);
            tr.addView(up);

            tl.addView(tr);
        }
    }

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
