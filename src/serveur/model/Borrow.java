package serveur.model;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Borrow {
    private int id;
    private Client client;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private Boolean restore=Boolean.FALSE;
    private Book book;

    /**
     * This constructor check if the format of borrowDate is "dd/MM/yyyy" and affect the returnDate 1 month after
     *
     * @param id;
     * @param client;
     * @param borrowDate;
     * @param book;
     * @throws ParseException Error of date format
     */
    public Borrow(int id, Client client, String borrowDate, Book book) throws ParseException {
        this.id = id;
        this.client = client;
        this.book = book;

        // Force the format of borrowDate to "yyyy-MM-dd"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            this.borrowDate = LocalDate.parse(borrowDate, formatter);
        } catch (Exception e) {
            throw new ParseException("The format needs to be 'year-mouth-day'", 0);
        }

        // Force the returnDate 1 month after the borrow
        this.returnDate = this.borrowDate.plusMonths(1);
    }


    //Getter
    public int getId() {
        return id;
    }
    public Client getClient() {
        return client;
    }
    public LocalDate getBorrowDate() {
        return borrowDate;
    }
    public Book getBook() {
        return book;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public Boolean getRestore(){
        return restore;
    }

    //Setter
    public void setClient(Client client) {
        this.client = client;
    }
    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public void setId(int id) {
    this.id = id;
    }
    public void setRestore(Boolean restore){
        this.restore=restore;
    }
}
