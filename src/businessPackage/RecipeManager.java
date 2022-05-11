package businessPackage;

import dataAccessPackage.RecipeDBAccess;
import dataAccessPackage.RecipeDataAccess;
import exceptionPackage.AllCategoriesException;
import exceptionPackage.AllIngredientsException;
import exceptionPackage.AllRecipesException;

import java.sql.SQLException;
import java.util.ArrayList;

import exceptionPackage.ConnectionException;
import modelPackage.Category;
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

    // Setter
    public void setDao(RecipeDataAccess dao) {
        this.dao = dao;
    }
}
