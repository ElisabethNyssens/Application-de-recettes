package exceptionPackage;

public class AllRecipesException extends Exception {

    public String getMessage() {
        return "La récupération de la liste des recettes a échoué...";
    }
}
