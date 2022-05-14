package modelPackage;

public class Step {
    private int orderNumber;
    private String recipeName;
    private String description;

    public Step(int orderNumber, String recipeName, String description) {
        this.orderNumber = orderNumber;
        this.recipeName = recipeName;
        this.description = description;
    }
}
