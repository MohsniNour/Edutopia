/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Sabrina
 */
public class Start_Interface extends Application {

    public static final String CURRENCY = "$";

    @Override
    public void start(Stage primaryStage) {
        try {
//         Parent root = FXMLLoader.load(getClass().getResource("Add_Student_FXML.fxml"));
//         Parent root = FXMLLoader.load(getClass().getResource("CoStudyingAdd.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("List_CoStudying.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Edutopia");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /* @param
    args the command line arguments

     */
    public static void main(String[] args) {
        launch(args);
    }

}
