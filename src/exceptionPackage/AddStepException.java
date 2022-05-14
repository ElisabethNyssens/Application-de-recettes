package exceptionPackage;

public class AddStepException extends Exception {
    public String getMessage() {
        return "L'ajout des étapes a échoué...";
    }
}
