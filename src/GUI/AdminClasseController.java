/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CalendarA.FullCalendarView;
import TREADER.ReadText;
import Entities.Student;
import Entities.User;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;
import Services.ClasseService;
import java.time.YearMonth;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AdminClasseController implements Initializable {

    /**
     * Initializes the controller class.
     */
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    /**
     * FXML Controller class
     *
     * @author lenovo
     */
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfclasse_size;
    private TextField tfcreated_date;
    @FXML
    private TextField tfid;
    private TextField tflast_update_by;
    @FXML
    private TextField tfid2;
    private TextField tflast_update_date;
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
    private TextField tfmatiere;
    private DatePicker tfdate;
    private TextField tflien;
    private TextField tfduree;
    private TableColumn<seance, String> colmatiere;
    private TableColumn<seance, Date> coldate;
    private TableColumn<seance, Integer> colduree;
    private TableColumn<seance, String> collien;
    private Button btnajouter1;
    private Button btnmodifier1;
    private Button btnsupprimer1;
    private TableView<seance> tvseance;
    private TextField tfid1;
    private TableColumn<seance, Integer> collid5;
    private TableColumn<seance, Integer> colidseance;
    private TextField recherche;
    private TextField rech;
    private Button btn;
    private TableView<Student> tvclasses;
    private TableColumn<Student, String> colnometu;
    private TableColumn<Student, String> colprenometud;
    private TableColumn<Student, String> colclasseetud;
    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label UserName;
    @FXML
    private Button btn_Course1;
    private Button meet;
    private TableColumn<Student, Integer> nbab;
    private TableColumn<Student, String> mailp;
    private ComboBox cobo;
    private Button ok;
    User current_user = LoginController.CurrentUser;

    ObservableList<String> typelist = FXCollections.observableArrayList("Abscent", "Présent");
    private TableColumn<Student, Integer> idetud;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showclasse();

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource() == btnajouter) {
            insertRecord();
        } else if (event.getSource() == btnupdate) {
            updateRecord();
        } else if (event.getSource() == btndelete) {
            deleteButton();
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

    public ObservableList<classe> getclasseList() {
        ObservableList<classe> classeList = FXCollections.observableArrayList();
        Connection conn;
        conn = getConnection();
        String query = "SELECT * FROM classe";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            classe classe;
            while (rs.next()) {
                classe = new classe(rs.getInt("id"), rs.getString("name"), rs.getInt("classe_size"), rs.getInt("created_by"), rs.getDate("created_date"), rs.getInt("last_update_by"), rs.getDate("Last_update_date"), rs.getInt("archived_by"), rs.getDate("archived_date"));
                classeList.add(classe);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return classeList;

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
                c.setCreated_by(current_user.getId());
                c.setCreated_date(rst.getDate("created_date"));
                c.setLast_update_date(rst.getDate("last_update_date"));
                c.setArchived_by(current_user.getId());
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

    private void insertRecord() {
        String query;
        // Random random = new Random();
        // IntStream ints = random.ints(1,1,100);

        query = "INSERT INTO classe (`name`, `classe_size`, `created_by`, `created_date`, `last_update_by`, `last_update_date`, `archived_by`, `archived_date`) VALUES ('" + tfnom.getText() + "','" + tfclasse_size.getText() + "',"+current_user.getId()+",'2021-04-02',' AzizHelmi ','2021-04-02',' AzizHelmi ','2021-04-02')";
        executeQuery(query);
        showclasse();
    }

    private void updateRecord() {
        String query = "UPDATE  classe SET classe_size  = '" + tfclasse_size.getText() + "'  WHERE id = " + tfid2.getText() + "";
        executeQuery(query);
        showclasse();
    }

    private void deleteButton() {
        String query = "DELETE FROM classe WHERE id =" + tfid.getText() + "";
        executeQuery(query);
        showclasse();

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
    private void CalendarAction() throws IOException {
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
        Label l1 = new Label("Décnnecter?");
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

    private void Read(ActionEvent event) {

        ObservableList<Student> StudentList = FXCollections.observableArrayList();
        Connection conn;
        conn = getConnection();
        String query;
        query = "SELECT * FROM user   ";
        Statement st;
        ResultSet rs;
        String text = "";
        String spacing = "                                         ,  ,          , ,                                                               ";
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

    @FXML
    private void UserAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserAddPicker.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
