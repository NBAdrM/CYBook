package client;

import java.io.*;
import java.net.*;

public class User {
    private static final String host = "localhost";
    private static final int port = 5543;

    public static void main(String[] args) {
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            int userInput = 201;
            out.println(userInput);
            System.out.println("Réponse du serveur: " + in.readLine());

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
