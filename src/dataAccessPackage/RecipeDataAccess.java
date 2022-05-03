package dataAccessPackage;

import exceptionPackage.AllRecipesException;
import modelPackage.Recipe;

import java.util.ArrayList;

public interface RecipeDataAccess {
     public ArrayList<Recipe> getAllRecipes() throws AllRecipesException;
     public void addRecipe(Recipe recipe);
}
