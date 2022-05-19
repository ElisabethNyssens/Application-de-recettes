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

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getDescription() {
        return description;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
