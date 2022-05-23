package businessPackage;

import dataAccessPackage.RecipeDBAccess;
import dataAccessPackage.RecipeDataAccess;
import exceptionPackage.*;
import modelPackage.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

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
    public ArrayList<IngredientQuantity> getAllIngredQuantities() throws AllIngredientsException {
        return dao.getAllIngredQuantities();
    }
    public ArrayList<IngredientQuantity> getAllIngredientsOfRecipe(String recipeName) throws AllIngredQuantitiesException {
        return dao.getAllIngredientsOfRecipe(recipeName);
    }
    public ArrayList<Step> getAllStepsOfRecipe(String recipeName) throws AllStepsException  {
        return dao.getAllStepsOfRecipe(recipeName);
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

    // Update
    public void updateRecipe(Recipe recipe) throws UpdateRecipeException {
        dao.updateRecipe(recipe);
    }

    // Delete
    public void deleteRecipe(String recipeTitle) throws DeleteRecipeException, DeleteStepException, DeleteIngredQuantException {
        dao.deleteRecipe(recipeTitle);
    }
    public void deleteSteps(String recipeTitle) throws DeleteStepException {
        dao.deleteSteps(recipeTitle);
    }
    public void deleteIngredQuants(String recipeTitle) throws DeleteIngredQuantException {
        dao.deleteIngredQuants(recipeTitle);
    }
    public void deleteMenu(String menuTitle) throws DeleteMenuException, DeleteMenuComponentException {
        dao.deleteMenu(menuTitle);
    }
    public void deleteMenuComponents(String menuTitle) throws DeleteMenuComponentException {
        dao.deleteMenuComponents(menuTitle);
    }

    // Searches
    public ArrayList<RecipeWithIngred> searchByIngredRecipes(String ingredients, Boolean with) throws SearchException {
        return dao.searchByIngredRecipes(ingredients, with);
    }
    public ArrayList<Menu> searchMenuByDieteryRegime(String regime) throws SearchException {
        return dao.searchMenuByDieteryRegime(regime);
    }
    public ArrayList<SeasonRecipe> searchBySeason(String category, GregorianCalendar date) throws SearchException {
        return dao.searchBySeason(category, date);
    }
    public ArrayList<RecipeInMenu> searchRecipesInMenu(String menuTitle) throws SearchException {
        return dao.searchRecipesInMenu(menuTitle);
    }

    // Tache metier
    public ArrayList<ShopListIngred> shoppingList(ArrayList<ShopListRecipe> shopListRecipes) throws AllIngredientsException, AllRecipesException, AllIngredQuantitiesException {

        ArrayList<ShopListIngred> shopListIngreds = new ArrayList<>();
        ArrayList<Ingredient> ingredients = dao.getAllIngredients();
        ArrayList<Recipe> recipes = dao.getAllRecipes();

        for (ShopListRecipe shopListRecipe : shopListRecipes) {
            String recipeTitle = shopListRecipe.getRecipe();

            Recipe correspRecipe = recipes.stream().filter(recipe ->
                    recipe.getTitle().equals(recipeTitle)).findFirst().orElse(null);
            int initNbPersons = correspRecipe.getNbPersons();
            double x = shopListRecipe.getNbPersons() / (double) initNbPersons;

            ArrayList<IngredientQuantity> ingredientQuantities = dao.getAllIngredientsOfRecipe(recipeTitle);

            for (IngredientQuantity ingredientQuantity : ingredientQuantities) {
                String ingredient = ingredientQuantity.getIngredient();
                double newQuantity = ingredientQuantity.getQuantity() * x;

                boolean ingredExists = shopListIngreds.stream().anyMatch(i -> i.getIngred().equals(ingredient));
                if (!ingredExists) {
                    String unit = ingredients.stream().filter(i ->
                            i.getName().equals(ingredient)).findFirst().orElse(null).getUnit();
                    shopListIngreds.add(new ShopListIngred(ingredient,newQuantity,unit));

                } else {
                    int iIngred = shopListIngreds.indexOf(shopListIngreds.stream().filter(i ->
                            i.getIngred().equals(ingredient)).findFirst().orElse(null));
                    shopListIngreds.get(iIngred).addQuantity(newQuantity);
                }
            }
        }
        return shopListIngreds;
    }

}
