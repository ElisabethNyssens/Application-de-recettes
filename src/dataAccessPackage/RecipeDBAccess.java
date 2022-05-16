package dataAccessPackage;

import exceptionPackage.*;
import modelPackage.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class RecipeDBAccess implements RecipeDataAccess {
    private Connection connection;

    public RecipeDBAccess() throws ConnectionException {
        connection = SingletonConnexion.getInstance();
    }

    // Create
    @Override
    public void addRecipe(Recipe recipe) throws AddRecipeException {
        String sql = "insert into recipes (title,creation_date,is_hot,is_sweet,is_salty,budget,preparation_time,nb_persons,author,category) values (?,?,?,?,?,?,?,?,?,?)";
        GregorianCalendar calendar = recipe.getCreationDate();
        java.sql.Date sqlDate = new Date(calendar.getTimeInMillis());

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, recipe.getTitle());
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setBoolean(3, recipe.isHot());
            preparedStatement.setBoolean(4, recipe.isSweet());
            preparedStatement.setBoolean(5, recipe.isSalty());
            preparedStatement.setString(6, recipe.getBudget());
            preparedStatement.setString(7, recipe.getPreparationTime());
            preparedStatement.setInt(8, recipe.getNbPersons());
            preparedStatement.setString(9, recipe.getAuthor());
            preparedStatement.setString(10, recipe.getCategory());
            preparedStatement.executeUpdate();

            if(recipe.getSeason() != null) {
                sql = "update recipes set season = ? where title = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, recipe.getSeason());
                preparedStatement.setString(2, recipe.getTitle());
                preparedStatement.executeUpdate();
            }

            if(recipe.getRegime() != null) {
                sql = "update recipes set dietery_regime = ? where title = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, recipe.getRegime());
                preparedStatement.setString(2, recipe.getTitle());
                preparedStatement.executeUpdate();
            }
        }
        catch(SQLException exception) {
            throw new AddRecipeException();
        }

    }
    @Override
    public void addStep(Step step) throws AddStepException {


    }
    @Override
    public void addIngredientQuantity(IngredientQuantity ingredientQuantity) throws AddIngredQuantException {

    }

    @Override
    public void addMenu(Menu menu) throws AddMenuException {

    }

    @Override
    public void addMenuComponent(MenuComponent menuComponent) throws AddMenuComponentException {

    }

    // Read
    @Override
    public ArrayList <Recipe> getAllRecipes() throws AllRecipesException {
        ArrayList <Recipe> allRecipes = new ArrayList <>();
        String sql = "select * from recipes";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet data = preparedStatement.executeQuery();
            Recipe recipe;
            GregorianCalendar creationDate;
            String season;
            String regime;

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
                        data.getString("preparation_time"),
                        data.getInt("nb_persons"),
                        data.getString("author"),
                        data.getString("category")
                );

                season = data.getString("season");
                if(!data.wasNull()) {
                    recipe.setSeason(season);
                }

                regime = data.getString("dietery_regime");
                if(!data.wasNull()) {
                    recipe.setRegime(regime);
                }

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
        String sql = "select * from categories";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet data = preparedStatement.executeQuery();
            Category category;

            while(data.next()) {
                category = new Category(
                        data.getString("id"),
                        data.getString("cat_name")
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
        String sql = "select * from ingredients";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet data = preparedStatement.executeQuery();
            Ingredient ingredient;

            while(data.next()) {
                ingredient = new Ingredient(
                        data.getString("ing_name"),
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

    @Override
    public ArrayList<DieteryRegime> getAllRegimes() throws AllRegimesException {
        ArrayList <DieteryRegime> allRegimes = new ArrayList <>();
        String sql = "select * from dietery_regimes";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet data = preparedStatement.executeQuery();
            DieteryRegime regime;

            while(data.next()) {
                regime = new DieteryRegime(
                        data.getString("id"),
                        data.getString("dr_name")
                );
                allRegimes.add(regime);
            }
            return allRegimes;
        }
        catch (SQLException exception) {
            throw new AllRegimesException();
        }
    }

    @Override
    public ArrayList<Author> getAllAuthors() throws AllAuthorsException {
        ArrayList <Author> allAuthors = new ArrayList <>();
        String sql = "select * from authors";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet data = preparedStatement.executeQuery();
            Author author;

            while(data.next()) {
                author = new Author(
                        data.getString("pseudo"),
                        data.getString("first_name"),
                        data.getString("last_name")
                );
                allAuthors.add(author);
            }
            return allAuthors;
        }
        catch (SQLException exception) {
            throw new AllAuthorsException();
        }
    }

    @Override
    public ArrayList<Menu> getAllMenus() throws AllMenusException {
        return null;
    }
    // Update

    // Delete

}
