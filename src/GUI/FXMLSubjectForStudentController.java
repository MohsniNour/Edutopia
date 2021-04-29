/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CalendarA.FullCalendarView;
import Services.SubjectService;
import Services.TeacherService;
import Entities.*;
import Entities.Teacher;
import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Mrad
 */
public class FXMLSubjectForStudentController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label UserName;
    @FXML
    private Button btn_Course1;
    @FXML
    private TextField tfrech;
    @FXML
    private Button btnrech;
    @FXML
    private Button btnconsultercours;
    @FXML
    private TableView<Subjectt> tvid;
    @FXML
    private TableColumn<Subjectt, String> tvname;
    @FXML
    private TableColumn<Subjectt, String> tvenseignant;
    private Button btn_Exam;
    @FXML
    private Button btn_Aganda;
    @FXML
    private Button btn_Course11;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        showSubjects();
    }

    public void showSubjects() {
        SubjectService ss = new SubjectService();
        ObservableList<Subjectt> ol = ss.getSubjects();
        TeacherService ts = new TeacherService();
        Teacher t = new Teacher();
        for (Subjectt s : ol) {

            t = ts.getTeacherParSonId(s.getId_teacher());
            s.setNomTeacher(t.getName() + " " + t.getLastname());
        }
        tvid.setItems(ol);
        tvname.setCellValueFactory(new PropertyValueFactory("id_Subject"));
// bech nakhou el nom de classe bel id ta classe w bech n7oto f table view 
        tvenseignant.setCellValueFactory(new PropertyValueFactory("nomTeacher"));
    }

   @FXML
    private void HomeAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("StudentHome.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void CourseAction(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("FXMLSubjectForStudent.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


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
        Parent root = FXMLLoader.load(getClass().getResource("List_CoStudying_Front.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void AccountAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UpdateAccountStudent.fxml"));
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
    @FXML
    private void recherche(ActionEvent event) {
        SubjectService ss = new SubjectService();
        ObservableList<Subjectt> s = ss.recherche(tfrech.getText());
        int i = -1;
        int row = -1;
        if (s == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Aucun Résultat Trouvé", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        } else {
            for (Subjectt sub : s) {
                TeacherService ts = new TeacherService();
                Teacher t = ts.getTeacherParSonId(sub.getId_teacher());
                classefxController cs = new classefxController();
                classe c = cs.getClasseParID(sub.getId_class());
                System.out.println(sub.getId_class());
                sub.setNomTeacher(t.getName() + " " + t.getLastname());
                sub.setNomClasse(c.getName());
            }
            tvid.setItems(s);
            tvname.setCellValueFactory(new PropertyValueFactory("id_Subject"));
// bech nakhou el nom de classe bel id ta classe w bech n7oto f table view 
            tvenseignant.setCellValueFactory(new PropertyValueFactory("nomTeacher"));

        }
    }

    @FXML
    private void deselect(KeyEvent event) {
    }

    @FXML
    private void select(MouseEvent event) {
    }

    @FXML
    private void handleconsultercours(ActionEvent event) throws IOException {
        Subjectt c = tvid.getSelectionModel().getSelectedItem();

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Front_ConsulterCours.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Liste des examen");
        Front_ConsulterCoursController controller = loader.getController();
        controller.initData(c);
        stage.show();

    }

    @FXML
    private void chargerCalendarHandle(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FullCalendar.fxml"));
        Parent root = loader.load();

        FullCalendarController controller = loader.getController();
        VBox f = new FullCalendarView(YearMonth.now()).getView();
        controller.calendarPane.getChildren().add(f);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btn_Exam) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front_ChargerExamen.fxml")));
            stage.setScene(scene);
            stage.show();

        }

    }

    @FXML
    private void ExamAction(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("Front_ChargerExamen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
