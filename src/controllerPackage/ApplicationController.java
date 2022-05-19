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
    public void addMenu(Menu menu) throws AddMenuException {
        recipeManager.addMenu(menu);
    }
    public void addMenuComponent(MenuComponent menuComponent) throws AddMenuComponentException {
        recipeManager.addMenuComponents(menuComponent);
    }

    // Read
    public ArrayList<Recipe> getAllRecipes() throws AllRecipesException {
        return recipeManager.getAllRecipes();
    }
    public ArrayList<Ingredient> getAllIngredients() throws AllIngredientsException {
        return recipeManager.getAllIngredients();
    }
    public ArrayList<IngredientQuantity> getAllIngredientsOfRecipe(String recipeName) throws AllIngredQuantitiesException {
        return recipeManager.getAllIngredientsOfRecipe(recipeName);
    }
    public ArrayList<Step> getAllStepsOfRecipe(String recipeName) throws AllStepsException {
        return recipeManager.getAllStepsOfRecipe(recipeName);
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
    public ArrayList<Menu> getAllMenus() throws AllMenusException {
        return recipeManager.getAllMenus();
    }

    // Update
    public void updateRecipe(Recipe recipe) throws UpdateRecipeException {
        recipeManager.updateRecipe(recipe);
    }

    // Delete
    public void deleteRecipe(String recipeTitle) throws DeleteRecipeException, DeleteStepException, DeleteIngredQuantException {
        recipeManager.deleteRecipe(recipeTitle);
    }
    public void deleteSteps(String recipeTitle) throws DeleteStepException {
        recipeManager.deleteSteps(recipeTitle);
    }
    public void deleteIngredQuants(String recipeTitle) throws DeleteIngredQuantException {
        recipeManager.deleteIngredQuants(recipeTitle);
    }
}
