package serveur.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class permits to manage all clients
 */
public class ClientManager {

    HashMap<Integer, Client> clients;

    public ClientManager() {
        this.clients = new HashMap<>();
    }

    /**
     * This method permits to add a client into the system.
     * It defines a new ID by looking from 0 to +inf if the number is already use for an ID
     * @param lastName;
     * @param firstName;
     * @param phone;
     */
    public void addClient(String lastName, String firstName, String phone){
        int newID=0;
        while(clients.containsKey(newID)){
                newID+=1;
            }

        Client client = new Client(newID, lastName, firstName, phone);
        clients.put(newID, client);
        System.out.println(clients.get(newID).toString() + "added");

    }

    /**
     * This method permits to remove a client from the system.
     * It checks if the client have restored all book he had borrow.
     * @param id;
     * @param borrowManager;
     */
    public void removeClient(int id,BorrowManager borrowManager){

        //Check if client restored all book he had borrow
        for(Map.Entry<Integer,Borrow> entry : borrowManager.getBorrowing().entrySet()){
            if(entry.getValue().getClient().getId()==id && entry.getValue().getRestore()!=Boolean.TRUE){
                //TODO ne renvoie pas vraiment le nom du livre tant que Book est Artwork ne sont pas lié
                System.out.println("The book" + entry.getValue().getBook().toString() + "have not been restored");
                System.out.println("Client can't be removed");
                return;
            }
        }
        clients.remove(id);
        System.out.println("Client removed");
    }

    /**
     * This method permits to change client's information.
     * Information that should not be modified must be written as an empty character string "".
     * @param id;
     * @param lastName;
     * @param firstName;
     * @param phone;
     */
    public void updateClient(int id,String lastName, String firstName, String phone){

        //To give the possibility of changing only one element, we assume that if an element must not be changed it is the empty string
        if(!lastName.isEmpty()) {
            clients.get(id).setLastName(lastName);
        }
        if(!firstName.isEmpty()) {
            clients.get(id).setFirstName(firstName);
        }
        if(!phone.isEmpty()) {
            clients.get(id).setPhone(phone);
        }
        System.out.println(clients.get(id).toString() + "updated");
    }

    /**
     * This method permits to search the id of a client by using is Last name or is First Name or is Phone number
     * @param lastName;
     * @param firstName;
     * @param phone;
     * @return id of the client or -1 if not found;
     */
    public int searchClient(String lastName, String firstName, String phone){

        //Search the id of the client with is Last/First name or is phone number
        for(Map.Entry<Integer, Client> entry : clients.entrySet()){
            if (entry.getValue().getLastName().equals(lastName) | entry.getValue().getFirstName().equals(firstName) | entry.getValue().getPhone().equals(phone)){
                return(entry.getKey());
            }
        }
        System.out.println("Client not found");
        return(-1);
    }
}
