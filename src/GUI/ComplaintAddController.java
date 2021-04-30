/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CalendarA.FullCalendarView;
import Entities.Complaint;
import Entities.User;
import Services.ComplaintService;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Sabrina
 */
public class ComplaintAddController implements Initializable {

    User currentUser = LoginController.CurrentUser;

    @FXML
    private TextField object;
    @FXML
    private TextArea description;
    @FXML
    private ComboBox<String> status = new ComboBox();
    ObservableList<String> options = FXCollections.observableArrayList(
            "Urgent",
            "Normal",
            "Low"
    );
    @FXML
    private Button send;
    @FXML
    private AnchorPane add_btn;

    @FXML
    private Button delete;
    @FXML
    private TableView<Complaint> table_v;
    @FXML
    private TableColumn<Complaint, String> descri;
    @FXML
    private TableColumn<Complaint, String> object_c;
    @FXML
    private TableColumn<Complaint, String> status_c;
    @FXML
    private TableColumn<Complaint, Integer> id;
    @FXML
    private Label countComplaint;
    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label UserName;
    @FXML
    private Button btn_Course1;
    @FXML
    private Button stats;
        User current_user = LoginController.CurrentUser;


    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ComplaintService cs = new ComplaintService();
        status.setItems(options);
        showComplaints();
        System.out.println(cs.countComplaints());
        countComplaint.setText(Integer.toString(cs.countComplaints()));
    }

    public void showComplaints() {
        ComplaintService ds = new ComplaintService();
        table_v.setItems((ObservableList<Complaint>) ds.getComplaints());
        object_c.setCellValueFactory(new PropertyValueFactory("object"));
        status_c.setCellValueFactory(new PropertyValueFactory("status"));
        descri.setCellValueFactory(new PropertyValueFactory("description"));
        id.setCellValueFactory(new PropertyValueFactory("id"));
    }

    @FXML
    private void btn_click(ActionEvent event) throws ParseException {
        ComplaintService ss = new ComplaintService();
        Complaint c = new Complaint(object.getText(), description.getText(), status.getValue().toString(), currentUser.getId());
        ss.AddComplaint(c);
        showComplaints();
    }

    @FXML
    private void delete_btn(ActionEvent event) {
        Complaint c = table_v.getSelectionModel().getSelectedItem();
        ComplaintService ds = new ComplaintService();
        c.setId(c.getId());
        System.out.println(c.getId());
        ds.DeleteComplaint(c);
        showComplaints();

    }

    @FXML
    private void modify_btn(ActionEvent event) {
        Complaint c = table_v.getSelectionModel().getSelectedItem();
        ComplaintService ds = new ComplaintService();
        c.setId(c.getId());
        c.setObject(object.getText());
        c.setDescription(description.getText());
        c.setStatus(status.getValue().toString());
        ds.UpdateComplaint(c.getId(), c);
        showComplaints();
    }

    @FXML
    private void HomeAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminHome.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

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
    private void stats(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Stats.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void UsersAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserAddPicker.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
