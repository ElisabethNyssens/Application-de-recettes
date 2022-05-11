package dataAccessPackage;

import exceptionPackage.AllRecipesException;
import exceptionPackage.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexion {
    private static Connection uniqueConnection;

    public static Connection getInstance() throws ConnectionException {
        if (uniqueConnection == null) {
            try{
                uniqueConnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/App_de_recettes",
                    "root",
                    "root");
            }
            catch (SQLException sqlException) {
                System.out.println(sqlException.getMessage());
                throw new ConnectionException();
            }
        }
        return uniqueConnection;
    }
}
