/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activity;
import Entities.Course;
import Entities.Student;
import Entities.Subjectt;
import static GUI.AddActivityController.current_user;
import static GUI.List_ActivityController.c;
import Services.ActivityService;
import Services.SubjectService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
public class List_User_ActivityController implements Initializable {

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
    @FXML
    private TextField txtSearch;
    @FXML
    private HBox hbox_data;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    File file = null;
    int id_Course;
    int column = 0;
    int row = 1;
    public static Course c;
    public static Student current_user = LoginController.CurrentStudent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void initData(Course c) {
        this.c=c;
        CourseName.setText(c.getName());
        UserName.setText(current_user.getEmail());
        id_Course = c.getId();
        showActivities();
    }

    void initData(Activity act) {
        CourseName.setText(act.getName());
        UserName.setText(current_user.getEmail());
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
                fxmlLoader.setLocation(getClass().getResource("ActivityUserItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                ActivityUserItemController itemController = fxmlLoader.getController();
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
    private void CourseAction(ActionEvent event) {
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
    private void BackAction(ActionEvent event) throws SQLException {
        Node node = (Node) event.getSource();
        Stage oldStage = (Stage) node.getScene().getWindow();
        oldStage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Front_ConsulterCours.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Liste des cours");
            SubjectService cs = new SubjectService();
            Subjectt s=cs.getSubjectParI(c.getId_subject());
            Front_ConsulterCoursController controller = loader.getController();
            controller.initData(s);
            stage.show();
            
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
        
    }

    @FXML
    private void SearchAction(ActionEvent event) {
    }

    @FXML
    private void RefreshAction(ActionEvent event) {
    }

    @FXML
    private void eventClicked(MouseEvent event) {
    }

}
