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
    private final Container mainContainer;
    private final JPanel welcomePanel, buttonsPanel;
    private final JLabel welcomeLabel;
    private final JButton registrationButton, connectionButton;

    private String welcomeMessage = "<html><div style='border:1px solid black; padding:5px 70px; text-align:center;'>" +
            "<p style='font-size:20px; font-weight:normal; margin-bottom:0px'>Bienvenue dans</p>" +
            "<h2 style='font-size:22px; font-weight:bold; margin-top:0px'>TaCuisine</h2>" +
            "</div></html>";


    public WelcomeWindow() {
        // Création de la fenêtre
        super("TaCuisine");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("img/chef.png").getImage());
        setLocationRelativeTo(null);

        // Conteneur principal
        mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        // Création du panel message
        welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        mainContainer.add(welcomePanel, BorderLayout.CENTER);

        // Message d'accueil
        welcomeLabel = new JLabel(welcomeMessage, SwingConstants.CENTER);
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
            changePanel(new NewUserPanel());
        }
    }

    private class ConnectionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            changePanel(new ConnectionPanel());
        }
    }

    public void changePanel(JPanel panel) {
        mainContainer.removeAll();
        mainContainer.add(panel);
        setVisible(true);
    }
}
