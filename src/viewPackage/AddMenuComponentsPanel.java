package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllRecipesException;
import exceptionPackage.ConnectionException;
import modelPackage.MenuComponent;
import modelPackage.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AddMenuComponentsPanel extends JPanel {
    private static int NB_MAX_RECIPES = 100;
    private int nbRecipes;
    private JList chosenRecipesList;
    private JButton addRecipeButton, removeRecipeButton;
    private MenuCreationForm parentPanel;
    private Object[] selectedRecipes;
    private ArrayList<MenuComponent> recipes;
    private JPanel recipePanel, btnPanel;
    private JLabel recipeLabel;
    private JComboBox recipe;
    private ApplicationController controller;
    private String[] recipesValues = new String[NB_MAX_RECIPES];


    public AddMenuComponentsPanel(MenuCreationForm parentPanel) throws ConnectionException {
        this.parentPanel = parentPanel;
        selectedRecipes = new Object[NB_MAX_RECIPES];
        recipes = new ArrayList<>();
        controller = new ApplicationController();
        nbRecipes = 0;

        // Récupération des noms de recettes
        try {
            ArrayList<Recipe> recipesList = controller.getAllRecipes();

            int iRecipe = 0;
            for (Recipe recipe : recipesList) {
                recipesValues[iRecipe] = recipe.getTitle();
                iRecipe++;
            }
        } catch (AllRecipesException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }

        recipePanel = new JPanel();
        recipeLabel = new JLabel("Recette* :");
        recipe = new JComboBox(recipesValues);
        recipe.setSelectedItem(null);
        recipe.setMaximumRowCount(6);
        recipePanel.add(recipeLabel);
        recipePanel.add(recipe);

        btnPanel = new JPanel(new GridLayout(2,1));
        addRecipeButton = new JButton("Ajouter la recette >>");
        addRecipeButton.addActionListener(new AddRecipeListener());
        removeRecipeButton = new JButton("<< Retirer la recette");
        removeRecipeButton.addActionListener(new RemoveRecipeListener());
        btnPanel.add(addRecipeButton);
        btnPanel.add(removeRecipeButton);

        chosenRecipesList = new JList();
        chosenRecipesList.setFixedCellWidth(250);
        chosenRecipesList.setFixedCellHeight(20);
        chosenRecipesList.setVisibleRowCount(8);
        chosenRecipesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        add(recipePanel);
        add(btnPanel);
        add(new JScrollPane(chosenRecipesList));
    }

    private class AddRecipeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<MenuComponent> duplicate = recipes.stream().filter(x -> x.getRecipeId().equals(recipe.getSelectedItem().toString())).collect(toList());

            if (recipe.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Sélectionne une recette");
            } else if (!duplicate.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Cette recette a déjà été choisie");
            } else {
                recipes.add(new MenuComponent(nbRecipes + 1, parentPanel.getMenuTitle(), recipe.getSelectedItem().toString()));

                selectedRecipes[nbRecipes] = recipe.getSelectedItem().toString();
                nbRecipes++;
                chosenRecipesList.setListData(selectedRecipes);
                AddMenuComponentsPanel.this.repaint();
            }
        }
    }
    private class RemoveRecipeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (chosenRecipesList.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(null, "Sélectionnez une recette !");
            } else {
                int iSelectedRecipe = chosenRecipesList.getSelectedIndex();
                recipes.remove(iSelectedRecipe);
                for (int i = iSelectedRecipe; i < nbRecipes; i++) {
                    if (i == nbRecipes-1) {
                        selectedRecipes[i] = null;
                    } else {
                        selectedRecipes[i] = selectedRecipes[i+1];
                        recipes.get(i).setOrderNumber(i+1);
                    }
                }
                /*for (int i = 0; i < recipes.size(); i++) {
                    recipes.get(i).setOrderNumber(i+1);
                }*/
                nbRecipes--;
                chosenRecipesList.setListData(selectedRecipes);
                AddMenuComponentsPanel.this.repaint();
            }
        }
    }

    public ArrayList<MenuComponent> getMenuComponents() {
        return recipes;
    }
}
