package Model;

public class Food {
    private String name;
    private String imgSrc;
    private double price;
    private String cardColor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return cardColor;
    }

    public void setColor(String color) {
        this.cardColor = color;
    }
}
