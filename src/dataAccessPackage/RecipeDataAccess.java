package dataAccessPackage;

import exceptionPackage.*;
import modelPackage.*;

import java.util.ArrayList;

public interface RecipeDataAccess {
     // Create
     void addRecipe(Recipe recipe) throws AddRecipeException;
     void addIngredientQuantity(IngredientQuantity ingredientQuantity) throws AddIngredQuantException;
     void addStep(Step step) throws AddStepException;
     void addMenu(Menu menu) throws AddMenuException;
     void addMenuComponent(MenuComponent menuComponent) throws AddMenuComponentException;
     // Read
     ArrayList<Recipe> getAllRecipes() throws AllRecipesException;
     ArrayList<Category> getAllCategories() throws AllCategoriesException;
     ArrayList<Ingredient> getAllIngredients() throws AllIngredientsException;
     ArrayList<IngredientQuantity> getAllIngredientsOfRecipe(String recipeName) throws AllIngredQuantitiesException;
     ArrayList<Step> getAllStepsOfRecipe(String recipeName) throws AllStepsException;
     ArrayList<DieteryRegime> getAllRegimes() throws AllRegimesException;
     ArrayList<Author> getAllAuthors() throws AllAuthorsException;
     ArrayList<Menu> getAllMenus() throws AllMenusException;
     // Update
     void updateRecipe(Recipe recipe) throws UpdateRecipeException;
  /*   void updateIngredientQuantity(IngredientQuantity ingredientQuantity) throws UpdateRecipeException;
     void updateStep(Step step) throws UpdateRecipeException;*/
     // Delete
     void deleteRecipe(String recipeTitle) throws DeleteRecipeException, DeleteStepException, DeleteIngredQuantException;
     void deleteSteps(String recipeTitle) throws DeleteStepException;
     void deleteIngredQuants(String recipeTitle) throws DeleteIngredQuantException;
}
