package controllerPackage;

import businessPackage.RecipeManager;
import exceptionPackage.*;
import modelPackage.Category;
import modelPackage.DieteryRegime;
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
    public ArrayList<DieteryRegime> getAllRegimes() throws AllRegimesException {
        return recipeManager.getAllRegimes();
    }

    // Update

    // Delete
}
