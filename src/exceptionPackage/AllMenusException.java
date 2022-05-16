package exceptionPackage;

public class AllMenusException extends Exception {
    @Override
    public String getMessage() {
        return "La récupération de la liste des Menus a échoué ...";
    }
}
