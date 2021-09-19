package Model;

import java.util.ArrayList;
import java.util.List;

public class Cuisine extends Food{
    private final List<Food> Starters = new ArrayList<>();
    private final List<Food> MainCourse = new ArrayList<>();
    private final List<Food> Sweets = new ArrayList<>();
    private final List<Food> Drinks = new ArrayList<>();
    
    public void addDrinks(Food drinks) {
        Drinks.add(drinks);
    }
    
    public void addStarters(Food starter) {Starters.add(starter); }
     
    public void addSweets(Food sweets) {
        Sweets.add(sweets);
    }
     
    public void addMainCourse(Food mainCourse) {
        MainCourse.add(mainCourse);
    }
     
    public List<Food> getDrinks() {
        return Drinks;
    }

    public List<Food> getMainCourse() {
        return MainCourse;
    }
     
    public List<Food> getStarters() {
        return Starters;
    }
     
    public List<Food> getSweets() {
        return Sweets;
    }
}
