/*
 * Course: CSC1120A 121
 * Spring 2023
 * Lab 11 - Auto Complete
 * Name: Michael Wood
 * Created: 3/28/2024
 */
package woodm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Driver used to run the program.
 */
public class AutoComplete extends Application {
    public void start(Stage stage) {
        try {
            Parent pane = FXMLLoader.load(getClass().getResource("layout.fxml"));
            stage.setScene(new Scene(pane));
            stage.setTitle("Auto Complete");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.out.println("There was an error reading the FXML file, please try again");
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
