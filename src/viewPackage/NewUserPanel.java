package viewPackage;

import exceptionPackage.ConnectionException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewUserPanel extends JPanel {
    private JPanel formPanel, buttonPanel;
    private JLabel title, pseudoLabel, firstNameLabel, lastNameLabel;
    private JTextField pseudoField, firstNameField, lastNameField;
    private JButton validation;

    public NewUserPanel() {
        setLayout(new BorderLayout());

        // Titre
        title = new JLabel("<html><h1 style='margin:50px 0 30px 0'>Créer un compte</h1></html>", SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        // Form
        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2));
        formPanel.setBorder(new EmptyBorder(0, 150, 0, 150));

        pseudoLabel = new JLabel("Pseudo :");
        pseudoField = new JTextField();
        formPanel.add(pseudoLabel);
        formPanel.add(pseudoField);

        firstNameLabel = new JLabel("Prénom :");
        firstNameField = new JTextField();
        formPanel.add(firstNameLabel);
        formPanel.add(firstNameField);

        lastNameLabel = new JLabel("Nom :");
        lastNameField = new JTextField();
        formPanel.add(lastNameLabel);
        formPanel.add(lastNameField);

        add(formPanel, BorderLayout.CENTER);

        // Bouton
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBorder(new EmptyBorder(150, 0, 50, 0));

        validation = new JButton("Valider");
        ValidationListener validationListener = new ValidationListener();
        validation.addActionListener(validationListener);
        buttonPanel.add(validation);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class ValidationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            WelcomeWindow frame = (WelcomeWindow) SwingUtilities.getAncestorOfClass(WelcomeWindow.class, validation);
            try {
                frame.changePanel(new ConnectionPanel());
            } catch (ConnectionException exception) {
                exception.printStackTrace();
            }
        }
    }
}
