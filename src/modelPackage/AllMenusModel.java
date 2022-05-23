package modelPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.ConnectionException;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AllMenusModel extends AbstractTableModel {
    private ApplicationController controller;
    private ArrayList<String> columnNames;
    private ArrayList<Menu> menus;

    public AllMenusModel(ArrayList<Menu> menus) throws ConnectionException {
        controller = new ApplicationController();
        columnNames = new ArrayList<>();
        columnNames.add("Titre");
        columnNames.add("Commentaire");
        this.menus = menus;
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

        switch(columnIndex) {
            case 0 : return menu.getTitle();
            case 1 : if (menu.getComment() != null)
                        return menu.getComment();
                    else
                        return null;
            default: return null;
        }
    }
}
