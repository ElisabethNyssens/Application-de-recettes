package dataAccessPackage;

import exceptionPackage.AllCategoriesException;
import exceptionPackage.AllIngredientsException;
import exceptionPackage.AllRecipesException;
import modelPackage.Category;
import modelPackage.Ingredient;
import modelPackage.Recipe;

import java.util.ArrayList;

public interface RecipeDataAccess {
     // Create
     void addRecipe(Recipe recipe);
     // Read
     ArrayList<Recipe> getAllRecipes() throws AllRecipesException;
     ArrayList<Category> getAllCategories() throws AllCategoriesException;
     ArrayList<Ingredient> getAllIngredients() throws AllIngredientsException;
     // Update

     // Delete

}
