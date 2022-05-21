package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllIngredientsException;
import exceptionPackage.ConnectionException;
import exceptionPackage.SearchException;
import modelPackage.Ingredient;
import modelPackage.RecipeWithIngred;
import modelPackage.SearchByIngredModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SearchByIngredientsPanel extends JPanel {
    private ApplicationController controller;
    private JLabel title, formLabel;
    private JButton searchBtn, addBtn, removeBtn, preparationBtn;
    private JRadioButton with, without;
    private ButtonGroup btnGroup;
    private JPanel btnSearchPanel, ingredListsPanel, ingredBtnPanel, radioPanel, formPanel, preparationBtnPanel;
    private JList<String> ingredList, selectIngredList;
    private ListSelectionModel listSelect;
    private ArrayList<String> selectedIngredients = new ArrayList<>();
    private ArrayList<Ingredient> ingredients;
    private static int NB_INGREDIENTS = 83;
    private static int MAX_SELECT_INGRED = 5;
    private String[] ingredientsValues = new String[NB_INGREDIENTS];
    private String[] ingredientsSelectValues = new String[MAX_SELECT_INGRED+1];
    private int nbSelectIngred;

    public SearchByIngredientsPanel() throws ConnectionException {
        controller = new ApplicationController();
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 150, 50, 150));
        nbSelectIngred = 0;

        // Title
        title = new JLabel("<html><h1 style='margin: 30px 0; font-size: 24px;'>Recherche de recettes selon les ingrédients</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // Search Button
        btnSearchPanel = new JPanel();
        searchBtn = new JButton("Rechercher");
        btnSearchPanel.add(searchBtn);

        // Form
        ingredList = new JList<>();
        ingredList.setFixedCellWidth(150);
        ingredList.setFixedCellHeight(20);
        ingredList.setVisibleRowCount(8);
        ingredList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        try {
            ingredients = controller.getAllIngredients();
            int iIngred = 0;
            for (Ingredient ingred : ingredients) {
                ingredientsValues[iIngred] = ingred.getName();
                iIngred++;
            }
            ingredList.setListData(ingredientsValues);
        } catch (AllIngredientsException e) {
            e.printStackTrace();
        }

        ingredBtnPanel = new JPanel();
        addBtn = new JButton("Ajouter >>");
        removeBtn = new JButton("<< Retirer");
        ingredBtnPanel.add(addBtn);
        ingredBtnPanel.add(removeBtn);

        selectIngredList = new JList<>();
        selectIngredList.setFixedCellWidth(150);
        selectIngredList.setFixedCellHeight(20);
        selectIngredList.setVisibleRowCount(8);
        selectIngredList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        ingredListsPanel = new JPanel();
        ingredListsPanel.setLayout(new GridLayout(1,3));
        ingredListsPanel.add(new JScrollPane(ingredList));
        ingredListsPanel.add(ingredBtnPanel);
        ingredListsPanel.add(new JScrollPane(selectIngredList));

        formLabel = new JLabel("Cherches-tu des recettes avec ou sans les ingrédients sélectionnés ?");
        btnGroup = new ButtonGroup();
        with = new JRadioButton("Avec");
        with.setSelected(true);
        without = new JRadioButton("Sans");
        btnGroup.add(with);
        btnGroup.add(without);
        radioPanel = new JPanel();
        radioPanel.setBorder(new EmptyBorder(30, 0, 200, 0));
        radioPanel.add(formLabel);
        radioPanel.add(with);
        radioPanel.add(without);

        formPanel = new JPanel();
        formPanel.setLayout(new BorderLayout());
        formPanel.add(ingredListsPanel, BorderLayout.CENTER);
        formPanel.add(radioPanel, BorderLayout.SOUTH);

        preparationBtnPanel = new JPanel();
        preparationBtn = new JButton("Préparation");
        preparationBtnPanel.add(preparationBtn);

        this.add(title, BorderLayout.NORTH);
        this.add(formPanel, BorderLayout.CENTER);
        this.add(btnSearchPanel, BorderLayout.SOUTH);

        // Listeners
        addBtn.addActionListener(new AddButtonListener());
        removeBtn.addActionListener(new RemoveButtonListener());
        searchBtn.addActionListener(new SearchButtonListener());
        //preparationBtn.addActionListener(new PreparationButtonListener());
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            List<String> ingredToAdd = ingredList.getSelectedValuesList();

            if (!ingredToAdd.isEmpty()) {
                if ((ingredToAdd.size() + selectedIngredients.size()) > 5) {
                    JOptionPane.showMessageDialog(null, "Choisis maximum 5 ingrédients !");
                } else {
                    for (String ingred : ingredToAdd) {
                        if (!selectedIngredients.contains(ingred)) {
                            selectedIngredients.add(ingred);
                            ingredientsSelectValues[nbSelectIngred] = ingred;
                            nbSelectIngred++;
                        }
                    }
                    selectIngredList.setListData(ingredientsSelectValues);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Sélectionne un ou plusieurs ingrédients !");
            }
        }
    }

    private class RemoveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            int iToRemove = selectIngredList.getSelectedIndex();
            if (iToRemove >= 0 && iToRemove < MAX_SELECT_INGRED) {
                    selectedIngredients.remove(iToRemove);
                    for (int i = iToRemove; i < nbSelectIngred; i++) {
                        ingredientsSelectValues[i] = ingredientsSelectValues[i+1];
                    }
                    nbSelectIngred--;

                selectIngredList.setListData(ingredientsSelectValues);
            } else {
                JOptionPane.showMessageDialog(null, "Sélectionne un ou plusieurs ingrédients !");
            }
        }
    }

    private class SearchButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (!selectedIngredients.isEmpty()) {
                String ingredients = "";
                for (int i = 0; i < selectedIngredients.size(); i++) {
                    ingredients += "'" + selectedIngredients.get(i) + "'" + (i==selectedIngredients.size()-1?"":",");
                }
                try {
                    ArrayList<RecipeWithIngred> recipes = controller.searchByIngredRecipes(ingredients,with.isSelected());
                    SearchByIngredModel model = new SearchByIngredModel(recipes);
                    /*
                    System.out.println(model.getValueAt(1,0));
                    System.out.println(model.getValueAt(1,1));
                    System.out.println(model.getValueAt(1,2));
                    System.out.println(model.getValueAt(1,3));*/

                    JTable list = new JTable(model);
                    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    listSelect = list.getSelectionModel();
                    JScrollPane scrollPane = new JScrollPane(list);

                    SearchByIngredientsPanel.this.remove(formPanel);
                    SearchByIngredientsPanel.this.remove(btnSearchPanel);
                    SearchByIngredientsPanel.this.add(scrollPane, BorderLayout.CENTER);
                    SearchByIngredientsPanel.this.add(preparationBtnPanel, BorderLayout.SOUTH);
                    preparationBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int iRowSelect = listSelect.getMinSelectionIndex();

                            if(iRowSelect != -1) {
                                String recipeTitle = list.getValueAt(iRowSelect, 0).toString();
                                RecipePreparationWindow preparationWindow = new RecipePreparationWindow(recipeTitle);
                            }  else {
                                JOptionPane.showMessageDialog(null, "Clique sur une des recettes");
                            }
                        }
                    });

                    SearchByIngredientsPanel.this.revalidate();
                    SearchByIngredientsPanel.this.repaint();
                } catch (SearchException exception) {
                    exception.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Sélectionne un ou plusieurs ingrédients !");
            }
        }
    }
}
