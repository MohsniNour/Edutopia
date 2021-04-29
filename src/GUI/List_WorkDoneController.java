/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activity;
import Entities.Student;
import Entities.Work_done;
import Services.Work_doneService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
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
public class List_WorkDoneController implements Initializable {

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
    int id_Activity;
    int column = 0;
    int row = 1;
    public static Activity act;
    public static Student current_user = LoginController.CurrentStudent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    void initData(Activity act) {
        this.act=act;
        CourseName.setText(act.getName());
        UserName.setText(current_user.getEmail());
        id_Activity = act.getId();
        showActivities();
    }
    public void showActivities() {
        Work_doneService ws = new Work_doneService();
        List<Work_done> works = new ArrayList<>();
        works = ws.getAvailableWorkDoneListByIdActivity(id_Activity);

        try {
            for (int i = 0; i < works.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("WorkDoneItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                WorkDoneItemController itemController = fxmlLoader.getController();
                itemController.setData(works.get(i));

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
    private void ClassAction(ActionEvent event) {
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
    private void BackAction(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage oldStage = (Stage) node.getScene().getWindow();
        oldStage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("List_Activity.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Liste des activités");
            List_ActivityController controller = loader.getController();
            controller.initData(act);
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

    @FXML
    private void ExamAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Front_ChargerExamen.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    
}
