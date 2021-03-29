package GUI;

import CalendarA.FullCalendarView;
import Entities.LigneExamen;
import Entities.Subjectt;
import Services.Exam_Service;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.security.auth.Subject;

public class Front_ChargerExamen implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Button btn_Exam;
    @FXML
    private Button btn_Passage;
    @FXML
    private Button btn_Aganda;
    @FXML
    private Button btn_Course;
    @FXML
    private Pane pnl_abonnement;

    private int idUser = 2;
    @FXML
    private TableView<Ligne_Examen> tableviewExamen;
    @FXML
    private TableColumn<Ligne_Examen, String> tablecolumnType;
    @FXML
    private TableColumn<Ligne_Examen, String> tablecolumnsujet;
    @FXML
    private TableColumn<Ligne_Examen, String> tablecolumnDateExamen;
    @FXML
    private TableColumn<Ligne_Examen, Label> tablecolumnNote;
    private int id_Subject;

    ObservableList<Ligne_Examen> list = FXCollections.observableArrayList();
    Exam_Service serviceexamen = new Exam_Service();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<LigneExamen> ligneExams;
        ligneExams = serviceexamen.getExamByUser(idUser);
        for (LigneExamen ln : ligneExams) {
            Ligne_Examen le = new Ligne_Examen(ln.getExam().getId(), ln.getExam().getType(),ln.getExam().getSubject(), ln.getExam().getStartdate().toString(), ln.getNote());
            list.add(le);
        }

        tablecolumnDateExamen.setCellValueFactory(new PropertyValueFactory<>("DateExamen"));
        tablecolumnNote.setCellValueFactory(new PropertyValueFactory<>("note"));
        tablecolumnType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        tablecolumnsujet.setCellValueFactory(new PropertyValueFactory<>("Sujet"));
        tableviewExamen.setItems(list);
    }

    void initData(Subjectt c) throws SQLException {
        id_Subject =c.getId();
        tablecolumnsujet.setText(c.getId_Subject());

    }

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btn_Exam) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front_ChargerExamen.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btn_Passage) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front_ConsulterExamenToday.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btn_Course) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Front_ConsulterCours.fxml")));
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

    public class Ligne_Examen {

        private int id;
        private String Type;
        private String Sujet;
        private String DateExamen;
        private Label note;

        public Ligne_Examen(int id, String Type, String Sujet, String DateExamen, float note) {
            this.id = id;
            this.Type = Type;
            this.Sujet = Sujet;
            this.DateExamen = DateExamen;
            this.note = new Label(Float.toString(note));
            if (note >= 10) {
                this.note.setTextFill(Color.GREEN);
            } else {
                this.note.setTextFill(Color.RED);
            }
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getSujet() {
            return Sujet;
        }

        public void setSujet(String Sujet) {
            this.Sujet = Sujet;
        }

       

        public String getDateExamen() {
            return DateExamen;
        }

        public void setDateExamen(String DateExamen) {
            this.DateExamen = DateExamen;
        }

        public Label getNote() {
            return note;
        }

        public void setNote(Label note) {
            this.note = note;
        }

    }

}
