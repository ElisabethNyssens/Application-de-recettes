package modelPackage;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SearchBySeasonModel extends AbstractTableModel {
    private String columnName;
    private ArrayList<String> recipesTitle;

    public SearchBySeasonModel(ArrayList<String> recipesTitle) {
        this.recipesTitle = recipesTitle;
        columnName = "Titre";
    }

    @Override
    public int getRowCount() {
        return recipesTitle.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String recipeTitle = recipesTitle.get(rowIndex);

        switch (columnIndex) {
            case 0: return recipeTitle;
            default: return null;
        }
    }
}
