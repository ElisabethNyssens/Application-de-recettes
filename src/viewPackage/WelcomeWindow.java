package viewPackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class WelcomeWindow extends JFrame {
    private Container mainContainer;
    private FoodAnimPanel foodAnimPanel;
    private JPanel welcomePanel, buttonsPanel;
    private JLabel welcomeLabel;
    private JButton registrationButton, connectionButton;

    private String welcomeMessage = "<html><div style='border:1px solid black; padding:5px 70px; text-align:center; background-color:#f7f7f7'>" +
            "<p style='font-size:20px; font-weight:normal; margin-bottom:0px'>Bienvenue dans</p>" +
            "<h2 style='font-size:22px; font-weight:bold; margin-top:0px'>TaCuisine</h2>" +
            "</div></html>";


    public WelcomeWindow() {
        // Création de la fenêtre
        super("TaCuisine");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("img/chef.png").getImage());
        setLocationRelativeTo(null);

        // Conteneur principal
        mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout());

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

        // Création du panel message
        welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        welcomePanel.setSize(1200, 770);
        welcomePanel.setOpaque(false);

        // Message d'accueil
        welcomeLabel = new JLabel(welcomeMessage, SwingConstants.CENTER);
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);
        mainContainer.add(welcomePanel, BorderLayout.CENTER);

        // Food animation
        foodAnimPanel = new FoodAnimPanel(this);
        mainContainer.add(foodAnimPanel, BorderLayout.CENTER);
        WelcomeThread movFood = new WelcomeThread(this);
        movFood.start();

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


    public Container getMainContainer() {
        return mainContainer;
    }

    public FoodAnimPanel getFoodAnimPanel() {
        return foodAnimPanel;
    }
}
