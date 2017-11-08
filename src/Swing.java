import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class Swing extends JFrame {

    public Swing(){
        super("BookStorage");
        setMinimumSize(new Dimension(700, 450));
        setLocation(150, 100);
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLayout( new FlowLayout());
        JPanel grid = new JPanel( new GridLayout(3, 1, 0, 8) );
        BookModel model = new BookModel();
        JTable table = new JTable(model);
        table.setPreferredSize(new Dimension(900,2000));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        TableColumnModel colModel = table.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(350);
        colModel.getColumn(1).setPreferredWidth(250);
        colModel.getColumn(2).setPreferredWidth(500);
        colModel.getColumn(3).setPreferredWidth(250);
        colModel.getColumn(4).setPreferredWidth(250);
        JScrollPane jScrollPane=new JScrollPane(table);
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton authorButton = new JButton("Author Edit");


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = JOptionPane.showInputDialog(null, "Enter table name", "BookTable", 1);
                if (fileName != null)
                    model.saveToFile(fileName + ".btab");
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator")+ "IdeaProjects" + System.getProperty("file.separator") + "Task1"));
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Book table(.btab)", "btab");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(getParent());
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    model.loadFromFile(chooser.getSelectedFile());
                    model.fireTableDataChanged();
                }


            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addBook(new Book("","", new Author("", "", ""), 0, 0));
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() > -1)
                model.removeBook(table.getSelectedRow());
            }
        });

        authorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() > -1)
                    new AuthorDialog(model.getAuthor(table.getSelectedRow()));
                else new AuthorDialog(model.getAuthor());
                model.fireTableDataChanged();
            }
        });

        add(jScrollPane);
        grid.add(saveButton);
        grid.add(loadButton);
        grid.add(addButton);
        grid.add(removeButton);
        grid.add(authorButton);
        add(grid);
        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Swing();
            }
        });
    }
}
