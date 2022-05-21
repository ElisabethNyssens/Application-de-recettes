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
        String sql = "insert into steps (order_number,recipe_id,description) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, step.getOrderNumber());
            preparedStatement.setString(2, step.getRecipeName());
            preparedStatement.setString(3, step.getDescription());
            preparedStatement.executeUpdate();
        }
        catch(SQLException exception) {
            throw new AddStepException();
        }
    }

    @Override
    public void addIngredientQuantity(IngredientQuantity ingredientQuantity) throws AddIngredQuantException {
        String sql = "insert into ingredient_quantities (ingredient_id,recipe_id,quantity) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ingredientQuantity.getIngredient());
            preparedStatement.setString(2, ingredientQuantity.getRecipe());
            preparedStatement.setDouble(3, ingredientQuantity.getQuantity());
            preparedStatement.executeUpdate();
        }
        catch(SQLException exception) {
            throw new AddIngredQuantException();
        }
    }

    @Override
    public void addMenu(Menu menu) throws AddMenuException {
        String sql = "insert into menus (title) value (?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println("ici "+menu.getTitle());
            preparedStatement.setString(1, menu.getTitle());
            preparedStatement.executeUpdate();

            if (menu.getComment() != null) {
                sql = "update menus set comment = ? where title = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, menu.getComment());
                preparedStatement.setString(2, menu.getTitle());
                preparedStatement.executeUpdate();
            }
        }
        catch(SQLException exception) {
            throw new AddMenuException();
        }
    }

    @Override
    public void addMenuComponent(MenuComponent menuComponent) throws AddMenuComponentException {
        String sql = "insert into menu_components (order_number,menu_id,recipe_id) values (?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, menuComponent.getOrderNumber());
            preparedStatement.setString(2, menuComponent.getMenuId());
            preparedStatement.setString(3, menuComponent.getRecipeId());
            preparedStatement.executeUpdate();
        }
        catch(SQLException exception) {
            throw new AddMenuComponentException();
        }
    }

    // Read
    @Override
    public ArrayList<Recipe> getAllRecipes() throws AllRecipesException {
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
            exception.printStackTrace();
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
    public ArrayList<IngredientQuantity> getAllIngredientsOfRecipe(String recipeName) throws AllIngredQuantitiesException {
        ArrayList <IngredientQuantity> allIngredQuant = new ArrayList <>();
        String sql = "select * from ingredient_quantities where recipe_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, recipeName);
            ResultSet data = preparedStatement.executeQuery();
            IngredientQuantity ingredQuant;

            while(data.next()) {
                ingredQuant = new IngredientQuantity(
                        data.getString("ingredient_id"),
                        data.getString("recipe_id"),
                        data.getDouble("quantity")
                );
                allIngredQuant.add(ingredQuant);
            }
            return allIngredQuant;
        }
        catch (SQLException exception) {
            throw new AllIngredQuantitiesException();
        }
    }

    @Override
    public ArrayList<Step> getAllStepsOfRecipe(String recipeName) throws AllStepsException {
        ArrayList <Step> allSteps = new ArrayList <>();
        String sql = "select * from steps where recipe_id = ? order by order_number";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, recipeName);
            ResultSet data = preparedStatement.executeQuery();
            Step step;

            while(data.next()) {
                step = new Step(
                        data.getInt("order_number"),
                        data.getString("recipe_id"),
                        data.getString("description")
                );
                allSteps.add(step);
            }
            return allSteps;
        }
        catch (SQLException exception) {
            throw new AllStepsException();
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
        ArrayList<Menu> allMenus = new ArrayList<>();
        String sql = "select * from menus";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet data = preparedStatement.executeQuery();
            Menu menu;
            String comment;

            while (data.next()) {
                menu = new Menu(data.getString("title"));

                comment = data.getString("comment");
                if(!data.wasNull()) {
                    menu.setComment(comment);
                }

                allMenus.add(menu);
            }
            return allMenus;
        }
        catch (SQLException exception) {
            throw new AllMenusException();
        }
    }


    // Update
    @Override
    public void updateRecipe(Recipe recipe) throws UpdateRecipeException {
        String sql = "update recipes set " +
                "creation_date = ?, " +
                "is_hot = ?, " +
                "is_sweet = ?, " +
                "is_salty = ?, " +
                "budget = ?, " +
                "preparation_time = ?, " +
                "nb_persons = ?, " +
                "author = ?, " +
                "category = ? " +
                "where title = ?";

        GregorianCalendar calendar = recipe.getCreationDate();
        java.sql.Date sqlDate = new Date(calendar.getTimeInMillis());

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, sqlDate);
            preparedStatement.setBoolean(2, recipe.isHot());
            preparedStatement.setBoolean(3, recipe.isSweet());
            preparedStatement.setBoolean(4, recipe.isSalty());
            preparedStatement.setString(5, recipe.getBudget());
            preparedStatement.setString(6, recipe.getPreparationTime());
            preparedStatement.setInt(7, recipe.getNbPersons());
            preparedStatement.setString(8, recipe.getAuthor());
            preparedStatement.setString(9, recipe.getCategory());
            preparedStatement.setString(10, recipe.getTitle());
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
            throw new UpdateRecipeException();
        }
    }


    // Delete
    @Override
    public void deleteRecipe(String recipeTitle) throws DeleteRecipeException, DeleteStepException, DeleteIngredQuantException {
        deleteSteps(recipeTitle);
        deleteIngredQuants(recipeTitle);

        String sql = "delete from recipes where title = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, recipeTitle);
            preparedStatement.executeUpdate();
        }
        catch(SQLException exception) {
            throw new DeleteRecipeException();
        }
    }

    @Override
    public void deleteSteps(String recipeTitle) throws DeleteStepException {
        String sql = "delete from steps where recipe_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, recipeTitle);
            preparedStatement.executeUpdate();
        }
        catch(SQLException exception) {
            throw new DeleteStepException();
        }
    }

    @Override
    public void deleteIngredQuants(String recipeTitle) throws DeleteIngredQuantException {
        String sql = "delete from ingredient_quantities where recipe_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, recipeTitle);
            preparedStatement.executeUpdate();
        }
        catch(SQLException exception) {
            throw new DeleteIngredQuantException();
        }
    }

    @Override
    public void deleteMenu(String menuTitle) throws DeleteMenuException, DeleteMenuComponentException {
        deleteMenuComponents(menuTitle);

        String sql = "delete from menus where title = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, menuTitle);
            preparedStatement.executeUpdate();
        }
        catch (SQLException exception) {
            throw new DeleteMenuException();
        }
    }

    @Override
    public void deleteMenuComponents(String menuTitle) throws DeleteMenuComponentException {
        String sql = "delete from menu_components where menu_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, menuTitle);
            preparedStatement.executeUpdate();
        }
        catch (SQLException exception) {
            throw new DeleteMenuComponentException();
        }
    }

    // Researches
    @Override
    public ArrayList<RecipeWithIngred> searchByIngredRecipes(String ingredients, Boolean with) throws SearchException {
        ArrayList <RecipeWithIngred> recipes = new ArrayList <>();
        String sql =  "select distinct r.title 'Titre', CONCAT(a.first_name,' ',a.last_name) 'Auteur', c.cat_name 'Categorie', r.budget, r.preparation_time " +
                "from recipes r, ingredient_quantities i, authors a, categories c " +
                "where a.pseudo = r.author " +
                "and c.id = r.category " +
                "and i.recipe_id = r.title " +
                "and title "+(with?"in":"not in")+" (" +
                "    select distinct recipe_id " +
                "    from ingredient_quantities " +
                "    where ingredient_id in ("+ingredients+"));";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet data = preparedStatement.executeQuery();

            RecipeWithIngred recipe;

            while(data.next()) {
                recipe = new RecipeWithIngred(
                        data.getString("Titre"),
                        data.getString("Auteur"),
                        data.getString("Categorie"),
                        data.getString("budget"),
                        data.getString("preparation_time")
                );
                recipes.add(recipe);
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new SearchException();
        }

        return recipes;
    }

    @Override
    public ArrayList<Menu> searchMenuByDieteryRegime(String regime) throws SearchException {
        ArrayList<Menu> menus = new ArrayList<>();
        String sql = "select distinct menu_id 'Titre'" +
                "from menu_components " +
                "group by menu_id, recipe_id " +
                "having recipe_id in " +
                    "(select r.title " +
                    "from recipes r, dietery_regimes dr " +
                    "where r.dietery_regime = dr.id " +
                    "and dr.dr_name = (?));";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, regime);
            ResultSet data = preparedStatement.executeQuery();

            Menu menu;

            while (data.next()) {
                System.out.println(data.getString("Titre"));
                menu = new Menu(data.getString("Titre"));
                menus.add(menu);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new SearchException();
        }

        return menus;
    }

    @Override
    public ArrayList<String> searchBySeason(String category, GregorianCalendar date) throws SearchException {
        ArrayList<String> recipesTitle = new ArrayList<>();
        GregorianCalendar calendar = date;
        String season;

        return null;
    }
}
