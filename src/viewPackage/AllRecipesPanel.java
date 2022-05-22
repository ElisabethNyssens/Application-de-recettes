package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.AllRecipesModel;
import modelPackage.Recipe;
import modelPackage.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class AllRecipesPanel extends JPanel {
    private Container mainContainer;
    private JTable list;
    private JScrollPane scrollPane;
    private ApplicationController controller;
    private ListSelectionModel listSelect;
    private JLabel title;
    private JPanel buttonPanel;
    private JButton createBtn, updateBtn, deleteBtn, preparationBtn;

    public AllRecipesPanel(Container mainContainer) throws ConnectionException {
        this.mainContainer =  mainContainer;
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 50, 50, 50));
        title = new JLabel("<html><h1 style='margin: 30px 0 15px 0; font-size: 24px;'>Recettes</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);

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

        buttonPanel = new JPanel();
        createBtn = new JButton("Ajouter");
        updateBtn = new JButton("Modifier");
        deleteBtn = new JButton("Supprimer");
        preparationBtn = new JButton("Préparation");
        buttonPanel.add(createBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(preparationBtn);

        deleteBtn.addActionListener(new DeleteListener());
        updateBtn.addActionListener(new UpdateListener());
        createBtn.addActionListener(new NewRecipeListener());
        preparationBtn.addActionListener(new PreparationListener());

        this.add(title, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private class NewRecipeListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                title = new JLabel("<html><h1 style='margin: 30px 0 15px 0; font-size: 24px;'>Création d'une recette</h1></html>");
                title.setHorizontalAlignment(SwingConstants.CENTER);
                AllRecipesPanel.this.removeAll();
                AllRecipesPanel.this.add(title, BorderLayout.NORTH);
                AllRecipesPanel.this.add(new RecipeCreationForm(mainContainer), BorderLayout.CENTER);
                AllRecipesPanel.this.revalidate();
                AllRecipesPanel.this.repaint();
            } catch (ConnectionException exception) {
                exception.printStackTrace();
            }
        }
    }

    public Recipe recipeRecovery(int iRowSelect) {
        Date date = (Date) list.getValueAt(iRowSelect, 2);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        Recipe recipe;

        recipe = new Recipe(
                list.getValueAt(iRowSelect, 0).toString(),
                calendar,
                Boolean.parseBoolean(list.getValueAt(iRowSelect, 4).toString()),
                Boolean.parseBoolean(list.getValueAt(iRowSelect, 5).toString()),
                Boolean.parseBoolean(list.getValueAt(iRowSelect, 6).toString()),
                list.getValueAt(iRowSelect, 7).toString(),
                list.getValueAt(iRowSelect, 8).toString(),
                Integer.parseInt(list.getValueAt(iRowSelect, 9).toString()),
                list.getValueAt(iRowSelect, 11) != null ? list.getValueAt(iRowSelect, 11).toString() : null,
                User.getInstance().getPseudo(),
                list.getValueAt(iRowSelect, 10) != null ? list.getValueAt(iRowSelect, 10).toString() : null,
                list.getValueAt(iRowSelect, 3).toString()
        );

        return recipe;
    }

    private class UpdateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int iRowSelect = listSelect.getMinSelectionIndex();

            if(iRowSelect != -1) {

                int answer = JOptionPane.showConfirmDialog(null,"Es-tu sûr de vouloir modifier cette recette ?", "Modification", JOptionPane.YES_NO_OPTION);

                if(answer == 0) {
                    Recipe recipe = recipeRecovery(iRowSelect);

                    try {
                        title = new JLabel("<html><h1 style='margin: 30px 0 15px 0; font-size: 24px;'>Modification d'une recette</h1></html>");
                        title.setHorizontalAlignment(SwingConstants.CENTER);
                        AllRecipesPanel.this.removeAll();
                        AllRecipesPanel.this.add(title, BorderLayout.NORTH);
                        AllRecipesPanel.this.add(new RecipeUpdateForm(mainContainer, recipe), BorderLayout.CENTER);
                        AllRecipesPanel.this.revalidate();
                        AllRecipesPanel.this.repaint();
                    } catch (ConnectionException exception) {
                        JOptionPane.showMessageDialog(null, exception.getMessage());
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Clique sur la ligne que tu souhaites modifier");
            }
        }
    }

    private class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int iRowSelect = listSelect.getMinSelectionIndex();

            if (iRowSelect != -1) {

                int answer = JOptionPane.showConfirmDialog(null, "Es-tu sûr de vouloir supprimer cette recette ?", "Suppression", JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    String recipeTitle = list.getValueAt(iRowSelect, 0).toString();

                    try {
                        controller.deleteRecipe(recipeTitle);
                        mainContainer.removeAll();
                        mainContainer.revalidate();
                        mainContainer.repaint();
                        mainContainer.add(new AllRecipesPanel(mainContainer), BorderLayout.CENTER);
                    } catch (ConnectionException | DeleteIngredQuantException | DeleteStepException | DeleteRecipeException exception) {
                        JOptionPane.showMessageDialog(null, exception.getMessage());
                        exception.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Clique sur la ligne que tu souhaites supprimer");
            }
        }
    }

    private class PreparationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int iRowSelect = listSelect.getMinSelectionIndex();

            if(iRowSelect != -1) {
                try {
                    RecipePreparationWindow recipePreparationWindow = new RecipePreparationWindow(list.getValueAt(iRowSelect, 0).toString());
                } catch (ConnectionException exception) {
                    exception.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Clique sur une recette pour afficher sa préparation");
            }
        }
    }

}
