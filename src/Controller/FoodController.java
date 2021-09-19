package Controller;

import Main.Main;
import Model.CartItem;
import Model.Cuisine;
import Model.Food;
import Model.Context;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.fxml.Initializable;
import Main.MyListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import CustomExceptions.AlreadyInsideCartException;

public class FoodController implements Initializable {
    @FXML
    private VBox ChosenFoodCard;

    @FXML
    private Label FoodNamelabel;

    @FXML
    private Label SearchError;

    @FXML
    private Label FoodPricelabel;

    @FXML
    private ImageView FoodImage;

    @FXML
    private Button AddToCart;

    @FXML
    private ToggleGroup TgCuisine;

    @FXML
    private ToggleGroup TgMainMenu;

    @FXML
    private RadioButton SouthIndian;

    @FXML
    private RadioButton NorthIndian;

    @FXML
    private RadioButton Starters;

    @FXML
    private RadioButton MainCourse;

    @FXML
    private RadioButton Sweets;

    @FXML
    private RadioButton Drinks;

    @FXML
    private Label AlertLabel;

    @FXML
    private TextField searchText;

    @FXML
    private ComboBox<String> Quantity;

    @FXML
    private Label welcomeUser;

    @FXML
    private GridPane grid;

    @FXML
    private Label Serve;


    ObservableList<String> QuantityList = FXCollections.observableArrayList("1","2","3","4","5");
    private MyListener myListener;
    private boolean IsSouth = false;
    private boolean IsNorth = false;
    private String s = "NULL";
    List<CartItem> cartItemList = Context.getInstance().currentCart();
    List<String> FoodName = Context.getInstance().currentFoodList();
    Cuisine NorthCuisine = new Cuisine();
    Cuisine SouthCuisine = new Cuisine();
    public int quantity = 1;
    public Food chosenFood;
    public static final String delimiter = ",";


    public void setChosenFood(Food food)  {
        Image image;
        ChosenFoodCard.setStyle(null);
        chosenFood = food;
        System.out.println(food.getName()+ "Inside setcho");
        String name = food.getName();
        FoodNamelabel.setText(name);
        FoodPricelabel.setText(Main.Currency + food.getPrice());
        image = new Image(getClass().getResourceAsStream(food.getImgSrc()));
        FoodImage.setImage(image);
        ChosenFoodCard.setStyle("-fx-background-color: " + food.getColor() + ";\n" +
                "    -fx-background-radius: 30;");
        AlertLabel.setVisible(false);
        SearchError.setVisible(false);
        AddToCart.setVisible(true);
        FoodNamelabel.setVisible(true);
        FoodPricelabel.setVisible(true);
        Quantity.setVisible(true);
        Serve.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LoginController LC = new LoginController();
        welcomeUser.setText("Hey! "+LC.getUsername()+ ", Welcome Back");
        Quantity.setItems(QuantityList);
        Quantity.setValue("1");

        try {
            File file = new File("C:\\FoodOrder\\src\\FoodInputs.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] tempArr;
            while ((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);

                Food food = new Food();
                food.setName(tempArr[2]);
                food.setImgSrc(tempArr[3]);
                food.setPrice(Double.parseDouble(tempArr[4]));
                food.setColor(tempArr[5]);
                if (tempArr[0].equals("S")) {
                    switch (Integer.parseInt(tempArr[1])) {
                        case 1:
                            SouthCuisine.addStarters(food);
                            break;
                        case 2:
                            SouthCuisine.addMainCourse(food);
                            break;
                        case 3:
                            SouthCuisine.addSweets(food);
                            break;
                        case 4:
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
                        case 4:
                            NorthCuisine.addDrinks(food);
                            break;
                    }
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


        List<Food> foodL = SouthCuisine.getStarters();
        if (foodL.size() > 0) {
            setChosenFood(foodL.get(0));
            FoodNamelabel.setText("NULL");
            FoodPricelabel.setText("NULL");
            FoodNamelabel.setVisible(false);
            Quantity.setVisible(false);
            Serve.setVisible(false);
            FoodPricelabel.setVisible(false);
            ChosenFoodCard.setStyle("-fx-background-image: url('/images/initial.png');" +
                    "-fx-background-repeat: stretch;" +
                    "-fx-background-size: 341 300;"   + "-fx-background-position: center center;");

            FoodImage.setImage(null);
            AddToCart.setVisible(false);


        }

        myListener = (food) -> setChosenFood(food);

        SouthIndian.fire();
        IsSouth = true;
        Starters.fire();
        SelectionFunction("Starters");


        TgCuisine.selectedToggleProperty().addListener((ob, o, n) -> {

            RadioButton rb = (RadioButton)TgCuisine.getSelectedToggle();

            if (rb != null) {

                s = rb.getText();

                if(s.equals("South Indian"))
                {
                    NorthIndian.setStyle("-fx-effect: null;");
                    SouthIndian.setStyle("-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0)");
                    IsSouth = true;
                    IsNorth = false;

                }
                else
                {
                    SouthIndian.setStyle("-fx-effect: null;");
                    NorthIndian.setStyle("-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0);");
                    IsNorth = true;
                    IsSouth = false;

                }
            }
        });


        TgMainMenu.selectedToggleProperty().addListener((ob, o, n) -> {
            RadioButton rb = (RadioButton)TgMainMenu.getSelectedToggle();

            if (rb != null) {
                s = rb.getText();
                SelectionFunction(s);
            }
        });

        AddToCart.setOnAction(event);
    }





    public void QuantitySelect(ActionEvent event)
    {
        quantity = Integer.parseInt(Quantity.getSelectionModel().getSelectedItem());
    }

    EventHandler<ActionEvent> event = new EventHandler<>() {
        public void handle(ActionEvent e) throws AlreadyInsideCartException
        {
            try {
                if (FoodName.contains(chosenFood.getName())) {
                    throw new AlreadyInsideCartException();
                }
            }
            catch (AlreadyInsideCartException e1)
            {
                AlertLabel.setVisible(true);
                AlertLabel.setText(e1.toString());
                System.out.println("Exception caught and handled : " + e1);
                return;
            }
            CartItem cartItem = new CartItem();
            cartItem.setSerialNo(1);
            cartItem.setQuantity(quantity);
            cartItem.setAmount(chosenFood.getPrice() * quantity);
            cartItem.setFoodPrice(chosenFood.getPrice());
            cartItem.setFoodName(chosenFood.getName());
            FoodName.add(chosenFood.getName());
            cartItemList.add(cartItem);
            System.out.println("cart items:"+ cartItemList);
        }
    };



    public void LoadItems(List<Food> foodL)
    {
        int column = 0;
        int row = 1;
//        grid.setGridLinesVisible(false);
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
        grid.getChildren().clear();
//        grid.setGridLinesVisible(true);
        try {
            for (int i = 0; i < foodL.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(foodL.get(i), myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void goToCart(ActionEvent event) throws IOException
    {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../views/cart.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//
        window.setScene(tableViewScene);
        window.show();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/views/cart.fxml"));

    }

    public void SelectionFunction(String s)
    {
        if(s.equals("Starters") & IsSouth)
        {
            MainCourse.setStyle("-fx-effect: null;");
            Sweets.setStyle("-fx-effect: null;");
            Drinks.setStyle("-fx-effect: null;");
            Starters.setStyle("-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0)");
            LoadItems(SouthCuisine.getStarters());
        }
        else if(s.equals("Main Course") & IsSouth)
        {
            Starters.setStyle("-fx-effect: null;");
            Sweets.setStyle("-fx-effect: null;");
            Drinks.setStyle("-fx-effect: null;");
            MainCourse.setStyle("-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0)");

            LoadItems(SouthCuisine.getMainCourse());
        }
        else if(s.equals("Sweets") & IsSouth)
        {
            Starters.setStyle("-fx-effect: null;");
            MainCourse.setStyle("-fx-effect: null;");
            Drinks.setStyle("-fx-effect: null;");
            Sweets.setStyle("-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0)");

            LoadItems( SouthCuisine.getSweets());
        }
        else if(s.equals("Drinks") & IsSouth)
        {
            Starters.setStyle("-fx-effect: null;");
            MainCourse.setStyle("-fx-effect: null;");
            Sweets.setStyle("-fx-effect: null;");
            Drinks.setStyle("-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0)");

            LoadItems(SouthCuisine.getDrinks());
        }
        else if(s.equals("Starters") & IsNorth)
        {
            MainCourse.setStyle("-fx-effect: null;");
            Sweets.setStyle("-fx-effect: null;");
            Drinks.setStyle("-fx-effect: null;");
            Starters.setStyle("-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0)");
            LoadItems(NorthCuisine.getStarters());
        }
        else if(s.equals("Main Course") & IsNorth)
        {
            Starters.setStyle("-fx-effect: null;");
            Sweets.setStyle("-fx-effect: null;");
            Drinks.setStyle("-fx-effect: null;");
            MainCourse.setStyle("-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0)");

            LoadItems(NorthCuisine.getMainCourse());
        }
        else if(s.equals("Sweets") & IsNorth)
        {
            Starters.setStyle("-fx-effect: null;");
            MainCourse.setStyle("-fx-effect: null;");
            Drinks.setStyle("-fx-effect: null;");
            Sweets.setStyle("-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0)");

            LoadItems(NorthCuisine.getSweets());
        }
        else if(s.equals("Drinks") & IsNorth)
        {
            Starters.setStyle("-fx-effect: null;");
            MainCourse.setStyle("-fx-effect: null;");
            Sweets.setStyle("-fx-effect: null;");
            Drinks.setStyle("-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0.1), 10.0 , 0.0 , 0.0 ,10.0)");

            LoadItems(NorthCuisine.getDrinks());
        }
    }


    public void search(ActionEvent event) throws InterruptedException {

        Threads thread1 = new Threads(SouthCuisine.getStarters(),NorthCuisine.getStarters(), searchText.getText());
        Threads thread2 = new Threads( SouthCuisine.getMainCourse(),NorthCuisine.getMainCourse(), searchText.getText());
        Threads thread3 = new Threads(SouthCuisine.getSweets(), NorthCuisine.getSweets(), searchText.getText());
        Threads thread4 = new Threads( SouthCuisine.getDrinks(),NorthCuisine.getDrinks(), searchText.getText());
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        int i = thread1.getResult();
        if(i != -1) {
            if (i > SouthCuisine.getStarters().size() - 1)
                setChosenFood(NorthCuisine.getStarters().get(i - SouthCuisine.getStarters().size()));
            else
                setChosenFood(SouthCuisine.getStarters().get(i));
            return;
        }


        i = thread2.getResult();
        if(i != -1) {
            if (i > SouthCuisine.getMainCourse().size() - 1)
                setChosenFood(NorthCuisine.getMainCourse().get(i - SouthCuisine.getMainCourse().size()));
            else
                setChosenFood(SouthCuisine.getMainCourse().get(i));
            return;
        }


        i = thread3.getResult();

        if(i != -1) {
            if (i > SouthCuisine.getSweets().size() - 1)
                setChosenFood(NorthCuisine.getSweets().get(i - SouthCuisine.getSweets().size()));
            else
                setChosenFood(SouthCuisine.getSweets().get(i));
            return;
        }

        i = thread4.getResult();

        if(i != -1){
            if(i > SouthCuisine.getDrinks().size()-1)
                setChosenFood(NorthCuisine.getDrinks().get(i - SouthCuisine.getDrinks().size()));
            else
                setChosenFood(SouthCuisine.getDrinks().get(i));
            return;}


            SearchError.setVisible(true);


    }
    
    public void LogOut(ActionEvent event) throws IOException {
        cartItemList.clear();
        FoodName.clear();
        Parent root = FXMLLoader.load(getClass().getResource("../views/LoginUI.fxml"));
        Scene scene = new Scene(root);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        
    }


}