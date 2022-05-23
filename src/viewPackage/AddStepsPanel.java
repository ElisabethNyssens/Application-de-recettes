package viewPackage;

import controllerPackage.ApplicationController;
import exceptionPackage.ConnectionException;
import modelPackage.Step;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddStepsPanel extends JPanel {
    private ApplicationController controller;
    private final static int NB_MAX_STEPS = 20;
    private Object[] stepsObj;
    private int nbSteps;
    private JLabel stepLabel, numberLabel;
    private JTextArea step;
    private JPanel numberContainer, stepContainer;
    private JSpinner stepNumber;
    private JButton addStepBtn, removeStepBtn;
    private JList stepsList;
    private ArrayList<Step> steps;
    private RecipeCreationForm parentPanel;

    public AddStepsPanel(RecipeCreationForm parentPanel) throws ConnectionException {
        this.parentPanel = parentPanel;
        stepsObj = new Object[NB_MAX_STEPS];
        steps = new ArrayList<>();
        nbSteps = 0;

        JPanel stepPanel = new JPanel();
        stepPanel.setLayout(new BorderLayout());

        numberContainer = new JPanel();
        numberContainer.setLayout(new FlowLayout(FlowLayout.LEFT,3,3));
        numberLabel = new JLabel("Numéro* :");
        stepNumber = new JSpinner(new SpinnerNumberModel(1,1,null,1));
        stepNumber.addChangeListener(new NumberChangeListener());
        numberContainer.add(numberLabel);
        numberContainer.add(stepNumber);

        stepContainer = new JPanel();
        stepLabel = new JLabel("Etape* :");
        step = new JTextArea(6,24);
        step.setWrapStyleWord(true);
        step.setLineWrap(true);
        stepContainer.add(stepLabel);
        stepContainer.add(new JScrollPane(step));

        stepPanel.add(numberContainer, BorderLayout.NORTH);
        stepPanel.add(stepContainer, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(2, 1));
        addStepBtn = new JButton("Ajouter l'étape >>");
        removeStepBtn = new JButton("<< Retirer l'étape");
        addStepBtn.addActionListener(new AddButtonListener());
        removeStepBtn.addActionListener(new RemoveButtonListener());
        btnPanel.add(addStepBtn);
        btnPanel.add(removeStepBtn);

        stepsList = new JList();
        stepsList.setFixedCellWidth(250);
        stepsList.setFixedCellHeight(20);
        stepsList.setVisibleRowCount(8);
        stepsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        this.add(stepPanel);
        this.add(btnPanel);
        this.add(new JScrollPane(stepsList));
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (!step.getText().isBlank()) {
                if (nbSteps <= NB_MAX_STEPS) {
                    int selectNumber = Integer.parseInt(stepNumber.getValue().toString());

                    if (selectNumber <= nbSteps) {
                        steps.set(selectNumber - 1, new Step(selectNumber, parentPanel.getRecipeTitle(), step.getText()));
                        stepsObj[selectNumber - 1] = (selectNumber) + ". " + step.getText();
                    } else {
                        steps.add(new Step(nbSteps + 1, parentPanel.getRecipeTitle(), step.getText()));
                        stepsObj[nbSteps] = (nbSteps + 1) + ". " + step.getText();
                        nbSteps++;
                        stepNumber.setValue(nbSteps + 1);
                        step.setText(null);
                    }
                    stepsList.setListData(stepsObj);

                    AddStepsPanel.this.repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Tu ne peux pas ajouter plus d'étapes, 20 c'est déjà assez !");
                }
            }
        }
    }

    private class RemoveButtonListener implements ActionListener {
        public void actionPerformed( ActionEvent event) {
            if (stepsList.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(null, "Sélectionne une étape !");
            } else {
                int iSelectStep = stepsList.getSelectedIndex();
                for (int i = iSelectStep; i < nbSteps; i++) {
                    if (i == nbSteps-1) {
                        stepsObj[i] = null;
                    } else {
                        stepsObj[i] = (i+1) + ". " + steps.get(i+1).getDescription();
                    }
                }
                steps.remove(iSelectStep);
                for (int i = 0; i < steps.size(); i++) {
                    steps.get(i).setOrderNumber(i+1);
                }
                nbSteps--;
                stepsList.setListData(stepsObj);
                AddStepsPanel.this.repaint();
            }
        }
    }

    private class NumberChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            int selectNumber = Integer.parseInt(stepNumber.getValue().toString());
            if (selectNumber > nbSteps) {
                step.setText(null);
                stepNumber.setValue(nbSteps+1);
            } else if (selectNumber <= nbSteps){
                step.setText(steps.get(selectNumber-1).getDescription());
            }
            AddStepsPanel.this.repaint();
        }
    }
}
