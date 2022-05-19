package exceptionPackage;

public class DeleteRecipeException extends Exception {
    public String getMessage() {
        return "Suppression de la recette impossible";
    }
}
