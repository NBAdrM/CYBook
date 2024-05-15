package serveur.model;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;


/**
 * This class permits to manage all borrow
 */
public class BorrowManager {

    private HashMap<Integer,Borrow> borrowing;
    private HashMap<Integer,Borrow> history;
    private HashMap<Book,Integer> nbborrowperbook;

    public BorrowManager() {
        this.borrowing = new HashMap<>();
        this.history = new HashMap<>();
        this.nbborrowperbook= new HashMap<>();
    }

    /**
     * This method permits to borrow a book for a specific user
     *
     * @param book;
     * @param user;
     * @throws ParseException Error of date format;
     */
    public void borrow_book(Book book,User user) throws ParseException {

        if(book.getStatue().equals(TypeStatue.FREE)){
            //Find new ID available in borrowing
            int newID=0;
            while(borrowing.containsKey(newID)){
                newID+=1;
            }

            Borrow borrow = new Borrow(newID,user,LocalDate.now().toString(),book);
            borrowing.put(newID,borrow);
            System.out.println(user.toString() + "have borrow" + book.toString());
        }
        else{System.out.println("This book is not free");}
    }

    /**
     * This method permits to return a book from a user
     * @param id of the borrow;
     */
    public void return_book(int id){
        borrowing.get(id).getBook().setStatue(TypeStatue.FREE);
        borrowing.get(id).setRestore(Boolean.TRUE);
        //Find a new ID available in history
        int newID=0;
        while(history.containsKey(newID)){
            newID+=1;
        }
        history.put(newID,borrowing.get(id));
        borrowing.remove(id);
        System.out.println("Book restored");
    }

    /**
     * This method return a list of the book with more borrow
     */
    public void getPopularBook() {
        // Establish a Hashmap with key : Book , value : number of Borrow
        for (Integer key : history.keySet()) {
            Borrow borrow = history.get(key);
            Book book = borrow.getBook();
            // Add to the hashmap the number of borrow
            nbborrowperbook.put(book, nbborrowperbook.getOrDefault(book, 0) + 1);
        }
        // Create a list and sort that list by value decreasing
        ArrayList<Map.Entry<Book, Integer>> list = new ArrayList<>(nbborrowperbook.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        System.out.println("Most popular book : ");
        for (Map.Entry<Book, Integer> entry : list) {
            System.out.println(entry.getKey() + " - Borrow number : " + entry.getValue());
        }
    }

    public HashMap<Integer, Borrow> getBorrowing() {
        return borrowing;
    }
}
