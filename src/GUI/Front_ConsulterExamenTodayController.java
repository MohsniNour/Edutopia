package GUI;

import CalendarA.FullCalendarView;
import Entities.Exam;
import Services.Exam_Service;
import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Front_ConsulterExamenTodayController implements Initializable {

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
    private Button btn_Questions;
    @FXML
    private Button btn_Course;
    @FXML
    private Pane pnl_abonnement;

    Exam_Service serviceexamen = new Exam_Service();
    @FXML
    private VBox vboxPassageExamen;
    @FXML
    private Text textNombreExamen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Exam> exams = serviceexamen.Affichertous();

        int i = 0;

        for (Exam e : exams) {
            if (!serviceexamen.VerfierPassageExamen(e.getId())) {
                java.util.Date dateNow = new java.util.Date();
                dateNow.setHours(0);
                dateNow.setMinutes(0);
                dateNow.setSeconds(0);
                java.util.Date dateDm1 = new java.util.Date();
                dateDm1.setHours(0);
                dateDm1.setMinutes(0);
                dateDm1.setSeconds(0);
                dateDm1.setTime(dateDm1.getTime() + 86399000);
                if ((dateNow.getTime() <= e.getStartdate().getTime()) && (dateDm1.getTime() >= e.getStartdate().getTime())) {
                    i++;
                    Hyperlink hp = new Hyperlink(e.getType() + ":" + e.getSubject());
                    vboxPassageExamen.getChildren().add(hp);

                    hp.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            try {

                                Node node = (Node) event.getSource();

                                Stage stage = (Stage) node.getScene().getWindow();
                                //stage.setMaximized(true);
                                stage.close();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("Front_PasserExamen.fxml"));
                                Parent root = loader.load();
                                Front_PasserExamenController controller = loader.getController();
                                controller.exam = e;
                                controller.chargerExam();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();

                            } catch (Exception ex) {
                                //Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
                                System.out.println(".handle()");
                            }
                        }
                    });
                }
            }
        }
        if (i == 1) {
            textNombreExamen.setText("Voici l'examen à passer aujourd'hui");
        } else if (i > 1) {
            textNombreExamen.setText("Voici les " + i + " examens à passer aujourd'hui");
        } else {
            textNombreExamen.setText("Pas d'examens à passer aujourd'hui");
        }
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

}
