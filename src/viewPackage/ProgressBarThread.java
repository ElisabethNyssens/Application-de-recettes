package viewPackage;

import javax.swing.*;

public class ProgressBarThread extends Thread {
    private ProgressBarPanel progressBar;
    private ProgressBarWindow progressBarWindow;

    public ProgressBarThread(ProgressBarPanel progressBar, ProgressBarWindow progressBarWindow) {
        super("ProgressBarThread");
        this.progressBar = progressBar;
        this.progressBarWindow = progressBarWindow;
    }

    public void run() {
        int iMessage = 1;

        for(int i = 0; i < progressBar.getBarWidth(); i += progressBar.getProgressionSpeed()) {
            try {
                progressBar.progression();
                progressBar.repaint();
                sleep(20 * progressBar.getProgressionSpeed());
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
    }
}
