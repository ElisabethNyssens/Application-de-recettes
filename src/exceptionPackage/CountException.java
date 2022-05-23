package exceptionPackage;

public class CountException extends Exception{
    @Override
    public String getMessage() {
        return "Le récupération du nombre d'éléments a échouée...";
    }
}
