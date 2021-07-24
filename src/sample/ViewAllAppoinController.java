package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewAllAppoinController implements Initializable {
    @FXML
    private TextField searchbar;

    @FXML
    private TableView<ModelTable> table;
    @FXML
    private TableColumn<ModelTable, Integer> col_id;
    @FXML
    private javafx.scene.control.TableColumn<ModelTable, String> col_pname;
    @FXML
    private javafx.scene.control.TableColumn<ModelTable, String> col_pnic;
    @FXML
    private javafx.scene.control.TableColumn<ModelTable, String> col_pno;
    @FXML
    private javafx.scene.control.TableColumn<ModelTable, String> col_appdate;
    @FXML
    private javafx.scene.control.TableColumn<ModelTable, String> col_apptime;
    @FXML
    private javafx.scene.control.TableColumn<ModelTable, String> col_covid;
    @FXML
    private javafx.scene.control.TableColumn<ModelTable, String> col_dname;
    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //DbConnection
        String jdbcURL = "jdbc:mysql://localhost/medibase";
        String username = "root";
        String password = "0852";
        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM appoinments");
            while (rs.next()) {
                oblist.add(new ModelTable(rs.getInt("Patient_No"), rs.getString("Patient_Name"),
                        rs.getString("Patient_ID"),
                        rs.getString("Docter_Name"),
                        rs.getString("Patient_Phone"),
                        rs.getString("Appoin_Date"),
                        rs.getString("Time_app"),
                        rs.getString("Covid_Note")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("pno"));
        col_pname.setCellValueFactory(new PropertyValueFactory<>("pname"));
        col_pnic.setCellValueFactory(new PropertyValueFactory<>("pid"));
        col_dname.setCellValueFactory(new PropertyValueFactory<>("dname"));
        col_pno.setCellValueFactory(new PropertyValueFactory<>("pphone"));
        col_appdate.setCellValueFactory(new PropertyValueFactory<>("appdate"));
        col_apptime.setCellValueFactory(new PropertyValueFactory<>("apptime"));
        col_covid.setCellValueFactory(new PropertyValueFactory<>("covidnote"));
        table.setItems(oblist);

        FilteredList<ModelTable> filteredData = new FilteredList<>(oblist, b -> true);
        searchbar.textProperty().addListener((observable, oldvalue, newValue) -> {
            filteredData.setPredicate(ModelTable -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (ModelTable.getPname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (ModelTable.getCovidnote().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (ModelTable.getDname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (ModelTable.getAppdate().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else
                    return false;

            });


        });
        SortedList<ModelTable> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);

    }
}
