/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.DepartmentService;
import Services.SpecialtyService;
import Services.TeacherService;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * FXML Controller class
 *
 * @author Mrad
 */
public class HistTeacherController implements Initializable {

    @FXML
    private TableView<Teacher> tvid;
    @FXML
    private TableColumn<Teacher, String> colteach;
    @FXML
    private TableColumn<Teacher, String> colcreatedby;
    @FXML
    private TableColumn<Teacher, String> colcreateddate;
    @FXML
    private TableColumn<Teacher, String> colupby;
    @FXML
    private TableColumn<Teacher, String> colupdate;
    @FXML
    private TableColumn<Teacher, String> colarchby;
    @FXML
    private TableColumn<Teacher, String> colarchdate;
    @FXML
    private Button btngetfile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TeacherService ts = new TeacherService();
        tvid.setItems( ts.getHistoriqueTeachers());
        colteach.setCellValueFactory(new PropertyValueFactory("name"));
        colcreatedby.setCellValueFactory(new PropertyValueFactory("created_by"));
        colcreateddate.setCellValueFactory(new PropertyValueFactory("created_date"));
        colupby.setCellValueFactory(new PropertyValueFactory("last_updated_by"));
        colupdate.setCellValueFactory(new PropertyValueFactory("last_update_date"));
        colarchby.setCellValueFactory(new PropertyValueFactory("archived_by"));
        colarchdate.setCellValueFactory(new PropertyValueFactory("archived_date"));
    }    

    @FXML
    private void getfile(ActionEvent event) throws IOException {
        FileOutputStream fileOut = null;
        try {
            DepartmentService ds = new DepartmentService();
            ObservableList<TableColumn<Teacher, ?>> ol = tvid.getColumns();
            ol.clear();
            ol.add(colteach);
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
            fileOut = new FileOutputStream("HistTeacher.xls");
            workbook.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDepartmentController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    
}
