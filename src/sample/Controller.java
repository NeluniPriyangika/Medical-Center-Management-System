package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;


//starting screen controller
public class Controller {
    @FXML
    private Button cancelbutton;
    @FXML
    private Label loginmessagelabel;
    @FXML
    private PasswordField password1;
    @FXML
    private TextField username1;
    PreparedStatement pst;
    ResultSet rs;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void validatelogin(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        //Database connection
        String jdbcURL = "jdbc:mysql://localhost/medibase";
        String username = "root";
        String password = "0852";
        Connection connection = DriverManager.getConnection(jdbcURL,username,password);
        Class.forName("com.mysql.jdbc.Driver");
        String uname = username1.getText();
        String psd = password1.getText();
        pst=connection.prepareStatement("SELECT * FROM user_account WHERE username=? and password=?");
        pst.setString(1, uname);
        pst.setString(2, psd);
        rs = pst.executeQuery();
        if(rs.next()){
        loginmessagelabel.setText("Congratulations");
            Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else{
            loginmessagelabel.setText("Login failed");
        }

    }

    public void loginbuttonAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
          //  loginmessagelabel.setText("You tried to login");
            if(username1.getText().isBlank()==false && password1.getText().isBlank()==false){
               // loginmessagelabel.setText("You tried to login");
                validatelogin(event);
            }else
              loginmessagelabel.setText("Please Enter username and password");
        }

        //rediretcs to the registration page
    public void switchtoSC1(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void cancelbuttonAction(ActionEvent event){
        Stage stage = (Stage) cancelbutton.getScene().getWindow();
        stage.close();
    }
}
