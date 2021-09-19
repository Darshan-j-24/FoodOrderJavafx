package Controller;

import Model.CartItem;
import Model.Context;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConfirmedController implements Initializable{

    List<CartItem> cartItemList = Context.getInstance().currentCart();
    List<String> FoodName = Context.getInstance().currentFoodList();


    @FXML
    private Label ThLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoginController LC = new LoginController();
        ThLabel.setText("Thank You "+LC.getUsername()+" for Ordering");
    }

        @FXML
    public void GoHomeFromConfirmOrder(ActionEvent event) throws IOException {
        cartItemList.clear();
        FoodName.clear();
        Parent root = FXMLLoader.load(getClass().getResource("../views/FoodOrderUIn.fxml"));
        Scene scene = new Scene(root);
//        FoodController fc = new FoodController();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setUserData(userData);
//        fc.receiveData(userData);
        window.setScene(scene);
        window.show();


    }

}