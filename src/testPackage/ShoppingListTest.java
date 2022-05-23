package testPackage;

import businessPackage.RecipeManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingListTest {
    private Assertions Assert;

    @Test
    public void ingredientMultiplier() throws Exception {
        Assert.assertEquals(1.5, RecipeManager.ingredientMultiplier(6, 4), 0.001);
        Assert.assertEquals(1.0, RecipeManager.ingredientMultiplier(6, 6), 0.001);
        Assert.assertEquals(0.6667, RecipeManager.ingredientMultiplier(4, 6), 0.001);
        Assert.assertEquals(2.0, RecipeManager.ingredientMultiplier(10, 5), 0.001);
        Assert.assertEquals(0.2, RecipeManager.ingredientMultiplier(4, 20), 0.001);
    }

    @Test
    public void newIngredQuantity() throws Exception {
        Assert.assertEquals(7.5, RecipeManager.newIngredQuantity(5, 1.5), 0.001);
        Assert.assertEquals(20, RecipeManager.newIngredQuantity(2, 10), 0.001);
        Assert.assertEquals(0.075, RecipeManager.newIngredQuantity(0.3, 0.25), 0.001);
        Assert.assertEquals(3, RecipeManager.newIngredQuantity(6, 0.5), 0.001);
        Assert.assertEquals(2.04, RecipeManager.newIngredQuantity(1.2, 1.7), 0.001);
    }
}
