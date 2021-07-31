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

public class RegController {
    @FXML
    private Button regButton,backLogin;
    @FXML
    private TextField fnameBox,lnameBox,unameBox,atBox;
    @FXML
    private PasswordField pBox;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    PreparedStatement pst;
    int rs;
    @FXML
    private Label label1;


    public void regFunction() throws SQLException, ClassNotFoundException {
        String jdbcURL = "jdbc:mysql://localhost/medibase";
        String username = "root";
        String password = "0852";
        Connection connection = DriverManager.getConnection(jdbcURL,username,password);
        Class.forName("com.mysql.jdbc.Driver");
        String fname=fnameBox.getText();
        String lname=lnameBox.getText();
        String uname=unameBox.getText();
        String pwd=pBox.getText();
        String accType=atBox.getText();
        pst=connection.prepareStatement("INSERT INTO user_account(firstname,lastname,username,password,account_type) VALUES (?,?,?,?,?)");
        pst.setString(1,fname);
        pst.setString(2,lname);
        pst.setString(3,uname);
        pst.setString(4,pwd);
        pst.setString(5,accType);
        rs= pst.executeUpdate();
        label1.setText("Success..! Now you can login");


    }
    public void switchtoLogin(ActionEvent event)throws IOException {

            Parent root = FXMLLoader.load(getClass().getResource("StartScreen.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

}
