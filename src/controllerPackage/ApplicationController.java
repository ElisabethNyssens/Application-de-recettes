package controllerPackage;

import businessPackage.RecipeManager;
import exceptionPackage.AllCategoriesException;
import exceptionPackage.AllIngredientsException;
import exceptionPackage.AllRecipesException;
import exceptionPackage.ConnectionException;
import modelPackage.Category;
import modelPackage.Ingredient;
import modelPackage.Recipe;

import java.util.ArrayList;

public class ApplicationController {
    private RecipeManager recipeManager;

    public ApplicationController() throws ConnectionException {
        recipeManager = new RecipeManager();
    }

    // Create

    // Read
    public ArrayList<Recipe> getAllRecipes() throws AllRecipesException {
        return recipeManager.getAllRecipes();
    }
    public ArrayList<Ingredient> getAllIngredients() throws AllIngredientsException {
        return recipeManager.getAllIngredients();
    }
    public ArrayList<Category> getAllCategories() throws AllCategoriesException {
        return recipeManager.getAllCategories();
    }

    // Update

    // Delete
}
