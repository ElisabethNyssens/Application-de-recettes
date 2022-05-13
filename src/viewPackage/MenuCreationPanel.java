package viewPackage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuCreationPanel extends JPanel {
    private JLabel title;

    public MenuCreationPanel() {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(0, 40, 40, 40));
        title = new JLabel("<html><h1 style='margin: 30px 0 15px 0'>Créer une recette</h1></html>");
        title.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(title, BorderLayout.NORTH);
    }
}
