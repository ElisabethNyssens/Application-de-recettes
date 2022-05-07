package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllRecipesException;
import exceptionPackage.ConnectionException;
import modelPackage.AllRecipesModel;
import modelPackage.Recipe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class AllRecipesPanel extends JPanel {
    private JTable list;
    private JScrollPane scrollPane;
    private ApplicationController controller;
    private ListSelectionModel listSelect;
    private JLabel title;

    public AllRecipesPanel() throws ConnectionException {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 50, 50, 50));
        title = new JLabel("<html><h1 style='margin: 30px 0 15px 0'>Recettes</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // getAllRecipes()

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
        // ajout JTable
    }


}
