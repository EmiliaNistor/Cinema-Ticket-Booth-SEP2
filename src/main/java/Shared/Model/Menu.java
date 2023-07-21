package Shared.Model;

public class Menu {
    private final int menuId;
    private String food;
    private double price;

    public Menu(int menuId, String food, double price) {
        this.food = food;
        this.price = price;
        this.menuId = menuId;
    }

    public int getMenuId() {
        return menuId;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
