package client;

import java.io.*;
import java.net.*;

public class Client {
    private static final String host = "localhost";
    private static final int port = 5543;

    public static void main(String[] args) {
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            String userInput = "105 9782810627196";
            out.println(userInput);

            out.println("150");

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + host);
            e.printStackTrace();
            System.exit(1);
        }
    }
}
