package modelPackage;

public class RecipeInMenu {
    private int orderNumber, nbPersons;
    private String title, category;

    public RecipeInMenu(int orderNumber, String title, int nbPersons, String category) {
        this.orderNumber = orderNumber;
        this.title = title;
        this.nbPersons = nbPersons;
        this.category = category;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getTitle() {
        return title;
    }

    public int getNbPersons() {
        return nbPersons;
    }

    public String getCategory() {
        return category;
    }
}
