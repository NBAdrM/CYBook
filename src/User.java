import java.io.*;
import java.net.*;

public class User {
    private static int port;
    public static void main (String argv[]) {
        Socket userSocket = null;

        try {
            userSocket = new Socket("localhost",port); // Adresse IP et port du serveur
        } catch (IOException e) {
            //Error in creation
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
}