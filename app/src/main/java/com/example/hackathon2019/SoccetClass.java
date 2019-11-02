package com.example.hackathon2019;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SoccetClass {

    Socket ss;
    Socket sv;
    PrintWriter pw;

    protected Void sendSong(String message) {

        try {
            ss = new Socket("192.168.1.20", 5555);

            pw = new PrintWriter(ss.getOutputStream());
            pw.write(message);
            pw.flush();
            pw.close();
            ss.close();

        } catch (IOException e) { e.printStackTrace(); }
        return null;
    }

    protected Void sendVotes(String message) {

        try {
            sv = new Socket("192.168.1.20", 5555);

            pw = new PrintWriter(sv.getOutputStream());
            pw.write(message);
            pw.flush();
            pw.close();
            sv.close();

        } catch (IOException e) { e.printStackTrace(); }
        return null;
    }
}
