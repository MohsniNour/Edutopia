package GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start_Interface extends Application {
    
    public static final String CURRENCY = "$";
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Front_Subject.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Liste des matières");
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
