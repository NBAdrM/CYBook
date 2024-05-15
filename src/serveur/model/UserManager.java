package serveur.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class permits to manage all users
 */
public class UserManager {

    HashMap<Integer, User> Users;

    public UserManager() {
        this.Users = new HashMap<>();
    }

    /**
     * This method permits to add a User into the system.
     * It defines a new ID by looking from 0 to +inf if the number is already use for an ID
     * @param lastName;
     * @param firstName;
     * @param phone;
     */
    public void addUser(String lastName, String firstName, String phone){
        int newID=0;
        while(Users.containsKey(newID)){
                newID+=1;
            }

        User User = new User(newID, lastName, firstName, phone);
        Users.put(newID, User);
        System.out.println(Users.get(newID).toString() + "added");

    }

    /**
     * This method permits to remove a User from the system.
     * It checks if the User have restored all book he had borrow.
     * @param id;
     * @param borrowManager;
     */
    public void removeUser(int id,BorrowManager borrowManager){

        //Check if User restored all book he had borrow
        for(Map.Entry<Integer,Borrow> entry : borrowManager.getBorrowing().entrySet()){
            if(entry.getValue().getUser().getId()==id && entry.getValue().getRestore()!=Boolean.TRUE){
                //TODO ne renvoie pas vraiment le nom du livre tant que Book est Artwork ne sont pas lié
                System.out.println("The book" + entry.getValue().getBook().toString() + "have not been restored");
                System.out.println("User can't be removed");
                return;
            }
        }
        Users.remove(id);
        System.out.println("User removed");
    }

    /**
     * This method permits to change User's information.
     * Information that should not be modified must be written as an empty character string "".
     * @param id;
     * @param lastName;
     * @param firstName;
     * @param phone;
     */
    public void updateUser(int id,String lastName, String firstName, String phone){

        //To give the possibility of changing only one element, we assume that if an element must not be changed it is the empty string
        if(!lastName.isEmpty()) {
            Users.get(id).setLastName(lastName);
        }
        if(!firstName.isEmpty()) {
            Users.get(id).setFirstName(firstName);
        }
        if(!phone.isEmpty()) {
            Users.get(id).setPhone(phone);
        }
        System.out.println(Users.get(id).toString() + "updated");
    }

    /**
     * This method permits to search the id of a User by using is Last name or is First Name or is Phone number
     * @param lastName;
     * @param firstName;
     * @param phone;
     * @return id of the User or -1 if not found;
     */
    public int searchUser(String lastName, String firstName, String phone){

        //Search the id of the User with is Last/First name or is phone number
        for(Map.Entry<Integer, User> entry : Users.entrySet()){
            if (entry.getValue().getLastName().equals(lastName) | entry.getValue().getFirstName().equals(firstName) | entry.getValue().getPhone().equals(phone)){
                return(entry.getKey());
            }
        }
        System.out.println("User not found");
        return(-1);
    }
}
