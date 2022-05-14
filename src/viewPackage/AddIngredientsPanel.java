package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllIngredientsException;
import exceptionPackage.ConnectionException;
import modelPackage.Ingredient;
import modelPackage.IngredientQuantity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class AddIngredientsPanel extends JPanel {
    private ApplicationController controller;
    private static int NB_INGREDIENTS = 32;
    private String[] ingredientsValues = new String[NB_INGREDIENTS];
    protected Object[] selectedIngredients;
    protected int nbSelectedIngred;

    private JLabel ingredientLabel, quantityLabel;
    private JComboBox ingredient;
    private JSpinner quantity;
    private JButton addIngredientBtn, resetBtn;
    private JList selectedIngredList;
    private ArrayList<IngredientQuantity> ingredientQuantities;
    protected String recipeName;

    public AddIngredientsPanel(String recipeName) throws ConnectionException {
        this.recipeName = recipeName;
        ingredientQuantities = new ArrayList<>();
        controller = new ApplicationController();

        selectedIngredients = new Object[NB_INGREDIENTS];
        nbSelectedIngred = 0;

        try {
            ArrayList<Ingredient> ingredList = controller.getAllIngredients();

            int iIngred = 0;
            for(Ingredient ingredient : ingredList) {
                ingredientsValues[iIngred] = ingredient.getName() + (ingredient.getUnit().equals("unite")?"":(" (" + ingredient.getUnit() + ")"));
                iIngred++;
            }

        } catch (AllIngredientsException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }

        JPanel ingredPanel = new JPanel();
        ingredPanel.setLayout(new GridLayout(2, 1));
        JPanel ingred = new JPanel();
        ingredientLabel = new JLabel("Ingrédient* :");
        ingredient = new JComboBox(ingredientsValues);
        ingredient.setMaximumRowCount(6);
        JPanel quant = new JPanel();
        quant.setLayout(new FlowLayout(FlowLayout.LEFT,3,3));
        quantityLabel = new JLabel("Quantité* :");
        quantity = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));
        ingred.add(ingredientLabel);
        ingred.add(ingredient);
        quant.add(quantityLabel);
        quant.add(quantity);
        ingredPanel.add(ingred);
        ingredPanel.add(quant);

        addIngredientBtn = new JButton("Ajouter l'ingrédient >>");
        addIngredientBtn.addActionListener(new AddButtonListener());

        selectedIngredList = new JList();
        selectedIngredList.setFixedCellWidth(250);
        selectedIngredList.setFixedCellHeight(20);
        selectedIngredList.setVisibleRowCount(8);
        selectedIngredList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        this.add(ingredPanel);
        this.add(addIngredientBtn);
        this.add(new JScrollPane(selectedIngredList));
    }

    public ArrayList<IngredientQuantity> getIngredientQuantities() {
            return ingredientQuantities;
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed( ActionEvent event) {
            int convertedQuantity = Integer.parseInt(quantity.getValue().toString());
            String[] ingredAndUnit = ingredient.getSelectedItem().toString().split("[()]", 2);
            String selectedIngredient = ingredAndUnit[0];
            /*String unit = ingredAndUnit[1];
            if (selectedIngredient.contains("(")) {

            }
            System.out.println(unit.equals("")?"":unit);*/

            List<IngredientQuantity> duplicate = ingredientQuantities.stream().filter(x ->
                    x.getIngredient().equals(selectedIngredient)).collect(toList());

            if (duplicate.isEmpty()) {
                ingredientQuantities.add(new IngredientQuantity(selectedIngredient,recipeName,convertedQuantity));
                selectedIngredients[nbSelectedIngred] = convertedQuantity + " " + ingredient.getSelectedItem().toString();
                nbSelectedIngred++;

                selectedIngredList.setListData(selectedIngredients);
                AddIngredientsPanel.this.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Cet ingrédient est déjà dans la liste !");
            }


        }
    }
}