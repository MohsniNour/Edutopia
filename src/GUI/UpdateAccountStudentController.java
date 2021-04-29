/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CalendarA.FullCalendarView;
import Entities.Student;
import Entities.User;
import Services.StudentService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.YearMonth;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class UpdateAccountStudentController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label UserName;
    @FXML
    private Button btn_Course1;
    @FXML
    private TextField name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField cin;
    @FXML
    private DatePicker birth_date;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button add_button;
    @FXML
    private TextField old_pw;
    @FXML
    private PasswordField confirm_pw;

    Student current_user = LoginController.CurrentStudent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        name.setText(current_user.getName());
        last_name.setText(current_user.getLast_name());
        cin.setText(String.valueOf(current_user.getCin()));
        phone.setText(String.valueOf(current_user.getPhone_number()));
        email.setText(current_user.getEmail());
        Date ld = (Date) current_user.getBirth_date();
        birth_date.setValue(ld.toLocalDate());

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
        Parent root = FXMLLoader.load(getClass().getResource("List_CoStudying_Front.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

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
    private void add_button_action(ActionEvent event) {
        StudentService ss = new StudentService();

        if (current_user.getPassword().equals(old_pw.getText())) {
            if (mdp.getText() != null && !mdp.getText().trim().isEmpty() && confirm_pw.getText() != null && !confirm_pw.getText().trim().isEmpty() && mdp.getText().equals(confirm_pw.getText())) {
                ss.editStudent(current_user.getId(), "name", name.getText());
                ss.editStudent(current_user.getId(), "last_name", last_name.getText());
                ss.editStudent(current_user.getId(), "email", email.getText());
                ss.editStudent(current_user.getId(), "password", mdp.getText());
                ss.editStudent(current_user.getId(), "cin", cin.getText());
                ss.editStudent(current_user.getId(), "phone_number", phone.getText());
                ss.editStudent(current_user.getId(), "birth_date", Date.valueOf(birth_date.getValue()));
                notificationShow("Mot de passe", "Mot de passe modifier avec succées");
            } else if (!mdp.getText().equals(confirm_pw.getText())) {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.WARNING);
                a.setContentText("Assurez-vous de confirmer correctement le mot de passe");
                a.show();
            } else if (mdp.getText() == null && mdp.getText().trim().isEmpty() && confirm_pw.getText() == null && confirm_pw.getText().trim().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.WARNING);
                a.setContentText("Remplir les champs du mot de passe");
                a.show();
            }
            ss.editStudent(current_user.getId(), "name", name.getText());
            ss.editStudent(current_user.getId(), "last_name", last_name.getText());
            ss.editStudent(current_user.getId(), "email", email.getText());
            ss.editStudent(current_user.getId(), "cin", cin.getText());
            ss.editStudent(current_user.getId(), "phone_number", phone.getText());
            ss.editStudent(current_user.getId(), "birth_date", Date.valueOf(birth_date.getValue()));

        } else {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("Mot de passe est incorrect ");
            a.show();
        }
    }
    
    public void notificationShow(String title, String message) {
        Notifications notificationBuilder = Notifications.create()
                .title(title).text(message).graphic(null).hideAfter(javafx.util.Duration.seconds(7))
                .position(Pos.BOTTOM_LEFT)
                .onAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        System.out.println("clicked ON ");
                    }
                });
        notificationBuilder.darkStyle();
        notificationBuilder.show();
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
