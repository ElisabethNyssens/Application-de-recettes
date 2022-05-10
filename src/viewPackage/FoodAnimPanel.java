package viewPackage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FoodAnimPanel extends JPanel {
    private JFrame container;
    private ArrayList<FoodIcon> foodArray = new ArrayList<>();
    private List<FoodIcon> foodIcons = Collections.synchronizedList(foodArray);

    public FoodAnimPanel(JFrame container) {
        this.container = container;
        this.setOpaque(false);
        setSize(container.getWidth(), container.getHeight());

        foodIcons.add(new FoodIcon(140,-75,4,"img/aubergine.png"));
        foodIcons.add(new FoodIcon(400,-25,3,"img/broccoli.png"));
        foodIcons.add(new FoodIcon(860,-90,4,"img/carrot.png"));
        foodIcons.add(new FoodIcon(1050,-20,3,"img/cheese.png"));
        foodIcons.add(new FoodIcon(180,-150,3,"img/grapes.png"));
        foodIcons.add(new FoodIcon(600,-140,4,"img/lemon.png"));
        foodIcons.add(new FoodIcon(900,-70,3,"img/lettuce.png"));
        foodIcons.add(new FoodIcon(60,-20,3,"img/meat.png"));
        foodIcons.add(new FoodIcon(300,-130,4,"img/milk.png"));
        foodIcons.add(new FoodIcon(650,-120,3,"img/orange.png"));
        foodIcons.add(new FoodIcon(1150,-120,4,"img/pineapple.png"));
        foodIcons.add(new FoodIcon(240,-50,3,"img/raspberry.png"));
        foodIcons.add(new FoodIcon(450,-190,5,"img/shrimp.png"));
        foodIcons.add(new FoodIcon(520,-100,3,"img/steak.png"));
        foodIcons.add(new FoodIcon(700,-160,5,"img/strawberry.png"));
        foodIcons.add(new FoodIcon(750,-15,3,"img/watermelon.png"));
        foodIcons.add(new FoodIcon(1000,-200,5,"img/zucchini.png"));
        foodIcons.add(new FoodIcon(950,-150,3,"img/egg.png"));

        foodIcons.add(new FoodIcon(120,-275,4,"img/orange.png"));
        foodIcons.add(new FoodIcon(410,-200,3,"img/strawberry.png"));
        foodIcons.add(new FoodIcon(860,-220,3,"img/raspberry.png"));
        foodIcons.add(new FoodIcon(1070,-290,4,"img/shrimp.png"));
        foodIcons.add(new FoodIcon(330,-500,4,"img/watermelon.png"));
        foodIcons.add(new FoodIcon(570,-280,3,"img/milk.png"));
        foodIcons.add(new FoodIcon(1030,-290,3,"img/steak.png"));
        foodIcons.add(new FoodIcon(60,-220,3,"img/pineapple.png"));
        foodIcons.add(new FoodIcon(300,-330,4,"img/lemon.png"));
        foodIcons.add(new FoodIcon(150,-320,3,"img/egg.png"));
        foodIcons.add(new FoodIcon(1150,-320,4,"img/meat.png"));
        foodIcons.add(new FoodIcon(250,-280,3,"img/zucchini.png"));
        foodIcons.add(new FoodIcon(450,-390,4,"img/cheese.png"));
        foodIcons.add(new FoodIcon(470,-320,3,"img/lettuce.png"));
        foodIcons.add(new FoodIcon(700,-210,3,"img/carrot.png"));
        foodIcons.add(new FoodIcon(800,-350,4,"img/grapes.png"));
        foodIcons.add(new FoodIcon(1000,-400,4,"img/broccoli.png"));
        foodIcons.add(new FoodIcon(950,-320,3,"img/aubergine.png"));
    }

    public void paint(Graphics g){
        super.paint(g);

        for (FoodIcon foodIcon : foodIcons) {
            foodIcon.draw(g);
        }
    }

    public List<FoodIcon> getFoodIcons() {
        return foodIcons;
    }
}
