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

    // getters

    public String getIngredient() {
        return ingredient;
    }

    public String getRecipe() {
        return recipe;
    }

    public double getQuantity() {
        return quantity;
    }

    // setters

    public void setRecipe(String recipe) {
        if (recipe.length() <= 100) this.recipe = recipe;
    }

    public void setIngredient(String ingredient) {
        if (ingredient.length() <= 30) this.ingredient = ingredient;
    }

    public void setQuantity(double quantity) {
        if (quantity > 0) this.quantity = quantity;
    }
}
