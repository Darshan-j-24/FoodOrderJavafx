package Model;

public class CartItem extends Food{
    public CartItem(){}
    public double Amount;
    public String foodName;
    public double foodPrice;
    public int SerialNo;
    public int quantity;

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSerialNo(int serialNo) {
        this.SerialNo = serialNo;
    }

    public int getSerialNo() {
        return SerialNo;
    }
}