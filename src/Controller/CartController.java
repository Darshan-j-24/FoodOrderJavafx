package Controller;

import CustomExceptions.RowNotSelectedException;
import Model.Food;
import Model.Context;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import Model.CartItem;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.Label;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import CustomExceptions.CartEmptyException;
public class CartController implements Initializable {


    @FXML
    private TableView<CartItem> cartTable;

    @FXML
    private TableColumn<CartItem, Integer> serialNo;

    @FXML
    private TableColumn<CartItem, String> foodName;

    @FXML
    private TableColumn<CartItem, Integer> foodQuantity;

    @FXML
    private TableColumn<CartItem, Double> foodPrice;


    @FXML
    private TableColumn<CartItem, Double> foodAmount;

    @FXML
    private Label totalAmountLabel;

    @FXML
    private Label SelectLabel;

    @FXML
    private Label CartEmptyLabel;



    public List<Food> cartItems = new ArrayList<>();
    ObservableList<CartItem> cartList = FXCollections.observableArrayList();
    List<CartItem> cartItemList = Context.getInstance().currentCart();
    List<String> FoodName = Context.getInstance().currentFoodList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("inside cart controller Initializable" + cartItems.size());
        System.out.println(cartItemList);
        LoadItems(cartItemList);
        SelectLabel.setVisible(false);
        CartEmptyLabel.setVisible(false);


    }

    public void goBacktoMain(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("../views/FoodOrderUIn.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }




    public void LoadItems(List<CartItem> CartL) {
        int amount = 0;
        for (int i = 0; i < CartL.size(); i++) {
            CartL.get(i).setSerialNo(i + 1);
            amount += CartL.get(i).getAmount();
            cartList.add(CartL.get(i));
        }


        serialNo.setCellValueFactory(
                new PropertyValueFactory<>("SerialNo")
        );
        foodName.setCellValueFactory(
                new PropertyValueFactory<>("foodName")
        );
        foodQuantity.setCellValueFactory(
                new PropertyValueFactory<>("quantity")
        );
        foodPrice.setCellValueFactory(
                new PropertyValueFactory<>("foodPrice")
        );
        foodAmount.setCellValueFactory(
                new PropertyValueFactory<>("Amount")
        );

        cartTable.setItems(cartList);
        totalAmountLabel.setText(String.valueOf(amount));
    }

    @FXML
    private void deleteRowTable() throws RowNotSelectedException{
        try {
            if(cartItemList.size() == 0 | cartTable.getSelectionModel().getSelectedItem() == null)
                throw new RowNotSelectedException();
        }
        catch (RowNotSelectedException e5)
        {
            SelectLabel.setVisible(true);
            SelectLabel.setText(e5.toString());
            System.out.println("Exception caught and handled " + e5);
            return;
        }
        SelectLabel.setVisible(false);
        cartItemList.remove(cartTable.getSelectionModel().getSelectedItem());
        FoodName.remove(cartTable.getSelectionModel().getSelectedItem().getFoodName());
        cartTable.getItems().removeAll(cartTable.getSelectionModel().getSelectedItem());
        System.out.println(cartItemList.size());
        cartTable.getItems().clear();
        LoadItems(cartItemList);


    }

    @FXML
    private void IncreaseQuant() throws RowNotSelectedException{
        try {
            if (cartItemList.size() == 0) {
                throw new RowNotSelectedException();
            }
        }
        catch (RowNotSelectedException e3)
        {
            SelectLabel.setVisible(true);
            SelectLabel.setText(e3.toString());
            System.out.println("Exception caught and handled " + e3);
            return;
        }
        SelectLabel.setVisible(false);
        CartItem ctS = cartTable.getSelectionModel().getSelectedItem();
        System.out.println(ctS);
        if(ctS == null)
        {
            SelectLabel.setVisible(true);
            return;
        }
        CartItem ct = cartItemList.get(cartItemList.indexOf(ctS));
        int quantity = ct.getQuantity() + 1;
        double price = ct.getFoodPrice() * (double) quantity;
        System.out.println();
        ct.setQuantity(quantity);
        ct.setAmount(price);
        cartTable.getItems().clear();
        LoadItems(cartItemList);
    }

    @FXML
    private void DecreaseQuant() throws RowNotSelectedException{
        try {
            if (cartItemList.size() == 0 | cartTable.getSelectionModel().getSelectedItem() == null)
                throw new RowNotSelectedException();
        }
        catch (RowNotSelectedException e4)
        {
            SelectLabel.setVisible(true);
            SelectLabel.setText(e4.toString());
            System.out.println("Exception caught and handled " + e4);
            return;
        }
        SelectLabel.setVisible(false);
        CartItem ctS = cartTable.getSelectionModel().getSelectedItem();
        CartItem ct = cartItemList.get(cartItemList.indexOf(ctS));
        int quantity = ct.getQuantity() - 1;
        if (quantity <= 0) {
            cartItemList.remove(ctS);
            cartTable.getItems().removeAll(ctS);
            cartTable.getItems().clear();
            LoadItems(cartItemList);
            return;
        }
        double price = ct.getFoodPrice() * quantity;
        ct.setQuantity(quantity);
        ct.setAmount(price);
        cartTable.getItems().clear();
        LoadItems(cartItemList);
    }

    @FXML
    private void confirmOrder(ActionEvent event) throws IOException, RowNotSelectedException {

        try {
            if (cartItemList.size() == 0)
                throw new CartEmptyException();
        }
        catch (CartEmptyException e2)
        {
            CartEmptyLabel.setVisible(true);
            CartEmptyLabel.setText(e2.toString());
            System.out.println("Exception caught and handled " + e2);
            return;
        }

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("../views/Confirmed.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/views/Confirmed.fxml"));

    }
}