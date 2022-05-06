package dataAccessPackage;

import exceptionPackage.AllRecipesException;
import modelPackage.Recipe;

import java.util.ArrayList;

public interface RecipeDataAccess {
     // Create
     void addRecipe(Recipe recipe);
     // Read
     ArrayList<Recipe> getAllRecipes() throws AllRecipesException;
     // Update

     // Delete

}
