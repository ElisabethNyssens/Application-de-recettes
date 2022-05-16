package exceptionPackage;

public class DeleteStepException extends Exception {
    public String getMessage() {
        return "Suppression des étapes de la recette impossible";
    }
}
