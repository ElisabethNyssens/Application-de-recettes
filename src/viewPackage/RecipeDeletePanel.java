package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.AllRecipesModel;
import modelPackage.Recipe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RecipeDeletePanel extends JPanel {
    private Container mainContainer;
    private JLabel title;
    private JTable list;
    private JScrollPane scrollPane;
    private ApplicationController controller;
    private ListSelectionModel listSelect;
    private JButton deleteButton;

    public RecipeDeletePanel(Container mainContainer) throws ConnectionException {
        this.mainContainer =  mainContainer;
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 40, 40, 40));
        title = new JLabel("<html><h1 style='margin: 30px 0 30px 0; font-size:24px'>Supprimer une recette</h1></html>");
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

        deleteButton = new JButton("Supprimer");
        deleteButton.addActionListener(new RecipeDeletePanel.DeleteListener());
        this.add(deleteButton, BorderLayout.SOUTH);

        this.add(title, BorderLayout.NORTH);

    }

    private class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int iLigneSelect = listSelect.getMinSelectionIndex();

            if (iLigneSelect != -1) {

                int answer = JOptionPane.showConfirmDialog(null, "Es-tu sûr de vouloir supprimer cette recette ?", "Suppression", JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    String recipeTitle = list.getValueAt(iLigneSelect, 0).toString();

                    try {
                        controller.deleteRecipe(recipeTitle);
                        mainContainer.removeAll();
                        mainContainer.revalidate();
                        mainContainer.repaint();
                        mainContainer.add(new RecipeDeletePanel(mainContainer), BorderLayout.CENTER);
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
}
