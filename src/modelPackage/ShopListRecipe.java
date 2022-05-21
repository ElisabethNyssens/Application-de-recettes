package modelPackage;

public class ShopListRecipe {
    String recipeTitle;
    int nbPersons;

    public ShopListRecipe(String recipeTitle, int nbPersons) {
        this.recipeTitle = recipeTitle;
        this.nbPersons = nbPersons;
    }

    public String getRecipe() {
        return recipeTitle;
    }

    public int getNbPersons() {
        return nbPersons;
    }
}
