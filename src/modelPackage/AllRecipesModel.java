package modelPackage;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class AllRecipesModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Recipe> recipes;

    public AllRecipesModel(ArrayList<Recipe> recipes) {
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
        switch(columnIndex) {
            case 0 : return recipe.getTitle();
            case 1 : return recipe.getAuthor();
            case 2 : return recipe.getCreationDate().getTime();
            case 3 : return recipe.getCategory();
            case 4 : return recipe.isHot();
            case 5 : return recipe.isSweet();
            case 6 : return recipe.isSalty();
            case 7 : return recipe.getBudget();
            case 8 : return recipe.getPreparationTime();
            case 9 : return recipe.getNbPersons();
            case 10 : if(recipe.getRegime() != null)
                        return recipe.getRegime();
                    else
                        return null;
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
            case 4 : c = Boolean.class; break;
            case 5 : c = Boolean.class; break;
            case 6 : c = Boolean.class; break;
            case 9: c = Integer.class; break;
            default: c = String.class; break;
        }
        return c;
    }
}
