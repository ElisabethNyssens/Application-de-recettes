package controllerPackage;

import businessPackage.RecipeManager;
import exceptionPackage.AllRecipesException;
import modelPackage.Recipe;

import java.sql.SQLException;
import java.util.ArrayList;

public class ApplicationController {
    private RecipeManager manager;

    public ApplicationController() throws SQLException {
        manager = new RecipeManager();
    }

    // Create

    // Read
    public ArrayList<Recipe> getAllRecipes() throws AllRecipesException {
        return manager.getAllRecipes();
    }
    // Update

    // Delete
}
