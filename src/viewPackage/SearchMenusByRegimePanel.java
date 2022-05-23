package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllRegimesException;
import exceptionPackage.ConnectionException;
import exceptionPackage.CountException;
import exceptionPackage.SearchException;
import modelPackage.DieteryRegime;
import modelPackage.Menu;
import modelPackage.SearchOnMenuModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchMenusByRegimePanel extends JPanel {
    private int nbRegimes;
    private ApplicationController controller;
    private JLabel title, regimeLabel;
    private JPanel formPanel, headerPanel, displayPanel, btnPanel;
    private JButton searchBtn, recipesBtn;
    private JComboBox regimesCB;
    private ListSelectionModel listSelect;
    private ArrayList<DieteryRegime> regimesList;
    private String[] regimes = new String[nbRegimes];

    public SearchMenusByRegimePanel() throws ConnectionException {
        controller = new ApplicationController();
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0, 150, 50, 150));

        // Récupération du nombre de régimes alimentaires
        try {
            nbRegimes = controller.getElementNumber("dietery_regimes");
        } catch (CountException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }

        // Header panel
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBorder(new EmptyBorder(0,0,50,0));
        add(headerPanel, BorderLayout.NORTH);

        // Title
        title = new JLabel("<html><h1 style='margin: 30px 0; font-size: 24px;'>Recherche de menus selon le régime alimentaire</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(title, BorderLayout.NORTH);

        // Form panel
        formPanel = new JPanel(new FlowLayout());
        headerPanel.add(formPanel, BorderLayout.CENTER);

        // Regime
        regimeLabel = new JLabel("Régime alimentaire :");
        formPanel.add(regimeLabel);

        int iReg = 0;
        try {
            regimesList = controller.getAllRegimes();
            for (DieteryRegime regime : regimesList) {
                regimes[iReg] = regime.getName();
                iReg++;
            }
        }
        catch (AllRegimesException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        regimesCB = new JComboBox(regimes);
        regimesCB.setSelectedItem(null);
        formPanel.add(regimesCB);

        // Search button
        searchBtn = new JButton("Rechercher");
        searchBtn.addActionListener(new SearchBtnListener());
        formPanel.add(searchBtn);

        // Display panel
        displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        add(displayPanel, BorderLayout.CENTER);

        // Bouton recettes
        btnPanel = new JPanel();
        recipesBtn = new JButton("Recettes");
        btnPanel.add(recipesBtn);
    }

    private class SearchBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (regimesCB.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Sélectionne un régime alimentaire");
            } else {
                String dieteryRegime = regimesCB.getSelectedItem().toString();

                try {
                    ArrayList<Menu> menus = controller.searchMenuByDieteryRegime(dieteryRegime);
                    SearchOnMenuModel model = new SearchOnMenuModel(menus);

                    JTable list = new JTable(model);
                    list.setRowHeight(20);
                    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    listSelect = list.getSelectionModel();

                    displayPanel.removeAll();
                    displayPanel.add(new JScrollPane(list), BorderLayout.CENTER);
                    displayPanel.add(btnPanel, BorderLayout.SOUTH);
                    revalidate();
                    repaint();

                    recipesBtn.addActionListener(e -> {
                        int iRowSelect = listSelect.getMinSelectionIndex();

                        if (iRowSelect == -1) {
                            JOptionPane.showMessageDialog(null, "Clique sur un menu");
                        } else {
                            String menuTitle = list.getValueAt(iRowSelect, 0).toString();
                            try {
                                MenuCompositionWindow menuCompositionWindow = new MenuCompositionWindow(menuTitle);
                            } catch (ConnectionException exception) {
                                exception.printStackTrace();
                            }
                        }
                    });
                } catch (SearchException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
