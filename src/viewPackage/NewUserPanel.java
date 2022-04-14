package viewPackage;

import javax.swing.*;
import java.awt.*;

public class NewUserPanel extends JPanel {
    private JPanel innerPanel;
    private JLabel test;

    public NewUserPanel() {
        innerPanel = new JPanel();
        innerPanel.setBounds(200, 100, 500, 400);
        innerPanel.setBackground(Color.blue);
        add(innerPanel);


        test = new JLabel("Inscription");
        innerPanel.add(test);


    }
}
