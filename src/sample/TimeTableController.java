package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TimeTableController implements Initializable {

    @FXML
    private TextField doc_name,ses_time;
    @FXML
    private DatePicker ses_date;
    PreparedStatement pst;
    @FXML
    private TableView<ModelTableTime> table;
    @FXML
    private TableColumn<ModelTableTime, Integer> col_id;
    @FXML
    private javafx.scene.control.TableColumn<ModelTableTime, String> col_name ;
    @FXML
    private javafx.scene.control.TableColumn<ModelTableTime, String> col_date;
    @FXML
    private javafx.scene.control.TableColumn<ModelTableTime, String> col_time;

    ObservableList<ModelTableTime> oblist = FXCollections.observableArrayList();


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
        oblist.clear();
        updateTable();
        doc_name.clear();
        ses_time.clear();


    }
    public void updateTable(){
        String jdbcURL = "jdbc:mysql://localhost/medibase";
        String username = "root";
        String password = "0852";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM timetabledb");
            while (rs.next()) {
                oblist.add(new ModelTableTime(rs.getInt("Session_ID"), rs.getString("Docter_Name"),
                        rs.getString("Date"),
                        rs.getString("Time")));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("sesid"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("sesname"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("sesdate"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("sestime"));
        table.setItems(oblist);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      updateTable();
    }
}