package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllMenusException;
import exceptionPackage.ConnectionException;
import modelPackage.Menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuForm extends JPanel {
    //private ApplicationController controller;
    private JPanel formPanel, titlePanel, recipesPanel, commentPanel;
    private JList recipesList, chosenRecipesList;
    private JLabel titleLabel, commentLabel, recipesLabel;
    private JTextField menuTitle;
    private JTextArea menuComment;
    private JButton validateBtn;
    ArrayList<Menu> allMenus = new ArrayList<>();

    public MenuForm() throws ConnectionException {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0, 150, 0, 150));
        //controller = new ApplicationController();

        // Récupération liste menus pour pouvoir tester que le titre n'est pas déjà pris
        /*
        try {
            allMenus = controller.getAllMenus();
        } catch (AllMenusException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }

         */

        // ---------------- FormPanel -----------------
        formPanel = new JPanel(new GridLayout(3,1));
        add(formPanel, BorderLayout.CENTER);

        // ----------- TitlePanel ------------
        titlePanel = new JPanel(new GridLayout(1, 2));

        titleLabel = new JLabel("Titre du menu :");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel);
        menuTitle = new JTextField();
        titlePanel.add(menuTitle);

        formPanel.add(titlePanel, BorderLayout.NORTH);

        // ----------- RecipesPanel ------------
        recipesPanel = new JPanel(new BorderLayout());

        recipesLabel = new JLabel("Ajoutez les recettes une par une dans l'ordre", SwingConstants.CENTER);
        recipesPanel.add(recipesLabel, BorderLayout.NORTH);

        // ------ addRecipesPanel -------
        AddRecipesInMenuPanel addRecipesPanel = new AddRecipesInMenuPanel(this);
        recipesPanel.add(addRecipesPanel, BorderLayout.CENTER);
        formPanel.add(recipesPanel, BorderLayout.CENTER);

        // ----------- CommentPanel ------------
        commentPanel = new JPanel(new GridLayout(1,2));

        commentLabel = new JLabel("Commentaires :");
        commentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        commentPanel.add(commentLabel);
        menuComment = new JTextArea();
        commentPanel.add(menuComment);

        formPanel.add(commentPanel, BorderLayout.SOUTH);

        // ---------------- Bouton de validation -----------------
        validateBtn = new JButton("Valider");
        add(validateBtn, BorderLayout.SOUTH);
        validateBtn.addActionListener(new ValidateListener());
    }

    private class ValidateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Validation
            boolean duplicateTitle = allMenus.stream().anyMatch(menu -> menu.getTitle().equals(menuTitle.getText()));

            if (menuTitle.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Ton menu a besoins d'un petit nom");
            } else if (recipesList.getSelectedValuesList().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Il faut sélectionner au moins une recette !");
            } else {
                Menu menu = new Menu(menuTitle.getText(), menuComment.getText());
            }
        }
    }

    public String getMenuTitle() {
        return menuTitle.getText();
    }
}
