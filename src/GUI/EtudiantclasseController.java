/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CalendarA.FullCalendarView;
import Entities.classe;
import Entities.Student;
import Entities.User;
import Entities.seance;
import TREADER.ReadText;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class EtudiantclasseController implements Initializable {

    private TextField tfnom;
    private TextField tflast_update_by;
    private TableColumn<seance, Integer> collid5;
    private TextField recherche;
    private Button btn;
    @FXML
    private TableView<Student> tvclasses;
    @FXML
    private TableColumn<Student, String> colnometu;
    @FXML
    private TableColumn<Student, String> colprenometud;
    private TableColumn<Student, String> colclasseetud;
    @FXML
    private VBox vboxdrawer11;
    @FXML
    private ImageView imagechange11;
    @FXML
    private Label UserName11;
    @FXML
    private TableColumn<Student, Integer> nbab;
    @FXML
    private TableColumn<Student, String> mailp;
    @FXML
    private ComboBox cobo;
    @FXML
    private Button ok;

    ObservableList<String> typelist = FXCollections.observableArrayList("Abscent", "Présent");
    @FXML
    private TableColumn<Student, Integer> idetud;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showStudent();
        cobo.setValue("Présence");
        cobo.setItems(typelist);

        // TODO
    }

    private void handleButtonAction(ActionEvent event) {

        if (event.getSource() == btn) {
            showStudent();
        }

    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/edutopia", "root", "");
            return conn;
        } catch (Exception ex) {
            System.out.println("error" + ex.getMessage());

        }
        return null;

    }

    /* public void newP() {
    ObservableList<Student> list =getStudentList();
    int j=0;
       for (int i = 0; i <tvclasses.getItems().size(); i++) {
              if (tvclasses.getItems().get(i).getEtat().isSelected())
              {
                  
           
       j= tvclasses.getItems().get(i).getNbp() + 1 ;
         
              }
            
           
            }
           tvclasses.setItems(list);
             showStudent();
         
             
       
        
    
      




}
     */
    public ObservableList<Student> getStudentList() {
        ObservableList<Student> StudentList = FXCollections.observableArrayList();
        Connection conn;
        conn = getConnection();
        String query;
        query = "SELECT * FROM etudiants";

        Statement st;

        ResultSet rs;

        try {
            st = conn.createStatement();
            // st.setString(1, p.getDescription());
            rs = st.executeQuery(query);
            Student Student;

            while (rs.next()) {
                Student s = new Student(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("classe"), rs.getInt("nbabsece"), rs.getString("mail_parent"));
                StudentList.add(s);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return StudentList;

    }

    public void showStudent() {
        ObservableList<Student> list = getStudentList();
        idetud.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        colnometu.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        colprenometud.setCellValueFactory(new PropertyValueFactory<Student, String>("last_name"));
//        colclasseetud.setCellValueFactory(new PropertyValueFactory<Student, String>("classe"));
        nbab.setCellValueFactory(new PropertyValueFactory<Student, Integer>("nbabsece"));
        mailp.setCellValueFactory(new PropertyValueFactory<Student, String>("mail_parent"));

        tvclasses.setItems(list);
    }

    public ObservableList<Student> search1(String input) {
        ObservableList<Student> etudians = FXCollections.observableArrayList();
        Connection conn;
        conn = getConnection();
        try {

            Statement stm;

            stm = conn.createStatement();

            String query;
            query = "SELECT * FROM `etudiants` where classe like '%" + input + "%'";
            ResultSet rst = stm.executeQuery(query);
            Student form;
            while (rst.next()) {
                Student c = new Student();
                c.setId(rst.getInt("id"));
                c.setName(rst.getString("nom"));
                c.setLast_name(rst.getString("last_name"));

                String classe_name = rst.getString("classe");
                classe cs = new classe(classe_name);
                c.setClasse(cs);
                c.setClasse_name(classe_name);

                c.setNbabsece(rst.getInt("nbasbsece"));
                c.setMail_parent(rst.getString("mail_parent"));

                etudians.add(c);

            }

        } catch (SQLException ex) {
            {
                ex.printStackTrace();
            }
        }

        return etudians;
    }

    public void searchkey1(KeyEvent event) {

        ObservableList<Student> Students = search1(tfnom.getText());
        colnometu.setCellValueFactory(new PropertyValueFactory<Student, String>("nom"));
        colprenometud.setCellValueFactory(new PropertyValueFactory<Student, String>("prenom"));
        colclasseetud.setCellValueFactory(new PropertyValueFactory<Student, String>("classe"));
        nbab.setCellValueFactory(new PropertyValueFactory<Student, Integer>("nbasbsece"));

        tvclasses.setItems(Students);

    }

    private void executeQuery(String query) {

        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
        }

    }

    @FXML
    private void HomeAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TeacherHome.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void ClassAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EnseignantClasse.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void CourseAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLSubjectForTeacher.fxml"));
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
    private void AccountAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLTeacherModify.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void ClaimAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ComplaintFront.fxml"));
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
    private void handleMouseAction2(MouseEvent event) {
        Student etud = tvclasses.getSelectionModel().getSelectedItem();
        ok.setOnMouseClicked((MouseEvent eve) -> {
            if (cobo.getSelectionModel().getSelectedItem().toString().equals("Abscent")) {
                Integer x = etud.getNbabsece();
                String m = etud.getMail_parent();
                if (x >= 8) {
                    String mess = m;
                    try {
                        sendmail.sendmail(mess);
                    } catch (Exception ex) {
                        Logger.getLogger(classefxController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                Integer i = etud.getId();
                x += 1;
                String query = "UPDATE  etudiants SET nbabsece  = '" + x + "'  WHERE id = " + i + "";
                executeQuery(query);
                showStudent();
                tvclasses.refresh();
            }
        });
    }

    @FXML
    private void Read(ActionEvent event) {

        ObservableList<Student> StudentList = FXCollections.observableArrayList();
        Connection conn;
        conn = getConnection();
        String query;
        query = "SELECT * FROM etudiants";
        Statement st;
        ResultSet rs;
        String text = "";
        String spacing = "                                    ,  ,          , ,                                                               ";
        try {
            st = conn.createStatement();
            // st.setString(1, p.getDescription());
            rs = st.executeQuery(query);
            while (rs.next()) {
                text = rs.getString("nom") + " " + rs.getString("prenom") + spacing;
                ReadText.readOutLoud(text);
            }
            System.out.println(text);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }



}
