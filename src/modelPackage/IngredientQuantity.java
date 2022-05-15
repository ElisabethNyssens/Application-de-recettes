package modelPackage;

public class IngredientQuantity {
    private String ingredient;
    private String recipe;
    private double quantity;

    public IngredientQuantity(String ingredient, String recipe, double quantity) {
        this.ingredient = ingredient;
        this.recipe = recipe;
        setQuantity(quantity);
    }

    public String toString() {
        return quantity + " " + ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getRecipe() {
        return recipe;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }
}
