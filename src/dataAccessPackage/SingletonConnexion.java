package dataAccessPackage;

import exceptionPackage.AllRecipesException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexion {
    private static Connection connexionUnique;

    public static Connection getInstance() throws SQLException {
        if (connexionUnique == null) {
            try{
                connexionUnique = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/App_de_recettes",
                    "root",
                    "root");
            }
            catch (SQLException sqlException) {
                throw new SQLException(sqlException);
            }
        }
        return connexionUnique;
    }
}
