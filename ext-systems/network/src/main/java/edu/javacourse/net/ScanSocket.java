package edu.javacourse.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ScanSocket extends Thread {
    int port;

    @Override
    public void run() {
        scanSocket();
    }

    public ScanSocket(int port) {
        this.port = port;
    }

    public void scanSocket() {
        System.out.println("Scanning socket # " + port);
        try {
            Socket socket = new Socket("127.0.0.1", port);
            //   socket.setSoTimeout(7000);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String answer = br.readLine();
            System.out.println("Client got answer " + answer + "on socket # " + port);
            br.close();
        } catch (IOException e) {
            System.out.println("Connection refused for socket # " + port);
        }
    }
}