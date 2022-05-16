package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllRecipesException;
import exceptionPackage.ConnectionException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuForm extends JPanel {
    private ApplicationController controller;
    private JPanel formPanel1, formPanel2, bottomPanel;
    private JList recipesList, chosenRecipesList;

    private JLabel titleLabel, commentLabel;
    private JTextField titleField;
    private JTextArea commentArea;
    private JButton selectRecipes, prevStepBtn, nextStepBtn;
    private int activeForm = 1;

    public MenuForm() throws ConnectionException {
        this.setLayout(new BorderLayout());
        controller = new ApplicationController();

        // Récupération liste recettes pour tester que le titre n'est pas déjà pris
        /*
        try {
            allRecipes = controller.getAllRecipes();
        } catch (AllRecipesException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }

         */

        //-----------------------------> Création du premier panel formulaire (ajout des recettes)
        formPanel1 = new JPanel();
        formPanel1.setBorder(new EmptyBorder(0, 150, 50, 150));
        formPanel1.setLayout(new FlowLayout());

        // ------ Recipes -------
        String[] recipes = { "Curry au tofu", "Salade fraises menthe", "Oeuf cocotte", "Pate à crêpes" };
        recipesList = new JList(recipes);
        recipesList.setVisibleRowCount(10);
        recipesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        chosenRecipesList = new JList();
        chosenRecipesList.setVisibleRowCount(5);
        chosenRecipesList.setFixedCellWidth(150);
        chosenRecipesList.setFixedCellHeight(15);
        chosenRecipesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectRecipes = new JButton("COPIER >>>");
        selectRecipes.addActionListener(new SelectRecipesListener());

        formPanel1.add(new JScrollPane(recipesList));
        formPanel1.add(selectRecipes);
        formPanel1.add(new JScrollPane(chosenRecipesList));

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

    private class SelectRecipesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] selectedRecipes = new Object[10];
            int i = 0;
            for (Object recipe : recipesList.getSelectedValuesList()) {
                selectedRecipes[i] = recipe;
                i++;
            }
            chosenRecipesList.setListData(selectedRecipes);

            formPanel1.repaint();
        }
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

                revalidate();
                repaint();
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

                revalidate();
                repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Le menu a été créé", "Validation", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
