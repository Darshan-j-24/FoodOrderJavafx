package Controller;

import Main.Main;
import Model.Food;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


import Main.MyListener;

public class ItemController {
    @FXML
    private Label Namelabel;

    @FXML
    private Label Pricelabel;

    @FXML
    private ImageView Img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(this.food);
    }

    private Food food;
    private MyListener myListener;

    public void setData(Food food, MyListener myListener) {
        this.food = food;
        this.myListener = myListener;
        Namelabel.setText(food.getName());
        Pricelabel.setText(Main.Currency + food.getPrice());
        Image image = new Image(getClass().getResourceAsStream(food.getImgSrc()));
        Img.setImage(image);
    }


}


