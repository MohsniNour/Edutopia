package GUI;

import CalendarA.FullCalendarView;
import Services.Exam_Service;
import java.io.IOException;

import java.net.URL;
import java.time.YearMonth;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class StatExamenNoteController implements Initializable {


    @FXML
    private Pane paneStat;
    public int idexamen = 0;
    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Button btn_Exam;
    @FXML
    private Button btn_Questions;
    @FXML
    private Button btn_Course;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadData();
    }

    public void loadData() {
        paneStat.getChildren().clear();
        ObservableList<PieChart.Data> lista = FXCollections.observableArrayList();
        Exam_Service sdao = new Exam_Service();
        List<Exam_Service.Stat> listStat = sdao.afficherStatExamenNote(home_ExamController.id_subjectstatic);
        Iterator iterator = listStat.iterator();
        while (iterator.hasNext()) {
            Exam_Service.Stat stat = (Exam_Service.Stat) iterator.next();
            lista.add(new PieChart.Data(stat.getGroup(), stat.getNombre()));
        }
        PieChart piechat = new PieChart(lista);
        piechat.setTitle("Note Matière");


        lista.forEach(data
                -> data.nameProperty().bind(
                        Bindings.concat(
                               (data.pieValueProperty().intValue()), " examens(s) ", data.getName() 
                        )
                )
        );

        paneStat.getChildren().add(piechat);

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
        VBox f=new FullCalendarView(YearMonth.now()).getView();
        controller.calendarPane.getChildren().add(f);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    

}
