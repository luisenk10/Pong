package es.luisenriquechacon.pong2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        
        StackPane root = new StackPane();
        var scene = new Scene (new StackPane (root), 640, 480);
        stage.setScene(scene);
        stage.show();
        
        Circle circleBall = new Circle();
        circleBall.setCenterX(10);
        circleBall.setCenterX(30);
        circleBall.setRadius(7);
        
        root.getChildren().add(circleBall);
       
 
    }

    public static void main(String[] args) {
        launch();
    }

}