package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllMenusException;
import exceptionPackage.ConnectionException;
import exceptionPackage.DeleteMenuComponentException;
import exceptionPackage.DeleteMenuException;
import modelPackage.AllMenusModel;
import modelPackage.Menu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AllMenusPanel extends JPanel {
    private Container mainContainer;
    private JTable list;
    private JScrollPane scrollPane;
    private ApplicationController controller;
    private ListSelectionModel listSelect;
    private JLabel title;
    private JPanel buttonPanel;
    private JButton createBtn, deleteBtn;

    public AllMenusPanel(Container mainContainer) throws ConnectionException {
        this.mainContainer = mainContainer;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0,50,50,50));
        title = new JLabel("<html><h1 style='margin: 30px 0 15px 0; font-size: 24px;'>Menus</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        controller = new ApplicationController();

        try {
            ArrayList<Menu> menus = controller.getAllMenus();
            AllMenusModel model = new AllMenusModel(menus);

            list = new JTable(model);
            list.setRowHeight(20);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listSelect = list.getSelectionModel();
            scrollPane = new JScrollPane(list);

            add(scrollPane, BorderLayout.CENTER);
        }
        catch (AllMenusException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }

        buttonPanel = new JPanel();
        createBtn = new JButton("Ajouter");
        deleteBtn = new JButton("Supprimer");
        buttonPanel.add(createBtn);
        buttonPanel.add(deleteBtn);

        deleteBtn.addActionListener(new DeleteMenuListener());
        createBtn.addActionListener(new NewMenuListener());

        this.add(title, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private class NewMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                title = new JLabel("<html><h1 style='margin: 30px 0 15px 0; font-size: 24px;'>Création d'un Menu</h1></html>");
                title.setHorizontalAlignment(SwingConstants.CENTER);
                removeAll();
                add(title, BorderLayout.NORTH);
                add(new MenuCreationForm(mainContainer), BorderLayout.CENTER);
                revalidate();
                repaint();
            }
            catch (ConnectionException exception) {
                exception.printStackTrace();
            }
        }
    }

    private  class DeleteMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int iLigneSelect = listSelect.getMinSelectionIndex();

            if (iLigneSelect != -1) {
                int answer = JOptionPane.showConfirmDialog(null, "Es-tu sûr de vouloir supprimer ce menu ?", "Suppression", JOptionPane.YES_NO_OPTION);
                if (answer == 0) {
                    String menuTitle = list.getValueAt(iLigneSelect, 0).toString();

                    try {
                        controller.deleteMenu(menuTitle);
                        mainContainer.removeAll();
                        mainContainer.revalidate();
                        mainContainer.repaint();
                        mainContainer.add(new AllMenusPanel(mainContainer), BorderLayout.CENTER);
                    }
                    catch (ConnectionException | DeleteMenuException | DeleteMenuComponentException exception) {
                        JOptionPane.showMessageDialog(null, exception.getMessage());
                        exception.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Clique sur la ligne que tu souhaites supprimer");
            }
        }
    }
}
