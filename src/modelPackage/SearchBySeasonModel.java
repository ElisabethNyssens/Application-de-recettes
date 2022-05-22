package modelPackage;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SearchBySeasonModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<SeasonRecipe> seasonRecipes;

    public SearchBySeasonModel(ArrayList<SeasonRecipe> seasonRecipes) {
        this.seasonRecipes = seasonRecipes;
        columnNames = new ArrayList<>();
        columnNames.add("Titre");
        columnNames.add("Auteur");
        columnNames.add("Catégorie");
        columnNames.add("Saison");
    }

    @Override
    public int getRowCount() {
        return seasonRecipes.size();
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
        SeasonRecipe seasonRecipe = seasonRecipes.get(rowIndex);

        switch (columnIndex) {
            case 0: return seasonRecipe.getTitle();
            case 1: return seasonRecipe.getAuthor();
            case 2: return seasonRecipe.getCategory();
            case 3: if(seasonRecipe.getSeason() != null)
                        return seasonRecipe.getSeason();
                    else
                        return null;
            default: return null;
        }
    }

    @Override
    public Class getColumnClass(int column) {
        return String.class;
    }
}
