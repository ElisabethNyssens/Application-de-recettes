package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.*;
import modelPackage.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RecipeForm extends JPanel {
    private ApplicationController controller;
    private JLabel recipeTitleLabel, dateLabel, recipeCategoryLabel, costLabel, difficultyLabel, preparationTimeLabel,
            nbPersonsLabel, regimeLabel;
    private JComboBox recipeCategory, cost, difficulty, preparationTime, regime;
    private JTextField recipeTitle ;
    private JSpinner date, nbPersons;
    private JCheckBox hot, sweet, salty;
    private Date today;

    private int activeFormStep;
    private static int NB_CATEGORIES = 8;
    public static int NB_REGIMES = 4;

    private JButton prevStepBtn, nextStepBtn;
    private JPanel step1Panel, step2Panel, bottomPanel, progressBar;
    private IngredientsSelectionPanel ingredientsSelectionPanel;
    private StepsPanel stepsPanel;

    private String[] recipeCategories = new String[NB_CATEGORIES];
    private String[] regimes = new String[NB_REGIMES];
    private final String[] costs = {"Bon marché", "Coût moyen", "Assez cher"};
    private final String[] difficulties = {"Très facile", "Facile", "Moyen", "Difficile"};
    private final String[] preparationTimes = {"Rapide", "Moyen", "Long"};

    private JPanel refToRecipePanel;


    public RecipeForm() throws ConnectionException {
        controller = new ApplicationController();
        refToRecipePanel = this;
        this.setLayout(new BorderLayout());
        activeFormStep = 1;

        // --------- Form step 1 ---------
        step1Panel = new JPanel();
        step1Panel.setBorder(new EmptyBorder(0, 150, 0, 150));
        step1Panel.setLayout(new GridLayout(9, 2));

        recipeTitleLabel = new JLabel("Titre :");
        recipeTitle = new JTextField();
        step1Panel.add(recipeTitleLabel);
        step1Panel.add(recipeTitle);

        dateLabel = new JLabel("Date de création :");
        today = new Date();
        date = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
        JSpinner.DateEditor editor = new JSpinner.DateEditor(date, "dd-MM-yyyy");
        date.setEditor(editor);
        step1Panel.add(dateLabel);
        step1Panel.add(date);

        nbPersonsLabel = new JLabel("Nombre de personnes :");
        nbPersons = new JSpinner(new SpinnerNumberModel(4, 1, 50, 1));
        step1Panel.add(nbPersonsLabel);
        step1Panel.add(nbPersons);

        recipeCategoryLabel = new JLabel("Catégorie :");
        int iCateg = 0;
        try {
            ArrayList<Category> categList = controller.getAllCategories();
            for(Category category : categList) {
                recipeCategories[iCateg] = category.getName();
                iCateg++;
            }
        } catch (AllCategoriesException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        recipeCategory = new JComboBox(recipeCategories);
        recipeCategory.setSelectedItem(recipeCategories[5]);
        step1Panel.add(recipeCategoryLabel);
        step1Panel.add(recipeCategory);

        costLabel = new JLabel("Prix :");
        cost = new JComboBox(costs);
        step1Panel.add(costLabel);
        step1Panel.add(cost);

        difficultyLabel = new JLabel("Difficulté :");
        difficulty = new JComboBox(difficulties);
        step1Panel.add(difficultyLabel);
        step1Panel.add(difficulty);

        preparationTimeLabel = new JLabel("Temps de préparation :");
        preparationTime = new JComboBox(preparationTimes);
        step1Panel.add(preparationTimeLabel);
        step1Panel.add(preparationTime);

        // regimes selection
        regimeLabel = new JLabel("Régime alimentaire :");
        int iReg = 0;
        try {
            ArrayList<DieteryRegime> regimesList = controller.getAllRegimes();
            for(DieteryRegime regime : regimesList) {
                regimes[iReg] = regime.getName();
                iReg++;
            }
        } catch (AllRegimesException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        regime = new JComboBox(regimes);
        regime.setSelectedItem(null);
        step1Panel.add(regimeLabel);
        step1Panel.add(regime);

        JPanel checkboxPanel = new JPanel();
        hot = new JCheckBox("Chaude");
        sweet = new JCheckBox("Sucrée");
        salty = new JCheckBox("Salée");
        checkboxPanel.add(sweet);
        checkboxPanel.add(salty);
        checkboxPanel.add(hot);
        step1Panel.add(new JPanel());
        step1Panel.add(checkboxPanel);


        // ------- Form step 2 -------
        step2Panel = new JPanel();
        step2Panel.setBorder(new EmptyBorder(0, 150, 0, 150));
        //step2Panel.setLayout(new GridLayout(2,1));

        //step2Panel.add(new JLabel("<html><h2>Les ingrédients</h2></html>"),BorderLayout.NORTH);

        ingredientsSelectionPanel = new IngredientsSelectionPanel();
        step2Panel.add(ingredientsSelectionPanel);

        stepsPanel = new StepsPanel();
        step2Panel.add(stepsPanel);



        this.add(step1Panel, BorderLayout.CENTER);

        // Buttons & progress bar
        prevStepBtn = new JButton("Retour");
        nextStepBtn = new JButton("Suivant");

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1,2));
        bottomPanel.add(prevStepBtn);
        bottomPanel.add(nextStepBtn);
        prevStepBtn.setVisible(false);

        this.add(bottomPanel, BorderLayout.SOUTH);

        // Buttons Listeners
        prevStepBtn.addActionListener(new PrevStepListener());
        nextStepBtn.addActionListener(new NextStepListener());
    }

    private class PrevStepListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            if (activeFormStep == 2) {
                refToRecipePanel.remove(step2Panel);
                prevStepBtn.setVisible(false);
                refToRecipePanel.add(step1Panel, BorderLayout.CENTER);
            } else if (activeFormStep == 3) {

            } else if (activeFormStep == 4) {

            }
            activeFormStep--;
            refToRecipePanel.revalidate();
            refToRecipePanel.repaint();
        }
    }

    private class NextStepListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            // validation des input

            if (activeFormStep == 1) {
                refToRecipePanel.remove(step1Panel);
                refToRecipePanel.add(step2Panel);
                prevStepBtn.setVisible(true);
                prevStepBtn.revalidate();
                prevStepBtn.repaint();
            } else if (activeFormStep == 2) {


                ProgressBarWindow progressBarWindow = new ProgressBarWindow();
            }
            activeFormStep++;
            refToRecipePanel.revalidate();
            refToRecipePanel.repaint();
        }
    }

    /*public void changeStepForm() {

    }*/
}
