package com.example.hackathon2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClass {

    Socket s;
    PrintWriter pw;
    ObjectInputStream ois;
    String IpAddress = "localhost"; //"10.27.253.101";
    int Port = 53312;

    protected void sendSong(String songName) {
        try {
            s = new Socket(IpAddress, Port);
            pw = new PrintWriter(s.getOutputStream());
            pw.write("NewSong: " + songName);
            pw.flush();
            pw.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void sendVote(String vote) {
        try {
            s = new Socket(IpAddress, Port);
            pw = new PrintWriter(s.getOutputStream());
            pw.write("NewVote: " + vote);
            pw.flush();
            pw.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected String getData() {
        try {
            s = new Socket();
            s.connect(new InetSocketAddress(IpAddress, Port), 1000);

            BufferedReader in;
            while (s.isConnected()) {
                in = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
                //if (in.ready()) {
                String msg = in.readLine();

                return msg;
                //}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "NULL";
    }

}
