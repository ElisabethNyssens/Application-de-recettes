package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProgressBarWindow extends JFrame {
    private JPanel messagesPanel;
    private ProgressBarPanel progressBarPanel;
    private JLabel progressMessage;
    private String [] messages = { "Validation du titre de la recette", "Collecte des ingrédients", "Nettoyage du matériel", "Réalisation des étapes de préparation", "Validation par les chefs Nyssens et Hanquet" };
    private Container mainContainer;
    private boolean update;

    public ProgressBarWindow(Container mainContainer, boolean update) {
        super(update?"Progression modification recette":"Progression création recette");
        this.mainContainer = mainContainer;
        this.update = update;
        setSize(700, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1));

        // Partie avec les messages
        messagesPanel = new JPanel();
        messagesPanel.setLayout(new BorderLayout());
        add(messagesPanel);

        progressMessage = new JLabel(messages[0], SwingConstants.CENTER);
        messagesPanel.add(progressMessage, BorderLayout.SOUTH);

        // partie avec la barre de chargement
        progressBarPanel = new ProgressBarPanel(this);
        add(progressBarPanel);

        ProgressBarThread progressBarThread = new ProgressBarThread(mainContainer, progressBarPanel, this, update);
        progressBarThread.start();

        setVisible(true);
    }

    public JPanel getMessagesPanel() {
        return messagesPanel;
    }

    public void changeMessage(int iMessage) {
        progressMessage.setText(messages[iMessage]);
    }
}
