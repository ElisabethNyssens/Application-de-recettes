package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllIngredQuantitiesException;
import exceptionPackage.AllIngredientsException;
import exceptionPackage.AllStepsException;
import exceptionPackage.ConnectionException;
import modelPackage.Ingredient;
import modelPackage.IngredientQuantity;
import modelPackage.Recipe;
import modelPackage.Step;

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
    private JPanel panel, resultPanel, ingredPanel, stepsPanel;
    private JList ingredList;
    private JTextArea stepList;
    private String[] ingredValues;
    private String stepsValues;
    private ArrayList<IngredientQuantity> ingredientQuantities;
    private ArrayList<Ingredient> allIngredients;
    private ArrayList<Step> steps;
    private String recipeName;

    public RecipePreparationWindow(String recipeName) throws ConnectionException {
        // Création de la fenêtre
        super("TaCuisine");
        this.recipeName = recipeName;
        controller = new ApplicationController();
        setSize(750, 500);
        setIconImage(new ImageIcon("img/chef.png").getImage());
        setLocationRelativeTo(null);

        addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                dispose();
            }
        } );

        title = new JLabel("<html><h1 style='margin: 20px 0 20px 0; font-size: 22px; color:#97002d; font-weight: normal'>"+recipeName+"</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // Conteneur principal
        mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        allIngredients = new ArrayList<>();
        try {
            allIngredients = controller.getAllIngredients();
        } catch (AllIngredientsException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(0, 30, 30, 30));
        panel.add(title,BorderLayout.NORTH);

        resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(1,2,30,0));

        // Ingredients list
        ingredPanel = new JPanel();
        ingredPanel.setLayout(new BorderLayout());
        ingredPanel.add(new JLabel("<html><p style='font-size:14px; margin-bottom: 5px'>Ingrédients :</p></html>"), BorderLayout.NORTH);
        ingredList = new JList<>();
        ingredList.setFixedCellWidth(150);
        ingredList.setFixedCellHeight(25);
        ingredList.setVisibleRowCount(5);
        ingredList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        try {
            ingredientQuantities = controller.getAllIngredientsOfRecipe(recipeName);

            ingredValues = new String[ingredientQuantities.size()];
            int nbIngred = 0;
            for(IngredientQuantity ingredientQuantity : ingredientQuantities) {
                double convertedQuantity = ingredientQuantity.getQuantity();
                DecimalFormat df = new DecimalFormat("###.#");
                String unit = allIngredients.stream().filter(i -> i.getName().equals(ingredientQuantity.getIngredient())).findFirst().orElse(null).getUnit();
                ingredValues[nbIngred] = " - " + df.format(convertedQuantity) + " " + (unit.equals("unite")?"":unit+" ") + ingredientQuantity.getIngredient();
                nbIngred++;
            }
            ingredList.setListData(ingredValues);
        } catch (AllIngredQuantitiesException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }
        ingredPanel.add(new JScrollPane(ingredList));

        // Steps list
        stepsPanel = new JPanel();
        stepsPanel.setLayout(new BorderLayout());
        stepsPanel.add(new JLabel("<html><p style='font-size:15px; margin-bottom: 5px;'>Etapes de préparation :</p></html>"), BorderLayout.NORTH);
        stepList = new JTextArea();
        stepList.setBorder(new EmptyBorder(5, 5, 0, 5));
        stepList.setWrapStyleWord(true);
        stepList.setLineWrap(true);
        stepList.setEditable(false);
        try {
            steps = controller.getAllStepsOfRecipe(recipeName);

            stepsValues = new String();
            for(Step step : steps) {
                stepsValues += step.getOrderNumber() + ". " + step.getDescription() + "\n\n";
            }
            stepList.setText(stepsValues);
            stepList.setCaretPosition(0);
        } catch (AllStepsException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
        }
        stepsPanel.add(new JScrollPane(stepList));

        resultPanel.add(ingredPanel);
        resultPanel.add(stepsPanel);
        panel.add(resultPanel, BorderLayout.CENTER);

        this.add(panel);
        setVisible(true);
    }
}
