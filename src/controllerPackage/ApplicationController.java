package controllerPackage;

import businessPackage.RecipeManager;
import exceptionPackage.*;
import modelPackage.*;

import java.util.ArrayList;

public class ApplicationController {
    private RecipeManager recipeManager;

    public ApplicationController() throws ConnectionException {
        recipeManager = new RecipeManager();
    }

    // Create
    public void addRecipe(Recipe recipe) throws AddRecipeException {
        recipeManager.addRecipe(recipe);
    }
    public void addStep(Step step) throws AddStepException {
        recipeManager.addStep(step);
    }
    public void addIngredientQuantity(IngredientQuantity ingredientQuantity) throws AddIngredQuantException {
        recipeManager.addIngredientQuantity(ingredientQuantity);
    }
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
    public ArrayList<Author> getAllAuthors() throws AllAuthorsException {
        return recipeManager.getAllAuthors();
    }

    // Update

    // Delete
}
