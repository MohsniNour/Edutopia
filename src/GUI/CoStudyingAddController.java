/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Co_Studying;
import Services.Co_StudyingService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
            "All",
            "1A",
            "2A",
            "2B",
            "3A",
            "3B"
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
    private TableColumn<Co_Studying, Blob> file_col;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Button update_btn;
    @FXML
    private TableColumn<Co_Studying, String> rating_col;

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
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();

            s = path;
        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("Insert a file please");
        }
    }

    @FXML
    private void on_add_btn(ActionEvent event) {
        Co_StudyingService ss = new Co_StudyingService();
        Co_Studying p = new Co_Studying(description.getText(), type.getValue().toString(), level.getValue().toString());
        ss.addCostudying(p);
        showCostudying();

    }

    @FXML
    private void on_update_btn(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("CoStudyingDisplay.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
