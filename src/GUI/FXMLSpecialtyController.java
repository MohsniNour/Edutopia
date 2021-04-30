package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import CalendarA.FullCalendarView;
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
import javafx.scene.control.MenuItem;
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
import org.controlsfx.control.CheckComboBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Mrad
 */
public class FXMLSpecialtyController implements Initializable {

    @FXML
    private Button btnadd;
    private TextField tfid;
    @FXML
    private TextField tfspec;
    @FXML
    private TableView<Specialty> tvid;
    private TableColumn<Specialty, Integer> tvidresp;
    @FXML
    private TableColumn<Specialty, String> tvspec;
    @FXML
    private TableColumn<Specialty, String> tvniveaux;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    private TextField tfniveau;
    private Button btndepart;
    @FXML
    private CheckComboBox<String> ccb;
    @FXML
    private TextField tfname;
    @FXML
    private TableColumn<Specialty, String> tvlastname;
    @FXML
    private TableColumn<Specialty, String> tvname;
    @FXML
    private TextField tflastname;
    @FXML
    private Button btnrech;
    @FXML
    private TextField tfrech;
    @FXML
    private Button btnprint;
    @FXML
    private Button btnhist;

    TeacherService ts = new TeacherService();
    User current_user = LoginController.CurrentUser;
    @FXML
    private VBox vboxdrawer1;
    @FXML
    private ImageView imagechange1;
    @FXML
    private Label UserName1;
    @FXML
    private Button btn_Course11;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnadd) {
            insert();
            showSpecialties();
        }
        if (event.getSource() == btndelete) {
            delete();
            showSpecialties();
        }
        if (event.getSource() == btnupdate) {
            update();
            showSpecialties();
        }
        if (event.getSource() == btndepart) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("edut.GUI.FXMLDepartment.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (event.getSource() == btnrech) {
            recherche();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showSpecialties();
        ObservableList<String> strings = FXCollections.observableArrayList();
        strings.add("1ere");
        strings.add("2eme");
        strings.add("3eme");
        strings.add("4eme");
        strings.add("5eme");
        ccb.getItems().addAll(strings);
        btndelete.setDisable(true);
        btnupdate.setDisable(true);
    }

    /*public ObservableList<Specialty> getListNiveaux() {
        SpecialtyService ss = new SpecialtyService();
        ObservableList ol = ss.getSpecialties();
        return ol;
    }*/
    public void showSpecialties() {
        SpecialtyService ss = new SpecialtyService();
        tvid.setItems(ss.getSpecialties());
        tvname.setCellValueFactory(new PropertyValueFactory("name"));
        tvlastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        tvspec.setCellValueFactory(new PropertyValueFactory("specialty"));
        tvniveaux.setCellValueFactory(new PropertyValueFactory("niveau"));
    }

    public void insert() {
        Specialty s = new Specialty();
        TeacherService ts = new TeacherService();
        Teacher t = new Teacher();
        Teacher t2 = new Teacher();

        t2.setName(tfname.getText());
        t2.setLastname(tflastname.getText());
        System.out.println(ts.getIdTeacher(t2));
        if (!tfspec.getText().matches("[A-Za-z-*\\s]+")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Nom de Spécialité n'est pas valide!");
            alert.showAndWait();
        } else if (!ts.exist(ts.getIdTeacher(t2))) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Enseignant n'existe pas !");
            alert.showAndWait();
        } else {
            t.setName(tfname.getText());
            t.setLastname(tflastname.getText());
            s.setName(tfname.getText());
            s.setLastname(tflastname.getText());
            s.setId_resp(ts.getIdTeacher(t));
            s.setSpecialty(tfspec.getText());
            ObservableList<String> ol = ccb.getCheckModel().getCheckedItems();
            s.setListNiveaux(ol.toString());
            s.setCreated_by(current_user.getId());
            s.setCreated_date(java.sql.Date.valueOf(java.time.LocalDate.now()));
            SpecialtyService ss = new SpecialtyService();
            ss.addSpecialty(s);
            s.setId(ss.getIdSpecialty(s));
        }
    }

    public void delete() {
        Specialty s = tvid.getSelectionModel().getSelectedItem();
//        Teacher t = new Teacher();
//        TeacherService ts= new TeacherService();
//        t.setName(s.getLastname());
//        t.setLastname(s.getName());
//        s.setId_resp(ts.getIdTeacher(t));
        SpecialtyService ss = new SpecialtyService();
        s.setId(ss.getIdSpecialty(s));
        s.setArchived_by(current_user.getId());
        s.setArchived_date(java.sql.Date.valueOf(java.time.LocalDate.now()));
        ss.remove(s);

    }

    public void update() {
        TeacherService ts = new TeacherService();
        Teacher t2 = new Teacher();

        t2.setName(tfname.getText());
        t2.setLastname(tflastname.getText());
//        System.out.println(ts.getIdTeacher(t2));
//        System.out.println(ts.getIdTeacher(t2)+" "+t2.getName()+" "+t2.getLastname());
        if (ts.exist(ts.getIdTeacher(t2))) {
            SpecialtyService ss = new SpecialtyService();
            Specialty s = tvid.getSelectionModel().getSelectedItem();
            s.setId(ss.getIdSpecialty(s));
            Teacher t = new Teacher();
            t.setName(s.getLastname());
            t.setLastname(s.getName());
            s.setName(tfname.getText());
            s.setLastname(tflastname.getText());
            s.setSpecialty(tfspec.getText());
            s.setId_resp(ts.getIdTeacher(t2));
            ObservableList<String> ol = ccb.getCheckModel().getCheckedItems();
            s.setNiveau(ol.toString());
            s.setLast_updated_by(current_user.getId());
            s.setLast_update_date(java.sql.Date.valueOf(java.time.LocalDate.now()));
            ss.update(s);
            showSpecialties();
        }
    }

    public void recherche() {
        SpecialtyService ss = new SpecialtyService();
        ObservableList<Specialty> s = ss.recherche(tfrech.getText());
        int i = -1;
        int row = -1;
        if (s == null) {
            Alert alert = new Alert(AlertType.INFORMATION, "Aucun Résultat Trouvé", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        } else {
            tvid.setItems(s);
            tvname.setCellValueFactory(new PropertyValueFactory("name"));
            tvlastname.setCellValueFactory(new PropertyValueFactory("lastname"));
            tvspec.setCellValueFactory(new PropertyValueFactory("specialty"));
            tvniveaux.setCellValueFactory(new PropertyValueFactory("niveau"));
        }
    }

    @FXML
    private void print(ActionEvent event) throws IOException {
        FileOutputStream fileOut = null;
        try {
            DepartmentService ds = new DepartmentService();
            ObservableList<TableColumn<Specialty, ?>> ol = tvid.getColumns();
            ol.clear();
            ol.add(tvname);
            ol.add(tvlastname);
            ol.add(tvspec);
            ol.add(tvniveaux);
            Workbook workbook = new HSSFWorkbook();
            Sheet spreadsheet = workbook.createSheet("sample");
            Row row = spreadsheet.createRow(0);
            for (int j = 0; j < tvid.getColumns().size(); j++) {
                System.out.println(tvid.getColumns().get(j).getText());
                row.createCell(j).setCellValue(tvid.getColumns().get(j).getText());
            }
            for (int i = 0; i < tvid.getItems().size(); i++) {
                row = spreadsheet.createRow(i + 1);
                for (int j = 0; j < tvid.getColumns().size(); j++) {
                    if (tvid.getColumns().get(j).getCellData(i) != null) {
                        row.createCell(j).setCellValue(tvid.getColumns().get(j).getCellData(i).toString());
                    } else {
                        row.createCell(j).setCellValue("");
                    }
                }
            }
            fileOut = new FileOutputStream("src\\Excel Files\\Specialty.xls");
            workbook.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDepartmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void historique(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HistSpecialty.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
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

    private void menuItemDepartment(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDepartment.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("edutopia");
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void deselect(KeyEvent event) {
//        BitMatrix bitMatrix = generateMatrix(data, size);
//
//        String imageFormat = "png";
//        String outputFileName = "c:/code/qrcode-01." + imageFormat;
//
//        // write in a file
//        writeImage(outputFileName, imageFormat, bitMatrix);
        if (event.getCode() == KeyCode.ESCAPE) {
            tvid.getSelectionModel().clearSelection();
            tfspec.clear();
            tfname.clear();
            tflastname.clear();
            btnupdate.setDisable(true);
            btndelete.setDisable(true);
        }
    }

    @FXML
    private void select(MouseEvent event) {
        Specialty s = tvid.getSelectionModel().getSelectedItem();
        tfspec.setText(s.getSpecialty());
        tfname.setText(s.getName());
        tflastname.setText(s.getLastname());
        btnupdate.setDisable(false);
        btndelete.setDisable(false);
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
    private void UserAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserAddPicker.fxml"));
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
    private void goBack(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDepartment.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


}
