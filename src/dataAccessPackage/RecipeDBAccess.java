package dataAccessPackage;

import exceptionPackage.AllCategoriesException;
import exceptionPackage.AllIngredientsException;
import exceptionPackage.AllRecipesException;
import exceptionPackage.ConnectionException;
import modelPackage.Category;
import modelPackage.Ingredient;
import modelPackage.Recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class RecipeDBAccess implements RecipeDataAccess {
    private Connection singletonConnection;

    public RecipeDBAccess() throws ConnectionException {
        singletonConnection = SingletonConnexion.getInstance();
    }

    // Create
    @Override
    public void addRecipe(Recipe recipe) {

    }

    // Read
    @Override
    public ArrayList <Recipe> getAllRecipes() throws AllRecipesException {
        ArrayList <Recipe> allRecipes = new ArrayList <>();
        String SQLInstruction = "select * from recipes";

        try {
            PreparedStatement preparedStatement = singletonConnection.prepareStatement(SQLInstruction);
            ResultSet data = preparedStatement.executeQuery();
            Recipe recipe;
            GregorianCalendar creationDate;

            while(data.next()) {
                creationDate = new GregorianCalendar();
                creationDate.setTime(data.getDate("creation_date"));
                recipe = new Recipe(
                        data.getString("title"),
                        creationDate,
                        data.getBoolean("is_hot"),
                        data.getBoolean("is_sweet"),
                        data.getBoolean("is_salty"),
                        data.getString("budget"),
                        data.getString("difficulty"),
                        data.getString("preparation_time"),
                        data.getInt("nb_persons")
                        // Gérer attributs facultatifs
                   /*     data.getString("season"),
                        data.getString("comment")*/
                );

                allRecipes.add(recipe);
            }
            return allRecipes;
        }
        catch (SQLException exception) {
            throw new AllRecipesException();
        }
    }

    @Override
    public ArrayList<Category> getAllCategories() throws AllCategoriesException {
        ArrayList <Category> allCategories = new ArrayList <>();
        String SQLInstruction = "select * from categories";

        try {
            PreparedStatement preparedStatement = singletonConnection.prepareStatement(SQLInstruction);
            ResultSet data = preparedStatement.executeQuery();
            Category category;

            while(data.next()) {
                category = new Category(
                        data.getString("id"),
                        data.getString("name")
                );
                allCategories.add(category);
            }
            return allCategories;
        }
        catch (SQLException exception) {
            throw new AllCategoriesException();
        }
    }

    @Override
    public ArrayList<Ingredient> getAllIngredients() throws AllIngredientsException {
        ArrayList <Ingredient> allIngredients = new ArrayList <>();
        String SQLInstruction = "select * from ingredients";

        try {
            PreparedStatement preparedStatement = singletonConnection.prepareStatement(SQLInstruction);
            ResultSet data = preparedStatement.executeQuery();
            Ingredient ingredient;

            while(data.next()) {
                ingredient = new Ingredient(
                        data.getString("name"),
                        data.getString("unit")
                );
                allIngredients.add(ingredient);
            }
            return allIngredients;
        }
        catch (SQLException exception) {
            throw new AllIngredientsException();
        }
    }

    // Update

    // Delete

}
