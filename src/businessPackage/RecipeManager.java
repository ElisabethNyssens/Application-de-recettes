package businessPackage;

import dataAccessPackage.RecipeDBAccess;
import dataAccessPackage.RecipeDataAccess;
import exceptionPackage.*;

import java.sql.SQLException;
import java.util.ArrayList;

import modelPackage.Category;
import modelPackage.DieteryRegime;
import modelPackage.Ingredient;
import modelPackage.Recipe;

public class RecipeManager {
    private RecipeDataAccess dao;

    // Constructeur
    public RecipeManager() throws ConnectionException {
        setDao(new RecipeDBAccess());
    }

    // Create
    public void addRecipe(Recipe recipe) {
        dao.addRecipe(recipe);
    }

    // Read
    public ArrayList<Recipe> getAllRecipes() throws AllRecipesException {
        return dao.getAllRecipes();
    }
    public ArrayList<Ingredient> getAllIngredients() throws AllIngredientsException {
        return dao.getAllIngredients();
    }
    public ArrayList<Category> getAllCategories() throws AllCategoriesException {
        return dao.getAllCategories();
    }
    public ArrayList<DieteryRegime> getAllRegimes() throws AllRegimesException {
        return dao.getAllRegimes();
    }

    // Setter
    public void setDao(RecipeDataAccess dao) {
        this.dao = dao;
    }
}
