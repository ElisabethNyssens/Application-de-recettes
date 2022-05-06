package viewPackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class RecipeForm extends JPanel {
    private JLabel recipeTitleLabel, dateLabel, recipeCategoryLabel, costLabel, difficultyLabel, preparationTimeLabel, nbPersonsLabel;
    private JComboBox recipeCategory, cost, difficulty, preparationTime, ingredients;
    private JTextField recipeTitle ;
    private JSpinner date, nbPersons, quantity;
    private JCheckBox tested, hot, sweet, salty;
    private Date today;

    private int activeFormStep;
    private JButton prevStepBtn, nextStepBtn;


    private JPanel step1Panel, step2Panel, bottomPanel, progressBar;

    private final String[] recipeCategories = {"Apéritif", "Entrée", "Plat principal", "Dessert", "Accompagnement", "Snack", "Sauce", "Boisson"};
    private final String[] costs = {"Bon marché", "Coût moyen", "Assez cher"};
    private final String[] difficulties = {"Très facile", "Facile", "Moyen", "Difficile"};
    private final String[] preparationTimes = {"Rapide", "Moyen", "Long"};
    // private final String[] costs = {"€", "€€", "€€€"}; ?


    private JPanel refToRecipePanel;


    public RecipeForm() {
        refToRecipePanel = this;
        this.setLayout(new BorderLayout());
        activeFormStep = 1;

        // Form step 1
        step1Panel = new JPanel();
        step1Panel.setBorder(new EmptyBorder(0, 150, 0, 150));
        step1Panel.setLayout(new GridLayout(10, 2));

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

        recipeCategoryLabel = new JLabel("Catégorie :");
        recipeCategory = new JComboBox(recipeCategories);
        recipeCategory.setSelectedItem(recipeCategories[2]);
        step1Panel.add(recipeCategoryLabel);
        step1Panel.add(recipeCategory);

        tested = new JCheckBox("Je l'ai déjà testée");
        hot = new JCheckBox("Chaude");
        sweet = new JCheckBox("Sucrée");
        salty = new JCheckBox("Salée");
        step1Panel.add(tested);
        step1Panel.add(hot);
        step1Panel.add(sweet);
        step1Panel.add(salty);

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

        nbPersonsLabel = new JLabel("Nombre de personnes :");
        nbPersons = new JSpinner(new SpinnerNumberModel(0, 0, 50, 1));
        step1Panel.add(nbPersonsLabel);
        step1Panel.add(nbPersons);


        // Form step 2




        // Form step 3




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
                //refToRecipePanel.remove(step2Panel);
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

            // à changer en state pattern ?
            if (activeFormStep == 1) {
                refToRecipePanel.remove(step1Panel);
                prevStepBtn.setVisible(true);
                prevStepBtn.revalidate();
                prevStepBtn.repaint();
            } else if (activeFormStep == 2) {

            } else if (activeFormStep == 3) {

            } else if (activeFormStep == 4) {

            }
            activeFormStep++;
            refToRecipePanel.revalidate();
            refToRecipePanel.repaint();
        }
    }

    /*public void changeStepForm() {

    }*/
}
