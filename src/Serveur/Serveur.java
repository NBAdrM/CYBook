package Serveur;

import java.io.*;
import java.net.*;

public class Serveur {
    private static int port;

    public static void main(String[] argv) {
        ServerSocket serverSocket=null;

        try {
            //Create Socket
            serverSocket = new ServerSocket(Serveur.port);
        } catch (IOException e) {
            //Error in creation
            System.out.println(e.getMessage());
            System.exit(-1);
        }


        try{
            //Waiting for connexion
            Socket userSocket = serverSocket.accept();
            //Connected
            try{
                //Input and Output from user
                PrintWriter out = new PrintWriter(userSocket.getOutputStream(),true);
                BufferedReader in = new BufferedReader( new InputStreamReader(userSocket.getInputStream()));

                while (true){
                    //For now, it's just an echo. It's stop when user write "End"
                    String line = in.readLine();
                    if (line.equals("End"))
                        break;
                    out.println(line);
                    out.flush();
                }

                out.close();
                in.close();
                userSocket.close();

            } catch (Exception e){
                System.out.println(e.getMessage());
                System.exit(-1);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(-1);
        } finally {
            try {
                //Close Server Socket
                serverSocket.close();
            } catch (Exception e){
                System.out.println(e.getMessage());
                System.exit(-1);
            }
        }
    }
}
