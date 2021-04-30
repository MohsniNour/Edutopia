/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CalendarA.FullCalendarView;
import Entities.Department;
import Services.DepartmentService;
import Services.SpecialtyService;
import Services.TeacherService;
import Entities.Specialty;
import Entities.Teacher;
import Entities.User;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.CheckComboBox;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * FXML Controller class
 *
 * @author Mrad
 */
public class FXMLDepartmentController implements Initializable {

    @FXML
    private Button btnadd;
    @FXML
    private TableView<Department> tv;
    @FXML
    private TableColumn<Department, String> colname;
    @FXML
    private TableColumn<Department, String> colspec;
    @FXML
    private TableColumn<Department, String> coloname;
    @FXML
    private TableColumn<Department, String> cololastname;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfoname;
    @FXML
    private TextField tfolastname;
    @FXML
    private CheckComboBox<String> ccb;
    @FXML
    private TextField tfrech;
    @FXML
    private Button btnrech;
    @FXML
    private Button btnhist;
    @FXML
    private Button btnfile;
    @FXML
    private Button btnstat;

    TeacherService ts = new TeacherService();
    User current_user = LoginController.CurrentUser;
    @FXML
    private Button btnAjouterSpécialité;
    @FXML
    private VBox vboxdrawer1;
    @FXML
    private ImageView imagechange1;
    @FXML
    private Label UserName1;
    @FXML
    private Button btn_Course11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showDepartments();
        SpecialtyService ss = new SpecialtyService();
        ObservableList<String> strings = FXCollections.observableArrayList();
        ObservableList<Specialty> elements = ss.getSpecialties();
        for (Specialty s : elements) {
            strings.add(s.getSpecialty());
        }
        ccb.getItems().addAll(strings);
        btndelete.setDisable(true);
        btnupdate.setDisable(true);

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnadd) {
            System.out.println("test");
            insert();
            showDepartments();
        }
        if (event.getSource() == btndelete) {
            delete();
            showDepartments();
        }
        if (event.getSource() == btnupdate) {
            System.out.println("test update");
            update();
            showDepartments();
        }
        if (event.getSource() == btnrech) {
            recherche();
        }
    }

    public void showDepartments() {
        DepartmentService ds = new DepartmentService();
        tv.setItems((ObservableList<Department>) ds.getDepartments());
        colname.setCellValueFactory(new PropertyValueFactory("name"));
        coloname.setCellValueFactory(new PropertyValueFactory("ownername"));
        cololastname.setCellValueFactory(new PropertyValueFactory("ownerlastname"));
        colspec.setCellValueFactory(new PropertyValueFactory("spec"));
    }

    public void insert() {
        Department d = new Department();
        DepartmentService ds = new DepartmentService();
        TeacherService ts = new TeacherService();
        Teacher t2 = new Teacher();
        t2.setName(tfoname.getText());
        t2.setLastname(tfolastname.getText());
        if (!tfname.getText().matches("[A-Za-z-*\\s]+")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Nom de département n'est pas valide!");
            alert.showAndWait();
        } else if (!ts.exist(ts.getIdTeacher(t2))) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Enseignant n'existe pas !");
            alert.showAndWait();
        } else {
            List<Department> l = new ArrayList<>();
            d.setOwnername(tfoname.getText());
            d.setOwnerlastname(tfolastname.getText());
            d.setOwnerId(ts.getIdTeacher(t2));
            d.setName(tfname.getText());
            ObservableList<String> ol = ccb.getCheckModel().getCheckedItems();
            d.setList(ol.toString());
            d.setCreated_date(java.sql.Date.valueOf(java.time.LocalDate.now()));
            d.setCreated_by(1);
            ds.create(d);
            d.setId(ds.getIdDep(d));
        }
    }

    public void delete() {
        Department d = tv.getSelectionModel().getSelectedItem();
        DepartmentService ds = new DepartmentService();
        d.setId(ds.getIdDep(d));
        d.setArchived_by(current_user.getId());
        d.setArchived_date(java.sql.Date.valueOf(java.time.LocalDate.now()));
        ds.remove(d);
        showDepartments();
    }

    public void update() {
        Department d = tv.getSelectionModel().getSelectedItem();
        System.out.println(d.toString());
        DepartmentService ds = new DepartmentService();
        d.setId(ds.getIdDep(d));
        d.setOwnername(tfoname.getText());
        d.setOwnerlastname(tfolastname.getText());
        d.setName(tfname.getText());
        ObservableList<String> ol = ccb.getCheckModel().getCheckedItems();
        d.setList(ol.toString());
        d.setLast_updated_by(current_user.getId());
        d.setLast_update_date(java.sql.Date.valueOf(java.time.LocalDate.now()));
        System.out.println(d.toString());
        ds.update(d);
    }

    public void recherche() {
        DepartmentService ss = new DepartmentService();
        ObservableList<Department> d = ss.recherche(tfrech.getText());
        System.out.println(tfrech.getText());
        int i = -1;
        int row = -1;
        if (d == null) {
            System.out.println("me 9rach ");
            Alert alert = new Alert(AlertType.INFORMATION, "Aucun Résultat Trouvé", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        } else {
            tv.setItems(d);
            colname.setCellValueFactory(new PropertyValueFactory("name"));
            coloname.setCellValueFactory(new PropertyValueFactory("ownername"));
            cololastname.setCellValueFactory(new PropertyValueFactory("ownerlastname"));
            colspec.setCellValueFactory(new PropertyValueFactory("spec"));
        }
    }

    @FXML
    private void historique(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HistDepartment.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    @FXML
    private void getFile(ActionEvent event) throws IOException {
        FileOutputStream fileOut = null;
        try {
            DepartmentService ds = new DepartmentService();
            ObservableList<TableColumn<Department, ?>> ol = tv.getColumns();
            ol.clear();
            ol.add(colname);
            ol.add(coloname);
            ol.add(cololastname);
            ol.add(colspec);
            Workbook workbook = new HSSFWorkbook();
            Sheet spreadsheet = workbook.createSheet("sample");
            Row row = spreadsheet.createRow(0);
            for (int j = 0; j < tv.getColumns().size(); j++) {
                row.createCell(j).setCellValue(tv.getColumns().get(j).getText());
            }
            for (int i = 0; i < tv.getItems().size(); i++) {
                row = spreadsheet.createRow(i + 1);
                for (int j = 0; j < tv.getColumns().size(); j++) {
                    if (tv.getColumns().get(j).getCellData(i) != null) {
                        row.createCell(j).setCellValue(tv.getColumns().get(j).getCellData(i).toString());
                    } else {
                        row.createCell(j).setCellValue("");
                    }
                }
            }
            fileOut = new FileOutputStream("src\\Excel Files\\Department.xls");
            workbook.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDepartmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void menuItemSpecialty(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLSpecialty.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("edutopia");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void menuItemTeacher(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLTeacher.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("edutopia");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void stats(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StatDepartement.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void select(MouseEvent event) {
        Department d = tv.getSelectionModel().getSelectedItem();
//            System.out.println(d.getName()+" "+d.getOwnername());
//            tv.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            if (newSelection != null) {
        tfname.setText(d.getName());
        tfolastname.setText(d.getOwnerlastname());
        tfoname.setText(d.getOwnername());
        btndelete.setDisable(false);
        btnupdate.setDisable(false);

//            }
//        });
    }

    @FXML
    private void deselect(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            tv.getSelectionModel().clearSelection();
            btnupdate.setDisable(true);
            btndelete.setDisable(true);
            tfname.clear();
            tfolastname.clear();
            tfoname.clear();
        }
    }

    @FXML
    private void HomeAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminHome.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void DepartmentAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDepartment.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void ClassAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminClasse.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void CourseAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLSubjectForAdmin.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void CalendarAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FullCalendar.fxml"));
        Parent root = loader.load();
        FullCalendarController controller = loader.getController();
        VBox f = new FullCalendarView(YearMonth.now()).getView();
        controller.calendarPane.getChildren().add(f);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void Co_StudyingAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("List_CoStudying.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void AccountAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLAdminModify.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void ClaimAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ComplaintAdd.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void DisconnectionAction(ActionEvent event) {
        Dialog confirmation = new Dialog();
        GridPane grid2 = new GridPane();
        Label l1 = new Label("Décnnecter ?");
        grid2.add(l1, 2, 2);
        confirmation.setTitle("Confirmation");
        confirmation.getDialogPane().setContent(grid2);
        ButtonType Confi = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
        ButtonType Ann = new ButtonType("Non", ButtonBar.ButtonData.OK_DONE);
        confirmation.getDialogPane().getButtonTypes().add(Confi);
        confirmation.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        confirmation.setResultConverter(new Callback<ButtonType, User>() {
            @Override
            public User call(ButtonType param) {
                if (param == Confi) {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(Add_Student_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
                }

                return null;
            }
        });
        confirmation.showAndWait();
    }

    @FXML
    private void ajouterSpécialité(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLSpecialty.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void UserAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserAddPicker.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
