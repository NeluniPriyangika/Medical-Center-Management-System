package sample;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
    @FXML
    private Button cancelbutton;
    @FXML
    private Label loginmessagelabel;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;


    public void validatelogin() {
    }

    public void loginbuttonAction(ActionEvent event){
            loginmessagelabel.setText("You tried to login");
            if(username.getText().isBlank()==false && password.getText().isBlank()==false){
                loginmessagelabel.setText("You tried to login");
                validatelogin();
            }else
                loginmessagelabel.setText("Please Enter username and password");

        }

    public void cancelbuttonAction(ActionEvent event){
        Stage stage = (Stage) cancelbutton.getScene().getWindow();
        stage.close();
    }
}
