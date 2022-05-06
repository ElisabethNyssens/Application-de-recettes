package dataAccessPackage;

import exceptionPackage.AllRecipesException;
import modelPackage.Recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class RecipeDBAccess implements RecipeDataAccess {
    private Connection singletonConnection;

    public RecipeDBAccess() throws SQLException {
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
            String recipeName;

            while(data.next()) {
                recipe = new Recipe(
                        data.getString("title")
                );

                allRecipes.add(recipe);
            }
            return allRecipes;
        }
        catch (SQLException exception) {
            throw new AllRecipesException (exception.getMessage());
        }

    }

    // Update

    // Delete

}
