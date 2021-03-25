/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Co_Studying;
import Entities.Rating;
import Entities.Student;
import Entities.User;
import Services.Co_StudyingService;
import static GUI.CoStudyingItemController.id_item;
import Services.RatingService;
import Utils.Config;
import Utils.Helpers;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class CoStudyingFrontController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;

    private List<Co_Studying> cos = new ArrayList<>();
    @FXML
    private Label type;
    @FXML
    private Label level;
    @FXML
    private ImageView fruitImg;
    @FXML
    private VBox chosenCoCard;
    @FXML
    private Label description;
    @FXML
    private TextField toCompare;
    @FXML
    private Button search_btn;
    @FXML
    private HBox hbox_data;
    @FXML
    private Button download_btn;
    @FXML
    private Label owner;
    @FXML
    private ImageView rate1;
    @FXML
    private ImageView rate2;
    @FXML
    private ImageView rate3;
    @FXML
    private ImageView rate4;
    @FXML
    private ImageView rate5;
    @FXML
    private Label rate;
    @FXML
    private Button add_btn;
    @FXML
    private Label mainmenu_txt;
    @FXML
    private ImageView mainmenu_icon;
    @FXML
    private HBox load_btn;
    @FXML
    private HBox order_btn;
    
    DropShadow shadow = new DropShadow();

    Co_StudyingService cs = new Co_StudyingService();
    Co_Studying co = new Co_Studying();
    RatingService rs = new RatingService();

    int column = 0;
    int row = 1;

    String path = "C:\\Users\\rayen\\OneDrive\\Documents\\NetBeansProjects\\Edutopia\\src\\img\\Star_.svg.png";
    String path1 = "C:\\Users\\rayen\\OneDrive\\Documents\\NetBeansProjects\\Edutopia\\src\\img\\1024px-Empty_Star.svg.png";

    User current_user = new Student(1, "Admin", "Rayen", "Br", 123, "rrayen@esprit.tn", 12345678);

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        description.setWrapText(true);
        type.setWrapText(true);
        // TODO

        List<Co_Studying> cos = new ArrayList<>();
        cos = cs.getListCo_studying();

        try {
            for (int i = 0; i < cos.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/CoStudyingItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                CoStudyingItemController itemController = fxmlLoader.getController();
                itemController.setData(cos.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));

                //initializing the chosen card as the first list item
                co = cs.FindCo_Studying(id_item);

                rate.setText(Integer.toString(cos.get(0).getRating()));
                description.setText(cos.get(0).getDescription());
                type.setText(cos.get(0).getType());
                level.setText(cos.get(0).getLevel());
                owner.setText(cos.get(0).getId_student().getName() + " " + cos.get(0).getId_student().getLast_name());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void defaultRating(int r) {
        try {

            Image coloredStar = new Image(new FileInputStream(path));
            Image emptyStar = new Image(new FileInputStream(path1));
            switch (r) {
                case 1:
                    rate1.setImage(coloredStar);
                    rate2.setImage(emptyStar);
                    rate3.setImage(emptyStar);
                    rate4.setImage(emptyStar);
                    rate5.setImage(emptyStar);
                    break;
                case 2:
                    rate1.setImage(coloredStar);
                    rate2.setImage(coloredStar);
                    rate3.setImage(emptyStar);
                    rate4.setImage(emptyStar);
                    rate5.setImage(emptyStar);
                    break;
                case 3:
                    rate1.setImage(coloredStar);
                    rate2.setImage(coloredStar);
                    rate3.setImage(coloredStar);
                    rate4.setImage(emptyStar);
                    rate5.setImage(emptyStar);
                    break;
                case 4:
                    rate1.setImage(coloredStar);
                    rate2.setImage(coloredStar);
                    rate3.setImage(coloredStar);
                    rate4.setImage(coloredStar);
                    rate5.setImage(emptyStar);
                    break;
                case 5:
                    rate1.setImage(coloredStar);
                    rate2.setImage(coloredStar);
                    rate3.setImage(coloredStar);
                    rate4.setImage(coloredStar);
                    rate5.setImage(coloredStar);
                    break;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void load() {
        grid.getChildren().clear();
        List<Co_Studying> cos = new ArrayList<>();
        cos = cs.getListCo_studying();
        column = 0;
        try {
            for (int i = 0; i < cos.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/CoStudyingItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                CoStudyingItemController itemController = fxmlLoader.getController();
                itemController.setData(cos.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void eventClicked(MouseEvent event) throws FileNotFoundException {
        if (id_item != 0) {

            co = cs.FindCo_Studying(id_item);
            if (rs.CheckUserExists(current_user.getId(), id_item)) {
                int defaultRate = rs.TakeUserRating(current_user.getId(), id_item);
                defaultRating(defaultRate);
            } else {
                Image emptyStar = new Image(new FileInputStream(path1));
                rate1.setImage(emptyStar);
                rate2.setImage(emptyStar);
                rate3.setImage(emptyStar);
                rate4.setImage(emptyStar);
                rate5.setImage(emptyStar);
            }
            rate.setText(Integer.toString(co.getRating()));
            description.setText(co.getDescription());
            type.setText(co.getType());
            level.setText(co.getLevel());
            owner.setText(co.getId_student().getName() + " " + co.getId_student().getLast_name());
        }

    }

    @FXML
    private void on_search_btn(ActionEvent event) {
        List<Co_Studying> cos = new ArrayList<>();
        cos = cs.consulterFiltered(toCompare.getText());

        if (row > 1 || column > 0) {
            grid.getChildren().clear();
        }
        column = 0;
        try {
            for (int i = 0; i < cos.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/CoStudyingItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                CoStudyingItemController itemController = fxmlLoader.getController();
                itemController.setData(cos.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }
                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void search_too(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            //do something
            List<Co_Studying> cos = new ArrayList<>();
            cos = cs.consulterFiltered(toCompare.getText());

            if (row > 1 || column > 0) {
                grid.getChildren().clear();
            }
            column = 0;
            try {
                for (int i = 0; i < cos.size(); i++) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/GUI/CoStudyingItem.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();
                    CoStudyingItemController itemController = fxmlLoader.getController();
                    itemController.setData(cos.get(i));
                    if (column == 3) {
                        column = 0;
                        row++;
                    }
                    grid.add(anchorPane, column++, row); //(child,column,row)
                    //set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);

                    //set grid height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(10));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void load_full_list(MouseEvent event) {
        load();
    }

    @FXML
    private void order_rating(MouseEvent event) {

        grid.getChildren().clear();
        List<Co_Studying> cos = new ArrayList<>();
        cos = cs.consulterOrdered();
        column = 0;
        try {
            for (int i = 0; i < cos.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/GUI/CoStudyingItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                CoStudyingItemController itemController = fxmlLoader.getController();
                itemController.setData(cos.get(i));
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void download_action(MouseEvent event) {
        if (id_item != 0) {
            Co_StudyingService p = new Co_StudyingService();
            Co_Studying co = new Co_Studying();
            co = p.FindCo_Studying(id_item);
            String fileName = co.getFile();
            Helpers.openWebpage(Config.BASE_URL2 + fileName);
        }
    }

    @FXML
    private void back_action(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CoStudyingAdd.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void main_menu_clicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("List_CoStudying.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    private void rate1_action(MouseEvent event) throws FileNotFoundException, SQLException {
        Image coloredStar = new Image(new FileInputStream(path));
        Image emptyStar = new Image(new FileInputStream(path1));
        rate1.setImage(coloredStar);
        rate2.setImage(emptyStar);
        rate3.setImage(emptyStar);
        rate4.setImage(emptyStar);
        rate5.setImage(emptyStar);

        RatingService rs = new RatingService();
        Co_StudyingService cs = new Co_StudyingService();
        Rating r = new Rating(id_item, current_user.getId(), 1);
        rs.addRating(r);
        int avg = rs.getAvgRating(id_item);
        cs.editCostudying(id_item, "rating", avg);
        co = cs.FindCo_Studying(id_item);
        rate.setText(Integer.toString(co.getRating()));
        load();
    }

    @FXML
    private void rate2_action(MouseEvent event) throws FileNotFoundException, SQLException {
        Image coloredStar = new Image(new FileInputStream(path));
        Image emptyStar = new Image(new FileInputStream(path1));
        rate1.setImage(coloredStar);
        rate2.setImage(coloredStar);
        rate3.setImage(emptyStar);
        rate4.setImage(emptyStar);
        rate5.setImage(emptyStar);

        RatingService rs = new RatingService();
        Co_StudyingService cs = new Co_StudyingService();
        Rating r = new Rating(id_item, current_user.getId(), 2);
        rs.addRating(r);
        int avg = rs.getAvgRating(id_item);
        cs.editCostudying(id_item, "rating", avg);
        co = cs.FindCo_Studying(id_item);
        rate.setText(Integer.toString(co.getRating()));
        load();

    }

    @FXML
    private void rate3_action(MouseEvent event) throws FileNotFoundException, SQLException {
        Image coloredStar = new Image(new FileInputStream(path));
        Image emptyStar = new Image(new FileInputStream(path1));
        rate1.setImage(coloredStar);
        rate2.setImage(coloredStar);
        rate3.setImage(coloredStar);
        rate4.setImage(emptyStar);
        rate5.setImage(emptyStar);

        RatingService rs = new RatingService();
        Co_StudyingService cs = new Co_StudyingService();
        Rating r = new Rating(id_item, current_user.getId(), 3);
        rs.addRating(r);
        int avg = rs.getAvgRating(id_item);
        cs.editCostudying(id_item, "rating", avg);
        co = cs.FindCo_Studying(id_item);
        rate.setText(Integer.toString(co.getRating()));
        load();
    }

    @FXML
    private void rate4_action(MouseEvent event) throws FileNotFoundException, SQLException {

        Image coloredStar = new Image(new FileInputStream(path));
        Image emptyStar = new Image(new FileInputStream(path1));
        rate1.setImage(coloredStar);
        rate2.setImage(coloredStar);
        rate3.setImage(coloredStar);
        rate4.setImage(coloredStar);
        rate5.setImage(emptyStar);

        RatingService rs = new RatingService();
        Co_StudyingService cs = new Co_StudyingService();
        Rating r = new Rating(id_item, current_user.getId(), 4);
        rs.addRating(r);
        int avg = rs.getAvgRating(id_item);
        cs.editCostudying(id_item, "rating", avg);
        co = cs.FindCo_Studying(id_item);
        rate.setText(Integer.toString(co.getRating()));
        load();
    }

    @FXML
    private void rate5_action(MouseEvent event) throws FileNotFoundException, SQLException {
        Image coloredStar = new Image(new FileInputStream(path));
        Image emptyStar = new Image(new FileInputStream(path1));
        rate1.setImage(coloredStar);
        rate2.setImage(coloredStar);
        rate3.setImage(coloredStar);
        rate4.setImage(coloredStar);
        rate5.setImage(coloredStar);

        RatingService rs = new RatingService();
        Co_StudyingService cs = new Co_StudyingService();
        Rating r = new Rating(id_item, current_user.getId(), 5);
        rs.addRating(r);
        int avg = rs.getAvgRating(id_item);
        cs.editCostudying(id_item, "rating", avg);
        co = cs.FindCo_Studying(id_item);
        rate.setText(Integer.toString(co.getRating()));
        load();

    }

    @FXML
    private void off_hover1(MouseEvent event) throws FileNotFoundException {
        Image emptyStar = new Image(new FileInputStream(path1));
        rate1.setImage(emptyStar);
        int defaultRate = rs.TakeUserRating(current_user.getId(), id_item);
        defaultRating(defaultRate);

    }

    @FXML
    private void on_hover1(MouseEvent event) throws FileNotFoundException {
        Image emptyStar = new Image(new FileInputStream(path1));
        rate1.setImage(emptyStar);
        rate2.setImage(emptyStar);
        rate3.setImage(emptyStar);
        rate4.setImage(emptyStar);
        rate5.setImage(emptyStar);
        Image coloredStar = new Image(new FileInputStream(path));
        rate1.setImage(coloredStar);

        on_hover_mouse_hand();

    }

    @FXML
    private void off_hover2(MouseEvent event) throws FileNotFoundException {
        Image emptyStar = new Image(new FileInputStream(path1));
        rate1.setImage(emptyStar);
        rate2.setImage(emptyStar);
        int defaultRate = rs.TakeUserRating(current_user.getId(), id_item);
        defaultRating(defaultRate);
    }

    @FXML
    private void on_hover2(MouseEvent event) throws FileNotFoundException {
        Image emptyStar = new Image(new FileInputStream(path1));
        rate1.setImage(emptyStar);
        rate2.setImage(emptyStar);
        rate3.setImage(emptyStar);
        rate4.setImage(emptyStar);
        rate5.setImage(emptyStar);
        Image coloredStar = new Image(new FileInputStream(path));
        rate1.setImage(coloredStar);
        rate2.setImage(coloredStar);

        on_hover_mouse_hand();
    }

    @FXML
    private void off_hover3(MouseEvent event) throws FileNotFoundException {
        Image emptyStar = new Image(new FileInputStream(path1));
        rate1.setImage(emptyStar);
        rate2.setImage(emptyStar);
        rate3.setImage(emptyStar);
        int defaultRate = rs.TakeUserRating(current_user.getId(), id_item);
        defaultRating(defaultRate);
    }

    @FXML
    private void on_hover3(MouseEvent event) throws FileNotFoundException {
        Image emptyStar = new Image(new FileInputStream(path1));
        rate1.setImage(emptyStar);
        rate2.setImage(emptyStar);
        rate3.setImage(emptyStar);
        rate4.setImage(emptyStar);
        rate5.setImage(emptyStar);
        Image coloredStar = new Image(new FileInputStream(path));
        rate1.setImage(coloredStar);
        rate2.setImage(coloredStar);
        rate3.setImage(coloredStar);

        on_hover_mouse_hand();
    }

    @FXML
    private void off_hover4(MouseEvent event) throws FileNotFoundException {
        Image emptyStar = new Image(new FileInputStream(path1));
        rate1.setImage(emptyStar);
        rate2.setImage(emptyStar);
        rate3.setImage(emptyStar);
        rate4.setImage(emptyStar);
        int defaultRate = rs.TakeUserRating(current_user.getId(), id_item);
        defaultRating(defaultRate);
    }

    @FXML
    private void on_hover4(MouseEvent event) throws FileNotFoundException {
        Image emptyStar = new Image(new FileInputStream(path1));
        rate1.setImage(emptyStar);
        rate2.setImage(emptyStar);
        rate3.setImage(emptyStar);
        rate4.setImage(emptyStar);
        rate5.setImage(emptyStar);
        Image coloredStar = new Image(new FileInputStream(path));
        rate1.setImage(coloredStar);
        rate2.setImage(coloredStar);
        rate3.setImage(coloredStar);
        rate4.setImage(coloredStar);

        on_hover_mouse_hand();
    }

    @FXML
    private void off_hover5(MouseEvent event) throws FileNotFoundException {
        Image emptyStar = new Image(new FileInputStream(path1));
        rate1.setImage(emptyStar);
        rate2.setImage(emptyStar);
        rate3.setImage(emptyStar);
        rate4.setImage(emptyStar);
        rate5.setImage(emptyStar);
        int defaultRate = rs.TakeUserRating(current_user.getId(), id_item);
        defaultRating(defaultRate);
    }

    @FXML
    private void on_hover5(MouseEvent event) throws FileNotFoundException {
        Image emptyStar = new Image(new FileInputStream(path1));
        rate1.setImage(emptyStar);
        rate2.setImage(emptyStar);
        rate3.setImage(emptyStar);
        rate4.setImage(emptyStar);
        rate5.setImage(emptyStar);
        Image coloredStar = new Image(new FileInputStream(path));
        rate1.setImage(coloredStar);
        rate2.setImage(coloredStar);
        rate3.setImage(coloredStar);
        rate4.setImage(coloredStar);
        rate5.setImage(coloredStar);

        on_hover_mouse_hand();
    }

    private void on_hover_mouse_hand() {
        rate1.setCursor(Cursor.HAND);
        rate2.setCursor(Cursor.HAND);
        rate3.setCursor(Cursor.HAND);
        rate4.setCursor(Cursor.HAND);
        rate5.setCursor(Cursor.HAND);
    }

    @FXML
    private void type_hover(MouseEvent event) {
        toCompare.setCursor(Cursor.TEXT);
    }

    @FXML
    private void search_hover(MouseEvent event) {
        search_btn.setCursor(Cursor.HAND);
        search_btn.setEffect(shadow);
    }
    
    @FXML
    private void search_offhover(MouseEvent event) {
        search_btn.setEffect(null);
    }

    @FXML
    private void download_hover(MouseEvent event) {
        download_btn.setCursor(Cursor.HAND);
        download_btn.setEffect(shadow);
    }
     @FXML
    private void download_offhover(MouseEvent event) {
        download_btn.setEffect(null);
    }

    @FXML
    private void add_hover(MouseEvent event) {
        add_btn.setCursor(Cursor.HAND);
        add_btn.setEffect(shadow);
    }
    
    @FXML
    private void add_offhover(MouseEvent event) {
        add_btn.setEffect(null);
    }

    @FXML
    private void mm_hover(MouseEvent event) {
        mainmenu_txt.setCursor(Cursor.HAND);
        mainmenu_icon.setCursor(Cursor.HAND);
    }

    @FXML
    private void load_list_hover(MouseEvent event) {
        load_btn.setCursor(Cursor.HAND);
    }

    @FXML
    private void rate_hover(MouseEvent event) {
        order_btn.setCursor(Cursor.HAND);
    }


   


}
