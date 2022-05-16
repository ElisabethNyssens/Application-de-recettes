package exceptionPackage;

public class AddMenuException extends Exception {
    @Override
    public String getMessage() {
        return "L'ajout du menu a échoué ...";
    }
}
