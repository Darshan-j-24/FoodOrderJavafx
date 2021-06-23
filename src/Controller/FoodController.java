package Controller;

import Model.Cuisine;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.List;


public class FoodController {
    @FXML
    private VBox ChosenFoodCard;

    @FXML
    private Label FoodNamelabel;

    @FXML
    private Label FoodPricelabel;

    @FXML
    private ImageView FoodImage;

    @FXML
    private Button AddToCart;

    @FXML
    private Button GoToCart;

    @FXML
    private ToggleGroup TgCuisine;

    @FXML
    private ToggleGroup TgMainMenu;

    @FXML
    private ScrollPane Scroll;

    @FXML
    private GridPane Grid;

    Cuisine NorthCuisine = new Cuisine();
    Cuisine SouthCuisine = new Cuisine();







    

}



