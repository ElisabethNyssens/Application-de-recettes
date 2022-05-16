package viewPackage;

import exceptionPackage.ConnectionException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuCreationPanel extends JPanel {
    private JLabel title;

    private MenuForm menuForm;

    public MenuCreationPanel() throws ConnectionException {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 40, 40, 40));
        title = new JLabel("<html><h1 style='margin: 30px 0 15px 0'>Concevoir un Menu</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        menuForm = new MenuForm();

        this.add(title, BorderLayout.NORTH);
        this.add(menuForm, BorderLayout.CENTER);
    }
}
