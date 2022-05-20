package exceptionPackage;

public class DeleteMenuException extends Exception {
    @Override
    public String getMessage() {
        return "Suppression du menu impossible";
    }
}
