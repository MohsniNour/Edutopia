/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import CalendarA.FullCalendarView;
import Entities.Co_Studying;
import Entities.User;
import Services.Co_StudyingService;
import Utils.Helpers;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class CoStudyingAddController implements Initializable {

    @FXML

    private ComboBox<String> type = new ComboBox();
    ObservableList<String> options = FXCollections.observableArrayList(
            "Summary",
            "Internship offer",
            "Opportunity",
            "Freelance"
    );
    @FXML
    private TextArea description;
    @FXML
    private Button import_button;
    @FXML
    private Button add_btn;
    @FXML
    private ComboBox<String> level = new ComboBox();
    ObservableList<String> option = FXCollections.observableArrayList(
            "Tout",
            "1er année",
            "2ème année A",
            "2ème année B",
            "3ème année A",
            "3ème année B",
            "4ème",
            "5ème"
    );

    public static String s;
    @FXML
    private TableView<Co_Studying> tableview;
    @FXML
    private TableColumn<Co_Studying, String> type_col;
    @FXML
    private TableColumn<Co_Studying, String> level_col;
    @FXML
    private TableColumn<Co_Studying, String> desc_col;
    @FXML
    private TableColumn<Co_Studying, String> rating_col;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label UserName;
    @FXML
    private Button btn_Course1;
    @FXML
    private ImageView mainmenu_icon;

    User current_user = LoginController.CurrentUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showCostudying();
        type.setItems(options);
        level.setItems(option);
    }

    public void showCostudying() {
        Co_StudyingService ds = new Co_StudyingService();
        tableview.setItems((ObservableList<Co_Studying>) ds.getCostudyings());
        type_col.setCellValueFactory(new PropertyValueFactory("type"));
        level_col.setCellValueFactory(new PropertyValueFactory("level"));
        desc_col.setCellValueFactory(new PropertyValueFactory("description"));
        rating_col.setCellValueFactory(new PropertyValueFactory("rating"));
    }

    @FXML
    private void on_import_action(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.pdf", "pdf");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            this.selectedFile = fileChooser.getSelectedFile();
            //String path = selectedFile.getAbsolutePath();
            //s = path;
        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("Insert a file please");
        }
    }

    File selectedFile = null;

    @FXML
    private void on_add_btn(ActionEvent event) {
        Co_StudyingService ss = new Co_StudyingService();
        if ((description.getText() == null || description.getText().trim().isEmpty())) {
            Alert a = new Alert(AlertType.NONE);
            a.setAlertType(AlertType.WARNING);
            a.setContentText("Fill the description field please");
            a.show();
        } else {
            String fileName = "";
            fileName = Helpers.uploadFile(selectedFile);
            Co_Studying p = new Co_Studying(description.getText(), fileName, type.getValue().toString(), level.getValue().toString());
            ss.addCostudying(p);
            showCostudying();
        }

    }

    private void on_update_btn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CoStudyingFront.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
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
    private void mm_hover(MouseEvent event) {
        mainmenu_icon.setCursor(Cursor.HAND);
    }

    @FXML
    private void main_menu_clicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CoStudyingFront.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
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
