package es.luisenriquechacon.pong2;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        
        final short SCENE_HEIGHT = 480;
        final short SCENE_WIDHT = 640;
        
 //       StackPane root = new StackPane();
        Pane root = new Pane();
        var scene = new Scene(new StackPane (root), SCENE_WIDHT, SCENE_HEIGHT);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
        
        Circle circleBall = new Circle();
        circleBall.setCenterX(10);
        circleBall.setCenterY(30);
        circleBall.setRadius(7);
        circleBall.setFill(Color.WHITE);
        //Circle circleBall = new Circle(10, 30, 7);
        
        root.getChildren().add(circleBall);
        short rectHeight = 50;
        
        Rectangle rectStick = new Rectangle();
        rectStick.setWidth(10);
        rectStick.setHeight(rectHeight);
        rectStick.setX(SCENE_WIDHT - 40);
        rectStick.setY((SCENE_HEIGHT-rectHeight)/2);
        rectStick.setFill(Color.WHITE);
        
        root.getChildren().add(rectStick);
        
        scene.setOnKeyPressed((final KeyEvent KeyEvent) -> {
            public void handle(final KeyEvent KeyEvent)
            switch(keyEvent.getCode()) {
                case UP:
                    
                
            }
        }
        
        
    }
    }

    public static void main(String[] args) {
        launch();
    }

}