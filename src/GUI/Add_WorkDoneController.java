/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activity;
import Entities.Work_done;
import Services.Work_doneService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class Add_WorkDoneController implements Initializable {

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
    private Label ActivityName;
    @FXML
    private Label worktodo;
    @FXML
    private Label importLabel;
    private int id_activity;

    private Path to;
    private Path from;
//    private Path fromUpdated;
//    private Path removePath;
    File file = null;
    @FXML
    private AnchorPane addWorkActivity;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void initData(Activity act) {
        ActivityName.setText(act.getName());
        UserName.setText(act.getCreated_by());
        worktodo.setText(act.getWork_todo());
        id_activity = act.getId();
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
    private void BackAction(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage oldStage = (Stage) node.getScene().getWindow();
        oldStage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("List_Activity_User.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }

    @FXML
    private void ImportButtonAction(ActionEvent event) {
        FileChooser filechooser = new FileChooser();
        Stage stage = (Stage) addWorkActivity.getScene().getWindow();
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("File", "*.pdf"));
        file = filechooser.showOpenDialog(stage);
        if (file != null) {
            from = Paths.get(file.toURI());
            to = Paths.get("src/WorkFiles/" + file.getName());
            importLabel.setText(to.toString());
            System.out.println(from + "hello");
        }
    }

    @FXML
    private void AddWorkAction(ActionEvent event) throws IOException {

        if (file == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("Le fichier est vide entrez un fichier ");
            alert.showAndWait();
        } else {
            System.out.println(String.valueOf(file));
            Work_doneService ws = new Work_doneService();
            Work_done work = new Work_done(String.valueOf(to), "Work_posted", id_activity, "1", java.sql.Date.valueOf(java.time.LocalDate.now()));
            Files.copy(from, to);
            System.out.print(to);
            ws.add(work);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Activité ajoutée avec succès");
            Node node = (Node) event.getSource();
            Stage oldStage = (Stage) node.getScene().getWindow();
            oldStage.close();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("List_Activity_User.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                stage.show();
            } catch (IOException e) {
                System.err.println(String.format("Error: %s", e.getMessage()));
            }
        }
    }

}
