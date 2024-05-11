import java.io.*;
import java.net.*;

public class Serveur2 {
    private static final int port = 5543; // Définition du port

    public static void main(String[] argv) {
        ServerSocket serverSocket = null;

        try {
            //démarage serveur
            serverSocket = new ServerSocket(Serveur2.port);
            System.out.println("Serveur démarré sur le port " + Serveur2.port);
            //attente de la connection client
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connecté.");

            //pour lire et ecrire au client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //on lit les lignes envoyer par le client
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Reçu du client: " + inputLine);
                if ("End".equals(inputLine)) {
                    out.println("Au revoir!");
                    break;
                }
            }

            //on ferme tout
            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + port + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
