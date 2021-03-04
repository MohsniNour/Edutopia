/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Co_Studying;
import Services.Co_StudyingService;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
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
            "1A",
            "2A",
            "3A"
    );

    public static String s;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.setItems(options);
        level.setItems(option);
    }

    @FXML
    private void on_import_action(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.pdf", "pdf");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();

            s = path;
        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("No Data");
        }
    }

    @FXML
    private void on_add_btn(ActionEvent event) {
        Co_StudyingService ss = new Co_StudyingService();
        Co_Studying p = new Co_Studying(description.getText(), type.getValue().toString(), level.getValue().toString());
        ss.addCostudying(p);

    }

}
