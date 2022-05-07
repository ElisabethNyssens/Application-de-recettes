package exceptionPackage;

public class ConnectionException extends Exception {
    public String getMessage() {
        return "La tentative de connexion à la base de données a échoué...";
    }
}
