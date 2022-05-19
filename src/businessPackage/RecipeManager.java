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
        dao = new RecipeDBAccess();
    }

    // Create
    public void addRecipe(Recipe recipe) throws AddRecipeException{
        dao.addRecipe(recipe);
    }
    public void addStep(Step step) throws AddStepException {
        dao.addStep(step);
    }
    public void addIngredientQuantity(IngredientQuantity ingredientQuantity) throws AddIngredQuantException {
        dao.addIngredientQuantity(ingredientQuantity);
    }
    public void addMenu(Menu menu) throws AddMenuException {
        dao.addMenu(menu);
    }
    public void addMenuComponents(MenuComponent menuComponent) throws AddMenuComponentException {
        dao.addMenuComponent(menuComponent);
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
    public ArrayList<Menu> getAllMenus() throws  AllMenusException {
        return dao.getAllMenus();
    }
}
