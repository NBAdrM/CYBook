import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * This class permits to manage all borrow
 */
public class BorrowManager {

    HashMap<Integer,Borrow> borrowing;
    HashMap<Integer,Borrow> history;

    public BorrowManager() {
        this.borrowing = new HashMap<>();
        this.history = new HashMap<>();
    }

    /**
     * This method permits to borrow a book for a specific client
     *
     * @param book;
     * @param client;
     * @throws ParseException Error of date format;
     */
    public void borrow_book(Book book,Client client) throws ParseException {

        if(book.getStatue().equals(TypeStatue.FREE)){
            //Find new ID available in borrowing
            int newID=0;
            while(borrowing.containsKey(newID)){
                newID+=1;
            }

            Borrow borrow = new Borrow(newID,client,LocalDate.now().toString(),book);
            borrowing.put(newID,borrow);
            System.out.println(client.toString() + "have borrow" + book.toString());
        }
        else{System.out.println("This book is not free");}
    }

    /**
     * This method permits to return a book from a client
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

    public void getPopularBook(){

    }
}
