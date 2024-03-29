package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.ConnectionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private ApplicationController controller;
    private Container mainContainer;
    private JMenuBar menuBar;
    private JMenu app, recipes, menus, shopList, reseach;
    private JMenuItem home, exit, displayRecipes, displayMenus,
        ingredientResearch, menuResearch, seasonResearch, createShopList;

    public MainWindow() throws ConnectionException {
        super("TaCuisine");
        setSize(1200,800);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("img/chef.png").getImage());
        controller = new ApplicationController();
        addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                controller.closeConnection();
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

        displayRecipes = new JMenuItem("Afficher les recettes");
        displayMenus = new JMenuItem("Afficher les menus");
        createShopList = new JMenuItem("Créer une liste de courses");
        recipes.add(displayRecipes);
        menus.add(displayMenus);
        shopList.add(createShopList);

        ingredientResearch = new JMenuItem("Rechercher des recettes selon les ingrédients");
        menuResearch = new JMenuItem("Rechercher des menus de régime alimentaire");
        seasonResearch = new JMenuItem("Rechercher des recettes de saison");
        reseach.add(ingredientResearch);
        reseach.add(menuResearch);
        reseach.add(seasonResearch);


        // ----------- Menu Listeners ------------

        // Quitter
        exit.addActionListener(event -> {
            controller.closeConnection();
            System.exit(0);
        });
        // Accueil
        home.addActionListener(new HomeListener());

        // Afficher recettes
        displayRecipes.addActionListener(new DisplayRecipesListener());
        // Afficher menus
        displayMenus.addActionListener(new DisplayMenusListener());
        // Rechercher
        ingredientResearch.addActionListener(new SearchByIngredListener());
        menuResearch.addActionListener(new SearchMenusByDieteryRegimeListener());
        seasonResearch.addActionListener(new SearchBySeasonListener());
        // Liste de courses
        createShopList.addActionListener(new ShoppingListListener());


        setVisible(true);
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
                mainContainer.add(new AllRecipesPanel(mainContainer));
            } catch (ConnectionException exception) {
                exception.printStackTrace();
            }
            setVisible(true);
        }
    }

    private class SearchByIngredListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            mainContainer.removeAll();
            try {
                mainContainer.add(new SearchByIngredientsPanel());
            } catch (ConnectionException exception) {
                exception.printStackTrace();
            }
            setVisible(true);
        }
    }

    private class DisplayMenusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainContainer.removeAll();
            try {
                mainContainer.add(new AllMenusPanel(mainContainer));
            }
            catch (ConnectionException exception) {
                System.out.println(exception.getMessage());
            }
            setVisible(true);
        }
    }

    private class SearchMenusByDieteryRegimeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainContainer.removeAll();
            try {
                mainContainer.add(new SearchMenusByRegimePanel());
            } catch (ConnectionException exception) {
                exception.printStackTrace();
            }
            setVisible(true);
        }
    }

    private class SearchBySeasonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainContainer.removeAll();
            try {
                mainContainer.add(new SearchBySeasonPanel());
            } catch (ConnectionException exception) {
                exception.printStackTrace();
            }
            setVisible(true);
        }
    }

    private class ShoppingListListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainContainer.removeAll();
            try {
                mainContainer.add(new ShoppingListPanel());
            } catch (ConnectionException exception) {
                exception.printStackTrace();
            }
            setVisible(true);
        }
    }
}
