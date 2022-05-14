package viewPackage;

import exceptionPackage.ConnectionException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RecipeModifPanel extends JPanel {
    private JLabel title;

    public RecipeModifPanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 40, 40, 40));
        title = new JLabel("<html><h1 style='margin: 30px 0 30px 0; font-size:24px'>Modifier une recette</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);



        this.add(title, BorderLayout.NORTH);

    }
}
