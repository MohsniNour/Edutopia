package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Entities.Student;
import Entities.seance;
import Entities.classe;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.Date;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class classefxController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfclasse_size;
    @FXML
    private TextField tfcreated_date;
    @FXML
    private TextField tfid;
    private TextField tflast_update_by;
    @FXML
    private TextField tfid2;
    @FXML
    private TextField tflast_update_date;
    @FXML
    private TextField tfarchived_date;
    @FXML
    private TableView<classe> tvclasse;
    @FXML
    private TableColumn<classe, Integer> collid;
    @FXML
    private TableColumn<classe, String> collnom;
    @FXML
    private TableColumn<classe, Integer> collclasse_size;
    @FXML
    private TableColumn<classe, String> collcreated_by;
    @FXML
    private TableColumn<classe, Date> collcreated_date;
    @FXML
    private TableColumn<classe, String> colllast_update_by;
    @FXML
    private TableColumn<classe, Date> colllast_update_date;
    @FXML
    private TableColumn<classe, String> collarchived_by;
    @FXML
    private TableColumn<classe, Date> collarchived_date;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private Label label;
    @FXML
    private TextField tfmatiere;
    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField tflien;
    @FXML
    private TextField tfduree;
    @FXML
    private TableColumn<seance, String> colmatiere;
    @FXML
    private TableColumn<seance, Date> coldate;
    @FXML
    private TableColumn<seance, Integer> colduree;
    @FXML
    private TableColumn<seance, String> collien;
    @FXML
    private Button btnajouter1;
    @FXML
    private Button btnmodifier1;
    @FXML
    private Button btnsupprimer1;
    @FXML
    private TableView<seance> tvseance;
    @FXML
    private TextField tfid1;
    private TableColumn<seance, Integer> collid5;
    @FXML
    private TableColumn<seance, Integer> colidseance;
    private TextField recherche;
    @FXML
    private TextField rech;
    private Button btn;
    @FXML
    private TableView<Student> tvclasses;
    @FXML
    private TableColumn<Student, String> colnometu;
    @FXML
    private TableColumn<Student, String> colprenometud;
    @FXML
    private TableColumn<Student, String> colclasseetud;
    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label UserName;
    @FXML
    private Button btn_Course1;
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
    private VBox vboxdrawer11;
    @FXML
    private ImageView imagechange11;
    @FXML
    private Label UserName11;
    @FXML
    private Button btn_Course111;
    @FXML
    private TableColumn<Student, Spinner> coletat;
    @FXML
    private TableColumn<Student, Integer> colnbp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showclasse();
        showseance();
        showStudent();

        // TODO
    }

    public void sample_add() throws IOException {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        primaryStage.setTitle("jdid");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource() == btnajouter) {
            insertRecord();
        } else if (event.getSource() == btnupdate) {
            updateRecord();
        } else if (event.getSource() == btndelete) {
            deleteButton();
        } else if (event.getSource() == btnajouter1) {
            insertseance();
        } else if (event.getSource() == btn) {
            showStudent();
        } else if (event.getSource() == meet) {
            start();
        } else if (event.getSource() == btnmodifier1) {
            updateseance();
        } else if (event.getSource() == btnsupprimer1) {
            deleteseance();
        }

    }

    @FXML
    public void handleMouseAction(MouseEvent event) {
        classe classe = tvclasse.getSelectionModel().getSelectedItem();
        tfnom.setText("" + classe.getName());
        tfclasse_size.setText("" + classe.getClasse_size());
        tfcreated_date.setText("" + classe.getCreated_date());
        tflast_update_date.setText("" + classe.getLast_update_date());
        tfarchived_date.setText("" + classe.getArchived_date());
        tfid.setText("" + classe.getId());
    }

    @FXML
    public void start() {
        Stage primaryStage = new Stage();
        WebView browser = new WebView();

        WebEngine webEngine = browser.getEngine();
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");

        webEngine.load("https://meet.google.com");

        StackPane root = new StackPane();
        root.getChildren().add(browser);

        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

    }

    @FXML
    public void start2() {
        Stage primaryStage = new Stage();
        WebView browser = new WebView();

        WebEngine webEngine = browser.getEngine();
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");

        webEngine.load("https://calendar.google.com/calendar");

        StackPane root = new StackPane();
        root.getChildren().add(browser);

        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

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

    public void showclasse() {
        ObservableList<classe> list = getclasseList();
        collid.setCellValueFactory(new PropertyValueFactory<classe, Integer>("id"));
        collnom.setCellValueFactory(new PropertyValueFactory<classe, String>("name"));
        collclasse_size.setCellValueFactory(new PropertyValueFactory<classe, Integer>("classe_size"));
        collcreated_by.setCellValueFactory(new PropertyValueFactory<classe, String>("created_by"));
        collcreated_date.setCellValueFactory(new PropertyValueFactory<classe, Date>("created_date"));
        colllast_update_by.setCellValueFactory(new PropertyValueFactory<classe, String>("last_update_by"));
        colllast_update_date.setCellValueFactory(new PropertyValueFactory<classe, Date>("last_update_date"));
        collarchived_by.setCellValueFactory(new PropertyValueFactory<classe, String>("archived_by"));
        collarchived_date.setCellValueFactory(new PropertyValueFactory<classe, Date>("archived_date"));
        tvclasse.setItems(list);
    }

    public void showseance() {
        ObservableList<seance> list = getseanceList();

        colduree.setCellValueFactory(new PropertyValueFactory<seance, Integer>("duree"));
        colmatiere.setCellValueFactory(new PropertyValueFactory<seance, String>("matiere"));
        collien.setCellValueFactory(new PropertyValueFactory<seance, String>("url"));
        coldate.setCellValueFactory(new PropertyValueFactory<seance, Date>("date"));
        colidseance.setCellValueFactory(new PropertyValueFactory<seance, Integer>("id"));

        tvseance.setItems(list);
    }

    public ObservableList<classe> getclasseList() {
        ObservableList<classe> classeList = FXCollections.observableArrayList();
        Connection conn;
        conn = getConnection();
        String query = "SELECT * FROM classe  ";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            classe classe;
            while (rs.next()) {
                classe = new classe(rs.getInt("id"), rs.getString("name"), rs.getInt("classe_size"), rs.getInt("created_by"), rs.getDate("created_date"), rs.getInt("last_update_by"), rs.getDate("last_update_date"), rs.getInt("archived_by"), rs.getDate("archived_date"));
                classeList.add(classe);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return classeList;

    }

    public classe getClasseParID(int id) {
        classe c = new classe();
        try {
            Connection conn;
            conn = getConnection();
            String query = "select * from classe where id=" + id + "";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                c = new classe(rs.getInt("id"), rs.getString("name"), rs.getInt("classe_size"), rs.getInt("created_by"), rs.getDate("created_date"), rs.getInt("last_update_by"), rs.getDate("last_update_date"), rs.getInt("archived_by"), rs.getDate("archived_date"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(classefxController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;

    }

    public ObservableList<seance> getseanceList() {
        ObservableList<seance> seanceList = FXCollections.observableArrayList();
        Connection conn;
        conn = getConnection();
        String query = "SELECT * FROM seance";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            seance seance;
            while (rs.next()) {
                seance = new seance(rs.getInt("duree"), rs.getDate("date"), rs.getString("url"), rs.getString("matiere"), rs.getInt("id"));
                seanceList.add(seance);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return seanceList;

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
        query = "SELECT * FROM Students    ";

        Statement st;

        ResultSet rs;
        try {
            st = conn.createStatement();
            // st.setString(1, p.getDescription());
            rs = st.executeQuery(query);
            Student Student;
            while (rs.next()) {
                // Student = new Student(rs.getString("nom"),rs.getString("prenom"),rs.getString("classe"),rs.getInt("nbp"));
                //StudentList.add(Student);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return StudentList;

    }

    public void showStudent() {
        ObservableList<Student> list = getStudentList();
        colnometu.setCellValueFactory(new PropertyValueFactory<Student, String>("nom"));
        colprenometud.setCellValueFactory(new PropertyValueFactory<Student, String>("prenom"));
        colclasseetud.setCellValueFactory(new PropertyValueFactory<Student, String>("classe"));
        coletat.setCellValueFactory(new PropertyValueFactory<Student, Spinner>("etat"));

        colnbp.setCellValueFactory(new PropertyValueFactory<Student, Integer>("nbp"));

        tvclasses.setItems(list);
    }

    public ObservableList<classe> search(String input) {
        ObservableList<classe> classes = FXCollections.observableArrayList();
        Connection conn;
        conn = getConnection();
        try {

            Statement stm;

            stm = conn.createStatement();

            String query;
            query = "SELECT * from classe where nom like '%" + input + "%'";
            ResultSet rst = stm.executeQuery(query);
            classe form;
            while (rst.next()) {
                classe c = new classe();
                c.setId(rst.getInt("id"));
                c.setName(rst.getString("nom"));
                c.setClasse_size(rst.getInt("classe_size"));
                c.setCreated_by(rst.getInt("created_by"));
                c.setCreated_date(rst.getDate("created_date"));
                c.setLast_update_by(rst.getInt("last_update_by"));
                c.setLast_update_date(rst.getDate("last_update_date"));
                c.setArchived_by(rst.getInt("archived_by"));
                c.setArchived_date(rst.getDate("archived_date"));
                form = new classe(rst.getInt("id"), rst.getString("nom"), rst.getInt("classe_size"), rst.getInt("created_by"), rst.getDate("created_date"), rst.getInt("last_update_by"), rst.getDate("last_update_date"), rst.getInt("archived_by"), rst.getDate("archived_date"));
                classes.add(c);

            }

        } catch (SQLException ex) {
            {
                ex.printStackTrace();
            }
        }

        return classes;
    }

    @FXML
    public void searchkey(KeyEvent event) {

        ObservableList<classe> classes = search(rech.getText());
        collid.setCellValueFactory(new PropertyValueFactory<classe, Integer>("id"));
        collnom.setCellValueFactory(new PropertyValueFactory<classe, String>("name"));
        collclasse_size.setCellValueFactory(new PropertyValueFactory<classe, Integer>("classe_size"));
        collcreated_by.setCellValueFactory(new PropertyValueFactory<classe, String>("created_by"));
        collcreated_date.setCellValueFactory(new PropertyValueFactory<classe, Date>("created_date"));
        colllast_update_by.setCellValueFactory(new PropertyValueFactory<classe, String>("last_update_by"));
        colllast_update_date.setCellValueFactory(new PropertyValueFactory<classe, Date>("last_update_date"));
        collarchived_by.setCellValueFactory(new PropertyValueFactory<classe, String>("archived_by"));
        collarchived_date.setCellValueFactory(new PropertyValueFactory<classe, Date>("archived_date"));
        tvclasse.setItems(classes);

    }

    public ObservableList<Student> search1(String input) {
        ObservableList<Student> etudians = FXCollections.observableArrayList();
        Connection conn;
        conn = getConnection();
        try {

            Statement stm;

            stm = conn.createStatement();

            String query;
            query = "SELECT * FROM `Students` where classe like '%" + input + "%'";
            ResultSet rst = stm.executeQuery(query);
            Student form;
            while (rst.next()) {
//            Student c =new Student();
//            c.setNom(rst.getString("nom"));
//            c.setPrenom(rst.getString("prenom"));
//            c.setClasse(rst.getString("classe"));
//             c.setNbp(rst.getInt("nbp"));
//            
//            form= new Student(rst.getString("nom"),rst.getString("prenom"),rst.getString("classe"),rst.getInt("nbp"));
//            etudians.add(c); 

            }

        } catch (SQLException ex) {
            {
                ex.printStackTrace();
            }
        }

        return etudians;
    }

    @FXML
    public void searchkey1(KeyEvent event) {

        ObservableList<Student> Students = search1(tfnom.getText());
        colnometu.setCellValueFactory(new PropertyValueFactory<Student, String>("nom"));
        colprenometud.setCellValueFactory(new PropertyValueFactory<Student, String>("prenom"));
        colclasseetud.setCellValueFactory(new PropertyValueFactory<Student, String>("classe"));
        coletat.setCellValueFactory(new PropertyValueFactory<Student, Spinner>("etat"));

        tvclasses.setItems(Students);

    }

    private void insertRecord() {
        String query;
        // Random random = new Random();
        // IntStream ints = random.ints(1,1,100);

        query = "INSERT INTO classe (`nom`, `classe_size`, `created_by`, `created_date`, `last_update_by`, `last_update_date`, `archived_by`, `archived_date`) VALUES ('" + tfnom.getText() + "','" + tfclasse_size.getText() + "','mounir ','"
                + tfcreated_date.getText() + "',' AzizHelmi ','" + tflast_update_date.getText() + "',' AzizHelmi ','" + tfarchived_date.getText() + "')";
        executeQuery(query);
        showclasse();
    }

    private void insertseance() {
        String query;
        // Random random = new Random();
        // IntStream ints = random.ints(1,1,100);

        query = "INSERT INTO `seance`(`duree`, `date`, `url`, `matiere`) VALUES ('" + tfduree.getText() + "','" + tfdate.getValue() + "','" + tflien.getText() + "','" + tfmatiere.getText() + "')";
        executeQuery(query);
        showseance();
    }

    private void updateRecord() {
        String query = "UPDATE  classe SET classe_size  = '" + tfclasse_size.getText() + "'  WHERE id = " + tfid2.getText() + "";
        executeQuery(query);
        showclasse();
    }

    private void updateseance() {
        String query = "UPDATE  seance SET url  = '" + tflien.getText() + "'  WHERE id = " + tfid1.getText() + "";
        executeQuery(query);
        showseance();
    }

    private void deleteButton() {
        String query = "DELETE FROM classe WHERE id =" + tfid.getText() + "";
        executeQuery(query);
        showclasse();
    }

    private void deleteseance() {
        String query = "DELETE FROM seance WHERE id =" + tfid1.getText() + "";
        executeQuery(query);
        showseance();
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
    private void ExamAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Front_ChargerExamen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void CalendarAction() {

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

    private void start(MouseEvent event) {
        Stage primaryStage = null;
        WebView browser = new WebView();

        WebEngine webEngine = browser.getEngine();
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");

        webEngine.load("https://calendar.google.com");

        StackPane root = new StackPane();
        root.getChildren().add(browser);

        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    @FXML
    private void newP(ActionEvent event) {
    }

}
