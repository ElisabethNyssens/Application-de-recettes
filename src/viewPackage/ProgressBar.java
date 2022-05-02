package viewPackage;

import javax.swing.*;
import java.awt.*;

public class ProgressBar extends JPanel{
    private Rectangle bar;
    private Rectangle progress;
    private int percentProgress;

    public ProgressBar() {
        // trouver un moyen de la centrer
        bar = new Rectangle(200,10);
        this.percentProgress = 25;
        progress = new Rectangle(0,0,bar.width*percentProgress/100,10);
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRoundRect(bar.x, bar.y, bar.width, bar.height,10,10);
        g.setColor(Color.BLUE);
        g.fillRoundRect(progress.x, progress.y, progress.width, progress.height,10,10);
    }

    public void paint(Graphics g){
        super.paint(g);
        this.draw(g);
    }

    public int width(){
        return this.getWidth();
    }
}
