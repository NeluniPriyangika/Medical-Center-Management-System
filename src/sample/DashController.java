package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import javafx.scene.control.CheckBox;

import java.sql.*;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DashController implements Initializable {
    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void Dashboard(ActionEvent event){
        borderPane.setCenter(null);

    }
    @FXML
    private void Dash1(ActionEvent event){
        loadUI("Dash1");
    }


    @FXML
    private void Dash2(ActionEvent event){
        loadUI("Dash2");
    }
    @FXML
    private void Dash3(ActionEvent event){
        loadUI("Dash3");

    }
    @FXML
    private TextField name,id,docter,phone,time;
    @FXML
    private TextArea covid;
    @FXML
    private DatePicker date;
    PreparedStatement pst;
    @FXML
    private Label messageLabel;
    @FXML
    private CheckBox iscovid;


    @FXML
    public void newappoinment() throws SQLException, ClassNotFoundException {
        //Database connection
        String jdbcURL = "jdbc:mysql://localhost/medibase";
        String username = "root";
        String password = "0852";
        Connection connection = DriverManager.getConnection(jdbcURL,username,password);
        Class.forName("com.mysql.jdbc.Driver");
        //

            String Pname=name.getText();
            String Pid=id.getText();
            String docname=docter.getText();
            String phoneno=phone.getText();
            String atime=time.getText();
            String covidnote=covid.getText();
            LocalDate adate = date.getValue();
            String appdate=adate.toString();
            pst=connection.prepareStatement("Insert INTO appoinments(Patient_Name," +
                    "Patient_ID,Docter_Name,Patient_Phone,Appoin_Date,Time_app,Covid_Note)VALUES " +
                    "(?,?,?,?,?,?,?)");
            pst.setString(1,Pname);
            pst.setString(2,Pid);
            pst.setString(3,docname);
            pst.setString(4,phoneno);
            pst.setString(5,appdate);
            pst.setString(6,atime);
            pst.setString(7,covidnote);
            pst.executeUpdate();
            //clearing fileds
            name.clear();
            id.clear();
            phone.clear();
            docter.clear();
            time.clear();
            covid.clear();

            messageLabel.setText("Appoinment added successfully ...! ");
            if(iscovid.isSelected()){
                //test
                System.out.println("checkbox working");
                //Covid risk patient DB update
                
            }
            else{
                System.out.println("negative");
            }

            //clear the checkbox tick mark
            iscovid.setSelected(false);



    }

    private void loadUI(String ui){
        Parent root =null;
        try{
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        }catch (IOException ex){
            Logger.getLogger(DashController.class.getName()).log(Level.SEVERE,null,ex);
        }
        borderPane.setCenter(root);
    }
}
