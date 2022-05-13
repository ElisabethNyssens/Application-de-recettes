package exceptionPackage;

public class AllAuthorsException extends Exception {
    public String getMessage() {
        return "La récupération de la liste des autheurs a échoué...";
    }
}
