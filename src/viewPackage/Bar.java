package viewPackage;

import java.awt.*;

public class Bar {
    private Rectangle rectangle;
    private ProgressBarPanel progressBarPanel;
    private int height;

    public Bar(ProgressBarPanel progressBarPanel, int x, int y, int width, int height) {
        this.height = height;
        rectangle = new Rectangle(x, y, width, height);
        this.progressBarPanel = progressBarPanel;
    }

    public void drawEmpty(Graphics g) {
        g.drawRoundRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height,height,height);
    }

    public void drawFilled(Graphics g) {
        g.fillRoundRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height,height,height);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
