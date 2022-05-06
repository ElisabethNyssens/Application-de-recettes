package viewPackage;

import java.awt.*;

public class Bar {
    private Rectangle rectangle;
    private ProgressBarPanel progressBarPanel;

    public Bar(ProgressBarPanel progressBarPanel, int x, int y, int width, int height) {
        rectangle = new Rectangle(x, y, width, height);
        this.progressBarPanel = progressBarPanel;
    }

    public void drawEmpty(Graphics g) {
        g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public void drawFilled(Graphics g) {
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
