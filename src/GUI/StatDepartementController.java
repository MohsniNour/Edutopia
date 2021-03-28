/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.DepartmentService;
import Services.TeacherService;
import Entities.Department;
import Entities.Teacher;
import java.awt.image.BufferedImage;
import javafx.stage.Stage;  
import java.io.File;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mrad
 */
public class StatDepartementController implements Initializable {

    @FXML
    private Label lblnb;
    @FXML
    private ComboBox<String> cbdep;
    @FXML
    private Label lblchefdep;
    @FXML
    private Label lblnbens;
    private Label lblens;
    @FXML
    private Button btnok;

    /**
     * Initializes the controller class.
     */
    ObservableList<String> ids = FXCollections.observableArrayList();
    @FXML
    private PieChart piechartnbmatiere = new PieChart();
    @FXML
    private PieChart piechartnbmatiereens;
    @FXML
    private Button btnstat1;
    @FXML
    private Button btnstat2;
    
    int young=0;
    int twenties=0;
    int thirties=0;
    int fourties=0;
    int fifties=0;
    int oneS=0;
    int twoS=0;
    int threeS=0;
    int plusThreeS=0;
    @FXML
    private Button btdown;
    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label UserName;
    @FXML
    private Button btn_Course1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
        DepartmentService ds = new DepartmentService();
        ObservableList<String> strings = FXCollections.observableArrayList();
        ObservableList<Department> elements = ds.getDepartments();
        for (Department s : elements) {
            strings.add(s.getName());
            ids.add(String.valueOf(s.getId()));
        }
        cbdep.getItems().addAll(strings);
        System.out.println(ids.toString()+ " "+strings.toString());
        lblnb.setText(String.valueOf(ds.nbrDepart()));
        
    }    

    @FXML
    private void select(ActionEvent event) {
        
        
        DepartmentService ds = new DepartmentService();
        TeacherService ts = new TeacherService();
        Department d = ds.getDepParId(Integer.parseInt(ids.get(cbdep.getSelectionModel().getSelectedIndex())));
        lblchefdep.setText(d.getOwnername() +" "+d.getOwnerlastname());
        lblnbens.setText(String.valueOf(ts.nbEnseignantselonDepart(cbdep.getSelectionModel().getSelectedItem())));
    }

    @FXML
    private void btnstat1(ActionEvent event) {
        
        TeacherService ts = new TeacherService();
        piechartnbmatiere.setTitle("Statistique Enseignant/Age");
        ObservableList<Teacher> olt = ts.getTeacherSelonId(Integer.parseInt(ids.get(cbdep.getSelectionModel().getSelectedIndex())));
        for (Teacher teach : olt){
            Calendar cal = Calendar.getInstance();
            cal.setTime(teach.getBirth_date());
            int year = cal.get(Calendar.YEAR);
            int currentyear = Calendar.getInstance().get(Calendar.YEAR);
            
            if (currentyear-year<20){
                young++;
            }
            else if (currentyear-year>=20 && currentyear-year<30){
                twenties++;
            }
            else if (currentyear-year>=30 && currentyear-year<40){
                thirties++;
            }
            else if (currentyear-year>=40 && currentyear-year<50){
                fourties++;
            }
            else {
                fifties++;
            } 
        }
        ObservableList<PieChart.Data> ol = FXCollections.observableArrayList(
                new PieChart.Data("Enseignants moins de 20 ans", young),
                new PieChart.Data("Enseignants plus de 20 ans et moins de 30 ans", twenties),
                new PieChart.Data("Enseignants plus de 30 ans et moins de 40 ans", thirties),
                new PieChart.Data("Enseignants plus de 40 ans et moins de 50 ans", fourties),
                new PieChart.Data("Enseignants plus de 50 ans", fifties)   
        );
        piechartnbmatiere.setData(ol);
        for (PieChart.Data data : piechartnbmatiere.getData()){
                data.nameProperty().set(data.getName()+" : "+(int)data.getPieValue());
                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, (javafx.scene.input.MouseEvent event1) -> {
                    JOptionPane.showMessageDialog(null,data.getName()+" "+(int) data.getPieValue()); //To change body of generated methods, choose Tools | Templates.
            });
        }
    }

    @FXML
    private void btnstat2(ActionEvent event) {
        
        int oneS=0;
        int twoS=0;
        int threeS=0;
        int plusThreeS=0;
        piechartnbmatiere.setTitle("Statistique Enseignant/Age");
        TeacherService ts = new TeacherService();
        ObservableList<Teacher> olt = ts.getTeacherSelonId(Integer.parseInt(ids.get(cbdep.getSelectionModel().getSelectedIndex())));
        for (Teacher teach : olt){
            Calendar cal = Calendar.getInstance();
            cal.setTime(teach.getBirth_date());
            int year = cal.get(Calendar.YEAR);
            int currentyear = Calendar.getInstance().get(Calendar.YEAR);
            if (teach.getNbMatiers()==1) {
                oneS++;
            } else if (teach.getNbMatiers()==2) {
                twoS++;
            } else if (teach.getNbMatiers()==3) {
                threeS++;
            } else {
                plusThreeS++;
            }
        }
        ObservableList<PieChart.Data> ol2 = FXCollections.observableArrayList(
                new PieChart.Data("Nombre enseignant avec une seule matière", oneS),
                new PieChart.Data("Nombre enseignant avec deux matières", twoS),
                new PieChart.Data("Nombre enseignant avec trois matières", threeS),
                new PieChart.Data("Nombre enseignant avec quatre matières ou plus", twoS)
        );
        piechartnbmatiere.setData(ol2);
        for (PieChart.Data data : piechartnbmatiere.getData()){
                data.nameProperty().set(data.getName()+" : "+(int)data.getPieValue());
                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, (javafx.scene.input.MouseEvent event2) -> {
                    JOptionPane.showMessageDialog(null,data.getName()+" "+(int) data.getPieValue()); //To change body of generated methods, choose Tools | Templates.
            });
        }
    }

    @FXML
    private void download(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new ExtensionFilter("PNG files", "*.PNG"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        File file = chooser.showSaveDialog(stage);
            if (file != null) {
                try {
                    Image img = piechartnbmatiere.snapshot(null, null);
                    BufferedImage imgData = SwingFXUtils.fromFXImage(img, null);
                    ImageIO.write(imgData, "png", file);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }               
            }
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
}
