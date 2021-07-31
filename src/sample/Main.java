package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
    try{
        Parent root = FXMLLoader.load(getClass().getResource("StartScreen.fxml"));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(new Scene(root, 607, 400));
        primaryStage.setTitle("Medical Center Management System by Neluni ");
        primaryStage.show();
        primaryStage.setResizable(false);
        root.getStylesheets().add(getClass().getResource("style1.css").toExternalForm());
    }
    catch (Exception e){
        e.printStackTrace();
    }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
