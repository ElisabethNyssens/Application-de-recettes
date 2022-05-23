package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.ConnectionException;
import exceptionPackage.SearchException;
import modelPackage.Recipe;
import modelPackage.RecipeInMenu;
import modelPackage.SearchRecipesByMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class MenuCompositionWindow extends JFrame {
    private ApplicationController controller;
    private Container mainContainer;
    private JLabel title;
    private JTable list;
    private JScrollPane scrollPane;
    private SearchRecipesByMenu model;
    private ArrayList<RecipeInMenu> recipes;
    private String menuTitle;
    public MenuCompositionWindow(String menuTitle) throws ConnectionException {
        // Création de la fenêtre
        super("TaCuisine");
        this.menuTitle = menuTitle;
        controller = new ApplicationController();
        setSize(750, 500);
        setIconImage(new ImageIcon("img/chef.png").getImage());
        setLocationRelativeTo(null);

        addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                dispose();
            }
        } );

        // Conteneur principal
        mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        // Titre
        title = new JLabel("<html><h1 style='margin: 20px 0 20px 0; font-size: 22px; color:#97002d; font-weight: normal'>"+menuTitle+"</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        mainContainer.add(title, BorderLayout.NORTH);

        // Affichage des recettes
        try {
            recipes = controller.searchRecipesInMenu(menuTitle);
            model = new SearchRecipesByMenu(recipes);

            list = new JTable(model);
            scrollPane = new JScrollPane(list);

            mainContainer.add(scrollPane, BorderLayout.CENTER);
        } catch (SearchException exception) {
            exception.printStackTrace();
        }

        setVisible(true);
    }
}
