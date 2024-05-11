/**
 * This class define books
 */
public class Book {
    private int ISBN;
    private TypeStatue statue;
    private String editor;

    public Book(int ISBN, TypeStatue statue, String editor) {
        this.ISBN = ISBN;
        this.statue = statue;
        this.editor = editor;
    }
    //Getter
    public int getISBN() {
        return ISBN;
    }
    public TypeStatue getStatue() {
        return statue;
    }
    public String getEditor() {
        return editor;
    }

    //Setter
    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }
    public void setStatue(TypeStatue statue) {
        this.statue = statue;
    }
    public void setEditor(String editor) {
        this.editor = editor;
    }
}
