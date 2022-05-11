package exceptionPackage;

public class AllRegimesException extends Exception {
    public String getMessage() {
        return "La récupération de la liste des régimes alimentaires a échoué...";
    }
}
