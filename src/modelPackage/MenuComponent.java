package modelPackage;

public class MenuComponent {
    private int orderNumber;
    private String menuId;
    private String recipeId;

    public MenuComponent(int orderNumber, String menuId, String recipeId) {
        setOrderNumber(orderNumber);
        setMenuId(menuId);
        setRecipeId(recipeId);
    }

    // getters

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getMenuId() {
        return menuId;
    }

    public String getRecipeId() {
        return recipeId;
    }

    // setters

    public void setOrderNumber(int orderNumber) {
        if (orderNumber > 0) this.orderNumber = orderNumber;
    }

    public void setMenuId(String menuId) {
        if (menuId.length() <= 100) this.menuId = menuId;
    }

    public void setRecipeId(String recipeId) {
        if (recipeId.length() <= 100) this.recipeId = recipeId;
    }
}
