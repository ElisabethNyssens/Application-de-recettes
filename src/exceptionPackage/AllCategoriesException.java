package exceptionPackage;

public class AllCategoriesException extends Exception {
    public String getMessage() {
        return "La récupération de la liste des catégories de recettes a échoué...";
    }
}
