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
        // columnNames.add("Auteur");
        columnNames.add("Date de création");
        columnNames.add("Chaud");
        columnNames.add("Sucré");
        columnNames.add("Salé");
        columnNames.add("Coût");
        columnNames.add("Difficulté");
        columnNames.add("Temps préparation");
        columnNames.add("Nombre personnes");
        /*columnNames.add("saison");
        columnNames.add("commentaire");*/
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
            case 1 : return recipe.getCreationDate().getTime();
            case 2 : return recipe.isHot();
            case 3 : return recipe.isSweet();
            case 4 : return recipe.isSalty();
            case 5 : return recipe.getBudget();
            case 6 : return recipe.getDifficulty();
            case 7 : return recipe.getPreparationTime();
            case 8 : return recipe.getNbPersons();
          /*  case 9 : return recipe.getSeason();
            case 10 : return recipe.getComment();*/
            default: return null;
        }
    }

    @Override
    public Class getColumnClass(int column) {
        Class c;
        switch(column) {
            case 0 : c = String.class; break;
            case 1 : c = Date.class; break;
            case 2 : c = Boolean.class; break;
            case 3 : c = Boolean.class; break;
            case 4 : c = Boolean.class; break;
            case 5 : c = String.class; break;
            case 6 : c = String.class; break;
            case 7 : c = String.class; break;
            case 8: c = Integer.class; break;
            default: c = String.class; break;
        }
        return c;
    }
}
