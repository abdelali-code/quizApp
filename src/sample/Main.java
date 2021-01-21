package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
    static Stage window;

    @Override
    public void start(Stage primaryStage) {
        try {
            window = primaryStage;
            Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
            //Parent quiz = FXMLLoader.load(getClass().getResource("Quiz.fxml"));
            Scene scene = new Scene(root, 950, 900);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
