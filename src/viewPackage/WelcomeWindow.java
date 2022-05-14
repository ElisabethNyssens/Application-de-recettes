package viewPackage;

import exceptionPackage.ConnectionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeWindow extends JFrame {
    private Container mainContainer;
    private FoodAnimPanel foodAnimPanel;
    private JPanel buttonsPanel;
    private JLayeredPane welcomeLayPane;
    private JLabel welcomeLabel;
    private JButton registrationButton, connectionButton;

    private String welcomeMessage = "<html><div style='border:1px solid black; padding:15px 70px; text-align:center; background-color:#f7f7f7; display:block;'>" +
            "<p style='font-size:20px; font-weight:normal; margin-bottom:0px'>Bienvenue dans</p>" +
            "<h2 style='font-size:22px; font-weight:bold; margin:0px; color:#97002d'>TaCuisine</h2>" +
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
        registrationButton.setEnabled(false);
        registrationButton.addActionListener(registrationListener);
        buttonsPanel.add(registrationButton);

        connectionButton = new JButton("Connexion");
        ConnectionListener connectionListener = new ConnectionListener();
        connectionButton.addActionListener(connectionListener);
        buttonsPanel.add(connectionButton);

        // Création du panel message
        welcomeLayPane = new JLayeredPane();
        welcomeLayPane.setLayout(new BorderLayout());
        mainContainer.add(welcomeLayPane, BorderLayout.CENTER);

        // Message d'accueil
        welcomeLabel = new JLabel(welcomeMessage, SwingConstants.CENTER);
        welcomeLabel.setSize(this.getWidth(), this.getHeight()-30);

        // Food animation
        foodAnimPanel = new FoodAnimPanel(this);
        welcomeLayPane.add(foodAnimPanel);
        welcomeLayPane.add(welcomeLabel);
        welcomeLayPane.moveToFront(welcomeLabel);
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
            try {
                changePanel(new ConnectionPanel());
            } catch (ConnectionException exception) {
                System.out.println(exception.getMessage());
            }
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
