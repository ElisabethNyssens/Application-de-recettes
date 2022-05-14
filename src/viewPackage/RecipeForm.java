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
import java.util.GregorianCalendar;

public class RecipeForm extends JPanel {
    private ApplicationController controller;
    private JLabel recipeTitleLabel, dateLabel, recipeCategoryLabel, costLabel, preparationTimeLabel,
            nbPersonsLabel, regimeLabel, seasonLabel;
    private JComboBox recipeCategory, cost, preparationTime, regime, season;
    private JTextField recipeTitle;
    private JSpinner date, nbPersons;
    private JCheckBox hot, sweet, salty;
    private Date today;

    private int activeFormStep;
    private static int NB_CATEGORIES = 9;
    public static int NB_REGIMES = 4;

    private JButton prevStepBtn, nextStepBtn;
    private JPanel step1Panel, step2Panel, bottomPanel;
    private AddIngredientsPanel addIngredientsPanel;
    private AddStepsPanel addStepsPanel;

    private ArrayList<Category> categList;
    private ArrayList<DieteryRegime> regimesList;
    private String[] recipeCategories = new String[NB_CATEGORIES];
    private String[] regimes = new String[NB_REGIMES];
    private final String[] costs = {"Bon marché", "Coût moyen", "Assez cher"};
    private final String[] preparationTimes = {"< 30min", "30min >< 1h", "> 1h"};
    private final String[] seasons = {"Printemps", "Ete", "Automne", "Hiver", "Toute saison"};

    private JPanel refToRecipePanel;


    public RecipeForm() throws ConnectionException {
        controller = new ApplicationController();
        refToRecipePanel = this;
        this.setLayout(new BorderLayout());
        activeFormStep = 1;

        // --------------- Form step 1 ----------------
        step1Panel = new JPanel();
        step1Panel.setBorder(new EmptyBorder(0, 150, 0, 150));
        step1Panel.setLayout(new GridLayout(8, 2));

        // ------ Title -------
        recipeTitleLabel = new JLabel("Nom de la recette* :");
        recipeTitleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        recipeTitle = new JTextField();
        step1Panel.add(recipeTitleLabel);
        step1Panel.add(recipeTitle);

        // ------ Creation date -------
        dateLabel = new JLabel("Date de création* :");
        dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        today = new Date();
        date = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
        JSpinner.DateEditor editor = new JSpinner.DateEditor(date, "dd-MM-yyyy");
        date.setEditor(editor);
        date.setEnabled(false);
        step1Panel.add(dateLabel);
        step1Panel.add(date);

        // ------ Number of persons -------
        nbPersonsLabel = new JLabel("Nombre de personnes* :");
        nbPersonsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        nbPersons = new JSpinner(new SpinnerNumberModel(4, 1, 50, 1));
        step1Panel.add(nbPersonsLabel);
        step1Panel.add(nbPersons);

        // ------ Recipe category -------
        recipeCategoryLabel = new JLabel("Catégorie* :");
        recipeCategoryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        int iCateg = 0;
        try {
            categList = controller.getAllCategories();
            for(Category category : categList) {
                recipeCategories[iCateg] = category.getName();
                //recipeCategories[iCateg] = category.getName() + " (" + category.getId() + ")";
                iCateg++;
            }
        } catch (AllCategoriesException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        recipeCategory = new JComboBox(recipeCategories);
        recipeCategory.setSelectedItem(recipeCategories[5]);
        step1Panel.add(recipeCategoryLabel);
        step1Panel.add(recipeCategory);
        // ------ Cost -------
        costLabel = new JLabel("Prix* :");
        costLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        cost = new JComboBox(costs);
        step1Panel.add(costLabel);
        step1Panel.add(cost);

        // ------ preparation time -------
        preparationTimeLabel = new JLabel("Temps de préparation* :");
        preparationTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        preparationTime = new JComboBox(preparationTimes);
        step1Panel.add(preparationTimeLabel);
        step1Panel.add(preparationTime);

        // ------ Checkboxes -------
        JPanel checkboxPanel = new JPanel();
        hot = new JCheckBox("Chaude");
        sweet = new JCheckBox("Sucrée");
        salty = new JCheckBox("Salée");
        checkboxPanel.add(sweet);
        checkboxPanel.add(salty);
        checkboxPanel.add(hot);
        step1Panel.add(new JPanel());
        step1Panel.add(checkboxPanel);

        // ---------------- Form step 2 -----------------
        step2Panel = new JPanel();
        step2Panel.setLayout(new GridLayout(3,1));
        step2Panel.setBorder(new EmptyBorder(0, 150, 0, 150));

        // ------ Ingredients -------
        addIngredientsPanel = new AddIngredientsPanel(recipeTitle.getText());
        step2Panel.add(addIngredientsPanel);

        // ------ Steps -------
        addStepsPanel = new AddStepsPanel(recipeTitle.getText());
        step2Panel.add(addStepsPanel);

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3,2));
        //gridPanel.setLayout(new FlowLayout(FlowLayout.CENTER,3,3));

        // ------ Dietery regime -------
        regimeLabel = new JLabel("Régime alimentaire :");
        regimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        int iReg = 0;
        try {
            regimesList = controller.getAllRegimes();
            for(DieteryRegime regime : regimesList) {
                regimes[iReg] = regime.getName();
                //regimes[iReg] = regime.getName() + " (" + regime.getId() + ")";
                iReg++;
            }
        } catch (AllRegimesException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
        regime = new JComboBox(regimes);
        regime.setSelectedItem(null);
        gridPanel.add(regimeLabel);
        gridPanel.add(regime);

        // ------ Season -------
        seasonLabel = new JLabel("Saison :");
        seasonLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        season = new JComboBox(seasons);
        season.setSelectedItem(null);
        gridPanel.add(seasonLabel);
        gridPanel.add(season);

        step2Panel.add(gridPanel);

        // ------------------------------------------------
        this.add(step1Panel, BorderLayout.CENTER);

        // Buttons
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
            refToRecipePanel.remove(step2Panel);
            prevStepBtn.setVisible(false);
            refToRecipePanel.add(step1Panel, BorderLayout.CENTER);
            activeFormStep--;

            refToRecipePanel.revalidate();
            refToRecipePanel.repaint();
        }
    }

    private class NextStepListener implements ActionListener {
        public void actionPerformed(ActionEvent event){

            if (activeFormStep == 1) {
                // validation step 1
                if(recipeTitle.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Ta recette a besoin d'un petit nom !");
                } else if (recipeTitle.getText().length() < 3) {
                    JOptionPane.showMessageDialog(null, "C'est un peu court comme nom, tu ne trouves pas ?");
                }
                else {
                    refToRecipePanel.remove(step1Panel);
                    refToRecipePanel.add(step2Panel);
                    prevStepBtn.setVisible(true);
                    prevStepBtn.revalidate();
                    prevStepBtn.repaint();
                    activeFormStep++;
                }
            } else if (activeFormStep == 2) {
                // Validation step 2
                ArrayList<IngredientQuantity> ingredientQuantites = addIngredientsPanel.getIngredientQuantities();
                ArrayList<Step> steps = addStepsPanel.getSteps();

                boolean ingredientMissing = ingredientQuantites.isEmpty();
                boolean stepsMissing = steps.isEmpty();

                if (!ingredientMissing && !stepsMissing) {
                    Date creationDate = (Date) date.getValue();
                    GregorianCalendar creationDateGC = new GregorianCalendar();
                    creationDateGC.setTime(creationDate);
                    String categId = categList.stream().filter(c ->
                            c.getName().equals(recipeCategory.getSelectedItem().toString())).findFirst().orElse(null).getId();
                    String regimeId = regimesList.stream().filter(c ->
                            c.getName().equals(regime.getSelectedItem().toString())).findFirst().orElse(null).getId();

                    Recipe recipe = new Recipe(
                            recipeTitle.getText(),
                            creationDateGC,
                            hot.isSelected(),
                            sweet.isSelected(),
                            salty.isSelected(),
                            cost.getSelectedItem().toString(),
                            preparationTime.getSelectedItem().toString(),
                            Integer.parseInt(nbPersons.getValue().toString()),
                            season.getSelectedItem()!=null?season.getSelectedItem().toString():null,
                            User.getInstance().getPseudo(),
                            regimeId,
                            categId
                    );

                    try {
                        controller.addRecipe(recipe);
                        JOptionPane.showMessageDialog(null, "Mmmh ça a l'air bon ! La recette a bien été enregistrée !");
                        refToRecipePanel.removeAll();
                        refToRecipePanel.revalidate();
                        refToRecipePanel.repaint();
                        refToRecipePanel.add(new RecipeForm());
                    }
                    catch (AddRecipeException | ConnectionException exception) {
                        JOptionPane.showMessageDialog(null, exception.getMessage());
                    }


                    ProgressBarWindow progressBarWindow = new ProgressBarWindow();
                } else {
                    String missingInfo = (ingredientMissing? "les ingrédients\n":"") + (stepsMissing? "les étapes":"");
                    JOptionPane.showMessageDialog(null,
                            "Il nous manque ces information pour créer ta recette :\n" + missingInfo);
                }


            }
            refToRecipePanel.revalidate();
            refToRecipePanel.repaint();
        }
    }

    /*public void changeStepForm() {

    }*/
}
