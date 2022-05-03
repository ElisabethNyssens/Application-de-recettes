package viewPackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectionPanel extends JPanel {
    private JPanel formPanel, buttonPanel;
    private JLabel title;
    private JComboBox pseudosBox;
    private JButton validation;

    private final String[] pseudos = {"Bichon", "Marvin", "Prof", "Anonyme1", "Anonyme2"};

    public ConnectionPanel() {
        setLayout(new BorderLayout());

        // Titre
        title = new JLabel("<html><h1 style='margin:50px 0 30px 0'>S'identifier</h1></html>", SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        // Form
        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2));
        formPanel.setBorder(new EmptyBorder(0, 150, 0, 150));

        pseudosBox = new JComboBox(pseudos);
        pseudosBox.setSelectedItem(pseudos[0]);
        formPanel.add(pseudosBox);

        add(formPanel, BorderLayout.CENTER);

        // Bouton
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBorder(new EmptyBorder(150, 0, 50, 0));

        validation = new JButton("Validation");
        ValidationListener validationListener = new ValidationListener();
        validation.addActionListener(validationListener);
        buttonPanel.add(validation);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class ValidationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("C'est parti !");
        }
    }
}
