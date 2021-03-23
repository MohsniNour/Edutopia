/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Co_Studying;
import Entities.Rating;
import Services.Co_StudyingService;
import static GUI.CoStudyingItemController.id_item;
import Services.RatingService;
import Utils.Config;
import Utils.Helpers;
import java.io.FileNotFoundException;
import javafx.geometry.Insets;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
    private Button rate_btn;
    @FXML
    private Label owner;
    @FXML
    private ComboBox<String> rating = new ComboBox();
    ObservableList<String> option = FXCollections.observableArrayList(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9"
    );

    Co_StudyingService cs = new Co_StudyingService();
    Co_Studying co = new Co_Studying();
    int column = 0;
    int row = 1;

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
        rating.setItems(option);
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
            }
        } catch (IOException e) {
            e.printStackTrace();
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

            Co_StudyingService p = new Co_StudyingService();
            Co_Studying co = new Co_Studying();
            co = p.FindCo_Studying(id_item);

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
    private void load_full_list(MouseEvent event) {
        load();
    }

    @FXML
    private void rate_action(MouseEvent event) throws SQLException {
        RatingService rs = new RatingService();
        Co_StudyingService cs = new Co_StudyingService();
        int rate = Integer.parseInt(rating.getValue());
        Rating r = new Rating(id_item, Integer.parseInt(rating.getValue()));
        rs.addRating(r);
        int avg = rs.getAvgRating(id_item);
//        Co_Studying co = cs.FindCo_Studying(id_item);
//        int rate2 = co.getRating() + rate;
//        int new_rate = rate2 / 2;
//        cs.editCostudying(id_item, "rating", new_rate);
        cs.editCostudying(id_item, "rating", avg);
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
        Parent root = FXMLLoader.load(getClass().getResource("Forum.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

}
