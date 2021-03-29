package GUI;

import Alert.AlertDialog;
import CalendarA.FullCalendarView;
import Entities.BadWords;
import Entities.Course;
import Entities.Subjectt;
import Services.CourseService;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class home_courseController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Button btn_Exam;
    private Button btn_Questions;
    @FXML
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
                                        AlertDialog.showNotification("Archivage", "Archivage avec success", AlertDialog.image_checked);

                                    } else {
                                        AlertDialog.showNotification("Erreur  !", "Deja archiver !", AlertDialog.image_cross);
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
        if (event.getSource() == btn_Exam) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Home_Exam.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btn_Course) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front_Subject.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
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
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("File", "*.txt"));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            file_name.setText(f.getAbsolutePath());

        }
        return fc;
    }

    @FXML
    private void ajouter_Cours(ActionEvent event) throws SQLException {
        BadWords.loadConfigs();
        File f = new File(file_name.getText());
        {
            if (txtName.getText().equals("")) {
                AlertDialog.showNotification("Error !", "champ vide de nom", AlertDialog.image_cross);

            } else if (BadWords.filterText(txtName.getText())) {
                AlertDialog.showNotification("Error !", "cette application n'autorise pas ces termes", AlertDialog.image_cross);
            }
            if (Description.getText().equals("")) {
                AlertDialog.showNotification("Error !", "champ vide de Description", AlertDialog.image_cross);

            } else if (BadWords.filterText(Description.getText())) {
                AlertDialog.showNotification("Error !", "cette application n'autorise pas ces termes", AlertDialog.image_cross);
            } else {
                Course c = new Course(txtName.getText(), f, id_Subject, Description.getText());
                service.Ajouter(c);
                refrech();
                AlertDialog.showNotification("Cours", "Cours ajouter", AlertDialog.image_checked);
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
}
