package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.ConnectionException;
import exceptionPackage.SearchException;
import modelPackage.Recipe;
import modelPackage.RecipeInMenu;
import modelPackage.SearchRecipesByMenu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class MenuCompositionWindow extends JFrame {
    private ApplicationController controller;
    private Container mainContainer;
    private JPanel panel;
    private JLabel title;
    private JTable list;
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
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(0, 30, 30, 30));
        mainContainer.add(panel);

        // Titre
        title = new JLabel("<html><h1 style='margin: 20px 0 20px 0; font-size: 22px; color:#97002d; font-weight: normal'>"+menuTitle+"</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title, BorderLayout.NORTH);

        // Panel
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(0, 100, 0, 100));
        mainContainer.add(panel, BorderLayout.CENTER);

        // Affichage des recettes
        try {
            recipes = controller.searchRecipesInMenu(menuTitle);
            model = new SearchRecipesByMenu(recipes);

            list = new JTable(model);
            list.setRowHeight(20);
            
            panel.add(new JScrollPane(list), BorderLayout.CENTER);

        } catch (SearchException exception) {
            exception.printStackTrace();
        }

        setVisible(true);
    }
}
