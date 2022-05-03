package viewPackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AllRecipesPanel extends JPanel {

    private JLabel title;

    public AllRecipesPanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 50, 50, 50));
        title = new JLabel("<html><h1 style='margin: 30px 0 15px 0'>Recettes</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // getAllRecipes()

        this.add(title, BorderLayout.NORTH);
        // ajout JTable
    }


}
