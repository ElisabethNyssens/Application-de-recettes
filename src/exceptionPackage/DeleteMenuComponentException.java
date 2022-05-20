package exceptionPackage;

public class DeleteMenuComponentException extends Exception {
    @Override
    public String getMessage() {
        return "Suppression des recettes dans le menu impossible";
    }
}
