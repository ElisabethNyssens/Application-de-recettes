package modelPackage;

public class IngredientQuantity {
    private String ingredient;
    private String recipe;
    private double quantity;

    public IngredientQuantity(String ingredient, String recipe, double quantity) {
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.quantity = quantity;
    }

    public String toString() {
        return quantity + " " + ingredient;
    }
}
