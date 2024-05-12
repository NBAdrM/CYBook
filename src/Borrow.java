import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * This class creates book borrowings
 **/
public class Borrow {
    private int id;
    private Client client;
    private Date borrowDate;
    private Date returnDate;
    private Book book;

    /**
     * This constructor check if the format of borrowDate is "dd/MM/year" and affect the returnDate 1 month after
     *
     * @param id;
     * @param client;
     * @param borrowDate;
     * @param book;
     * @throws ParseException
     */
    public Borrow(int id, Client client, String borrowDate, Book book) throws ParseException {
        this.id = id;
        this.client = client;
        this.book = book;

        //Force the format of borrowDate to "dd/MM/year"
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            this.borrowDate = sdf.parse(borrowDate);
        } catch (ParseException e) {
            throw new ParseException("The format need to be 'day/month/year'", 0);
        }

        //Force the returnDate 1 month after the borrow
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.borrowDate);
        calendar.add(Calendar.MONTH, 1);
        this.returnDate = calendar.getTime();
    }

    //Getter
    public int getId() {
        return id;
    }
    public Client getClient() {
        return client;
    }
    public Date getBorrowDate() {
        return borrowDate;
    }
    public Book getBook() {
        return book;
    }
    public Date getReturnDate() {
        return returnDate;
    }

    //Setter
    public void setClient(Client client) {
        this.client = client;
    }
    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public void setId(int id) {
    this.id = id;
    }
}
