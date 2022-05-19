package exceptionPackage;

public class SearchException extends Exception {
    public String getMessage() {
        return "La recherche a échoué...";
    }
}
