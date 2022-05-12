package modelPackage;

public class IngredientQuantity {
    private String ingredient;
    private String recipe;
    private int quantity;

    public IngredientQuantity(String ingredient, String recipe, int quantity) {
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

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }
}
