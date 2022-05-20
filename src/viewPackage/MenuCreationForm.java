package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AddMenuComponentException;
import exceptionPackage.AddMenuException;
import exceptionPackage.AllMenusException;
import exceptionPackage.ConnectionException;
import modelPackage.Menu;
import modelPackage.MenuComponent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuCreationForm extends JPanel {
    private ApplicationController controller;
    private JPanel formPanel, titlePanel, recipesPanel, commentPanel;
    private JList recipesList, chosenRecipesList;
    private JLabel titleLabel, commentLabel, recipesLabel;
    private JTextField menuTitle;
    private JTextArea menuComment;
    private JButton validateBtn;
    ArrayList<Menu> allMenus = new ArrayList<>();
    private AddMenuComponentsPanel addRecipesPanel;

    public MenuCreationForm() throws ConnectionException {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0, 150, 0, 150));
        controller = new ApplicationController();

        // Récupération liste menus pour pouvoir tester que le titre n'est pas déjà pris
        try {
            allMenus = controller.getAllMenus();
        } catch (AllMenusException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }

        // ---------------- FormPanel -----------------
        formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        add(formPanel, BorderLayout.CENTER);

        // ----------- TitlePanel ------------
        titlePanel = new JPanel(new GridLayout(1, 2));

        titleLabel = new JLabel("Titre du menu :");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel);
        menuTitle = new JTextField();
        titlePanel.add(menuTitle);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.insets = new Insets(0, 0, 30, 0);
        c.ipady = 20;
        c.gridx = 0;
        c.gridy = 0;
        formPanel.add(titlePanel, c);

        // ----------- RecipesPanel ------------
        recipesPanel = new JPanel(new BorderLayout());

        recipesLabel = new JLabel("Ajoutez les recettes une par une dans l'ordre", SwingConstants.CENTER);
        recipesPanel.add(recipesLabel, BorderLayout.NORTH);

        // ------ addRecipesPanel -------
        addRecipesPanel = new AddMenuComponentsPanel(this);
        recipesPanel.add(addRecipesPanel, BorderLayout.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 0, 0, 0);
        c.ipady = 50;
        c.gridy = 1;
        formPanel.add(recipesPanel, c);

        // ----------- CommentPanel ------------
        commentPanel = new JPanel(new GridLayout(1,2));

        commentLabel = new JLabel("Commentaires :");
        commentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        commentPanel.add(commentLabel);
        menuComment = new JTextArea();
        commentPanel.add(menuComment);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 150;
        c.gridy = 2;
        formPanel.add(commentPanel, c);

        // ---------------- Bouton de validation -----------------
        validateBtn = new JButton("Valider");
        add(validateBtn, BorderLayout.SOUTH);
        validateBtn.addActionListener(new ValidateListener());
    }

    private class ValidateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            // Validation
            boolean duplicateTitle = allMenus.stream().anyMatch(menu -> menu.getTitle().equals(menuTitle.getText()));

            if (menuTitle.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Ton menu a besoins d'un petit nom");
            } else if (recipesList.getSelectedValuesList().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Il faut sélectionner au moins une recette !");
            } else {
                ArrayList<MenuComponent> menuComponents = addRecipesPanel.getMenuComponents();

                Menu menu = new Menu(menuTitle.getText(), menuComment.getText());

                try {
                    controller.addMenu(menu);
                    menuComponents.forEach(menuC -> {
                        try {
                            controller.addMenuComponent(menuC);
                        } catch (AddMenuComponentException e) {
                            e.printStackTrace();
                        }
                    });
                }
                catch (AddMenuException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
            revalidate();
            repaint();
        }
    }

    public String getMenuTitle() {
        return menuTitle.getText();
    }
}
