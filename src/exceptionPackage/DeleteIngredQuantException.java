package exceptionPackage;

public class DeleteIngredQuantException extends Exception {
    public String getMessage() {
        return "Suppression des ingrédients de la recette impossible";
    }
}
