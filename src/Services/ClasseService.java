/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Student;
import Entities.classe;
import Entities.seance;
import Utils.DataBaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Mrad
 */
public class ClasseService {
    Connection cnx;

    public ClasseService() {
        cnx = DataBaseConnection.getInstance().getConnection();
    }

    public classe getClasseId(String classe_name) {

        classe c = new classe();
        try {

            Statement pst = cnx.createStatement();
            ResultSet rs = pst.executeQuery("SELECT * FROM classe WHERE name ='" + classe_name + "'");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int size = rs.getInt("classe_size");
                c.setId(id);
                c.setName(name);
                c.setClasse_size(size);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return c;

    }

    public List<classe> consulterClasse() {
        List<classe> myList = new ArrayList<>();
        try {

            Statement pst = cnx.createStatement();

            ResultSet rs = pst.executeQuery("SELECT * FROM classe ORDER BY name asc");
            while (rs.next()) {
                classe C = new classe();
                C.setId(rs.getInt("id"));
                C.setName(rs.getString("name"));
                myList.add(C);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }

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
    private TextField tfdate;
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
    @FXML
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
    private TableColumn<Student, Integer> nbab;
    @FXML
    private TableColumn<Student, String> mailp;

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
                classe = new classe(rs.getInt("id"), rs.getString("nom"), rs.getInt("classe_size"), rs.getInt("created_by"), rs.getDate("created_date"), rs.getInt("last_update_by"), rs.getDate("Last_update_date"), rs.getInt("archived_by"), rs.getDate("archived_date"));
                classeList.add(classe);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return classeList;

    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            return conn;
        } catch (Exception ex) {
            System.out.println("error" + ex.getMessage());

        }
        return null;

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

    public ObservableList<Student> getetudiantList() {
        ObservableList<Student> StudentList = FXCollections.observableArrayList();
        Connection conn;
        conn = getConnection();
        String query;
        query = "SELECT * FROM user";

        Statement st;

        ResultSet rs;
        try {
            st = conn.createStatement();
            // st.setString(1, p.getDescription());
            rs = st.executeQuery(query);
            Student Student;

            while (rs.next()) {
                String classe_name = rs.getString("classe");
                classe c = new classe(classe_name);
                Student = new Student(rs.getInt("id"), rs.getString("name"), rs.getString("last_name"), rs.getInt("nbasbsece"), rs.getString("mail_parent"), c);
                StudentList.add(Student);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return StudentList;

    }

    public void showetudiant() {
        ObservableList<Student> list = getetudiantList();
        colnometu.setCellValueFactory(new PropertyValueFactory<Student, String>("nom"));
        colprenometud.setCellValueFactory(new PropertyValueFactory<Student, String>("prenom"));
        colclasseetud.setCellValueFactory(new PropertyValueFactory<Student, String>("classe"));
        nbab.setCellValueFactory(new PropertyValueFactory<Student, Integer>("nbabsece"));
        mailp.setCellValueFactory(new PropertyValueFactory<Student, String>("mail_parent"));

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
            query = "SELECT * FROM `user` where classe like '%" + input + "%'";
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
                c.setNbabsece(rst.getInt("nbabsece"));
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

    @FXML
    public void searchkey1(KeyEvent event) {

        ObservableList<Student> Students = search1(tfnom.getText());
        colnometu.setCellValueFactory(new PropertyValueFactory<Student, String>("nom"));
        colprenometud.setCellValueFactory(new PropertyValueFactory<Student, String>("prenom"));
        colclasseetud.setCellValueFactory(new PropertyValueFactory<Student, String>("classe"));

        tvclasses.setItems(Students);

    }

    public void insertRecord() {
        String query;
        // Random random = new Random();
        // IntStream ints = random.ints(1,1,100);

        query = "INSERT INTO classe (`nom`, `classe_size`, `created_by`, `created_date`, `last_update_by`, `last_update_date`, `archived_by`, `archived_date`) VALUES ('" + tfnom.getText() + "','" + tfclasse_size.getText() + "','mounir ','"
                + tfcreated_date.getText() + "',' AzizHelmi ','" + tflast_update_date.getText() + "',' AzizHelmi ','" + tfarchived_date.getText() + "')";
        executeQuery(query);
        showclasse();
    }

    public void insertseance() {
        String query;
        // Random random = new Random();
        // IntStream ints = random.ints(1,1,100);

        query = "INSERT INTO `seance`(`duree`, `date`, `url`, `matiere`) VALUES ('" + tfduree.getText() + "','" + tfdate.getText() + "','" + tflien.getText() + "','" + tfmatiere.getText() + "')";
        executeQuery(query);
        showseance();
    }

    public void updateRecord() {
        String query = "UPDATE  classe SET classe_size  = '" + tfclasse_size.getText() + "'  WHERE id = " + tfid2.getText() + "";
        executeQuery(query);
        showclasse();
    }

    public void updateseance() {
        String query = "UPDATE  seance SET url  = '" + tflien.getText() + "'  WHERE id = " + tfid1.getText() + "";
        executeQuery(query);
        showseance();
    }

    public void deleteButton() {
        String query = "DELETE FROM classe WHERE id =" + tfid.getText() + "";
        executeQuery(query);
        showclasse();
    }

    public void deleteseance() {
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

    
}
