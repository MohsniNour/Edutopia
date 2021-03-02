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
    private TextField phone;
    @FXML
    private TextField birth_date;
    @FXML
    private Button add_button;

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

            StudentService ss = new StudentService();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
            java.util.Date date = sdf.parse(birth_date.getText());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            System.out.println(sqlDate);
            Student p = new Student(role.getValue().toString(), name.getText(), last_name.getText(), Integer.parseInt(cin.getText()), email.getText(), Integer.parseInt(phone.getText()), sqlDate);
            ss.addUser(p);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

}
