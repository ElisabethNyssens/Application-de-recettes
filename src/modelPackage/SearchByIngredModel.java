package modelPackage;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SearchByIngredModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<RecipeWithIngred> recipes;

    public SearchByIngredModel(ArrayList<RecipeWithIngred> recipes) {
        this.recipes = recipes;
        columnNames = new ArrayList<>();
        columnNames.add("Titre");
        columnNames.add("Auteur");
        columnNames.add("Catégorie");
        columnNames.add("Budget");
        columnNames.add("Temps de préparation");
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
        RecipeWithIngred recipe = recipes.get(rowIndex);

        switch(columnIndex) {
            case 0 : return recipe.getTitle();
            case 1 : return recipe.getAuthor();
            case 2 : return recipe.getCategory();
            case 3 : return recipe.getBudget();
            case 4 : return recipe.getPreparationTime();
            default: return null;
        }
    }

    @Override
    public Class getColumnClass(int column) {
        return String.class;
    }
}
