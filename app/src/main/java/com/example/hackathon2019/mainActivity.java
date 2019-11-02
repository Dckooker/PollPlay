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
    SocketClass socketclass = new SocketClass();
    ArrayList<String> queue = new ArrayList<>();
    String dataString = "";

    String IP = "10.27.253.101";
    int PORT = 53312;
    private ClientThread clientThread;
    private Thread thread;

    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        etSubmitSong = findViewById(R.id.et_song);
        tl = findViewById(R.id.table);
        bAdd = findViewById(R.id.bt_add);


        queue.add("song1 7");
        queue.add("song2 4");
        queue.add("song3 123");
        queue.add("song4 0");

        addRows();

        new Thread(new Runnable() {
            @Override
            public void run() {
                update();
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //add song button
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientThread = new ClientThread();
                thread = new Thread(clientThread);
                thread.start();
                dataString = etSubmitSong.toString();
                clientThread.sendMessage(dataString);
            }
        });
    }

    private void update() {
        while (true) {
            if (!socketclass.getData().equals("NULL")) {
                clientThread = new ClientThread();
                updateQueue(socketclass.getData());
            }
        }
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
                    String send = "vote " + songName.getText() + " 1";
                    clientThread = new ClientThread();
                    thread = new Thread(clientThread);
                    thread.start();
                    dataString = send;
                    clientThread.sendMessage(dataString);


                }
            });

            // DOWN VOTE
            Button down = new Button(this);
            down.setText("DOWN");
            down.setLayoutParams(new TableRow.LayoutParams(180, TableRow.LayoutParams.WRAP_CONTENT));

            down.setOnClickListener(new View.OnClickListener() {
                String send = "vote " + songName.getText() + " -1";
                @Override
                public void onClick(View v) {
                    clientThread = new ClientThread();
                    thread = new Thread(clientThread);
                    thread.start();
                    dataString = send;
                    clientThread.sendMessage(dataString);
                }
            });

            tr.addView(songName);
            tr.addView(down);
            tr.addView(vote);
            tr.addView(up);

            tl.addView(tr);
        }
    }

    private void updateQueue(String s) {
        String[] songs = s.split(",");
        ArrayList<String> arrListSongs = new ArrayList<>();

        for (int i = 0; i <= songs.length; i++) {
            arrListSongs.add(songs[i]);
        }

        Collections.copy(queue, arrListSongs);

        addRows();
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




    class ClientThread implements Runnable {

        private Socket socket;
        private BufferedReader input;

        @Override
        public void run() {
            try {
                InetAddress serverAddr = InetAddress.getByName(IP);
                socket = new Socket(serverAddr, PORT);


                while (!Thread.currentThread().isInterrupted()) {

                    this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String message = input.readLine();
                    if (null == message || "Disconnect".contentEquals(message)) {
                        Thread.interrupted();
                        message = "Server Disconnected.";
                       System.out.println(message);
                        break;
                    }
                    System.out.println("Server: " + message);
                }

            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

        void sendMessage(final String message) {
            void sendMessage(final String message) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (null != socket) {
                                PrintWriter out = new PrintWriter(new BufferedWriter(
                                        new OutputStreamWriter(socket.getOutputStream())),
                                        true);
                                out.println(message);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != clientThread) {
            clientThread.sendMessage("Disconnect");
            clientThread = null;
        }
    }



}
