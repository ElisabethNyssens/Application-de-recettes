package viewPackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HomePanel extends JPanel {
    JLabel title;
    JPanel recipesPanel;

    public HomePanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 50, 50, 50));
        title = new JLabel("<html><h1 style='margin: 30px; font-size: 24px'>Recettes du moment</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);


        recipesPanel = new JPanel();
        recipesPanel.setLayout(new GridLayout(1,3,50,5));
        recipesPanel.add(new RecipeCard("Curry au tofu", "Julien Hanquet","Plat principal","Végan","img/1curry.jpg"));
        recipesPanel.add(new RecipeCard("Salade fraise menthe", "Elisabeth Nyssens","Accompagnement","Végétarien","img/2salad.jpg"));
        recipesPanel.add(new RecipeCard("Soupe carotte gingembre","Julien Hanquet","Soupe","Végétarien","img/3soup.jpg"));

        this.add(title, BorderLayout.NORTH);
        this.add(recipesPanel, BorderLayout.CENTER);
    }
}
