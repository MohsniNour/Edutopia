/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Student;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import Services.StudentService;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class Add_Student_FXMLController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField cin;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField birth_date;
    @FXML
    private Button add_button;
    @FXML
    private ComboBox<String> classe = new ComboBox();
    ObservableList<String> option = FXCollections.observableArrayList(
            "1A",
            "2A",
            "2B",
            "3A",
            "3B"
    );
    @FXML
    private VBox vbox;
    @FXML
    private Button goto_list;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        classe.setItems(option);
    }

    @FXML
    private void add_button_action(ActionEvent event) {
        if (name.getText() == "") {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning !");
            alert.setHeaderText(null);
            alert.setContentText("Fill the name please ");
            alert.showAndWait();
        } else if (last_name.getText() == "") {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning !");
            alert.setHeaderText(null);
            alert.setContentText("Fill the last name please ");
            alert.showAndWait();

        } else if (Integer.parseInt(cin.getText()) == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning !");
            alert.setHeaderText(null);
            alert.setContentText("Cin must be a positive number");
            alert.showAndWait();

        } else {

            try {

                StudentService ss = new StudentService();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
                java.util.Date date = sdf.parse(birth_date.getText());
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                System.out.println(sqlDate);
                Student p = new Student("Student", name.getText(), last_name.getText(), Integer.parseInt(cin.getText()), email.getText(), Integer.parseInt(phone.getText()), sqlDate);
                ss.addStudent(p);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void on_goto_list(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("display_Student_FXML.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
