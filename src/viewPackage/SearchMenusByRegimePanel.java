package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.AllRegimesException;
import exceptionPackage.ConnectionException;
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
    private final static int NB_REGIMES = 4;
    private ApplicationController controller;
    private JLabel title, regimeLabel;
    private JPanel formPanel, headerPanel, displayPanel;
    private JButton searchBtn;
    private JComboBox regimesCB;
    private ListSelectionModel listSelect;
    private ArrayList<DieteryRegime> regimesList;
    private String[] regimes = new String[NB_REGIMES];

    public SearchMenusByRegimePanel() throws ConnectionException {
        controller = new ApplicationController();
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0, 150, 50, 150));

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
                    revalidate();
                    repaint();
                } catch (SearchException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
