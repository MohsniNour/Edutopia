/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
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
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class Add_ActivityController implements Initializable {

    @FXML
    private Button  btnDeadlineChooser;
    @FXML
    private Label importLabel;
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
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("PDF Files", "*.pdf"));
        File f = fc.showOpenDialog(null);
        if (f !=null)
        {
            importLabel.setText(f.getAbsolutePath());
        }
    }

    @FXML
    private void ListAllCoursAction(ActionEvent event) {
    }
    
}