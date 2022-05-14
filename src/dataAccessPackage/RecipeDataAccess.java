package dataAccessPackage;

import exceptionPackage.*;
import modelPackage.*;

import java.util.ArrayList;

public interface RecipeDataAccess {
     // Create
     void addRecipe(Recipe recipe);
     public void addIngredientQuantity(IngredientQuantity ingredientQuantity);
     void addStep(Step step);
     // Read
     ArrayList<Recipe> getAllRecipes() throws AllRecipesException;
     ArrayList<Category> getAllCategories() throws AllCategoriesException;
     ArrayList<Ingredient> getAllIngredients() throws AllIngredientsException;
     ArrayList<DieteryRegime> getAllRegimes() throws AllRegimesException;
     ArrayList<Author> getAllAuthors() throws AllAuthorsException;
     // Update

     // Delete

}
