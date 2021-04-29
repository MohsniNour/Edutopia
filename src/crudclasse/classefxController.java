/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudclasse;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Mrad
 */
public class classefxController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfclasse_size;
    @FXML
    private TextField tfcreated_date;
    @FXML
    private TextField tflast_update_date;
    @FXML
    private TextField tfarchived_date;
    @FXML
    private Button btnupdate;
    @FXML
    private TextField tfid2;
    @FXML
    private TextField tfid;
    @FXML
    private Button btndelete;
    @FXML
    private TableView<?> tvclasse;
    @FXML
    private TableColumn<?, ?> collid;
    @FXML
    private TableColumn<?, ?> collnom;
    @FXML
    private TableColumn<?, ?> collclasse_size;
    @FXML
    private TableColumn<?, ?> collcreated_by;
    @FXML
    private TableColumn<?, ?> collcreated_date;
    @FXML
    private TableColumn<?, ?> colllast_update_by;
    @FXML
    private TableColumn<?, ?> colllast_update_date;
    @FXML
    private TableColumn<?, ?> collarchived_by;
    @FXML
    private TableColumn<?, ?> collarchived_date;
    @FXML
    private TextField rech;
    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label UserName;
    @FXML
    private Button CHargerFrontExman;
    @FXML
    private Button btn_Course1;
    @FXML
    private Button btnajouter;
    @FXML
    private TextField tfmatiere;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField tflien;
    @FXML
    private TextField tfduree;
    @FXML
    private TableView<?> tvseance;
    @FXML
    private TableColumn<?, ?> colmatiere;
    @FXML
    private TableColumn<?, ?> coldate;
    @FXML
    private TableColumn<?, ?> colduree;
    @FXML
    private TableColumn<?, ?> collien;
    @FXML
    private TableColumn<?, ?> colidseance;
    @FXML
    private Button btnmodifier1;
    @FXML
    private Button btnsupprimer1;
    @FXML
    private TextField tfid1;
    @FXML
    private VBox vboxdrawer1;
    @FXML
    private ImageView imagechange1;
    @FXML
    private Label UserName1;
    @FXML
    private Button btn_Course11;
    @FXML
    private Button meet;
    @FXML
    private Button btnajouter1;
    @FXML
    private TableView<?> tvclasses;
    @FXML
    private TableColumn<?, ?> colnometu;
    @FXML
    private TableColumn<?, ?> colprenometud;
    @FXML
    private TableColumn<?, ?> colclasseetud;
    @FXML
    private TableColumn<?, ?> coletat;
    @FXML
    private TableColumn<?, ?> colnbp;
    @FXML
    private VBox vboxdrawer11;
    @FXML
    private ImageView imagechange11;
    @FXML
    private Label UserName11;
    @FXML
    private Button btn_Course111;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void searchkey1(KeyEvent event) {
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
    }

    @FXML
    private void searchkey(KeyEvent event) {
    }

    @FXML
    private void HomeAction(ActionEvent event) {
    }

    @FXML
    private void DepartmentAction(ActionEvent event) {
    }

    @FXML
    private void ClassAction(ActionEvent event) {
    }

    @FXML
    private void CourseAction(ActionEvent event) {
    }

    @FXML
    private void ExamAction(ActionEvent event) {
    }

    @FXML
    private void CalendarAction(ActionEvent event) {
    }

    @FXML
    private void Co_StudyingAction(ActionEvent event) {
    }

    @FXML
    private void AccountAction(ActionEvent event) {
    }

    @FXML
    private void ClaimAction(ActionEvent event) {
    }

    @FXML
    private void DisconnectionAction(ActionEvent event) {
    }

    @FXML
    private void start(MouseEvent event) {
    }

    @FXML
    private void start2(ActionEvent event) {
    }

    @FXML
    private void newP(ActionEvent event) {
    }
    
}
