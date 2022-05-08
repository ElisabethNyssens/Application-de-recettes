package viewPackage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FoodIcon {
    private int x;
    private int y;
    private int width = 30;
    private int height = 30;
    private int deltaY;
    //private Rectangle frame;
    private String path;
    private BufferedImage photo;

    public FoodIcon(int x, int y, int deltaY, String path) {
        this.x = x;
        this.y = y;
        this.deltaY = deltaY;
        //frame = new Rectangle(x,y,width,height);
        this.path = path;
        photo = null;

        try {
            photo = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("L'image n'a pas été trouvée");
        }
    }

    public void draw(Graphics g) {
        //Graphics2D g2 = (Graphics2D) g;
        //g2.rotate(Math.PI / 4, photo.getWidth() / 2, photo.getHeight() / 2);
        g.drawImage(photo,x,y,width,height,null);
    }
    /*public void draw(Graphics g){
        g.drawImage(photo, 0, 0,width,height,null);

    }*/

    public void move(){
        this.y += deltaY;
    }

    public int getY() {
        return y;
    }
}
