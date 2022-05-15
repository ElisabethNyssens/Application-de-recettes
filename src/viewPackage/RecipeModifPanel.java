package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllRecipesException;
import exceptionPackage.ConnectionException;
import modelPackage.AllRecipesModel;
import modelPackage.Recipe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class RecipeModifPanel extends JPanel {
    private JLabel title;
    private JTable list;
    private JScrollPane scrollPane;
    private ApplicationController controller;
    private ListSelectionModel listSelect;

    public RecipeModifPanel() throws ConnectionException {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 40, 40, 40));
        title = new JLabel("<html><h1 style='margin: 30px 0 30px 0; font-size:24px'>Modifier une recette</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        controller = new ApplicationController();

        try {
            ArrayList<Recipe> recipes = controller.getAllRecipes();
            AllRecipesModel model = new AllRecipesModel(recipes);

            list = new JTable(model);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listSelect = list.getSelectionModel();
            scrollPane = new JScrollPane(list);

            this.add(scrollPane, BorderLayout.CENTER);
        } catch (AllRecipesException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }


        this.add(title, BorderLayout.NORTH);

    }
}
