package dataAccessPackage;

import exceptionPackage.*;
import modelPackage.*;

import java.util.ArrayList;

public interface RecipeDataAccess {
     // Create
     void addRecipe(Recipe recipe) throws AddRecipeException;
     void addIngredientQuantity(IngredientQuantity ingredientQuantity) throws AddIngredQuantException;
     void addStep(Step step) throws AddStepException;
     // Read
     ArrayList<Recipe> getAllRecipes() throws AllRecipesException;
     ArrayList<Category> getAllCategories() throws AllCategoriesException;
     ArrayList<Ingredient> getAllIngredients() throws AllIngredientsException;
     ArrayList<DieteryRegime> getAllRegimes() throws AllRegimesException;
     ArrayList<Author> getAllAuthors() throws AllAuthorsException;
     // Update

     // Delete
     void deleteRecipe(String recipeTitle) throws DeleteRecipeException, DeleteStepException, DeleteIngredQuantException;
}
