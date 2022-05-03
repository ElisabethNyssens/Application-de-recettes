package dataAccessPackage;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnexion {
    private static Connection connexionUnique;

    public static Connection getInstance() {
        if (connexionUnique == null) {
            connexionUnique =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdb", "root",
                            "...")
        }
        return connexionUnique;
    }
}
