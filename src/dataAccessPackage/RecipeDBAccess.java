package dataAccessPackage;

import exceptionPackage.AllRecipesException;
import modelPackage.Recipe;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecipeDBAccess implements RecipeDataAccess {

    @Override
    public ArrayList <Recipe> getAllRecipes() throws AllRecipesException {
        Connection singletonConnexion;
        ArrayList <Recipe> allRecipes = new ArrayList <>();
        try {
            String instructionSQL = "select * from recipes";
            // Essayer d’accéder à la base de données via SingletonConnexion.getInstance()
            singletonConnexion = SingletonConnexion.getInstance();
            // Essayer de lire les recettes dans la table recipes
            // Créer et retourner une ArrayList de recettes
        }
        catch (SQLException exception) {
            throw new AllRecipesException (exception.getMessage());
        }

        return allRecipes;
    }

    @Override
    public void addRecipe(Recipe recipe) {

    }
}
