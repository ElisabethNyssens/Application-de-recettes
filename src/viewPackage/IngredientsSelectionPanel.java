package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllIngredientsException;
import exceptionPackage.ConnectionException;
import modelPackage.Ingredient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class IngredientsSelectionPanel extends JPanel {
    private ApplicationController controller;
    private static int NB_INGREDIENTS = 32;
    private String[] ingredientsValues = new String[NB_INGREDIENTS];
    protected Object[] selectedIngredients;
    protected int nbSelectedIngred;

    private JLabel ingredientLabel, quantityLabel;
    private JComboBox ingredient;
    private JSpinner quantity;
    private JButton addIngredientBtn;
    private JList selectedIngredList;

    public IngredientsSelectionPanel() throws ConnectionException {
        controller = new ApplicationController();
        //this.setLayout(new GridLayout(1, 3));
        selectedIngredients = new Object[NB_INGREDIENTS];
        nbSelectedIngred = 0;

        try {
            ArrayList<Ingredient> ingredList = controller.getAllIngredients();

            int iIngred = 0;
            for(Ingredient ingredient : ingredList) {
                ingredientsValues[iIngred] = ingredient.getName() + (ingredient.getUnit().equals("unité")?"":(" (" + ingredient.getUnit() + ")"));
                iIngred++;
            }

        } catch (AllIngredientsException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }

        JPanel ingredPanel = new JPanel();
        ingredPanel.setLayout(new GridLayout(2, 1));
        JPanel ingred = new JPanel();
        ingredientLabel = new JLabel("Ingrédient :");
        ingredient = new JComboBox(ingredientsValues);
        ingredient.setMaximumRowCount(6);
        JPanel quant = new JPanel();
        quantityLabel = new JLabel("Quantité :");
        quantity = new JSpinner(new SpinnerNumberModel(0, 0.0, 10000, 0.5));
        ingred.add(ingredientLabel);
        ingred.add(ingredient);
        quant.add(quantityLabel);
        quant.add(quantity);
        ingredPanel.add(ingred);
        ingredPanel.add(quant);

        addIngredientBtn = new JButton("Ajouter l'ingrédient >>");
        addIngredientBtn.addActionListener(new ButtonListener());

        selectedIngredList = new JList();
        selectedIngredList.setFixedCellWidth(170);
        selectedIngredList.setFixedCellHeight(20);
        selectedIngredList.setVisibleRowCount(6);
        selectedIngredList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        this.add(ingredPanel);
        this.add(addIngredientBtn);
        this.add(new JScrollPane(selectedIngredList));
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed( ActionEvent event) {
            //private ArrayList <String> SelectedIngredients = new ArrayList<>();

            double newQuantity = Double.parseDouble(quantity.getValue().toString());

            if(newQuantity > 0) {
                //SelectedIngredients[nbSelectedIngred] = newQuantity + " " + ingredient.getSelectedItem().toString();
                selectedIngredients[nbSelectedIngred] = newQuantity + " " + ingredient.getSelectedItem().toString();
                nbSelectedIngred++;

                selectedIngredList.setListData(selectedIngredients);
                IngredientsSelectionPanel.this.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "La quantité doit être supérieure à 0");
            }
        }
    }
}
