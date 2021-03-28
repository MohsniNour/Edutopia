/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.DepartmentService;
import Services.SpecialtyService;
import Entities.Specialty;
import Entities.Teacher;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * FXML Controller class
 *
 * @author Mrad
 */
public class HistSpecialtyController implements Initializable {

    @FXML
    private TableView<Specialty> tvid;
    @FXML
    private TableColumn<Specialty, String> colspec;
    @FXML
    private TableColumn<Specialty, String> colcreatedby;
    @FXML
    private TableColumn<Specialty, String> colcreateddate;
    @FXML
    private TableColumn<Specialty, String> colupby;
    @FXML
    private TableColumn<Specialty, String> colupdate;
    @FXML
    private TableColumn<Specialty, String> colarchby;
    @FXML
    private TableColumn<Specialty, String> colarchdate;
    @FXML
    private Button btngetfile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showSpecialties();
    }    

    public void showSpecialties(){
        SpecialtyService ss = new SpecialtyService();
        tvid.setItems( ss.getHistoriqueSpecialties());
        colspec.setCellValueFactory(new PropertyValueFactory("specialty"));
        colcreatedby.setCellValueFactory(new PropertyValueFactory("created_by"));
        colcreateddate.setCellValueFactory(new PropertyValueFactory("created_date"));
        colupby.setCellValueFactory(new PropertyValueFactory("last_updated_by"));
        colupdate.setCellValueFactory(new PropertyValueFactory("last_update_date"));
        colarchby.setCellValueFactory(new PropertyValueFactory("archived_by"));
        colarchdate.setCellValueFactory(new PropertyValueFactory("archived_date"));
    }
    @FXML
    private void goback(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLSpecialty.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("edutopia");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void getfile(ActionEvent event) throws IOException {
        FileOutputStream fileOut = null;
        try {
            DepartmentService ds = new DepartmentService();
            ObservableList<TableColumn<Specialty, ?>> ol = tvid.getColumns();
            ol.clear();
            ol.add(colspec);
            ol.add(colcreatedby);
            ol.add(colcreateddate);
            ol.add(colupby);
            ol.add(colupdate);
            ol.add(colarchby);
            ol.add(colarchdate);
            
            Workbook workbook = new HSSFWorkbook();
            Sheet spreadsheet = workbook.createSheet("sample");
            Row row = spreadsheet.createRow(0);
            for (int j = 0; j < tvid.getColumns().size(); j++) {
                row.createCell(j).setCellValue(tvid.getColumns().get(j).getText());
            }
            for (int i = 0; i < tvid.getItems().size(); i++) {
                row = spreadsheet.createRow(i + 1);
                for (int j = 0; j < tvid.getColumns().size(); j++) {
                    if(tvid.getColumns().get(j).getCellData(i) != null) {
                        row.createCell(j).setCellValue(tvid.getColumns().get(j).getCellData(i).toString()); 
                    }
                    else {
                        row.createCell(j).setCellValue("");
                    }   
                }
            }
            fileOut = new FileOutputStream("HistSpecialty.xls");
            workbook.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDepartmentController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
}
