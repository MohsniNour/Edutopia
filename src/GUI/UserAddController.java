/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.UserService;
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


/**
 * FXML Controller class
 *
 * @author Sabrina
 */
public class UserAddController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField last_name;
    @FXML
    private ComboBox<String> role = new ComboBox();
    ;
    ObservableList<String> options = FXCollections.observableArrayList(
            "Admin",
            "Teacher",
            "Student"
    );
    @FXML
    private TextField cin;
    @FXML
    private TextField email;
    @FXML
    private TextField birth_date;
    @FXML
    private Button add_button;
    @FXML
    private TextField phone_number;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        role.setItems(options);
    }

    @FXML
    private void add_button_action(ActionEvent event) {
        try {

            UserService ss = new UserService();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
            java.util.Date date = sdf.parse(birth_date.getText());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            System.out.println(sqlDate);
            User u = new User(role.getValue().toString(), name.getText(), last_name.getText(), Integer.parseInt(cin.getText()), email.getText(), Integer.parseInt(phone_number.getText()), sqlDate);
            ss.addUser(u);
            
        } catch (ParseException ex) {
            ex.printStackTrace(); 
        }
    }
}
