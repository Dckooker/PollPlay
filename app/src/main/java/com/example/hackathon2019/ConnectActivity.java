package com.example.hackathon2019;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class ConnectActivity extends AppCompatActivity {

    private Socket s = null;
    private String IP = "";
    private int PORT = 0;

    private boolean firstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        startConnection();

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        (new Thread(new sendDataThread("balls"))).start();

        waitForResponseThead res = new waitForResponseThead();
        (new Thread(res)).start();

        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public class connectThread implements Runnable {

        @Override
        public void run() {
            connectToServer();
        }
    }

    public void connectToServer() {
        try {
            s = new Socket();
            s.connect(new InetSocketAddress(IP, PORT), 1000);
            //Send username
        } catch (IOException e) {

        }
    }

    public class sendDataThread implements Runnable {
        private String data;
        public sendDataThread(String data) {
            this.data = data;
        }

        @Override
        public void run() {
            sendData(data);
        }
    }

    public boolean sendData(String data) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())));
            out.println(data);
            out.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public class waitForResponseThead implements Runnable {
        String res = "NULL";
        @Override
        public void run() {
            String res = waitForResponse();
        }
        public String getRes() {
            return res;
        }
    }

    public String waitForResponse() {
        try {
            BufferedReader in;
            while(s.isConnected()) {
                in = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
                if (in.ready()) {
                    String msg = in.readLine();
                    return msg;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void startConnection() {
        (new Thread(new connectThread())).start();
    }



}
