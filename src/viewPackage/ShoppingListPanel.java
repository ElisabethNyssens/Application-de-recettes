package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllIngredQuantitiesException;
import exceptionPackage.AllIngredientsException;
import exceptionPackage.AllRecipesException;
import exceptionPackage.ConnectionException;
import modelPackage.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ShoppingListPanel extends JPanel {
    private ApplicationController controller;
    private JLabel title;
    private JPanel form, centerPanel, shopListPanel;
    private JButton addIngredientBtn, removeIngredBtn, submitBtn;

    private static int NB_MAX_RECIPES = 50;
    private static int NB_MAX_INGRED = 85;
    private String[] recipesValues; // pour dans JComboBox
    private String[] selectedRecipes, ingredList; // pour dans JList
    private int nbSelectedRecipes;

    private JLabel recipeLabel, NbPersLabel;
    private JComboBox recipes;
    private JSpinner nbPersons;
    private JList<String> selectedRecipesList, shopList;
    private ArrayList<ShopListRecipe> shopListRecipes;

    public ShoppingListPanel() throws ConnectionException {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 150, 50, 150));

        title = new JLabel("<html><h1 style='margin: 30px 0; font-size: 24px;'>Créer une liste de courses</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        controller = new ApplicationController();

        shopListRecipes = new ArrayList<>();
        recipesValues = new String[NB_MAX_RECIPES];
        selectedRecipes = new String[NB_MAX_RECIPES];
        nbSelectedRecipes = 0;

        try {
            ArrayList<Recipe> recipes = controller.getAllRecipes();

            int iRecipe = 0;
            for(Recipe recipe : recipes) {
                recipesValues[iRecipe] = recipe.getTitle();
                iRecipe++;
            }
        } catch (AllRecipesException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }

        JPanel recipePanel = new JPanel();
        recipePanel.setLayout(new GridLayout(2, 1));
        JPanel recip = new JPanel();
        recipeLabel = new JLabel("Recette* :");
        recipes = new JComboBox(recipesValues);
        recipes.setSelectedItem(null);
        recipes.setMaximumRowCount(6);
        JPanel pers = new JPanel();
        pers.setLayout(new FlowLayout(FlowLayout.LEFT,3,3));
        NbPersLabel = new JLabel("Nombre de personnes* :");
        nbPersons = new JSpinner(new SpinnerNumberModel(4, 1, 50, 1));
        recip.add(recipeLabel);
        recip.add(recipes);
        pers.add(NbPersLabel);
        pers.add(nbPersons);
        recipePanel.add(recip);
        recipePanel.add(pers);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(2, 1));
        addIngredientBtn = new JButton("Ajouter la recette >>");
        removeIngredBtn = new JButton("<< Retirer la recette");
        addIngredientBtn.addActionListener(new AddButtonListener());
        removeIngredBtn.addActionListener(new RemoveButtonListener());
        btnPanel.add(addIngredientBtn);
        btnPanel.add(removeIngredBtn);

        selectedRecipesList = new JList();
        selectedRecipesList.setFixedCellWidth(300);
        selectedRecipesList.setFixedCellHeight(20);
        selectedRecipesList.setVisibleRowCount(8);
        selectedRecipesList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        submitBtn = new JButton("Valider");
        submitBtn.addActionListener(new SubmitListener());

        form = new JPanel();
        form.add(recipePanel);
        form.add(btnPanel);
        form.add(new JScrollPane(selectedRecipesList));
        form.add(submitBtn);

        shopListPanel = new JPanel();
        shopListPanel.setLayout(new BorderLayout());
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2,1));
        centerPanel.add(form);
        centerPanel.add(shopListPanel);

        this.add(title, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        //this.add(submitBtn, BorderLayout.SOUTH);
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed( ActionEvent event) {
            int convertedNbPers = Integer.parseInt(nbPersons.getValue().toString());
            String selectedRecipe = recipes.getSelectedItem().toString();

            ShopListRecipe duplicate = shopListRecipes.stream().filter(x ->
                    x.getRecipe().equals(selectedRecipe)).findFirst().orElse(null);

            if (duplicate == null) {
                shopListRecipes.add(new ShopListRecipe(selectedRecipe, convertedNbPers));
                selectedRecipes[nbSelectedRecipes] = selectedRecipe + " - " + convertedNbPers + " pers.";
                nbSelectedRecipes++;

                selectedRecipesList.setListData(selectedRecipes);
                ShoppingListPanel.this.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Cette recette est déjà dans la liste !");
            }
        }
    }

    private class RemoveButtonListener implements ActionListener {
        public void actionPerformed( ActionEvent event) {
            if (selectedRecipesList.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(null, "Sélectionne une recette !");
            } else {
                int iSelectRecipe = selectedRecipesList.getSelectedIndex();
                shopListRecipes.remove(iSelectRecipe);
                for (int i = iSelectRecipe; i < nbSelectedRecipes; i++) {
                    selectedRecipes[i] = selectedRecipes[i+1];
                }
                nbSelectedRecipes--;
                selectedRecipesList.setListData(selectedRecipes);
            }
        }
    }

    private class SubmitListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (!shopListRecipes.isEmpty()) {
                try {
                    ArrayList<ShopListIngred> shopListIngreds = controller.shoppingList(shopListRecipes);
                    DecimalFormat df = new DecimalFormat("###.#");

                    shopListPanel.removeAll();
                    shopList = new JList();
                    shopList.setFixedCellWidth(400);
                    shopList.setFixedCellHeight(20);
                    shopList.setVisibleRowCount(12);
                    ingredList = new String[NB_MAX_INGRED];

                    int nbIngred = 0;
                    for (ShopListIngred shopListIngred : shopListIngreds) {
                        String unit = shopListIngred.getUnit();
                        ingredList[nbIngred] = " - " + df.format(shopListIngred.getQuantity()) + " " + (unit.equals("unite")?"":unit+" ") + shopListIngred.getIngred();
                        nbIngred++;
                    }
                    shopList.setListData(ingredList);
                    shopListPanel.add(new JLabel("<html><p style='font-size:16px; margin-bottom: 5px'>Liste de courses :</p></html>"), BorderLayout.NORTH);
                    shopListPanel.add(new JScrollPane(shopList), BorderLayout.CENTER);
                    shopListPanel.revalidate();
                    shopListPanel.repaint();
                } catch (AllIngredientsException | AllRecipesException | AllIngredQuantitiesException e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Sélectionne au moins une recette !","Attention",JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
