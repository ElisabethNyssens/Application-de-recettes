package businessPackage;

import dataAccessPackage.RecipeDBAccess;
import dataAccessPackage.RecipeDataAccess;
import exceptionPackage.*;

import java.sql.SQLException;
import java.util.ArrayList;

import modelPackage.*;

public class RecipeManager {
    private RecipeDataAccess dao;

    // Constructeur
    public RecipeManager() throws ConnectionException {
        setDao(new RecipeDBAccess());
    }

    // Create
    public void addRecipe(Recipe recipe) {
        dao.addRecipe(recipe);
    }
    public void addStep(Step step) {
        dao.addStep(step);
    }
    public void addIngredientQuantity(IngredientQuantity ingredientQuantity) {
        dao.addIngredientQuantity(ingredientQuantity);
    }

    // Read
    public ArrayList<Recipe> getAllRecipes() throws AllRecipesException {
        return dao.getAllRecipes();
    }
    public ArrayList<Ingredient> getAllIngredients() throws AllIngredientsException {
        return dao.getAllIngredients();
    }
    public ArrayList<Category> getAllCategories() throws AllCategoriesException {
        return dao.getAllCategories();
    }
    public ArrayList<DieteryRegime> getAllRegimes() throws AllRegimesException {
        return dao.getAllRegimes();
    }
    public ArrayList<Author> getAllAuthors() throws AllAuthorsException {
        return dao.getAllAuthors();
    }

    // Setter
    public void setDao(RecipeDataAccess dao) {
        this.dao = dao;
    }
}
