/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Services.DepartmentService;
//import Services.SubjectService;
import Services.TeacherService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import Entities.Department;
//import Entities.Subject;
import Entities.Teacher;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.controlsfx.control.CheckComboBox;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.nio.file.Paths;

/**
 * FXML Controller class
 *
 * @author Mrad
 */
public class FXMLTeacherController implements Initializable {

    @FXML
    private TextField tfname;

    @FXML
    private TextField tflastname;

    @FXML
    private TextField tfcin;

    @FXML
    private DatePicker tfdate;

    @FXML
    private TableView<Teacher> tv;

    @FXML
    private TableColumn<Teacher, String> colname;

    @FXML
    private TableColumn<Teacher, String> collastname;

    @FXML
    private TableColumn<Teacher, Long> colcin;

    @FXML
    private TableColumn<Teacher, String> colemail;

    @FXML
    private TableColumn<Teacher, Integer> colphone;

    @FXML
    private TableColumn<Teacher, String> coldep;

    @FXML
    private TableColumn<Teacher, String> colbirthdate;

    @FXML
    private TableColumn<Teacher, Teacher> colsubjct;

    @FXML
    private Button btnupdate;

    @FXML
    private Button btndelete;

    @FXML
    private TextField tfemail;

    @FXML
    private TextField tfnumber;

    @FXML
    private ComboBox<String> ccbdep;

    @FXML
    private CheckComboBox<String> ccbsubject;
    @FXML
    private Button btnadd;
    @FXML
    private TextField tfrech;
    @FXML
    private Button btnrech;
    @FXML
    private Button btnhist;
    @FXML
    private Button btnfile;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    ObservableList<Integer> ids = FXCollections.observableArrayList();
    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label UserName;
    @FXML
    private Button btn_Course1;
    @FXML
    private Button btnqrcode;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("taaaaaaaaaaaaat");
        int i=0;
        int str[] = null;
        showTeachers();
        DepartmentService ds = new DepartmentService();
        ObservableList<String> strings = FXCollections.observableArrayList();
        ObservableList<Department> elements = ds.getDepartments();
        for (Department s : elements) {
            strings.add(s.getName());
            ids.add(s.getId());
        }
        System.out.println(strings);
        ccbdep.getItems().addAll(strings);
//        SubjectService ss = new SubjectService();
//        ObservableList<String> stringss = FXCollections.observableArrayList();
//        ObservableList<Subject> elementss = ss.getSubjects();
//        for (Subject s1 : elementss) {
//            stringss.add(s1.getName());
//        }
//        System.out.println(stringss);
//        ccbsubject.getItems().addAll(stringss);
        btndelete.setDisable(true);
        btnupdate.setDisable(true);     
        btnqrcode.setDisable(true);
    }    
    public void showTeachers(){
        TeacherService ts = new TeacherService();
        for (Teacher t : ts.getTeachersObs()) {
            System.out.println(t.toString());
        }
        tv.setItems(ts.getTeachersObs());
        colname.setCellValueFactory(new PropertyValueFactory("name"));
        collastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        colcin.setCellValueFactory(new PropertyValueFactory("cin"));
        colemail.setCellValueFactory(new PropertyValueFactory("email"));
        colphone.setCellValueFactory(new PropertyValueFactory("phone_number"));
        coldep.setCellValueFactory(new PropertyValueFactory("deps"));
        colsubjct.setCellValueFactory(new PropertyValueFactory("subject"));
        colbirthdate.setCellValueFactory(new PropertyValueFactory("birth_date"));
    }

    @FXML
    private void update(ActionEvent event) {
        Teacher t = tv.getSelectionModel().getSelectedItem();
        DepartmentService ds = new DepartmentService();
        TeacherService ts = new TeacherService();
        t.setId(ts.getIdTeacher(t));
        t.setName(tfname.getText());
        t.setLastname(tflastname.getText());
        t.setCin(Integer.parseInt(tfcin.getText()));
        LocalDate date = tfdate.getValue(); 
        java.sql.Date dd = java.sql.Date.valueOf(date);
        t.setBirth_date(dd);
        t.setEmail(tfemail.getText());
        t.setPhone_number(Integer.parseInt(tfnumber.getText()));
        String sss = ccbdep.getValue();
        t.setDeps(sss);
        ObservableList<String> ol2 = ccbsubject.getCheckModel().getCheckedItems();
        t.setSubject(ol2.toString());
        t.setLast_updated_by("hamza");
        t.setLast_update_date(java.sql.Date.valueOf(java.time.LocalDate.now()));
        System.out.println(t.toString());
        ts.update(t);
        showTeachers();
    }

    @FXML
    private void remove(ActionEvent event) {
        Teacher t = tv.getSelectionModel().getSelectedItem();
        TeacherService ts = new TeacherService();
        t.setArchived_by("mahmoud");
        t.setArchived_date(java.sql.Date.valueOf(java.time.LocalDate.now()));
        t.setId(ts.getIdTeacher(t));
        ts.remove(t);
        showTeachers();
    }

    @FXML
    private void add(ActionEvent event) {
        Teacher t = new Teacher();
        TeacherService ts = new TeacherService();
        DepartmentService ds = new DepartmentService();
        if (!tfname.getText().matches("[A-Za-z-*\\s]+")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Nom incorrect!");
            alert.showAndWait();
        }
        else if (!tflastname.getText().matches("[A-Za-z-*\\s]+")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Prénom incorrect!");
            alert.showAndWait();
        }
        else if (!tfcin.getText().matches("[0-9]+")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Cin incorrect!");
            alert.showAndWait();
        }
        else if (tfdate.getValue() == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Date de naissance invalide!!");
            alert.showAndWait();
        }
        else if (ccbdep.getSelectionModel().getSelectedItem()==null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez affecté un départemenet!");
            alert.showAndWait();
        }
        else if (ccbsubject.getCheckModel().getCheckedItems()==null){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez affecté au moins une matière!");
            alert.showAndWait();
        }
        else if (!tfnumber.getText().matches("[0-9]+")) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Numéro de téléphone invalide!");
            alert.showAndWait();
        }
        else {
            t.setName(tfname.getText());
            t.setLastname(tflastname.getText());
            t.setCin(Integer.parseInt(tfcin.getText()));
            LocalDate date = tfdate.getValue(); 
            java.sql.Date d = java.sql.Date.valueOf(date);
            t.setBirth_date(d);
            t.setEmail(tfemail.getText());
            t.setPhone_number(Integer.parseInt(tfnumber.getText()));
            String deps = ccbdep.getValue();
            t.setDepId(ids.get(ccbdep.getSelectionModel().getSelectedIndex()));
            System.out.println(t.getDepId());
            t.setDeps(deps);
            ObservableList<String> ol2 = ccbsubject.getCheckModel().getCheckedItems();
            t.setSubject(ol2.toString());
            t.setCreated_by("hamza");
            t.setCreated_date(java.sql.Date.valueOf(java.time.LocalDate.now()));
            ts.addTeacher(t);
            t.setId(ts.getIdTeacher(t));
            showTeachers();
        }
    }
    @FXML
    public void recherche(){
        TeacherService ts = new TeacherService();
        ObservableList<Teacher> t = ts.recherche(tfrech.getText());
        int i=-1;
        int row =-1;
        if (t==null) {
            Alert alert = new Alert(AlertType.INFORMATION, "Aucun Résultat Trouvé", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        else {
            tv.setItems(t);
            colname.setCellValueFactory(new PropertyValueFactory("name"));
            collastname.setCellValueFactory(new PropertyValueFactory("lastname"));
            colcin.setCellValueFactory(new PropertyValueFactory("cin"));
            colemail.setCellValueFactory(new PropertyValueFactory("email"));
            colphone.setCellValueFactory(new PropertyValueFactory("phone_number"));
            coldep.setCellValueFactory(new PropertyValueFactory("deps"));
            colsubjct.setCellValueFactory(new PropertyValueFactory("subject"));
            colbirthdate.setCellValueFactory(new PropertyValueFactory("birth_date"));
        }  
    }

    @FXML
    private void historique(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HistTeacher.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("edutopia");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void getfile(ActionEvent event) throws IOException {
        FileOutputStream fileOut = null;
        try {
            DepartmentService ds = new DepartmentService();
            ObservableList<TableColumn<Teacher, ?>> ol = tv.getColumns();
            ol.clear();
            ol.add(colname);
            ol.add(collastname);
            ol.add(colcin);
            ol.add(colemail);
            ol.add(colphone);
            ol.add(coldep);
            ol.add(colbirthdate);
            ol.add(colsubjct);
            Workbook workbook = new HSSFWorkbook();
            Sheet spreadsheet = workbook.createSheet("sample");
            Row row = spreadsheet.createRow(0);
            for (int j = 0; j < tv.getColumns().size(); j++) {
                row.createCell(j).setCellValue(tv.getColumns().get(j).getText());
            }
            for (int i = 0; i < tv.getItems().size(); i++) {
                row = spreadsheet.createRow(i + 1);
                for (int j = 0; j < tv.getColumns().size(); j++) {
                    if(tv.getColumns().get(j).getCellData(i) != null) {
                        row.createCell(j).setCellValue(tv.getColumns().get(j).getCellData(i).toString()); 
                    }
                    else {
                        row.createCell(j).setCellValue("");
                    }   
                }
            }
            fileOut = new FileOutputStream("Teacher.xls");
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
        if (event.getCode()==KeyCode.ESCAPE) {
            tv.getSelectionModel().clearSelection();
            tfcin.clear();
            tfname.clear();
            tflastname.clear();
            tfemail.clear();
            tfnumber.clear();
            tfdate.setValue(null);
            btnupdate.setDisable(true);
            btndelete.setDisable(true);
            btnqrcode.setDisable(true);
        }
    }

    @FXML
    private void select(MouseEvent event) {
        Teacher t = tv.getSelectionModel().getSelectedItem();
        tfcin.setText(String.valueOf(t.getCin()));
        tfname.setText(t.getName());
        tflastname.setText(t.getLastname());
        tfemail.setText(t.getEmail());
        tfnumber.setText(String.valueOf(t.getPhone_number()));
        java.sql.Date ld = (java.sql.Date) t.getBirth_date();
        tfdate.setValue(ld.toLocalDate());
        btnupdate.setDisable(false);
        btndelete.setDisable(false);
        btnqrcode.setDisable(false);
        try {
            qrcode();
        } catch (IOException ex) {
            Logger.getLogger(FXMLTeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void qrcode() throws IOException {

        Teacher t = tv.getSelectionModel().getSelectedItem();
        String str = t.getName()+", "+t.getLastname()+", "+t.getCin()+", "+t.getEmail()+", "+t.getPhone_number()+", "+t.getBirth_date()+", "+t.getSubject()+", "+t.getDeps();
        try {
            String imageFormat = "png";
            String outputFileName = "e:\\Teacher"+t.getName()+t.getLastname()+"." + imageFormat;
            BitMatrix matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 500, 500);
            MatrixToImageWriter.writeToPath(matrix, imageFormat, Paths.get(outputFileName));
        } catch (WriterException ex) {
            Logger.getLogger(FXMLTeacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
           
        
   
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
}
