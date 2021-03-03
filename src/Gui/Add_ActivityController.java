/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class Add_ActivityController implements Initializable {

    @FXML
    private Button  btnDeadlineChooser;
    @FXML
    private Button add_button;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField txtName;
    @FXML
    private SplitMenuButton txtCours;
    @FXML
    private DatePicker pkDeadline;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cancelAction(ActionEvent event) {
    }

    @FXML
    private void addAction(ActionEvent event) {
    }

    @FXML
    private void ImportButtonAction(ActionEvent event) {
    }

    @FXML
    private void ListAllCoursAction(ActionEvent event) {
    }
    
}
