package exceptionPackage;

public class AllIngredQuantitiesException extends Exception {
    public String getMessage() {
        return "La récupération de la liste des quantités d'ingrédients a échoué...";
    }
}
