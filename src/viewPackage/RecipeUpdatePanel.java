package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllRecipesException;
import exceptionPackage.ConnectionException;
import modelPackage.AllRecipesModel;
import modelPackage.Recipe;
import modelPackage.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class RecipeUpdatePanel extends JPanel {
    private final JLabel title;
    private JTable list;
    private JScrollPane scrollPane;
    private final ApplicationController controller;
    private ListSelectionModel listSelect;
    private final JButton updateButton;

    public RecipeUpdatePanel() throws ConnectionException {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 40, 40, 40));
        title = new JLabel("<html><h1 style='margin: 30px 0 30px 0; font-size:24px'>Modifier une recette</h1></html>");
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

        updateButton = new JButton("Modifier");
        updateButton.addActionListener(new UpdateListener());
        this.add(updateButton, BorderLayout.SOUTH);

        this.add(title, BorderLayout.NORTH);

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
                        RecipeUpdatePanel.this.remove(scrollPane);
                        RecipeUpdatePanel.this.remove(updateButton);
                        RecipeUpdatePanel.this.add(new RecipeUpdateForm(recipe), BorderLayout.CENTER);
                        RecipeUpdatePanel.this.revalidate();
                        RecipeUpdatePanel.this.repaint();
                    } catch (ConnectionException exception) {
                        JOptionPane.showMessageDialog(null, exception.getMessage());
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Clique sur la ligne que tu souhaites modifier");
            }
        }
    }
}
