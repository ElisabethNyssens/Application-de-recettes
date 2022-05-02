package viewPackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RecipeCard extends JPanel {
    private String title;
    private String path;
    private BufferedImage photo;

    public RecipeCard(String title, String path) {
        this.setLayout(new BorderLayout(100,100));

        JLabel titleLabel = new JLabel("<html><h2 style='margin:210px 0 0 20px;'>"+title+"</h3></html>");
        this.path = path;
        photo = null;

        setBorder(BorderFactory.createLineBorder(Color.GRAY, 2,true));


        try {
            photo = ImageIO.read(new File(path));

           /* autre option pour les photos : en faire des JLabel, peut être mieux dans certains cas
           mais ici, problème de positionement et difficile de régler la taille

            JLabel photoLabel = new JLabel(new ImageIcon(photo));
            this.add(photoLabel, BorderLayout.NORTH);*/

        } catch (IOException e) {
            System.out.println("L'image n'a pas été trouvée");
        }

        this.add(titleLabel,BorderLayout.NORTH);

    }

   protected void paintComponent(Graphics g)
   {
       int photoBorderY = (photo.getHeight()-250)/2;
       int photoBorderX = (photo.getWidth()-this.getWidth())/2;
       g.drawImage(photo, 0, 0, this.getWidth(),250,photoBorderX,photoBorderY,photo.getWidth()-photoBorderX,photo.getHeight()-photoBorderY,null);
   }
}
