package dataAccessPackage;

import modelPackage.Recipe;

import java.util.ArrayList;

public interface RecipeDataAccess {
     public ArrayList<Recipe> getAllRecipes();
     public void addRecipe(Recipe recipe);
}
