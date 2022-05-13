package exceptionPackage;

public class AddRecipeException extends Exception {
    public String getMessage() {
        return "L'ajout de la recette a échoué...";
    }
}
