package javacourse.net;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

        //ScanSockets.scanSockets(1000);
        for (int i = 0; i < 15; i++) {
            SimpleClient sc = new SimpleClient(i);
            sc.start();
//            ScanSocket ss = new ScanSocket(i);
//            ss.start();
        }
    }
}

class SimpleClient extends Thread {
    private int cmdNumber;
    private static final String[] COMMAND = {
            "HELLO", "MORNING", "DAY", "EVENING"
    };

    public SimpleClient(int cmdNumber) {
        this.cmdNumber = cmdNumber;
    }

    @Override
    public void run() {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 25225);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String str = "ED ";
            String command = COMMAND[cmdNumber%COMMAND.length];
            bw.write(str + command);
            bw.newLine();
            bw.flush();
            String answer = br.readLine();
            System.out.println("Client got answer " + answer);
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

