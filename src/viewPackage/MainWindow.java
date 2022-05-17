package viewPackage;

import exceptionPackage.ConnectionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private Container mainContainer;
    private JMenuBar menuBar;
    private JMenu app, recipes, menus, shopList, reseach;
    private JMenuItem home, exit, displayRecipes, displayMenus,
        ingredientResearch, menuResearch, seasonResearch, createShopList;

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
        recipes = new JMenu("Recettes");
        menus = new JMenu("Menus");
        shopList = new JMenu("Liste de courses");
        reseach = new JMenu("Rechercher");

        menuBar.add(app);
        menuBar.add(recipes);
        menuBar.add(menus);
        menuBar.add(shopList);
        menuBar.add(reseach);

        home = new JMenuItem("Accueil");
        exit = new JMenuItem("Quitter");
        app.add(home);
        app.addSeparator();
        app.add(exit);

        displayRecipes = new JMenuItem("Afficher");
        displayMenus = new JMenuItem("Afficher");
        createShopList = new JMenuItem("Liste de courses");
        recipes.add(displayRecipes);
        menus.add(displayMenus);
        shopList.add(createShopList);

        ingredientResearch = new JMenuItem("Recettes selon ingrédients");
        menuResearch = new JMenuItem("Menus de régime alimentaire");
        seasonResearch = new JMenuItem("Recettes de saison");
        reseach.add(ingredientResearch);
        reseach.add(menuResearch);
        reseach.add(seasonResearch);


        // ----------- Menu Listeners ------------

        // Quitter
        exit.addActionListener(event -> System.exit(0));
        // Accueil
        home.addActionListener(new HomeListener());

        // Afficher recettes
        displayRecipes.addActionListener(new DisplayRecipesListener());
        // Afficher menus


        /*
        createRecipe.addActionListener(new NewRecipeListener());
        editRecipe.addActionListener(new EditRecipeListener());
        deleteRecipe.addActionListener(new DeleteRecipeListener());
        createMenu.addActionListener(new NewMenuListener());
        */


        setVisible(true);
    }

    private class HomeListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            mainContainer.removeAll();
            mainContainer.add(new HomePanel());
            setVisible(true);
        }
    }

    private class NewRecipeListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            mainContainer.removeAll();
            try {
                mainContainer.add(new RecipeCreationPanel());
            } catch (ConnectionException exception) {
                exception.printStackTrace();
                System.out.println(exception.getMessage());
            }
            setVisible(true);
        }
    }

   /* private class NewMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainContainer.removeAll();
            try {
                mainContainer.add(new MenuCreationPanel());
            } catch (ConnectionException exception) {
                exception.printStackTrace();
                System.out.println(exception.getMessage());
            }
            setVisible(true);
        }
    }*/

    private class DisplayRecipesListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            mainContainer.removeAll();
            try {
                mainContainer.add(new AllRecipesPanel(mainContainer));
            } catch (ConnectionException exception) {
                System.out.println(exception.getMessage());
            }
            setVisible(true);
        }
    }

 /*   private class EditRecipeListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            mainContainer.removeAll();
            try {
                mainContainer.add(new RecipeUpdatePanel());
            } catch (ConnectionException exception) {
                exception.printStackTrace();
            }
            setVisible(true);
        }
    }*/

   /* private class DeleteRecipeListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            mainContainer.removeAll();
            try {
                mainContainer.add(new RecipeDeletePanel(mainContainer));
            } catch (ConnectionException exception) {
                System.out.println(exception.getMessage());
            }
            setVisible(true);
        }
    }*/

}
