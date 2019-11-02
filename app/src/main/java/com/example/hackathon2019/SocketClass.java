package com.example.hackathon2019;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClass extends AsyncTask<String, Void, Void> {

    private PrintWriter out;
    private BufferedReader in;
    String IP = "10.27.253.101"; //Adam: 10.27.248.205 , me: 10.27.253.101
    int Port = 53312;
    Socket s;
     Socket tempClientSocket;



    @Override
    protected  Void doInBackground(String... voids) {

        String message = voids[0];

        try {
            s = new Socket(IP, Port);

            out = new PrintWriter(s.getOutputStream());

            out.write(message);
            out.flush();
            out.close();
            s.close();

        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }

    protected String getData() {
        try {
            while (s.isConnected()) {
                in = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
                String msg = in.readLine();
                return msg;
            }
            s.close();
        } catch (IOException e) {e.printStackTrace();}
        return "NULL";
    }








}





















/**
    private PrintWriter out;
    private BufferedReader in = null;
    Socket s = null;

    @Override
    protected Void doInBackground(String... voids) {
        return null;
    }

    protected void sendSong(String songName) {
        try {
            out = new PrintWriter(s.getOutputStream());
            out.write("song " + songName);
            out.flush();
        } catch (IOException e) {e.printStackTrace();}
    }

    protected void sendVote(String vote) {
        try {
            out = new PrintWriter(s.getOutputStream());
            out.write("vote " + vote);
            out.flush();
        } catch (IOException e) {e.printStackTrace();}
    }

    protected void removeSong(String songName) {
        try {
            out = new PrintWriter(s.getOutputStream());
            out.write("rsong " + songName);
            out.flush();
        } catch (IOException e) {e.printStackTrace();}
    }

    protected void sendName(String name) {
        try {
            out = new PrintWriter(s.getOutputStream());
            out.write("name " + name);
            out.flush();
        } catch (IOException e) {e.printStackTrace();}
    }

    protected String getData() {
        try {
            while (s.isConnected()) {
                in = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
                String msg = in.readLine();
                return msg;
            }
            s.close();
        } catch (IOException e) {e.printStackTrace();}
        return "NULL";
    }



}
**/
