import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthorDialog extends JFrame
{
    private static final long serialVersionUID = 1L;
    public AuthorDialog(Author author) {
        super("Author Dialog");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        JPanel grid = new JPanel( new GridLayout(3, 3, 8, 4) );
        JLabel label = new JLabel(author.toString());
        JPanel contents = new JPanel();
        JLabel l1 = new JLabel("set name");
        JLabel l2 = new JLabel("set email");
        JLabel l3 = new JLabel("set gender");
        JTextField tf1 = new JTextField(author.getName());
        JTextField tf2 = new JTextField(author.getEmail());
        JTextField tf3 = new JTextField(author.getGender());
        JButton confirm = new JButton("Confirm author");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                author.setName(tf1.getText());
                author.setEmail(tf2.getText());
                author.setGender(tf3.getText());
                label.setText(author.toString());
            }
        });

        contents.add(label);
        grid.add(l1);
        grid.add(l2);
        grid.add(l3);
        grid.add(tf1);
        grid.add(tf2);
        grid.add(tf3);
        grid.add(confirm);
        contents.add(grid);
        setContentPane(contents);
        setSize(550, 180);
        setLocation(300, 300);
        setVisible(true);
    }

}