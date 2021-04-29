/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CalendarA.FullCalendarView;
import Entities.User;
import Entities.seance;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class EnseignantClasseController implements Initializable {

    private TextField tflast_update_by;
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
    private Button btn;
    @FXML
    private VBox vboxdrawer1;
    @FXML
    private ImageView imagechange1;
    @FXML
    private Label UserName1;
    @FXML
    private Button meet;

    ObservableList<String> typelist = FXCollections.observableArrayList("Abscent", "Présent");
    @FXML
    private Button btnajouter11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showseance();

        // TODO
    }

    public void sample_add() throws IOException {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        primaryStage.setTitle("New");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnajouter1) {
            insertseance();
        } else if (event.getSource() == meet) {
            start();
        } else if (event.getSource() == btnmodifier1) {
            updateseance();
        } else if (event.getSource() == btnsupprimer1) {
            deleteseance();
        }

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

    public void showseance() {
        ObservableList<seance> list = getseanceList();

        colduree.setCellValueFactory(new PropertyValueFactory<seance, Integer>("duree"));
        colmatiere.setCellValueFactory(new PropertyValueFactory<seance, String>("matiere"));
        collien.setCellValueFactory(new PropertyValueFactory<seance, String>("url"));
        coldate.setCellValueFactory(new PropertyValueFactory<seance, Date>("date"));
        colidseance.setCellValueFactory(new PropertyValueFactory<seance, Integer>("id"));

        tvseance.setItems(list);
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
    private void insertseance() {
        String query;
        // Random random = new Random();
        // IntStream ints = random.ints(1,1,100);

        query = "INSERT INTO `seance`(`duree`, `date`, `url`, `matiere`) VALUES ('" + tfduree.getText() + "','" + tfdate.getValue() + "','" + tflien.getText() + "','" + tfmatiere.getText() + "')";
        executeQuery(query);
        showseance();
    }

    private void updateseance() {
        String query = "UPDATE  seance SET url  = '" + tflien.getText() + "'  WHERE id = " + tfid1.getText() + "";
        executeQuery(query);
        showseance();
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
    private void HomeAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TeacherHome.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void ClassAction(ActionEvent event) {
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
    private void etud(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Etudiantclasse.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
