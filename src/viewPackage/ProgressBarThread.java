package viewPackage;

import exceptionPackage.ConnectionException;

import javax.swing.*;
import java.awt.*;

public class ProgressBarThread extends Thread {
    private ProgressBarPanel progressBar;
    private ProgressBarWindow progressBarWindow;
    private Container mainContainer;
    private boolean update;

    public ProgressBarThread(Container mainContainer, ProgressBarPanel progressBar, ProgressBarWindow progressBarWindow, boolean update) {
        super("ProgressBarThread");
        this.mainContainer = mainContainer;
        this.progressBar = progressBar;
        this.progressBarWindow = progressBarWindow;
        this.update = update;
    }

    public void run() {
        int iMessage = 1;

        for(int i = 0; i < progressBar.getBarWidth(); i += progressBar.getProgressionSpeed()) {
            try {
                progressBar.progression();
                progressBar.repaint();
                sleep(15 * progressBar.getProgressionSpeed());
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

            if (i % (progressBar.getBarWidth()/5) == 0 && i != 0 && iMessage < 5) {
                progressBarWindow.changeMessage(iMessage);
                iMessage++;
            }
        }
        progressBarWindow.dispose();
        if (update) {
            JOptionPane.showMessageDialog(null, "Mmmh ça a l'air encore meilleur ! La recette a bien été modifiée !");
        } else {
            JOptionPane.showMessageDialog(null, "Mmmh ça a l'air bon ! La recette a bien été enregistrée !");
        }
        try {
            mainContainer.removeAll();
            mainContainer.add(new AllRecipesPanel(mainContainer));
            mainContainer.revalidate();
            mainContainer.repaint();
        } catch (ConnectionException exception) {
            exception.printStackTrace();
        }

    }
}
