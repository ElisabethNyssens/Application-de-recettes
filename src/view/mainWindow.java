package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private Container mainContainer;
    private JMenuBar menuBar;
    private JMenu home, create, myRecipes, reseach, shoppingList;
    private JMenuItem recipe, menu, IngredientResearch, menuResearch, seasonResearch;

    public MainWindow() {
        super("TaCuisine");

        setSize(800,700);
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

        // Menu
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        home = new JMenu("Accueil");
        create = new JMenu("Créer");
        myRecipes = new JMenu("Mes recettes");
        reseach = new JMenu("Rechercher");
        shoppingList = new JMenu("Liste de course");

        menuBar.add(home);
        menuBar.add(create);
        menuBar.add(myRecipes);
        menuBar.add(reseach);
        menuBar.add(shoppingList);


        setVisible(true);
    }
}
