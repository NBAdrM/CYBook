package Serveur;

public class Client {
    private final int id;
    private String lastName;
    private String firstName;
    private String phone;

    //Constructor
    public Client(int id, String lastName, String firstName, String phone) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
    }

    //Getter
    public int getId() {
        return id;
    }
    public String getLastName() {
        return lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getPhone() {
        return phone;
    }

    //Setter
     public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return (firstName + ' ' + lastName + ' ');
    }
}
