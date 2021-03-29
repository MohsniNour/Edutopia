/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Course;
import Services.CourseService;
import Services.ForumService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class List_CourseController implements Initializable {

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
    private TableView<Course> TableView;
    @FXML
    private TableColumn<Course, String> CourseName;
    @FXML
    private TextField txtSearch;
    @FXML
    private Label SubjectName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCourses();
    }

    public void showCourses() {
        CourseService cs = new CourseService();
        ArrayList<Course> al = cs.getCoursesList();
        ObservableList<Course> oL = FXCollections.observableArrayList(al);
        CourseName.setCellValueFactory(new PropertyValueFactory("name"));
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
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("List_Course.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Liste des cours");
            List_Course_UserController controller = loader.getController();
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }

    @FXML
    private void ExamAction(ActionEvent event) {
    }

    @FXML
    private void CalendarAction(ActionEvent event) {
    }

    @FXML
    private void Co_StudyingAction(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CoStudyingFront.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Espace 'CoStudying'");
            List_CourseController controller = loader.getController();
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
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
        showCourses();
    }

    @FXML
    private void BackAction(ActionEvent event) {
    }

    @FXML
    private void ArchivedForumListAction(ActionEvent event) {
        if (TableView.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("selectionnez un cours ");
            alert.showAndWait();
        } else {
            Course c = TableView.getSelectionModel().getSelectedItem();
            System.out.println(c.getId());
            ForumService fs = new ForumService();
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("List_Archived_Forum.fxml"));
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Liste des forum archivés");
                List_Archived_ForumController controller = loader.getController();
                controller.initData(c);
                stage.show();
            } catch (IOException e) {
                System.err.println(String.format("Error: %s", e.getMessage()));
            }
        }
    }

    @FXML
    private void AvailableActivityListAction(ActionEvent event) {
        if (TableView.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("selectionnez un cours ");
            alert.showAndWait();
        } else {
            Course c = TableView.getSelectionModel().getSelectedItem();
            System.out.println(c.getId());
            ForumService fs = new ForumService();
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("List_Activity.fxml"));
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Liste des activités");
                List_ActivityController controller = loader.getController();
                controller.initData(c);
                stage.show();
            } catch (IOException e) {
                System.err.println(String.format("Error: %s", e.getMessage()));
            }
        }
    }

    @FXML
    private void ArchivedActivityListAction(ActionEvent event) {
        if (TableView.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("selectionnez un cours ");
            alert.showAndWait();
        } else {
            Course c = TableView.getSelectionModel().getSelectedItem();
            System.out.println(c.getId());
            ForumService fs = new ForumService();
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("List_Archived_Activity.fxml"));
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Liste des activités archivés");
                List_Archived_ActivityController controller = loader.getController();
                controller.initData(c);
                stage.show();
            } catch (IOException e) {
                System.err.println(String.format("Error: %s", e.getMessage()));
            }
        }
    }

    @FXML
    private void AvailableForumListAction(ActionEvent event) {
        if (TableView.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("selectionnez un cours ");
            alert.showAndWait();
        } else {
            Course c = TableView.getSelectionModel().getSelectedItem();
            System.out.println(c.getId());
            ForumService fs = new ForumService();
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Forum.fxml"));
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Liste des forum disponibles");
                ForumController controller = loader.getController();
                controller.initData(c);
                stage.show();
            } catch (IOException e) {
                System.err.println(String.format("Error: %s", e.getMessage()));
            }
        }
    }

    @FXML
    private void AddAction(ActionEvent event) {
    }

    @FXML
    private void RemoveAction(ActionEvent event) {
    }

}
