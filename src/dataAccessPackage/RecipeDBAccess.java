package dataAccessPackage;

import exceptionPackage.AllRecipesException;
import exceptionPackage.ConnectionException;
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

    // Update

    // Delete

}
