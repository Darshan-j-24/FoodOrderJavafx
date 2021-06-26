package Controller;

import Model.Cuisine;
import Model.Food;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class FoodController implements Initializable {
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
    private GridPane grid;
    public static final String delimiter = ",";
//   public static void read(String csvFile, Cuisine North, Cuisine South)


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Cuisine NorthCuisine = new Cuisine();
        Cuisine SouthCuisine = new Cuisine();


        //Reading from the csv file and adding it to list in cuisine object
        try {
            File file = new File("C:\\FoodOrder\\src\\FoodInputs.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while ((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);

                Food food = new Food();
                food.setName(tempArr[2]);
                food.setImgSrc(tempArr[3]);
//                System.out.println(food.getImgSrc());
                food.setPrice(Double.parseDouble(tempArr[4]));
                food.setColor(tempArr[5]);
//                System.out.println("Food"+food.getName());
//                System.out.println(Integer.parseInt(tempArr[1]));
                if (tempArr[0].equals("S")) {
//                    System.out.println("if inside");
                    switch (Integer.parseInt(tempArr[1])) {
                        case 1:
                            SouthCuisine.addStarters(food);
//                            System.out.println(SouthCuisine.getStarters().size() + "inside swith starter");
                            break;
                        case 2:
                            SouthCuisine.addMainCourse(food);
                            break;
                        case 3:
                            SouthCuisine.addSweets(food);
                            break;
                        default:
                            SouthCuisine.addDrinks(food);
                            break;
                    }
                } else
                    switch (Integer.parseInt(tempArr[1])) {
                        case 1:
                            NorthCuisine.addStarters(food);
                            break;
                        case 2:
                            NorthCuisine.addMainCourse(food);
                            break;
                        case 3:
                            NorthCuisine.addSweets(food);
                            break;
                        default:
                            NorthCuisine.addDrinks(food);
                            break;
                    }
//                System.out.println(SouthCuisine.getMainCourse().size());
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
//        System.out.println(SouthCuisine.getMainCourse().size());
//        System.out.print(SouthCuisine.getStarters().get(0).getName() + "\n"+SouthCuisine.getMainCourse().get(0).getName());


        List<Food> foodL = SouthCuisine.getStarters();
        System.out.println("Size: " + foodL.size());
        int column = 0;
        int row = 1;
        try {
//            for (int i = 0; i < foodL.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../views/item.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            ItemController itemController = fxmlLoader.getController();
            System.out.println(foodL.get(0).getName());
            itemController.setData(foodL.get(0));

            if (column == 3) {
                column = 0;
                row++;
            }

            grid.add(anchorPane, column++, row); //(child,column,row)
            //set grid width
//                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
//                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
//                grid.setMaxWidth(Region.USE_PREF_SIZE);

            //set grid height
//                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
//                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
//                grid.setMaxHeight(Region.USE_PREF_SIZE);

            GridPane.setMargin(anchorPane, new Insets(10));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}






