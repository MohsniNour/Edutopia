/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Activity;
import Entities.Course;
import Services.ActivityService;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class Add_ActivityController implements Initializable {

    @FXML
    private Button btnDeadlineChooser;
    @FXML
    private Label importLabel;
    @FXML
    private Button add_button;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField txtName;
    @FXML
    private DatePicker pkDeadline;
    @FXML
    private AnchorPane coursSelected;
    @FXML
    private TableView<Activity> TableView;
    @FXML
    private TableColumn<Activity, String> idName;
    @FXML
    private TableColumn<Activity, Date> idDeadline;
    @FXML
    private TableColumn<Activity, String> id;
    private int id_Course;
    private Path to;
    private Path from;
    private Path fromUpdated;
    private Path removePath;
    File file = null;
    @FXML
    private Label CourseName;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableColumn<Activity, String> url;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showActivities();
    }

    void initData(Course c) {
        CourseName.setText(c.getName());
        id_Course = c.getId();
        showActivities();
    }

    @FXML
    private void addAction(ActionEvent event) throws ParseException, IOException {

        if (txtName.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("Le nom est vide saisir un nom ");
            alert.showAndWait();
        } else if ((pkDeadline.getValue() == null)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("La date est vide entrez une date ");
            alert.showAndWait();
        } else if (file == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("Le fichier est vide entrez un fichier ");
            alert.showAndWait();
        } else {
            System.out.println(String.valueOf(file));
            ActivityService sa = new ActivityService();
            Activity act = new Activity(txtName.getText(), Date.valueOf(pkDeadline.getValue()), String.valueOf(to), id_Course, "nour", java.sql.Date.valueOf(java.time.LocalDate.now()));
            Files.copy(from, to);
            System.out.print(to);
            sa.add(act);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Activité ajoutée avec succès");
            showActivities();
        }
    }

    @FXML
    private void ImportButtonAction(ActionEvent event) {

        FileChooser filechooser = new FileChooser();
        Stage stage = (Stage) coursSelected.getScene().getWindow();
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("File", "*.pdf"));
        file = filechooser.showOpenDialog(stage);
        if (file != null) {
            from = Paths.get(file.toURI());
            to = Paths.get("src/Files/" + file.getName());
            importLabel.setText(to.toString());
            System.out.println(from + "hello");
        }
    }

    public void showActivities() {
        ActivityService as = new ActivityService();
        ArrayList<Activity> al = as.getActivitiesListByIdCourse(id_Course);
        ObservableList<Activity> oL = FXCollections.observableArrayList(al);
        idName.setCellValueFactory(new PropertyValueFactory("name"));
        idDeadline.setCellValueFactory(new PropertyValueFactory("deadline"));
        url.setCellValueFactory(new PropertyValueFactory("work_todo"));
        id.setCellValueFactory(new PropertyValueFactory("id"));
        TableView.setItems(oL);
    }

    @FXML
    private void updateAction(ActionEvent event) throws ParseException, IOException {

        Activity act = TableView.getSelectionModel().getSelectedItem();
        fromUpdated = Paths.get(act.getWork_todo());
        act.setName(txtName.getText());
        act.setDeadline(Date.valueOf(pkDeadline.getValue()));
        act.setId_Course(id_Course);
        act.setWork_todo(String.valueOf(to));
        act.setLast_updated_by("hamza");
        act.setLast_updated_Date(java.sql.Date.valueOf(java.time.LocalDate.now()));
        ActivityService as = new ActivityService();
        System.out.println(act.getId());
        Files.copy(from, to);
        from = Paths.get(act.getWork_todo());
        Files.delete(fromUpdated);
        as.update(act.getId(), act);
        TableView.refresh();
        showActivities();

    }

    @FXML
    private void removeButton(ActionEvent event) throws ParseException, IOException {
        if (TableView.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("selectionnez une activité ");
            alert.showAndWait();
        } else {
            Activity act = TableView.getSelectionModel().getSelectedItem();
            ActivityService as = new ActivityService();
            System.out.println(act.getId());
            from = Paths.get(act.getWork_todo());
            File f = new File(from.toString());
            removePath = Paths.get("src/RemovedFiles/" + f.getName());
            Files.copy(from, removePath);
            Files.delete(from);
            as.remove(act.getId(), removePath.toString());
            showActivities();
        }
    }

    @FXML
    private void backAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("List_Course.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
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
            al = as.searchActivity(txtSearch.getText(),id_Course);
            ObservableList<Activity> oL = FXCollections.observableArrayList(al);
            TableView.setItems(oL);
            idName.setCellValueFactory(new PropertyValueFactory("name"));
            idDeadline.setCellValueFactory(new PropertyValueFactory("deadline"));
        }
    }

        @FXML
        private void refreshAction
        (ActionEvent event
        
            ) {
            showActivities();

        }

        @FXML
        private void downloadPDFAction
        (ActionEvent event) throws IOException {

            Activity act = TableView.getSelectionModel().getSelectedItem();
            from = Paths.get(act.getWork_todo());
            FileChooser fileChooser = new FileChooser();
            Stage stage = (Stage) coursSelected.getScene().getWindow();
            fileChooser.setInitialFileName(act.getWork_todo());
            FileStore f = Files.getFileStore(from);
            File filee = fileChooser.showSaveDialog(stage);
            PrintWriter writer;
            writer = new PrintWriter(filee.getPath());
            writer.close();

        }
    }
