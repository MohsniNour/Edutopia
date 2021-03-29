/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activity;
import Entities.Course;
import Services.ActivityService;
import Services.ForumService;
import Services.Work_doneService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class List_ActivityController implements Initializable {

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
    private Label CourseName;
    private Path to;
    private Path from;
    private Path fromUpdated;
    private Path removePath;
    File file = null;
    String id_Course;
    @FXML
    private TextField txtSearch;
    @FXML
    private GridPane grid;
    int column = 0;
    int row = 1;
    @FXML
    private HBox hbox_data;
    @FXML
    private ScrollPane scroll;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void initData(Course c) {
        CourseName.setText(c.getName());
        UserName.setText(c.getName());
        id_Course = c.getId();
        showActivities();
    }
    void initData(Activity act) {
        CourseName.setText(act.getName());
        UserName.setText(act.getName());
        id_Course = act.getId_Course();
        showActivities();
    }

    public void showActivities() {
        ActivityService as = new ActivityService();
        List<Activity> acts = new ArrayList<>();
        acts = as.getAvailableActivitiesListByIdCourse(id_Course);

        try {
            for (int i = 0; i < acts.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ActivityItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ActivityItemController itemController = fxmlLoader.getController();
                itemController.setData(acts.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            List_CourseController controller = loader.getController();
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
    private void SearchAction(ActionEvent event) throws SQLException {
        if (txtSearch.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("Le nom est vide saisir un nom ");
            alert.showAndWait();
        } else {
            List<Activity> al = new ArrayList();
            ActivityService as = new ActivityService();
            al = as.searchActivity(txtSearch.getText(), id_Course);
            ObservableList<Activity> oL = FXCollections.observableArrayList(al);
//            TableView.setItems(oL);
//            Name.setCellValueFactory(new PropertyValueFactory("name"));
//            Deadline.setCellValueFactory(new PropertyValueFactory("deadline"));
        }
    }

    @FXML
    private void RefreshAction(ActionEvent event) {
        showActivities();
    }

    @FXML
    private void BackAction(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage oldStage = (Stage) node.getScene().getWindow();
        oldStage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("List_Course.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Liste des cours");
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }

    @FXML
    private void eventClicked(MouseEvent event) {
    }

    @FXML
    private void AddActivityAction(ActionEvent event) {
        
//            ActivityService as = new ActivityService();
//            Node node = (Node) event.getSource();
//            Stage stage = (Stage) node.getScene().getWindow();
//            stage.close();
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("List_Activity.fxml"));
//                stage.setScene(new Scene(loader.load()));
//                stage.setTitle("Liste des activités");
//                List_ActivityController controller = loader.getController();
//                controller.initData(c);
//                stage.show();
//            } catch (IOException e) {
//                System.err.println(String.format("Error: %s", e.getMessage()));
//            }
    }

}
