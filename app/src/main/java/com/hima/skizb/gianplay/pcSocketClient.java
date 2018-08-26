package com.hima.skizb.gianplay;

import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by root on 7/20/18.
 */

public class pcSocketClient {
    private String TAG = "pcSocketClient";
    private PrintWriter out;
    private MainActivity context;

    public pcSocketClient(MainActivity context){
        this.context=context;
    }

    public void makeClient(String hostName, int portNumber) {
        try {
            Socket echoSocket = new Socket(hostName, portNumber);
//            Log.d(TAG,"Connected to server");
            out = new PrintWriter(echoSocket.getOutputStream(), true);
//            for(int i = 0; i<100; i++) {

//            }
//            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
//            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        }catch (Exception e){
            Log.d(TAG, e+"");
//            Toast.makeText(context,"Wrong IP Address",Toast.LENGTH_SHORT).show();
        }
    }

    public void sendLink(String link){
        try {
            out.println(link);
//            Thread.sleep(20000);
//            out.println("http://3b70ebc4.kablakaka.ru/iptv/8STFMNLEWA983T/601/index.m3u8");
        }catch (Exception e){}
    }
}
