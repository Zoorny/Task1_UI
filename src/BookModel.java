import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookModel extends AbstractTableModel {

    private List<Book> books = new ArrayList<>();

    public void addBook(Book b){
        books.add(b);
        fireTableDataChanged();
    }

    public void removeBook(int index){
            books.remove(index);
        fireTableDataChanged();
    }

    public Author getAuthor(int index){
        return books.get(index).getAuthor();
    }

    public Author getAuthor(){
        return new Author("default", "default", "default");
    }

    public void setValueAt(Object value,int rowIndex, int columnIndex){
        Book cur = books.get(rowIndex);
        switch (columnIndex) {
            case 0:
                cur.setName(value.toString());
                break;
            case 1:
                cur.setGenre(value.toString());
                break;
            case 2:
                cur.setAuthor((Author) value);
                break;
            case 3:
                cur.setPrice((double) value);
                break;
            case 4:
                cur.setQty((int) value);
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book cur = books.get(rowIndex);
        switch (columnIndex){
            case 0:
                return cur.getName();
            case 1:
                return cur.getGenre();
            case 2:
                return cur.getAuthor().getName();
            case 3:
                return cur.getPrice();
            case 4:
                return cur.getQty();
        }
        return null;
    }

    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Book name";
            case 1:
                return "Genre";
            case 2:
                return "Author";
            case 3:
                return "Price";
            case 4:
                return "Count";
        }
        return "";
    }

    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Author.class;
            case 3:
                return Double.class;
            case 4:
                return Integer.class;
        }
        return Object.class;
    }



    public void saveToFile(String name){
        try(ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(name))){
            oos.writeObject(books);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void loadFromFile(File file){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)))
        {
            books = (ArrayList) ois.readObject();
        }catch(IOException ioe){
            ioe.printStackTrace();
            return;
        }catch(ClassNotFoundException c){
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }
}
