package Controller;

import javafx.event.ActionEvent;
import java.sql.Connection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private BorderPane LoginBP;

    @FXML
    private TextField NameField;

    @FXML
    private PasswordField Password;

    @FXML
    private Label InvalidError;

    Stage dialogStage = new Stage();
    Scene scene;
    static String username, passw;
    Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public LoginController( ){
        connection = SQLConnection.connectdb();

    }

    public String getUsername(){
        return username;
    }

    public void loginAction(ActionEvent event)
    {
        username = NameField.getText();
        passw = Password.getText();

        String sql = "SELECT * FROM userdetails WHERE BINARY user_name = ? and BINARY password = ?";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, passw);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                InvalidError.setVisible(true);
                InvalidError.setText("Invalid Credentials");
            }else{
//                infoBox("Login Successfull",null,"Success" )
                Node node = (Node)event.getSource();
                dialogStage = (Stage) node.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource("../views/FoodOrderUIn.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }


    public void SignUp(ActionEvent event) {
        try {

            BorderPane pane  = FXMLLoader.load(getClass().getResource("../views/SignUp.fxml"));
            LoginBP.getChildren().setAll(pane);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        InvalidError.setVisible(false);

    }

}

