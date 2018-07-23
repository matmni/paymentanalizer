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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class MainController extends Application {


    @FXML
    private GridPane welcomeText;
    @FXML
    private VBox addCompanyView;

    @FXML
    private VBox companyListView;

    @FXML
    private GridPane testGrid;
    @FXML
    private Text actiontarget;
    static private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
    }

    @FXML
    public void deleteCompany(ActionEvent e) throws IOException {
        System.out.println("usuwam");
        testGrid.setVisible(true);
        addCompanyView.setVisible(false);
    }

    @FXML
    public void addCompany(ActionEvent e) throws IOException {
        System.out.println("dodaje");
        testGrid.setVisible(false);
        welcomeText.setVisible(false);
        addCompanyView.setVisible(true);
    }

    @FXML
    public void handleKeyInput(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../views/addCompanyView.fxml"));
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("FXML Welcome2");
        stage.setScene(scene);
//        stage.show();
        welcomeText.setVisible(false);
//        ((Node)(e.getSource())).getParent().getParent().getScene().getWindow().hide();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Group root = new Group();
//        Group circles = new Group();
//        for (int i = 0; i < 30; i++) {
//            Circle circle = new Circle(150, Color.web("white", 0.05));
//            circle.setStrokeType(StrokeType.OUTSIDE);
//            circle.setStroke(Color.web("white", 0.16));
//            circle.setStrokeWidth(4);
//            circles.getChildren().add(circle);
//        }
//        circles.setEffect(new BoxBlur(10, 10, 3));
//
//        Scene scene = new Scene(root, 800, 600, Color.BLACK);
//        primaryStage.setScene(scene);
//        Rectangle colors = new Rectangle(scene.getWidth(), scene.getHeight(),
//                new LinearGradient(0f, 1f, 1f, 0f, true, CycleMethod.NO_CYCLE, new
//                        Stop[]{
//                        new Stop(0, Color.web("#f8bd55")),
//                        new Stop(0.14, Color.web("#c0fe56")),
//                        new Stop(0.28, Color.web("#5dfbc1")),
//                        new Stop(0.43, Color.web("#64c2f8")),
//                        new Stop(0.57, Color.web("#be4af7")),
//                        new Stop(0.71, Color.web("#ed5fc2")),
//                        new Stop(0.85, Color.web("#ef504c")),
//                        new Stop(1, Color.web("#f2660f")),}));
//        colors.widthProperty().bind(scene.widthProperty());
//        colors.heightProperty().bind(scene.heightProperty());
//        root.getChildren().add(colors); root.getChildren().add(circles);
//        primaryStage.show();

        Parent root = FXMLLoader.load(getClass().getResource("../views/mainView.fxml"));
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
// /*
 /*       primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root2 = new StackPane();
        root2.getChildren().add(btn);
        primaryStage.setScene(new Scene(root2, 300, 250));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setId("welcome-text");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn2 = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn2);
        grid.add(hbBtn, 1, 4);


        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                actiontarget.setId("actiontarget");
                actiontarget.setText("Sign in button pressed");
            }
        });


        Scene scene = new Scene(grid, 300, 275);
        scene.getStylesheets().add
                (Main.class.getResource("Test.css").toExternalForm());
        primaryStage.setScene(scene);

        primaryStage.show();*/
    }

    public void viewCompanyList(ActionEvent actionEvent) {
        testGrid.setVisible(false);
        welcomeText.setVisible(false);
        companyListView.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.50));
        companyListView.setMinHeight(500);
        companyListView.setVisible(true);
    }
}