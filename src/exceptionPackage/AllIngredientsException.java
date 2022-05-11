package exceptionPackage;

public class AllIngredientsException extends Exception {
    public String getMessage() {
        return "La récupération de la liste des ingrédients a échoué...";
    }
}
