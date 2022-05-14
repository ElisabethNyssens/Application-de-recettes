package viewPackage;

import exceptionPackage.ConnectionException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RecipeCreationPanel extends JPanel {
    private JLabel title;
    private RecipeForm recipeForm;

    public RecipeCreationPanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 40, 40, 40));
        title = new JLabel("<html><h1 style='margin: 30px 0 30px 0; font-size:24px'>Créer une recette</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        try {
            recipeForm = new RecipeForm();
        } catch (ConnectionException exception) {
            exception.getMessage();
        }

        this.add(title, BorderLayout.NORTH);
        this.add(recipeForm, BorderLayout.CENTER);
    }
}
