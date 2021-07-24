package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class TimeTableController {

    @FXML
    private TextField doc_name,ses_time;
    @FXML
    private DatePicker ses_date;
    PreparedStatement pst;

    //adding new docter session

    public void addsesstion() throws ClassNotFoundException, SQLException {
        String jdbcURL = "jdbc:mysql://localhost/medibase";
        String username = "root";
        String password = "0852";
        Connection connection = DriverManager.getConnection(jdbcURL,username,password);
        Class.forName("com.mysql.jdbc.Driver");
        String Vdname=doc_name.getText();
        LocalDate date=ses_date.getValue();
        String Vdate=date.toString();
        String Vtime=ses_time.getText();
        pst=connection.prepareStatement("INSERT INTO timetabledb (Docter_Name,Date,Time)VALUES (?,?,?) ");
        pst.setString(1,Vdname);
        pst.setString(2,Vdate);
        pst.setString(3,Vtime);
        pst.executeUpdate();


    }

}