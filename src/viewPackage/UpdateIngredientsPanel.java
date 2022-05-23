package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllIngredQuantitiesException;
import exceptionPackage.AllIngredientsException;
import exceptionPackage.ConnectionException;
import exceptionPackage.CountException;
import modelPackage.Ingredient;
import modelPackage.IngredientQuantity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class UpdateIngredientsPanel extends JPanel {
    private ApplicationController controller;
    private int nbIngredients;
    private String[] ingredientsValues = new String[nbIngredients];
    private Object[] selectedIngredients;
    private int nbSelectedIngred;

    private JLabel ingredientLabel, quantityLabel;
    private JComboBox ingredient;
    private JSpinner quantity;
    private JButton addIngredientBtn, removeIngredBtn;
    private JList selectedIngredList;
    private ArrayList<IngredientQuantity> ingredientQuantities;
    private ArrayList<Ingredient> ingredList;
    private RecipeUpdateForm parentPanel;
    private String recipeName;

    public UpdateIngredientsPanel(RecipeUpdateForm parentPanel, String recipeName) throws ConnectionException {
        this.parentPanel = parentPanel;
        this.recipeName = recipeName;
        controller = new ApplicationController();

        try {
            ingredList = controller.getAllIngredients();
            nbIngredients = controller.getElementNumber("ingredients");

            int iIngred = 0;
            for(Ingredient ingredient : ingredList) {
                ingredientsValues[iIngred] = ingredient.getName() + (ingredient.getUnit().equals("unite")?"":(" (" + ingredient.getUnit() + ")"));
                iIngred++;
            }

        } catch (AllIngredientsException | CountException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }

        JPanel ingredPanel = new JPanel();
        ingredPanel.setLayout(new GridLayout(2, 1));
        JPanel ingred = new JPanel();
        ingredientLabel = new JLabel("Ingrédient* :");
        ingredient = new JComboBox(ingredientsValues);
        ingredient.setSelectedItem(null);
        ingredient.setMaximumRowCount(6);
        JPanel quant = new JPanel();
        quant.setLayout(new FlowLayout(FlowLayout.LEFT,3,3));
        quantityLabel = new JLabel("Quantité* :");
        quantity = new JSpinner(new SpinnerNumberModel(1, 0.5, 10000, 0.5));
        ingred.add(ingredientLabel);
        ingred.add(ingredient);
        quant.add(quantityLabel);
        quant.add(quantity);
        ingredPanel.add(ingred);
        ingredPanel.add(quant);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(2, 1));
        addIngredientBtn = new JButton("Ajouter l'ingrédient >>");
        removeIngredBtn = new JButton("<< Retirer l'ingrédient");
        addIngredientBtn.addActionListener(new AddButtonListener());
        removeIngredBtn.addActionListener(new RemoveButtonListener());
        btnPanel.add(addIngredientBtn);
        btnPanel.add(removeIngredBtn);

        selectedIngredList = new JList();
        selectedIngredList.setFixedCellWidth(250);
        selectedIngredList.setFixedCellHeight(20);
        selectedIngredList.setVisibleRowCount(8);
        selectedIngredList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        selectedIngredients = new Object[nbIngredients];
        nbSelectedIngred = 0;
        try {
            ingredientQuantities = controller.getAllIngredientsOfRecipe(recipeName);

            for(IngredientQuantity ingredientQuantity : ingredientQuantities) {
                double convertedQuantity = ingredientQuantity.getQuantity();
                DecimalFormat df = new DecimalFormat("###.#");
                String unit = ingredList.stream().filter(
                        i -> i.getName().equals(ingredientQuantity.getIngredient())).findFirst().orElse(null).getUnit();
                selectedIngredients[nbSelectedIngred] = df.format(convertedQuantity) + " " + (unit.equals("unite")?"":unit+" ") + ingredientQuantity.getIngredient();
                nbSelectedIngred++;
            }
            selectedIngredList.setListData(selectedIngredients);
            UpdateIngredientsPanel.this.repaint();

        } catch (AllIngredQuantitiesException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }

        this.add(ingredPanel);
        this.add(btnPanel);
        this.add(new JScrollPane(selectedIngredList));
    }

    public ArrayList<IngredientQuantity> getIngredientQuantities() {
        return ingredientQuantities;
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed( ActionEvent event) {
            double convertedQuantity = Double.parseDouble(quantity.getValue().toString());
            DecimalFormat df = new DecimalFormat("###.#");
            String[] ingredAndUnit = ingredient.getSelectedItem().toString().split("[)(]", 3);

            String selectedIngredient = ingredAndUnit[0];
            if (Character.compare(selectedIngredient.charAt(selectedIngredient.length()-1), ' ') == 0) {
                selectedIngredient = selectedIngredient.substring(0, selectedIngredient.length()-1);
            }
            String unit = ingredAndUnit.length > 1 ? (ingredAndUnit[1] + " ") : "";

            String finalSelectedIngredient = selectedIngredient;
            List<IngredientQuantity> duplicate = ingredientQuantities.stream().filter(x ->
                    x.getIngredient().equals(finalSelectedIngredient)).collect(toList());

            if (duplicate.isEmpty()) {
                ingredientQuantities.add(new IngredientQuantity(selectedIngredient,parentPanel.getRecipeTitle(),convertedQuantity));
                selectedIngredients[nbSelectedIngred] = df.format(convertedQuantity) + " " + unit + selectedIngredient;
                nbSelectedIngred++;

                selectedIngredList.setListData(selectedIngredients);
                UpdateIngredientsPanel.this.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Cet ingrédient est déjà dans la liste !");
            }


        }
    }

    private class RemoveButtonListener implements ActionListener {
        public void actionPerformed( ActionEvent event) {
            if (selectedIngredList.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(null, "Sélectionne un ingrédient !");
            } else {
                int iSelectIngred = selectedIngredList.getSelectedIndex();

                ingredientQuantities.remove(iSelectIngred);
                for (int i = iSelectIngred; i < nbSelectedIngred; i++) {
                    selectedIngredients[i] = selectedIngredients[i+1];
                }
                nbSelectedIngred--;
                selectedIngredList.setListData(selectedIngredients);
            }
        }
    }
}
