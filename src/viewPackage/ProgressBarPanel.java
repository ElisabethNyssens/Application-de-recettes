package viewPackage;

import javax.swing.*;
import java.awt.*;

public class ProgressBarPanel extends JPanel {
    private JFrame frame;
    private Bar emptyBar, filledBar;
    private int progressionSpeed = 1;
    private int x = 150;
    private int y = 20;
    private int width = 400;
    private int height = 20;

    public ProgressBarPanel(JFrame frame) {
        this.frame = frame;
        filledBar = new Bar(this, x, y, 0, height);
        emptyBar = new Bar(this, x, y, width, height);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        filledBar.drawFilled(g);
        emptyBar.drawEmpty(g);
    }

    public void progression() {
        filledBar.getRectangle().width += progressionSpeed;
        emptyBar.getRectangle().width -= progressionSpeed;
        emptyBar.getRectangle().x += progressionSpeed;
    }

    public int getProgressionSpeed() {
        return progressionSpeed;
    }

    public int getBarWidth() {
        return width;
    }

    public JFrame getFrame() {
        return frame;
    }
}
