package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.ConnectionException;
import modelPackage.MenuComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddRecipesInMenuPanel extends JPanel {
    private static int NB_MAX_RECIPES = 10;
    private int nbRecipes;
    private JList recipesList;
    private JButton addRecipeButton;
    private MenuForm parentPanel;
    private Object[] recipesObject;
    private ArrayList<MenuComponent> recipes;
    private JPanel numberContainer, recipeContainer;
    private JLabel numberLabel, recipeLabel;
    private JSpinner recipeNumber;
    private JTextArea recipe;
    private ApplicationController controller;


    public AddRecipesInMenuPanel(MenuForm parentPanel) throws ConnectionException {
        this.parentPanel = parentPanel;
        recipesObject = new Object[NB_MAX_RECIPES];
        recipes = new ArrayList<>();
        controller = new ApplicationController();
        nbRecipes = 0;

        JPanel recipesPanel = new JPanel(new BorderLayout());

        numberContainer = new JPanel(new FlowLayout(FlowLayout.LEFT,3,3));
        numberLabel = new JLabel("Numéro* :");
        recipeNumber = new JSpinner();
        recipeNumber.setValue(nbRecipes+1);
        recipeNumber.setEnabled(false);
        numberContainer.add(numberLabel);
        numberContainer.add(recipeNumber);

        recipeContainer = new JPanel();
        recipeLabel = new JLabel("Recette* :");
        recipe = new JTextArea(6,24);
        recipe.setLineWrap(true);
        recipeContainer.add(recipeLabel);
        recipeContainer.add(new JScrollPane(recipe));

        recipesPanel.add(numberContainer, BorderLayout.NORTH);
        recipesPanel.add(recipeContainer, BorderLayout.CENTER);

        addRecipeButton = new JButton("Ajouter la recette >>");
        addRecipeButton.addActionListener(new AddRecipeListener());

        recipesList = new JList();
        recipesList.setFixedCellWidth(250);
        recipesList.setFixedCellHeight(20);
        recipesList.setVisibleRowCount(8);
        recipesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        add(recipesPanel);
        add(addRecipeButton);
        add(new JScrollPane(recipesList));
    }

    private class AddRecipeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!recipe.getText().isBlank()) {
                recipes.add(new MenuComponent(nbRecipes + 1, parentPanel.getMenuTitle(), recipe.getText()));

                recipesObject[nbRecipes] = (nbRecipes+1) + ". " + recipe.getText();
                nbRecipes++;
                recipeNumber.setValue(nbRecipes+1);
                recipesList.setListData(recipesObject);
                recipe.setText(null);
                AddRecipesInMenuPanel.this.repaint();
            }
        }
    }
}
