package businessPackage;

import dataAccessPackage.RecipeDBAccess;
import dataAccessPackage.RecipeDataAccess;
import exceptionPackage.AllRecipesException;
import java.util.ArrayList;

import modelPackage.Recipe;

public class RecipeManager {
    private RecipeDataAccess dao;

    // Constructeur
    public RecipeManager() {
        setDao(new RecipeDBAccess());
    }

    // Methodes
    public ArrayList<Recipe> getAllRecipes() throws AllRecipesException {
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