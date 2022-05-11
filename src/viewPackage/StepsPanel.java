package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StepsPanel extends JPanel {
    private static int NB_MAX_STEPS = 32;
    protected Object[] steps;
    protected int nbSteps;

    private JLabel stepLabel, numberLabel;
    private JTextArea step;
    private JPanel numberContainer, stepContainer;
    protected JSpinner stepNumber;
    private JButton addStepBtn, resetBtn;
    private JList stepsList;

    public StepsPanel() {
        steps = new Object[NB_MAX_STEPS];
        nbSteps = 0;

        JPanel stepPanel = new JPanel();

        numberContainer = new JPanel();
        numberLabel = new JLabel("Numéro :");
        stepNumber = new JSpinner(new SpinnerNumberModel());
        stepNumber.setValue(nbSteps + 1);
        stepNumber.setEnabled(false);
        numberContainer.add(numberLabel);
        numberContainer.add(stepNumber);

        stepContainer = new JPanel();
        stepLabel = new JLabel("Etape :");
        step = new JTextArea(6,25);
        step.setLineWrap(true);
        stepContainer.add(stepLabel);
        stepContainer.add(new JScrollPane(step));

        stepPanel.add(numberContainer);
        stepPanel.add(stepContainer);

        addStepBtn = new JButton("Ajouter l'étape >>");
        addStepBtn.addActionListener(new StepsPanel.AddButtonListener());

        stepsList = new JList();
        stepsList.setFixedCellWidth(250);
        stepsList.setFixedCellHeight(20);
        stepsList.setVisibleRowCount(10);
        stepsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        this.add(stepPanel);
        this.add(addStepBtn);
        this.add(new JScrollPane(stepsList));
    }

    private class AddButtonListener implements ActionListener {
        public void actionPerformed( ActionEvent event) {
            steps[nbSteps] = (nbSteps+1) + ". " + step.getText();
            nbSteps++;
            stepNumber.setValue(nbSteps + 1);
            stepsList.setListData(steps);
            StepsPanel.this.repaint();
        }
    }
}
