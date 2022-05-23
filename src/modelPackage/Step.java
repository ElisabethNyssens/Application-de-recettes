package modelPackage;

public class Step {
    private int orderNumber;
    private String recipeName;
    private String description;

    public Step(int orderNumber, String recipeName, String description) {
        setOrderNumber(orderNumber);
        setRecipeName(recipeName);
        setDescription(description);
    }

    // getters

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getDescription() {
        return description;
    }

    //setters

    public void setOrderNumber(int orderNumber) {
        if (orderNumber > 0) this.orderNumber = orderNumber;
    }

    public void setDescription(String description) {
        if (description.length() <= 1000) this.description = description;
    }

    public void setRecipeName(String recipeName) {
        if (recipeName.length() <= 100) this.recipeName = recipeName;
    }
}
