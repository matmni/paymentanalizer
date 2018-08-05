package controllers;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainController extends Application {


    static private Stage primaryStage;
    @FXML
    private GridPane welcomeText;
    @FXML
    private VBox addCompanyView;
    @FXML
    private VBox companyListView;

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    public void addCompany(ActionEvent e) throws IOException {
        System.out.println("dodaje");
        welcomeText.setVisible(false);
        companyListView.setVisible(false);
        addCompanyView.setVisible(true);
    }

    @FXML
    public void viewCompanyList(ActionEvent actionEvent) {
        welcomeText.setVisible(false);
        addCompanyView.setVisible(false);
        companyListView.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.50));
        companyListView.setMinHeight(500);
        companyListView.setVisible(true);
    }

    public void start(Stage primaryStage) throws Exception {
        System.out.println(getClass().getClassLoader().getResource(""));
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainView.fxml"));
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        Scene scene = new Scene(root, width, height - 100);
        primaryStage.setTitle("Payments Analizer");
        javafx.scene.control.MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        primaryStage.setScene(scene);
        this.primaryStage = primaryStage;
        primaryStage.show();
    }

    public void importFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if(selectedFile != null) {

        }
    }
}