package modelPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllCategoriesException;
import exceptionPackage.AllRegimesException;
import exceptionPackage.ConnectionException;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class AllRecipesModel extends AbstractTableModel {
    private ApplicationController controller;
    private ArrayList<String> columnNames;
    private ArrayList<Recipe> recipes;
    private ArrayList<Category> categories;
    private ArrayList<DieteryRegime> regimes;

    public AllRecipesModel(ArrayList<Recipe> recipes) throws ConnectionException {
        controller = new ApplicationController();
        columnNames = new ArrayList<>();
        columnNames.add("Titre");
        columnNames.add("Auteur");
        columnNames.add("Date de création");
        columnNames.add("Catégorie");
        columnNames.add("Chaud");
        columnNames.add("Sucré");
        columnNames.add("Salé");
        columnNames.add("Coût");
        columnNames.add("Temps préparation");
        columnNames.add("Nb personnes");
        columnNames.add("Régime");
        columnNames.add("Saison");
        this.recipes = recipes;
    }

    @Override
    public int getRowCount() {
        return recipes.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Recipe recipe = recipes.get(rowIndex);

        String recipeCategory;
        try {
            categories = controller.getAllCategories();
        } catch (AllCategoriesException exception) {
            exception.printStackTrace();
        }
        recipeCategory = categories.stream().filter(c -> c.getId().equals(recipe.getCategory())).findFirst().orElse(null).getName();

        String regime;
        if(recipe.getRegime() != null) {
            try {
                regimes = controller.getAllRegimes();
            } catch (AllRegimesException exception) {
                exception.printStackTrace();
            }
            regime = regimes.stream().filter(r -> r.getId().equals(recipe.getRegime())).findFirst().orElse(null).getName();
        } else {
            regime = null;
        }

        switch(columnIndex) {
            case 0 : return recipe.getTitle();
            case 1 : return recipe.getAuthor();
            case 2 : return recipe.getCreationDate().getTime();
            case 3 : return recipeCategory;
            case 4 : return recipe.isHot();
            case 5 : return recipe.isSweet();
            case 6 : return recipe.isSalty();
            case 7 : return recipe.getBudget();
            case 8 : return recipe.getPreparationTime();
            case 9 : return recipe.getNbPersons();
            case 10 : return regime;
            case 11 : if(recipe.getSeason() != null)
                        return recipe.getSeason();
                    else
                        return null;
            default: return null;
        }
    }

    @Override
    public Class getColumnClass(int column) {
        Class c;
        switch(column) {
            case 2 : c = Date.class; break;
            case 4 :
            case 5 :
            case 6 :
                c = Boolean.class; break;
            case 9: c = Integer.class; break;
            default: c = String.class; break;
        }
        return c;
    }
}
