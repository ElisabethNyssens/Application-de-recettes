package viewPackage;

import exceptionPackage.ConnectionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class MainWindow extends JFrame {
    private Container mainContainer;
    private JMenuBar menuBar;
    private JMenu app, create, edit, delete, display, reseach;
    private JMenuItem home, exit, createRecipe, createMenu, editRecipe, editMenu,
            deleteRecipes, deleteMenus,
            displayRecipes, displayMenus,
            ingredientResearch, menuResearch, seasonResearch, shoppingList;

    public MainWindow() {
        super("TaCuisine");
        setSize(1200,800);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("img/chef.png").getImage());
        addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                System.exit(0);
            }
        } );

        // Récupération de la référence du conteneur de la fenêtre :
        mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout());

        // Home panel
        mainContainer.add(new HomePanel(), BorderLayout.CENTER, 0);

        // ------------- Création Menu --------------
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        app = new JMenu("TaCuisine");
        create = new JMenu("Créer");
        edit = new JMenu("Modifier");
        delete = new JMenu("Supprimer");
        display = new JMenu("Afficher");
        reseach = new JMenu("Rechercher");

        menuBar.add(app);
        menuBar.add(create);
        menuBar.add(edit);
        menuBar.add(delete);
        menuBar.add(display);
        menuBar.add(reseach);

        home = new JMenuItem("Accueil");
        exit = new JMenuItem("Quitter");
        app.add(home);
        app.addSeparator();
        app.add(exit);

        createRecipe = new JMenuItem("Recette");
        createMenu = new JMenuItem("Menu");
        shoppingList = new JMenuItem("Liste de courses");
        create.add(createRecipe);
        create.add(createMenu);
        create.addSeparator();
        create.add(shoppingList);

        editRecipe = new JMenuItem("Recette");
        editMenu = new JMenuItem("Menu");
        edit.add(editRecipe);
        edit.add(editMenu);

        deleteRecipes = new JMenuItem("Recettes");
        deleteMenus = new JMenuItem("Menus");
        delete.add(deleteRecipes);
        delete.add(deleteMenus);

        displayRecipes = new JMenuItem("Recettes");
        displayMenus = new JMenuItem("Menus");
        display.add(displayRecipes);
        display.add(displayMenus);

        ingredientResearch = new JMenuItem("Recettes selon ingrédients");
        menuResearch = new JMenuItem("Menus de régime alimentaire");
        seasonResearch = new JMenuItem("Recettes de saison");
        reseach.add(ingredientResearch);
        reseach.add(menuResearch);
        reseach.add(seasonResearch);



        // ----------- Menu Listeners ------------

        // Quitter
        exit.addActionListener(event -> System.exit(0));
        // Menu accueil
        home.addActionListener(new HomeListener());
        // Menu Créer recette
        createRecipe.addActionListener(new NewRecipeListener());

        // Afficher recettes
        displayRecipes.addActionListener(new DisplayRecipesListener());


        setVisible(true);
    }

    private class NewRecipeListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            mainContainer.removeAll();
            mainContainer.add(new RecipeCreationPanel());
            setVisible(true);
        }
    }

    private class HomeListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            mainContainer.removeAll();
            mainContainer.add(new HomePanel());
            setVisible(true);
        }
    }

    private class DisplayRecipesListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            mainContainer.removeAll();
            try {
                mainContainer.add(new AllRecipesPanel());
            } catch (ConnectionException exception) {
                System.out.println(exception.getMessage());
            }
            setVisible(true);
        }
    }

    // TO DO:  fonction affichage panel
}
