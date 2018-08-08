package controllers;

import Exceptions.FileImportedException;
import Exceptions.ImportFileErrorsContainer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.PaymentService;
import spring.configuration.ApplicationContextSingleton;

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
    @FXML
    private VBox fileImportedView;
    @FXML
    private VBox paymentsView;

    public static void main(String[] args) {
        launch(args);
    }

    private void hoverAll() {
        welcomeText.setVisible(false);
        companyListView.setVisible(false);
        addCompanyView.setVisible(false);
        fileImportedView.setVisible(false);
        paymentsView.setVisible(false);
        companyListView.setMinHeight(0);
        fileImportedView.setMinHeight(0);
        paymentsView.setMinHeight(0);
    }

    @FXML
    public void addCompany(ActionEvent e) throws IOException {
        hoverAll();
        addCompanyView.setVisible(true);
    }

    @FXML
    public void viewCompanyList(ActionEvent actionEvent) {
        hoverAll();
        companyListView.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.50));
        companyListView.setMinHeight(500);
        companyListView.setVisible(true);
    }

    @FXML
    public void viewImportedFileList(ActionEvent actionEvent) {
        hoverAll();
        fileImportedView.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.50));
        fileImportedView.setMinHeight(500);
        fileImportedView.setVisible(true);
    }

    @FXML
    public void viewPaymentsList(ActionEvent actionEvent) {
        hoverAll();
        paymentsView.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.50));
        paymentsView.setMinHeight(750);
        paymentsView.setVisible(true);
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

        if (selectedFile != null) {
            PaymentService paymentService = ApplicationContextSingleton.INSTANCE.getInstance().getBean(PaymentService.class);
            try {
                ImportFileErrorsContainer container = paymentService.importDataFile(selectedFile);
                if (container.hasErrors()) {
                    showErrorDialogView(container);
                } else {
                    showCorrectDialogView();
                }

            } catch (FileImportedException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showCorrectDialogView() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Poprawnie zaczytano plik!");
        alert.setHeaderText("Plik zaczytany do bazy");
        alert.show();
    }

    private void showErrorDialogView(ImportFileErrorsContainer container) {
        StringBuilder strB = new StringBuilder();
        container.getMessages().forEach(message -> strB.append(message.getMessage()).append("\n\n").append(message.getRow()).append("\n\n\n"));
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errors while parsing file!");
        alert.setHeaderText("Information Alert");
        alert.setContentText(strB.toString());
        alert.show();
    }

}