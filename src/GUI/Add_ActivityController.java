/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activity;
import Services.ActivityService;
import java.io.File;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
    private DatePicker pkDeadline;
    @FXML
    private ComboBox<String> selectedCours= new ComboBox();
    ObservableList<String> options = FXCollections.observableArrayList(
            "GL",
            "TLA",
            "PIDEV"
    );
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectedCours.setItems(options);
    }    

    @FXML
    private void cancelAction(ActionEvent event) {
    }

    @FXML
    private void addAction(ActionEvent event) {
//        ActivityService as = new ActivityService();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
////            Date date;
//////            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//////            System.out.println(sqlDate);
////            date = (Date)pkDeadline.getValue();
//        Activity act= new Activity(txtName.getText(), selectedCours.getValue(), "nour");
//        as.add(act);
    }

    @FXML
    private FileChooser ImportButtonAction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("PDF Files", "*.pdf"));
        File f = fc.showOpenDialog(null);
        if (f !=null)
        {
            importLabel.setText(f.getAbsolutePath());
        }
        return fc;
    }

    
}