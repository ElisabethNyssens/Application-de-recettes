package businessPackage;

import dataAccessPackage.RecipeDBAccess;
import dataAccessPackage.RecipeDataAccess;
import modelPackage.Recipe;

import java.util.ArrayList;

public class RecipeManager {
    private RecipeDataAccess dao;

    // Constructeur
    public RecipeManager() {
        setDao(new RecipeDBAccess());
    }

    // Methodes
    public ArrayList<Recipe> getAllRecipes() {
        return dao.getAllRecipes();
    }
    public void addRecipe(Recipe recipe) {
        dao.addRecipe(recipe);
    }

    // Setter
    public void setDao(RecipeDataAccess dao) {
        this.dao = dao;
    }
}
