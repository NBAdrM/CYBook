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

    /**
     * This method permits to borrow a book for a specific client
     *
     * @param book;
     * @param client;
     * @throws ParseException Error of date format;
     */
    public void borrow_book(Book book,Client client) throws ParseException {

        if(book.getStatue().equals(TypeStatue.FREE)){

        //Find a new ID
        int max=0;
        for(Map.Entry<Integer,Borrow> entry : borrowing.entrySet()){
            if(entry.getKey()>max){
                max=entry.getKey();
            }
        }
        int newID=max+1;

        Borrow borrow = new Borrow(newID,client,LocalDate.now().toString(),book);
        borrowing.put(newID,borrow);
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
        history.put(id,borrowing.get(id));
        borrowing.remove(id);
        System.out.println("Book restored");
    }

    public void getPopularBook(){

    }
}
