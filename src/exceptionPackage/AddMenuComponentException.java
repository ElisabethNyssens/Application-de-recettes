package exceptionPackage;

public class AddMenuComponentException extends Exception {
    @Override
    public String getMessage() {
        return "L'ajout des recettes au menu a échoué ...";
    }
}
