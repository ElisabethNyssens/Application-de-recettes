package viewPackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuForm extends JPanel {
    private JPanel menuFormPanel, formPanel1, formPanel2, bottomPanel;
    private JLabel titleLabel, commentLabel;
    private JTextField titleField;
    private JTextArea commentArea;
    private JButton prevStepBtn, nextStepBtn;
    private int activeForm = 1;

    public MenuForm() {
        menuFormPanel = this;
        this.setLayout(new BorderLayout());

        //-----------------------------> Création du premier panel formulaire (ajout des recettes)
        formPanel1 = new JPanel();
        formPanel1.setBorder(new EmptyBorder(0, 150, 0, 150));
        formPanel1.setLayout(new GridLayout(3,2));

        //-----------------------------> Création du deuxième panel formulaire (ajout du titre et du commentaire)
        formPanel2 = new JPanel();
        formPanel2.setBorder(new EmptyBorder(0, 150, 200, 150));
        formPanel2.setLayout(new GridLayout(2,2));

        // ------ Title -------
        titleLabel = new JLabel("Titre du menu (*) :");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel2.add(titleLabel);
        titleField = new JTextField();
        formPanel2.add(titleField);

        // ------ Comment -------
        commentLabel = new JLabel("Commentaires :");
        commentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel2.add(commentLabel);
        commentArea = new JTextArea();
        formPanel2.add(commentArea);

        //-----------------------------> Création du panel boutons
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,2));

        prevStepBtn = new JButton("Retour");
        nextStepBtn = new JButton("Suivant");

        bottomPanel.add(prevStepBtn);
        bottomPanel.add(nextStepBtn);
        prevStepBtn.setVisible(false);

        prevStepBtn.addActionListener(new PreviousStepListener());
        nextStepBtn.addActionListener(new NextStepListener());

        //-----------------------------> Ajout des panels
        add(formPanel1, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    private class PreviousStepListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (activeForm == 2) {
                activeForm--;
                prevStepBtn.setVisible(false);
                nextStepBtn.setText("Suivant");

                remove(formPanel2);
                add(formPanel1, BorderLayout.CENTER);
            }
        }
    }

    private class NextStepListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (activeForm == 1) {
                // Modifs boutons
                activeForm++;
                prevStepBtn.setVisible(true);
                nextStepBtn.setText("Valider");

                // Modifs Panel
                remove(formPanel1);
                add(formPanel2, BorderLayout.CENTER);
            } else {
                JOptionPane.showMessageDialog(null, "Le menu a été créé", "Validation", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}

/*
Pour créer un menu il nous faut :
-> Des recettes
-> Un nom de Menu
-> [Un commentaire]
 */