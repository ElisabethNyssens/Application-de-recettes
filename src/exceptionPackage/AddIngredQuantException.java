package exceptionPackage;

public class AddIngredQuantException extends Exception {
    public String getMessage() {
        return "L'ajout des ingrédients a échoué...";
    }
}
