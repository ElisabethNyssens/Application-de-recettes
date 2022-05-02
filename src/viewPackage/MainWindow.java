package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private Container mainContainer;
    private JMenuBar menuBar;
    private JMenu app, create, edit, delete, display, reseach;
    private JMenuItem home, exit, createRecipe, createMenu, editRecipe, editMenu, editIngredient,
            deleteRecipes, deleteMenus, deleteIngredients,
            displayRecipes, displayMenus, displayIngredients,
            ingredientResearch, menuResearch, seasonResearch, shoppingList;

    public MainWindow() {
        super("TaCuisine");

        setSize(1100,800);
        setLocationRelativeTo(null);

        // fermeture de la fenêtre si clic sur icône X :
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
        editIngredient = new JMenuItem("Ingrédient");
        edit.add(editRecipe);
        edit.add(editMenu);
        edit.add(editIngredient);

        deleteRecipes = new JMenuItem("Recettes");
        deleteMenus = new JMenuItem("Menus");
        deleteIngredients = new JMenuItem("Ingrédients");
        delete.add(deleteRecipes);
        delete.add(deleteMenus);
        delete.add(deleteIngredients);

        displayRecipes = new JMenuItem("Recettes");
        displayMenus = new JMenuItem("Menus");
        displayIngredients = new JMenuItem("Ingrédients");
        display.add(displayRecipes);
        display.add(displayMenus);
        display.add(displayIngredients);

        ingredientResearch = new JMenuItem("Recettes selon ingrédients");
        menuResearch = new JMenuItem("Menus de régime alimentaire");
        seasonResearch = new JMenuItem("Recettes de saison");
        reseach.add(ingredientResearch);
        reseach.add(menuResearch);
        reseach.add(seasonResearch);



        // ----------- Menu Listeners ------------

        // Menu accueil
        home.addActionListener(new HomeListener());
        // Menu Créer recette
        createRecipe.addActionListener(new NewRecipeListener());


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
}
