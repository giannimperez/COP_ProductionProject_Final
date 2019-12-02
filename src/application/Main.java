package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        primaryStage.setTitle("Production App");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        Scene scene = new Scene(new Group(), 800, 600);
        scene.getStylesheets().add("Sample.css");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
