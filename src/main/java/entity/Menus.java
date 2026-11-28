package entity;

public class Menus {
    private int menuId;
    private int restaurantId;
    private String menuName;
    private String menuDescription;
    private  double menuPrice;
    private String menuType;

    public Menus(int restaurantId, String menuName, String menuDescription, double menuPrice, String menuType) {
        this.restaurantId = restaurantId;
        this.menuName = menuName;
        this.menuDescription = menuDescription;
        this.menuPrice = menuPrice;
        this.menuType = menuType;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDescription() {
        return menuDescription;
    }

    public void setMenuDescription(String menuDescription) {
        this.menuDescription = menuDescription;
    }

    public double getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(double menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    @Override
    public String toString() {
        return "Menus{" +
                "menuId=" + menuId +
                ", restaurantId=" + restaurantId +
                ", menuName='" + menuName + '\'' +
                ", menuDescription='" + menuDescription + '\'' +
                ", menuPrice=" + menuPrice +
                ", menuType='" + menuType + '\'' +
                '}';
    }
}
