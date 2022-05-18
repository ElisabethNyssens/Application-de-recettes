package exceptionPackage;

public class UpdateRecipeException extends Exception {
    public String getMessage() {
        return "Erreur lors de la modification de la recette...";
    }
}
