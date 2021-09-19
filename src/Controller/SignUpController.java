package Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;
import Model.Validation;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpController implements Initializable {


        @FXML
        private PasswordField PasswordField;

        @FXML
        private TextField UserNameField;

        @FXML
        private TextField EmailField;

        @FXML
        private TextField PhNumField;

        @FXML
        private Label EMError;

        @FXML
        private Label UNError;

        @FXML
        private Label PWError;

        @FXML
        private Button SignUpBtn;

        @FXML
        private Label PHNumError;


    @FXML
        private BorderPane SignUpBP;



    @FXML
    public void SignUp(ActionEvent event) throws SQLException{

        UNError.setVisible(false);
        EMError.setVisible(false);
        PHNumError.setVisible(false);
        PWError.setVisible(false);

        Window owner = SignUpBtn.getScene().getWindow();

        Validation EmailValidate = () -> {
            String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(EmailField.getText());
            if(!matcher.matches())
            {
                EMError.setVisible(true);
                EMError.setText("Invalid Email");
            }
            return matcher.matches();
        };

        Validation PhNumValidate = () -> {
            String regex = "[0-9]{10}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(PhNumField.getText());
            if(!matcher.matches()) {
                PHNumError.setVisible(true);
                PHNumError.setText("Invalid Phone\nNumber");
            }
            return matcher.matches();
        };



        if (UserNameField.getText().isEmpty()) {

            UNError.setVisible(true);
            UNError.setText("Field is empty");

        }

        if (EmailField.getText().isEmpty()) {

            EMError.setVisible(true);
            EMError.setText("Field is Empty");

        }
        else
        {
            EmailValidate.validate();
        }
        if(PhNumField.getText().isEmpty())
        {
            PHNumError.setVisible(true);
            PHNumError.setText("Field is Empty");

        }
        else
        {
            PhNumValidate.validate();
        }
        if (PasswordField.getText().isEmpty()) {
            PWError.setVisible(true);
            PWError.setText("Field is Empty");
            return;
        }



        if(!EmailValidate.validate() | !PhNumValidate.validate()){
            return;
        }


        String fullName = UserNameField.getText();
        String emailId = EmailField.getText();
        String password = PasswordField.getText();
        String phoneNumber = PhNumField.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertRecord(fullName, emailId, phoneNumber,password);

        showAlert(owner,
                "Welcome " + UserNameField.getText());
        UserNameField.clear();
        PhNumField.clear();
        EmailField.clear();
        PasswordField.clear();


    }

    private static void showAlert(Window owner, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Registration Successful!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();

    }

    public void GoBackToLogin(){
        try {
            BorderPane pane = FXMLLoader.load(getClass().getResource("../views/LoginUI.fxml"));
            SignUpBP.getChildren().setAll(pane);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        UNError.setVisible(false);
        EMError.setVisible(false);
        PHNumError.setVisible(false);
        PWError.setVisible(false);

    }
}


