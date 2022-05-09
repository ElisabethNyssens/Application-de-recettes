package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WelcomeThread extends Thread {
    private WelcomeWindow welcomeWindow;
    private Container mainContainer;
    private FoodAnimPanel foodAnimPanel;
    private List<FoodIcon> foodIcons;
    private Graphics g;

    public WelcomeThread(WelcomeWindow welcomeWindow) {
        this.welcomeWindow = welcomeWindow;
        this.mainContainer = welcomeWindow.getMainContainer();
        this.foodAnimPanel = welcomeWindow.getFoodAnimPanel();
        this.foodIcons = foodAnimPanel.getFoodIcons();
    }

    public void run() {
        for (int i = 0; i < 300; i++) {
            try {
                sleep(30);
                for (FoodIcon foodIcon : foodIcons) {
                    foodIcon.move();
                    foodAnimPanel.repaint();
                }
            }
            catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        mainContainer.remove(foodAnimPanel);
        welcomeWindow.repaint();

    }
}
