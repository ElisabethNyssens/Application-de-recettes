package modelPackage;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SearchRecipesByMenu extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<RecipeInMenu> recipes;

    public SearchRecipesByMenu(ArrayList<RecipeInMenu> recipes) {
        this.recipes = recipes;
        columnNames.add("Ordre");
        columnNames.add("Titre");
        columnNames.add("Nombre de personnes");
        columnNames.add("Catégorie");
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
        RecipeInMenu recipe = recipes.get(rowIndex);

        switch (columnIndex) {
            case 0: return recipe.getOrderNumber();
            case 1: return recipe.getTitle();
            case 2: return recipe.getNbPersons();
            case 3: return recipe.getCategory();
            default: return null;
        }
    }
}
