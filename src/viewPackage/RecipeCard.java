package viewPackage;

import exceptionPackage.ConnectionException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RecipeCard extends JPanel {
    private String title;
    private String author;
    private String category;
    private String regime;
    private String path;
    private BufferedImage photo;
    private JButton preparationBtn;

    public RecipeCard(String title, String author, String category, String regime ,String path) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.regime = regime;
        this.path = path;
        photo = null;

        this.setLayout(new BorderLayout(100,100));
        this.setBorder(new EmptyBorder(200, 50, 50, 50));
        JLabel titleLabel = new JLabel("" +
                "<html><div style='margin:210px 20px 0 20px; font-size:13px'>" +
                "<h2 style='font-size:20px; color:#97002d; font-weight: normal'>"+title+"</h2>" +
                "<h3 style='margin:0 0 20px 0; font-weight: normal'>Par "+author+"</h4><br>" +
                "<p>"+category+"</p><br>"+
                "<p>"+regime+"</p>"+
                "</div></html>");

        setBorder(BorderFactory.createLineBorder(Color.GRAY, 2,true));

        try {
            photo = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("L'image n'a pas été trouvée");
        }

        preparationBtn = new JButton("Préparation");
        preparationBtn.addActionListener(new BtnListener());

        this.add(titleLabel,BorderLayout.NORTH);
        this.add(preparationBtn, BorderLayout.SOUTH);
    }

   protected void paintComponent(Graphics g)
   {
       int photoBorderY = (photo.getHeight()-250)/2;
       int photoBorderX = (photo.getWidth()-this.getWidth())/2;
       g.drawImage(photo, 0, 0, this.getWidth(),250,photoBorderX,photoBorderY,photo.getWidth()-photoBorderX,photo.getHeight()-photoBorderY,null);
   }

    private class BtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                RecipePreparationWindow recipePreparationWindow = new RecipePreparationWindow(title);
            } catch (ConnectionException exception) {
                exception.printStackTrace();
            }
        }
    }
}
