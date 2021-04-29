package GUI;

import CalendarA.FullCalendarView;
import Entities.BadWords;
import Entities.Course;
import Services.CourseService;
import Utils.Helpers;
import Entities.Subjectt;
import Entities.User;
import Services.ForumService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class home_courseController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    private Button btn_Course;
    @FXML
    private Pane pnl_abonnement;
    @FXML
    private TextField txt_Seach;
    @FXML
    private TableView<Course> tabview;
    @FXML
    private TableColumn<Course, String> col_Name;
    @FXML
    private TableColumn<Course, Date> col_created_date;
    @FXML
    private TableColumn<Course, Date> col_archived_date;
    private TableColumn<Course, Integer> col_id_subject;
    @FXML
    private TableColumn<Course, String> col_Description;
    @FXML
    private Button btn_ajout;
    private Button btnBack;
    @FXML
    private TextField txtName;
    private CourseService service = new CourseService();
    private TableColumn<Course, String> col_btnArchiver;
    @FXML
    private Button btnfileChooser;
    @FXML
    private TextField Description;
    @FXML
    private Label file_name;
    private int id_Subject;
    @FXML
    private Label UserName;
    @FXML
    private Label SubjectName;
    @FXML
    private Button CourseAction;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        search();
        Modifier();
        try {
            refrech();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        tabview.setEditable(true);
        col_btnArchiver = new TableColumn("Archiver");

        javafx.util.Callback<TableColumn<Course, String>, TableCell<Course, String>> cellFactory = new Callback<TableColumn<Course, String>, TableCell<Course, String>>() {

            public TableCell call(final TableColumn<Course, String> param) {
                final TableCell<Course, String> cell = new TableCell<Course, String>() {
                    final Button btn = new Button("Archiver");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                Course u = getTableView().getItems().get(getIndex());
                                try {
                                    if (u.getArchived_Date() == null) {
                                        service.Archiver(u.getId());
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Archivage ");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Archivage avec success ");
                                        alert.showAndWait();
                                    } else {
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Erreur ! ");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Deja archiver");
                                        alert.showAndWait();
                                    }
                                } catch (SQLException ex) {
                                }
                                try {
                                    refrech();
                                } catch (SQLException ex) {
                                }

                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        col_btnArchiver.setCellFactory(cellFactory);
        tabview.getColumns().add(col_btnArchiver);
    }

    void initData(Subjectt c) throws SQLException {
        id_Subject = c.getId();
        SubjectName.setText(c.getId_Subject());
        show();

    }

    public void show() throws SQLException {
        col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_Name.setCellFactory(TextFieldTableCell.<Course>forTableColumn());
        col_created_date.setCellValueFactory(new PropertyValueFactory<>("created_Date"));
        col_archived_date.setCellValueFactory(new PropertyValueFactory<>("archived_Date"));
        col_Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        col_Description.setCellFactory(TextFieldTableCell.<Course>forTableColumn());
        tabview.getItems().clear();
        tabview.setItems(service.AfficherListCoursMatiere(id_Subject));
    }

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btn_Course) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/FXMLSubjectForTeacher.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    private void chargerCalendarHandle(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FullCalendar.fxml"));
        Parent root = loader.load();

        FullCalendarController controller = loader.getController();
        VBox f = new FullCalendarView(YearMonth.now()).getView();
        controller.calendarPane.getChildren().add(f);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private FileChooser ImportButtonAction(ActionEvent event) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.pdf", "pdf");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            this.selectedFile = fileChooser.getSelectedFile();
            //String path = selectedFile.getAbsolutePath();
            //s = path;
        } else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("Insert a file please");
        }
        return null;
    }

    File selectedFile = null;

    @FXML
    private void ajouter_Cours(ActionEvent event) throws SQLException {
        BadWords.loadConfigs();

        {
            if (txtName.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention !");
                alert.setHeaderText(null);
                alert.setContentText("champ vide de Quetion ");
                alert.showAndWait();
            } else if (BadWords.filterText(txtName.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention !");
                alert.setHeaderText(null);
                alert.setContentText("cette application n'autorise pas ces termes ");
                alert.showAndWait();
            } else if (Description.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention !");
                alert.setHeaderText(null);
                alert.setContentText("champ vide de Quetion ");
                alert.showAndWait();
            } else if (BadWords.filterText(Description.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Attention !");
                alert.setHeaderText(null);
                alert.setContentText("cette application n'autorise pas ces termes ");
                alert.showAndWait();
            } else {
                String fileName = "";
                fileName = Helpers.uploadFile(selectedFile);
                Course c = new Course(txtName.getText(), fileName, id_Subject, Description.getText());
                service.Ajouter(c);
                refrech();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cours");
                alert.setHeaderText(null);
                alert.setContentText("Cours ajouter");
                alert.showAndWait();
            }

        }
    }

    public void refrech() throws SQLException {
        show();

    }

    public void search() {
        txt_Seach.setOnKeyReleased(e
                -> {
            if (txt_Seach.getText().equals("")) {

                try {
                    refrech();
                } catch (SQLException ex) {
                }

            } else {
                col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
                col_created_date.setCellValueFactory(new PropertyValueFactory<>("created_Date"));
                col_archived_date.setCellValueFactory(new PropertyValueFactory<>("archived_Date"));
                col_Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
                tabview.getItems().clear();
                try {
                    tabview.setItems(service.serach(txt_Seach.getText()));
                } catch (SQLException ex) {
                    Logger.getLogger(home_courseController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
    }

    public void Modifier() {

        col_Name.setOnEditCommit((TableColumn.CellEditEvent<Course, String> event) -> {
            TablePosition<Course, String> pos = event.getTablePosition();
            String name = event.getNewValue();
            int row = pos.getRow();
            Course c = event.getTableView().getItems().get(row);
            c.setName(name);
            try {
                service.Modifier(c, c.getId());
            } catch (Exception ex) {
            }
        });
        col_Description.setOnEditCommit((TableColumn.CellEditEvent<Course, String> event) -> {
            TablePosition<Course, String> pos = event.getTablePosition();
            String name = event.getNewValue();
            int row = pos.getRow();
            Course c = event.getTableView().getItems().get(row);
            c.setName(name);

            try {
                service.Modifier(c, c.getId());
            } catch (Exception ex) {
            }
        });
    }

    @FXML
    private void backAction(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLSubjectForTeacher.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

  @FXML
    private void HomeAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TeacherHome.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void ClassAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("EnseignantClasse.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    private void CourseAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLSubjectForTeacher.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void CalendarAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FullCalendar.fxml"));
        Parent root = loader.load();
        FullCalendarController controller = loader.getController();
        VBox f = new FullCalendarView(YearMonth.now()).getView();
        controller.calendarPane.getChildren().add(f);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void AccountAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLTeacherModify.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void ClaimAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ComplaintFront.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void DisconnectionAction(ActionEvent event) {
        Dialog confirmation = new Dialog();
        GridPane grid2 = new GridPane();
        Label l1 = new Label("Décnnecter ?");
        grid2.add(l1, 2, 2);
        confirmation.setTitle("Confirmation");
        confirmation.getDialogPane().setContent(grid2);
        ButtonType Confi = new ButtonType("Oui", ButtonBar.ButtonData.OK_DONE);
        ButtonType Ann = new ButtonType("Non", ButtonBar.ButtonData.OK_DONE);
        confirmation.getDialogPane().getButtonTypes().add(Confi);
        confirmation.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        confirmation.setResultConverter(new Callback<ButtonType, User>() {
            @Override
            public User call(ButtonType param) {
                if (param == Confi) {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(Add_Student_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
                }

                return null;
            }
        });
        confirmation.showAndWait();
    }

    @FXML
    private void AvailableActivityListAction(ActionEvent event) {
        if (tabview.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("selectionnez un cour ");
            alert.showAndWait();
        } else {
            Course c = tabview.getSelectionModel().getSelectedItem();
            System.out.println(c.getId());
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
        if (tabview.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("selectionnez un cour ");
            alert.showAndWait();
        } else {
            Course c = tabview.getSelectionModel().getSelectedItem();
            System.out.println(c.getId());
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("List_Removed_Activity.fxml"));
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Liste des activités archivés");
                List_Removed_ActivityController controller = loader.getController();
                controller.initData(c);
                stage.show();
            } catch (IOException e) {
                System.err.println(String.format("Error: %s", e.getMessage()));
            }
        }
    }

    @FXML
    private void AvailableForumListAction(ActionEvent event) {
        if (tabview.getSelectionModel().getSelectedItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention !");
            alert.setHeaderText(null);
            alert.setContentText("selectionnez un cours ");
            alert.showAndWait();
        } else {
            Course c = tabview.getSelectionModel().getSelectedItem();
            System.out.println(c.getId());
            ForumService fs = new ForumService();
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AvailableForum.fxml"));
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Liste des forum disponibles");
                AvailableForumController controller = loader.getController();
                controller.initData(c);
                stage.show();
            } catch (IOException e) {
                System.err.println(String.format("Error: %s", e.getMessage()));
            }
        }
    }

}
