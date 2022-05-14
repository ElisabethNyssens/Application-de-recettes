package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllAuthorsException;
import exceptionPackage.AllCategoriesException;
import exceptionPackage.ConnectionException;
import modelPackage.Author;
import modelPackage.Category;
import modelPackage.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ConnectionPanel extends JPanel {
    private ApplicationController controller;
    private JPanel formPanel, buttonPanel;
    private JLabel title;
    private JComboBox pseudo;
    private JButton validation;

    public static int NB_PSEUDOS = 5;
    private final String[] pseudos = new String[NB_PSEUDOS];

    public ConnectionPanel() throws ConnectionException {
        controller = new ApplicationController();
        setLayout(new BorderLayout());

        // Titre
        title = new JLabel("<html><h1 style='margin:50px 0 30px 0'>S'identifier</h1></html>", SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        // Form
        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2));
        formPanel.setBorder(new EmptyBorder(0, 150, 0, 150));

        int iPseudo = 0;
        try {
            ArrayList<Author> authorsList = controller.getAllAuthors();
            for(Author author : authorsList) {
                pseudos[iPseudo] = author.getPseudo() + " (" + author.getFirstName() + " " + author.getLastName() + ")";
                iPseudo++;
            }
        } catch (AllAuthorsException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        pseudo = new JComboBox(pseudos);
        pseudo.setSelectedItem(pseudos[1]);
        formPanel.add(pseudo);

        add(formPanel, BorderLayout.CENTER);

        // Bouton
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBorder(new EmptyBorder(150, 0, 50, 0));

        validation = new JButton("C'est parti !");
        ValidationListener validationListener = new ValidationListener();
        validation.addActionListener(validationListener);
        buttonPanel.add(validation);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class ValidationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            WelcomeWindow frame = (WelcomeWindow) SwingUtilities.getAncestorOfClass(WelcomeWindow.class, validation);
            String selectPseudo = pseudo.getSelectedItem().toString().split(" ",2)[0];
            String firstName = pseudo.getSelectedItem().toString().split("[ )(]",4)[2];
            String lastName = pseudo.getSelectedItem().toString().split("[ )(]",5)[3];

            User user = new User(selectPseudo,firstName,lastName);
            MainWindow mainWindow = new MainWindow();

            frame.dispose();
        }
    }
}
