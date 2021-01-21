package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class StartQuiz {
    @FXML private Button goToSecondLev;
    @FXML
    private void startQuiz(ActionEvent event) {

        try {
            System.out.println("hello world !!!!!!!!!!!!");
            Parent root = FXMLLoader.load(getClass().getResource("Quiz.fxml"));
            Scene scene = new Scene(root, 950, 900);
            Main.window.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSecondLevel(ActionEvent actionEvent) {
        actionEvent.consume();
        startQuiz(actionEvent);
    }
}
