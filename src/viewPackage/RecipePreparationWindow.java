package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllIngredQuantitiesException;
import modelPackage.IngredientQuantity;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class RecipePreparationWindow extends JFrame {
    private ApplicationController controller;
    private static int NB_INGREDIENTS = 83;
    private Container mainContainer;
    private JLabel title;
    private JPanel panel;
    private String recipeName;
    private ArrayList<IngredientQuantity> ingredientQuantities;

    public RecipePreparationWindow(String recipeName) {
        // Création de la fenêtre
        super("TaCuisine");
        setSize(800, 600);
        setIconImage(new ImageIcon("img/chef.png").getImage());
        setLocationRelativeTo(null);

        addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                dispose();
            }
        } );

        title = new JLabel("<html><h1 style='margin: 20px 0 10px 0; font-size: 18px;'>"+recipeName+"</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // Conteneur principal
        mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        this.recipeName = recipeName;

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(0, 30, 30, 30));

        panel.add(title,BorderLayout.NORTH);

       /* nbSelectedIngred = 0;
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
        }*/

        this.add(panel);

        setVisible(true);
    }
}
