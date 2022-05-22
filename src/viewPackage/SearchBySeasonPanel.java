package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllCategoriesException;
import exceptionPackage.ConnectionException;
import exceptionPackage.SearchException;
import modelPackage.Category;
import modelPackage.SearchBySeasonModel;
import modelPackage.SeasonRecipe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SearchBySeasonPanel extends JPanel {
    ApplicationController controller;
    private final int NB_CATEGORIES = 9;
    private JLabel title, infos, categoryLabel, dateLabel;
    private JPanel headerPanel, titlePanel, formPanel, entriesPanel, displayPanel, btnPanel;
    private JComboBox recipeCategory;
    private JSpinner date;
    private JButton searchBtn, ingredientsBtn;
    private ArrayList<Category> categList;
    private String[] categories = new String[NB_CATEGORIES];
    private Date today;
    private ListSelectionModel listSelect;

    public SearchBySeasonPanel() throws ConnectionException {
        controller = new ApplicationController();
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0, 150, 50, 150));

        // Header Panel
        headerPanel = new JPanel(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);

        // Title panel
        titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        headerPanel.add(titlePanel, BorderLayout.NORTH);

        // Title
        title = new JLabel("<html><h1 style='margin: 30px 0; font-size: 24px;'>Recherche de recettes selon la saison</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(title, BorderLayout.NORTH);

        // Infos
        infos = new JLabel("Recherche de recettes selon une catégorie et une saison, entre la date à laquelle tu souhaites réaliser la recette");
        infos.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(infos, BorderLayout.CENTER);

        // Form panel
        formPanel = new JPanel(new FlowLayout());
        headerPanel.add(formPanel, BorderLayout.CENTER);

        // Entries panel
        entriesPanel = new JPanel(new GridLayout(2,2));
        formPanel.add(entriesPanel);

        // Categorie
        categoryLabel = new JLabel("Catégorie :");
        entriesPanel.add(categoryLabel);

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
        searchBtn.addActionListener(new SearchBtnListener());
        formPanel.add(searchBtn);

        // Diplay panel
        displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(new EmptyBorder(50, 0, 0, 0));
        add(displayPanel, BorderLayout.CENTER);

        // Bouton Ingrédients
        btnPanel = new JPanel();
        ingredientsBtn = new JButton("Préparation");
        btnPanel.add(ingredientsBtn);
    }

    private class SearchBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (recipeCategory.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Sélectionne une catégorie");
            } else {
                GregorianCalendar searchDate = new GregorianCalendar();
                searchDate.setTime((Date) date.getValue());

                try {
                    ArrayList<SeasonRecipe> seasonRecipes = controller.searchBySeason(recipeCategory.getSelectedItem().toString(), searchDate);
                    SearchBySeasonModel model = new SearchBySeasonModel(seasonRecipes);

                    JTable list = new JTable(model);
                    list.setRowHeight(20);
                    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    listSelect = list.getSelectionModel();
                    JScrollPane scrollPane = new JScrollPane(list);

                    displayPanel.removeAll();
                    displayPanel.add(scrollPane, BorderLayout.CENTER);
                    displayPanel.add(btnPanel, BorderLayout.SOUTH);
                    revalidate();
                    repaint();

                    ingredientsBtn.addActionListener(e -> {
                        int iRowSelect = listSelect.getMinSelectionIndex();

                        if (iRowSelect == -1) {
                            JOptionPane.showMessageDialog(null, "Clique sur une recette");
                        } else {
                            String recipeTitle = list.getValueAt(iRowSelect, 0).toString();
                            try {
                                RecipePreparationWindow preparationWindow = new RecipePreparationWindow(recipeTitle);
                            } catch (ConnectionException exception) {
                                exception.printStackTrace();
                            }
                        }
                    });

                    revalidate();
                    repaint();
                } catch (SearchException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
