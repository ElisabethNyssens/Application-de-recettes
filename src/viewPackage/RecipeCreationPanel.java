package viewPackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RecipeCreationPanel extends JPanel {
    private JLabel title;
    private RecipeForm recipeForm;

    public RecipeCreationPanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 50, 50, 50));
        title = new JLabel("<html><h1 style='margin: 30px 0 15px 0'>Créer une recette</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        recipeForm = new RecipeForm();

        this.add(title, BorderLayout.NORTH);
        this.add(recipeForm, BorderLayout.CENTER);
    }
}
