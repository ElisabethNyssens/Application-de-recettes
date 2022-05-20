package modelPackage;

public class MenuComponent {
    private Integer orderNumber;
    private String menuId;
    private String recipeId;

    public MenuComponent(Integer orderNumber, String menuId, String recipeId) {
        this.orderNumber = orderNumber;
        this.menuId = menuId;
        this.recipeId = recipeId;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public String getMenuId() {
        return menuId;
    }

    public String getRecipeId() {
        return recipeId;
    }
}
