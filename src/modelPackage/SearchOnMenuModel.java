package modelPackage;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SearchOnMenuModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Menu> menus;

    public SearchOnMenuModel(ArrayList<Menu> menus) {
        this.menus = menus;
        columnNames = new ArrayList<>();
        columnNames.add("Titre");
    }

    @Override
    public int getRowCount() {
        return menus.size();
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
        Menu menu = menus.get(rowIndex);

        switch (columnIndex) {
            case 0 : return menu.getTitle();
            default: return null;
        }
    }
}
