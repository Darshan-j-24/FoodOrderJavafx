package Model;

import java.util.ArrayList;
import java.util.List;

public class Context {
    private final static Context instance = new Context();

    public static Context getInstance() {
        return instance;
    }

    private List<CartItem> cartItem = new ArrayList<>();
    List<String> FoodName = new ArrayList<>();

    public List<String> currentFoodList() {return FoodName;}
    public List<CartItem> currentCart() {
        return cartItem;
    }
}