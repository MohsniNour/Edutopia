/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CalendarA.FullCalendarView;
import Entities.Student;
import Entities.User;
import Entities.classe;
import Services.ClasseService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import Services.StudentService;
import java.io.IOException;
import java.sql.Date;
import java.time.YearMonth;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import nl.captcha.Captcha;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class Add_Student_FXMLController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField cin;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private DatePicker birth_date;
    @FXML
    private TextField mdp;
    @FXML
    private Button add_button;
    @FXML
    private Button update_btn;
    @FXML
    private ImageView cap;
    @FXML
    private TextField code;
    @FXML
    private Button reset;
    @FXML
    private ComboBox<String> classe = new ComboBox();
    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label UserName;
    @FXML
    private Button btn_Course1;
    private Label mainmenu_txt;
    @FXML
    private ImageView mainmenu_icon;

    User current_user = LoginController.CurrentUser;

    public Captcha setCaptcha() {
        Captcha captcha = new Captcha.Builder(250, 200)
                .addText()
                .addBackground()
                .addNoise()
                .gimp()
                .addBorder()
                .build();
        System.out.println(captcha.getImage());
        Image image = SwingFXUtils.toFXImage(captcha.getImage(), null);
        cap.setImage(image);
        return captcha;
    }
    Captcha captcha;

    List<classe> myLst;
    ClasseService CC = new ClasseService();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//      classe.setItems(option);
        myLst = CC.consulterClasse();
        for (int i = 0; i < myLst.size(); i++) {
            classe.getItems().add(myLst.get(i).getName());
        }
        captcha = setCaptcha();

    }

    @FXML
    private void add_button_action(ActionEvent event) {

        if (captcha.isCorrect(code.getText())) {

            try {
                StudentService ss = new StudentService();
                Student p;
                classe c = new classe();
                ClasseService cs = new ClasseService();
                c = cs.getClasseId(classe.getValue().toString());
                if ((name.getText() == null || name.getText().trim().isEmpty())) {
                    Alert a = new Alert(AlertType.NONE);
                    a.setAlertType(AlertType.WARNING);
                    a.setContentText("Remplissez le champ du prénom s'il vous plaît ");
                    a.show();
                } else if ((last_name.getText() == null || last_name.getText().trim().isEmpty())) {
                    Alert a = new Alert(AlertType.NONE);
                    a.setAlertType(AlertType.WARNING);
                    a.setContentText("Remplissez le champ du nom s'il vous plaît ");
                    a.show();
                } else if ((email.getText() == null || email.getText().trim().isEmpty())) {
                    Alert a = new Alert(AlertType.NONE);
                    a.setAlertType(AlertType.WARNING);
                    a.setContentText("Remplissez le champ de l'e-mail s'il vous plaît ");
                    a.show();
                } else if ((mdp.getText() == null || mdp.getText().trim().isEmpty())) {
                    Alert a = new Alert(AlertType.NONE);
                    a.setAlertType(AlertType.WARNING);
                    a.setContentText("Remplissez le champ du mot de passe s'il vous plaît ");
                    a.show();
                } else {
                    String tilte = "Captcha";
                    String message = "Correct";
                    TrayNotification tray = new TrayNotification();
                    AnimationType type = AnimationType.POPUP;
                    tray.setAnimationType(type);
                    tray.setTitle(tilte);
                    tray.setMessage(message);
                    tray.setNotificationType(NotificationType.SUCCESS);
                    tray.showAndDismiss(Duration.millis(3000));

                    p = new Student("Student", name.getText(), last_name.getText(), Integer.parseInt(cin.getText()), email.getText(), Integer.parseInt(phone.getText()), Date.valueOf(birth_date.getValue()), c);
                    ss.addStudent(p);
                    name.setText("");
                    last_name.setText("");
                    cin.setText("");
                    email.setText("");
                    phone.setText("");
                    mdp.setText("");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else {
            String tilte = "Captcha";
            String message = "Incorrect Captcha";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));

            captcha = setCaptcha();
            code.setText("");
        }

    }

    @FXML
    private void on_update_btn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("display_Student_FXML.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void reseting(ActionEvent event) {
        captcha = setCaptcha();
        code.setText("");
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
    private void mm_hover(MouseEvent event) {
        mainmenu_icon.setCursor(Cursor.HAND);
    }

    @FXML
    private void main_menu_clicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserAddPicker.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
