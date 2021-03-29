package GUI;

import Entities.Subjectt;
import Services.SubjectService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Front_SubjectController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label UserName;
    @FXML
    private Button btn_Course1;
    @FXML
    private Pane pnl_abonnement;
    @FXML
    private Label SubjectName;
    @FXML
    private TableView<Subjectt> TableView;
    @FXML
    private TableColumn<Subjectt, String> CourseName;
    @FXML
    private TextField txtSearch;

    /**
     * Initializes the controller class.
     */
@Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showCourses();
        } catch (SQLException ex) {
            Logger.getLogger(Front_SubjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showCourses() throws SQLException {
        SubjectService cs = new SubjectService();
        ArrayList<Subjectt> al = cs.Affichertout();
        ObservableList<Subjectt> oL = FXCollections.observableArrayList(al);
        CourseName.setCellValueFactory(new PropertyValueFactory("id_Subject"));
        TableView.setItems(oL);
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
    private void ExamAction(ActionEvent event) {
    }

    @FXML
    private void CalendarAction(ActionEvent event) {
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


    @FXML
    private void SearchAction(ActionEvent event) {
    }


    @FXML
    private void RefreshAction(ActionEvent event) {
    }

    @FXML
    private void BackAction(ActionEvent event) {
    }

    @FXML
    private void AddAction(ActionEvent event) throws SQLException {
        if (TableView.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("selectionnez une matière ");
            alert.showAndWait();
        } else {
            Subjectt c = TableView.getSelectionModel().getSelectedItem();
            SubjectService fs = new SubjectService();
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Home_Course.fxml"));
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Liste des courses");
                home_courseController controller = loader.getController();
                controller.initData(c);
                stage.show();
            } catch (IOException e) {
                System.err.println(String.format("Error: %s", e.getMessage()));
            }
    }}

    @FXML
    private void AddExamenAction(ActionEvent event) throws SQLException {
        if (TableView.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("selectionnez une matière ");
            alert.showAndWait();
        } else {
            Subjectt c = TableView.getSelectionModel().getSelectedItem();
            System.out.println(c.getId());
            SubjectService fs = new SubjectService();
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Home_Exam.fxml"));
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Liste des courses");
                home_ExamController controller = loader.getController();
                controller.initData(c);
                stage.show();
            } catch (IOException e) {
                System.err.println(String.format("Error: %s", e.getMessage()));
            }
        }
    }
    
}
