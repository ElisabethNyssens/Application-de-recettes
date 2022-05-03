package exceptionPackage;

public class AllRecipesException extends Exception {
    private String exception;

    public AllRecipesException(String exception){
        this.exception = exception;
    }

    public String getMessage() {
        return exception;
    }
}
