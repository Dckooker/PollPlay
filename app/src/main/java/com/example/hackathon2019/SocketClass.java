package com.example.hackathon2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClass {

    private PrintWriter out;
    private BufferedReader in = null;
    Socket s = null;

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

