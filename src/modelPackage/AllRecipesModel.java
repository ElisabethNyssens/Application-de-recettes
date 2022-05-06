package modelPackage;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllRecipesModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Recipe> recipes;

    public AllRecipesModel(ArrayList<Recipe> recipes) {
        columnNames = new ArrayList<>();
        columnNames.add("Titre");
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
        switch(columnIndex) {
            case 0 : return recipe.getTitle();
            default: return null;
        }
    }
}
