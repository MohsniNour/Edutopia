/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activity;
import Entities.Course;
import Entities.SmsSender;
import Entities.Student;
import Entities.Work_done;
import Services.ActivityService;
import Services.CourseService;
import Services.Work_doneService;
import Utils.Config;
import Utils.Helpers;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class ActivityUserItemController implements Initializable {

    @FXML
    private AnchorPane id_clicked;
    @FXML
    private Label Deadline;
    @FXML
    private Label ActivityName;
    public static Activity act;
    static int id_Activity;
    File file = null;
    int id_Course;
    public static Course c;
    public static Student current_user = LoginController.CurrentStudent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Deadline.setWrapText(true);
        ActivityName.setWrapText(true);
    }

    public void setData(Activity act) {
        this.act = act;
        CourseService cs = new CourseService();
        c = cs.getCourseById(act.getId_Course());
        id_Activity = act.getId();
        String date = act.getDeadline().toString();
        Deadline.setText(date);
        ActivityName.setText(act.getName());

    }

    @FXML
    private void OpenAction(ActionEvent event) {
        if (id_Activity != 0) {
            ActivityService as = new ActivityService();
            Activity act = new Activity();
            act = as.FindActivityById(id_Activity);
            String fileName = act.getWork_todo();
            Helpers.openWebpage(Config.BASE_URL2 + fileName);
        }
    }
    File selectedFile = null;

    @FXML
    private void uploadAction(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.pdf", "pdf");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            this.selectedFile = fileChooser.getSelectedFile();
        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("Insert a file please");
        }
        String fileName = "";
        fileName = Helpers.uploadFile(selectedFile);
        Work_doneService ws = new Work_doneService();
        Work_done work = new Work_done(fileName, "Work_posted", id_Activity, current_user.getId(), java.sql.Date.valueOf(java.time.LocalDate.now()));
        ws.add(work);
        SmsSender s = new SmsSender();
        String msg = " Travail ajouté avec succés";
        s.send(msg, "b");
        Node node = (Node) event.getSource();
        Stage oldStage = (Stage) node.getScene().getWindow();
        oldStage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("List_User_ActivityDone.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Liste des cours");
            CourseService cs = new CourseService();
            Course c = new Course();
            c = cs.getCourseById(id_Course);
            System.out.println(c);
            List_User_ActivityDoneController controller = loader.getController();
            controller.initData(c);
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }

    @FXML
    private void on_mouse_clicked(MouseEvent event) {
    }

}
