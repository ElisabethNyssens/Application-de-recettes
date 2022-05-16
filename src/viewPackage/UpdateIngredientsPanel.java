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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class UpdateIngredientsPanel extends JPanel {
    private ApplicationController controller;
    private static int NB_INGREDIENTS = 68;
    private String[] ingredientsValues = new String[NB_INGREDIENTS];
    private Object[] selectedIngredients;
    private int nbSelectedIngred;

    private JLabel ingredientLabel, quantityLabel;
    private JComboBox ingredient;
    private JSpinner quantity;
    private JButton addIngredientBtn, resetBtn;
    private JList selectedIngredList;
    private ArrayList<IngredientQuantity> ingredientQuantities;
    private RecipeUpdateForm parentPanel;

    public UpdateIngredientsPanel(RecipeUpdateForm parentPanel) throws ConnectionException {
        this.parentPanel = parentPanel;
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
        quantity = new JSpinner(new SpinnerNumberModel(1, 0.5, 10000, 0.5));
        ingred.add(ingredientLabel);
        ingred.add(ingredient);
        quant.add(quantityLabel);
        quant.add(quantity);
        ingredPanel.add(ingred);
        ingredPanel.add(quant);

        addIngredientBtn = new JButton("Ajouter l'ingrédient >>");
        addIngredientBtn.addActionListener(new UpdateIngredientsPanel.AddButtonListener());

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
}
