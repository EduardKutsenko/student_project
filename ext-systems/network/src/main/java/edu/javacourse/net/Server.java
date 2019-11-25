package edu.javacourse.net;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(25225);
        Map<String, Greetable> handlers = loadHandlers();
        System.out.println("Server started ....");
        while (true) {
            Socket client = socket.accept();
            new SimpleServer(client, handlers).start();
        }
    }

    private static Map<String, Greetable> loadHandlers() {
        Map<String, Greetable> result = new HashMap<>();
        try (InputStream is = Server.class.getClassLoader().getResourceAsStream("server.properties")) {
            Properties properties = new Properties();
            properties.load(is);

            for (Object command : properties.keySet()) {
                String className = properties.getProperty(command.toString());
                Class<Greetable> cl = (Class<Greetable>) Class.forName(className);
                Greetable handler = cl.getConstructor().newInstance();
                result.put(command.toString(), handler);
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }
}

class SimpleServer extends Thread {
    private Socket client;
    private Map<String, Greetable> handlers;

    public SimpleServer(Socket client, Map<String, Greetable> handlers) {
        this.client = client;
        this.handlers = handlers;
    }

    public void run() {
        handleRequest();
    }

    public void handleRequest() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            String request = br.readLine();
            String[] lines = request.split("\\s+");
            String command = lines[1];
            String userName = lines[0];
          //  System.out.println("Server got string 1:  " + command);
          //  System.out.println("Server got string 2:  " + userName);

            String response = buildResponse(command, userName);

            bw.write(response);
            bw.newLine();
            bw.flush();
            br.close();
            bw.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String buildResponse(String command, String userName) {
//        switch (command) {
//            case "HELLO":
//                return "Hello " + userName;
//            case "MORNING":
//                return "Good Morning " + userName;
//            case "DAY":
//                return "Good Afternoon " + userName;
//            case "EVENING":
//                return "Good evening " + userName;
//            default:
//                return "Hi!!!!!!!!!!!!!!!!!!11!";
//        }
        Greetable handler = handlers.get(command);
  //      return handler.buildResponse("debug");
        if (handler != null)
            return handler.buildResponse(userName);
        else
            return "Hello, " + userName;

    }
}

