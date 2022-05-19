package exceptionPackage;

public class AllStepsException extends Exception {
    public String getMessage() {
        return "La récupération des étapes de préparation de la recette a échoué...";
    }
}
