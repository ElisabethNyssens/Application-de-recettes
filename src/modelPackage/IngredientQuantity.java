package modelPackage;

public class IngredientQuantity {
    private String ingredient;
    private String recipe;
    private int quantity;

    public IngredientQuantity(String ingredient, String recipe, int quantity) {
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.quantity = quantity;
    }

    public String toString() {
        return quantity + " " + ingredient;
    }
}
