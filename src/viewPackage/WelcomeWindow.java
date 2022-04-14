package viewPackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;

public class WelcomeWindow extends JFrame {
    private Container mainContainer;
    private JPanel welcomePanel, buttonsPanel;
    private JLabel welcomeLabel;
    private JButton registrationButton, connectionButton;

    public WelcomeWindow() {
        // Création de la fenêtre
        super("TaCuisine");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("Application-de-recettes/img/chef.png").getImage());

        // Conteneur principal
        mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        // Création du panel message
        welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        mainContainer.add(welcomePanel, BorderLayout.CENTER);
        welcomePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2,true));

        // Message d'accueil
        welcomeLabel = new JLabel("<html><p>Bienvenue dans<br>TaCuisine</p></html>", SwingConstants.CENTER);
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        // Panel des boutons
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout());
        mainContainer.add(buttonsPanel, BorderLayout.SOUTH);

        registrationButton = new JButton("Inscription");
        RegistrationListener registrationListener = new RegistrationListener();
        registrationButton.addActionListener(registrationListener);
        buttonsPanel.add(registrationButton);

        connectionButton = new JButton("Connexion");
        ConnectionListener connectionListener = new ConnectionListener();
        connectionButton.addActionListener(connectionListener);
        buttonsPanel.add(connectionButton);

        setVisible(true);
    }

    private class RegistrationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainContainer.removeAll();
            mainContainer.add(new NewUserPanel());
            setVisible(true);
        }
    }

    private class ConnectionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainContainer.removeAll();
            mainContainer.add(new ConnectionPanel());
            setVisible(true);
        }
    }
}
