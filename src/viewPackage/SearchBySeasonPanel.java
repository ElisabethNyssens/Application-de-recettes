package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllCategoriesException;
import exceptionPackage.ConnectionException;
import modelPackage.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SearchBySeasonPanel extends JPanel {
    ApplicationController controller;
    private final int NB_CATEGORIES = 9;
    private JLabel title, infos, categoryLabel, dateLabel;
    private JPanel headerPanel, titlePanel, formPanel, entriesPanel, displayPanel;
    private JComboBox recipeCategory;
    private JSpinner date;
    private JButton searchBtn;
    private ArrayList<Category> categList;
    private String[] categories = new String[NB_CATEGORIES];
    private Date today;

    public SearchBySeasonPanel() throws ConnectionException {
        setLayout(new BorderLayout());

        // Header Panel
        headerPanel = new JPanel(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);

        // Title panel
        titlePanel = new JPanel(new GridLayout(2,1));
        headerPanel.add(titlePanel, BorderLayout.NORTH);

        // Title
        title = new JLabel("<html><h1 style='margin: 30px 0; font-size: 24px;'>Recherche de recettes selon la saison</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(title);

        // Infos
        infos = new JLabel("Recherche de recettes selon une catégorie et une saison, entrez la date à laquelle vous souhaitez réaliser la recette");
        infos.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(infos);

        // Form panel
        formPanel = new JPanel(new FlowLayout());
        headerPanel.add(formPanel, BorderLayout.CENTER);

        // Entries panel
        entriesPanel = new JPanel(new GridLayout(2,2));
        formPanel.add(entriesPanel);

        // Categorie
        categoryLabel = new JLabel("Catégorie :");
        entriesPanel.add(categoryLabel);
        categories[0] = "Entree";
        categories[1] = "Plat";
        categories[2] = "Dessert";

        /*
        int iCateg = 0;
        try {
            categList = controller.getAllCategories();
            for (Category category : categList) {
                categories[iCateg] = category.getName();
                iCateg++;
            }
        } catch (AllCategoriesException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        */
        recipeCategory = new JComboBox(categories);
        recipeCategory.setSelectedItem(null);
        entriesPanel.add(recipeCategory);

        // Date
        dateLabel = new JLabel("Date de préparation :");
        entriesPanel.add(dateLabel);

        today = new Date();
        date = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
        JSpinner.DateEditor editor = new JSpinner.DateEditor(date, "dd-MM-yyyy");
        date.setEditor(editor);
        entriesPanel.add(date);

        // Bouton recherche
        searchBtn = new JButton("Rechercher");
        formPanel.add(searchBtn);

        // Diplay panel
        displayPanel = new JPanel();
        add(displayPanel, BorderLayout.CENTER);
    }

    private class SearchBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (recipeCategory.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Sélectionne une catégorie");
            }
        }
    }
}
