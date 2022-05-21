package modelPackage;

public class ShopListIngred {
    private String ingred;
    private double quantity;
    private String unit;

    public ShopListIngred(String ingred, double quantity, String unit) {
        this.ingred = ingred;
        this.quantity = quantity;
        this.unit = unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getIngred() {
        return ingred;
    }

    public String getUnit() {
        return unit;
    }

    public void addQuantity(double quantity) {
        this.quantity += quantity;
    }
}
