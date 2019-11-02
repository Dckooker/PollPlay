package com.example.hackathon2019;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SoccetClass {

    Socket s;
    PrintWriter pw;



    protected Void sendData(String message) {

        try {
            s = new Socket("192.168.1.20", 5555);

            pw = new PrintWriter(s.getOutputStream());
            pw.write(message);
            pw.flush();
            pw.close();
            s.close();

        } catch (IOException e) { e.printStackTrace(); }
        return null;
    }
}
