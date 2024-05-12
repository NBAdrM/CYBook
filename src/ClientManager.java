import com.sun.net.httpserver.Request;
import java.util.HashMap;
import java.util.Map;

/**
 * This class permits to manage all clients
 */
public class ClientManager {

    HashMap<Integer, Client> clients;

    /**
     * This method permits to add a client into the system.
     * It defines a new ID by finding the biggest id number and adding 1.
     * @param lastName;
     * @param firstName;
     * @param phone;
     */
    public void addClient(String lastName, String firstName, String phone){
        //Find a new ID
        int max=0;
        for(Map.Entry<Integer,Client> entry : clients.entrySet()){
            if(entry.getKey()>max){
                max=entry.getKey();
            }
        }
        int newID=max+1;

        Client client = new Client(newID, lastName, firstName, phone);
        clients.put(newID, client);
        System.out.println(clients.get(newID).toString() + "added");
    }

    /**
     * This method permits to remove a client from the system.
     * It checks if the client have restored all book he had borrow.
     * @param id;
     */
    public void removeClient(int id){

        //TODO Verifier les emprunts en cours avant de supprimer
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
