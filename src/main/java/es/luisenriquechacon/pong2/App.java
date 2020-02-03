package es.luisenriquechacon.pong2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {

    final short SCENE_HEIGHT = 480;
    final short SCENE_WIDTH = 640;
    final short TEXT_SIZE = 24;
    
    short ballCenterX = 0;
    byte ballCurrentSpeedX = 3;
    byte ballDirectionX = 1;
    
    short ballCenterY = 0;
    byte ballCurrentSpeedY = 3;
    byte ballDirectionY = 1;
       
    short stickHeight = 50;        
    short stickPosY = (short)((SCENE_HEIGHT-stickHeight)/2);
    byte stickCurrentSpeed = 4;
    byte stickDirection = 0;
    
    // Cuadros de texto para las puntuaciones
    Text textScore;
    Text textHighScore;
    // Puntuación actual
    int score;
    // Puntuación máxima
    int highScore;
    
    @Override
    public void start(Stage stage) {               
//        StackPane root = new StackPane();
        Pane root = new Pane();
        var scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.setFill(Color.BLACK);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        
        // DIBUJO DE LA BOLA        
        // new Circle() => Crear un objeto de la clase Circle
        Circle circleBall = new Circle();
        // Llamando a métodos del objeto circleBall
        circleBall.setCenterX(10);
        circleBall.setCenterY(30);
        circleBall.setRadius(7);  
        circleBall.setFill(Color.WHITE);
        // Se podría hacer también así:        
        //Circle circleBall = new Circle(10, 30, 7, Color.WHITE);        
        root.getChildren().add(circleBall);
        
        // DIBUJO DE LA PALA
        Rectangle rectStick = new Rectangle();
        rectStick.setWidth(10);
        rectStick.setHeight(stickHeight);
        rectStick.setX(SCENE_WIDTH - 40);
        rectStick.setY(stickPosY);
        rectStick.setFill(Color.WHITE);        
        root.getChildren().add(rectStick);

        // DIBUJO DE LA RED
        for(int i=0; i<SCENE_HEIGHT; i+=30) {
            Line line = new Line(SCENE_WIDTH/2, i, SCENE_WIDTH/2, i+10);
            line.setStroke(Color.WHITE);
            line.setStrokeWidth(4);
            root.getChildren().add(line);
        }
        
        // Panel para mostrar textos (puntuaciones)
        HBox paneTextScore = new HBox();
        paneTextScore.setTranslateY(20);
        paneTextScore.setMinWidth(SCENE_WIDTH);
        paneTextScore.setAlignment(Pos.CENTER);
        root.getChildren().add(paneTextScore);

        // Texto de etiqueta para la puntuación
        Text textTitleScore = new Text("Score: ");
        textTitleScore.setFont(Font.font(TEXT_SIZE));
        textTitleScore.setFill(Color.WHITE);
        // Texto para la puntuación
        textScore = new Text("0");
        textScore.setFont(Font.font(TEXT_SIZE));
        textScore.setFill(Color.WHITE);
        // Texto de etiqueta para la puntuación máxima
        Text textTitleMaxScore = new Text("          Max.Score: ");
        textTitleMaxScore.setFont(Font.font(TEXT_SIZE));
        textTitleMaxScore.setFill(Color.WHITE);
        // Texto para la puntuación máxima
        textHighScore = new Text("0");
        textHighScore.setFont(Font.font(TEXT_SIZE));
        textHighScore.setFill(Color.WHITE);

        // Añadir los textos al panel reservado para ellos 
        paneTextScore.setSpacing(10);
        paneTextScore.getChildren().add(textTitleScore);
        paneTextScore.getChildren().add(textScore);
        paneTextScore.getChildren().add(textTitleMaxScore);
        paneTextScore.getChildren().add(textHighScore);
        
        // CONTROL DEL TECLADO
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent keyEvent) {
                switch(keyEvent.getCode()) {
                    case UP:
                        stickDirection = -1;
                        break;
                    case DOWN:
                        stickDirection = 1;
                        break;
                }                
            }
        });
        // Así se haría si se quisiera parar al soltar la tecla
//        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
//            public void handle(final KeyEvent keyEvent) {
//                stickDirection = 0;
//            }
//        });
        
        Timeline timeline = new Timeline(
            // 0.017 ~= 60 FPS
            new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
                public void handle(ActionEvent ae) {
                    // ANIMACIÓN DE LA BOLA
                    circleBall.setCenterX(ballCenterX);
                    circleBall.setCenterY(ballCenterY);
                    ballCenterX += ballCurrentSpeedX * ballDirectionX;
                    ballCenterY += ballCurrentSpeedY * ballDirectionY;
                    // Control de rebote horizontal
                    if(ballCenterX >= SCENE_WIDTH) {
                        if(score > highScore) {
                            highScore = score;
                            textHighScore.setText(String.valueOf(highScore));
                        }
                        score = 0;
                        textScore.setText(String.valueOf(score));
                        ballCenterX = 10;
                        ballDirectionX = 1;
                    } else if(ballCenterX <= 0){
                        ballDirectionX = 1;
                    }
                    // Control de rebote vertical
                    if(ballCenterY >= SCENE_HEIGHT) {
                        ballDirectionY = -1;
                    } else if(ballCenterY <= 0){
                        ballDirectionY = 1;
                    }
                    
                    // ANIMACIÓN DE LA PALA
                    rectStick.setY(stickPosY);
                    stickPosY += stickCurrentSpeed * stickDirection;
                    if(stickPosY <= 0 || stickPosY >= SCENE_HEIGHT-stickHeight) {
                        stickDirection = 0;
                    }
                    if(stickPosY <= 0) {
                        stickDirection = 0;
                        stickPosY = 0;
                    } else if (stickPosY >= SCENE_HEIGHT-stickHeight) {
                        stickDirection = 0;
                        stickPosY = (short)(SCENE_HEIGHT-stickHeight);
                    }
                    
                    // DETECCIÓN DE COLISIÓN DE BOLA Y PALA
                    Shape shapeCollision = Shape.intersect(circleBall, rectStick);
                    boolean colisionVacia = shapeCollision.getBoundsInLocal().isEmpty();
                    if(colisionVacia == false && ballDirectionX == 1) {
                        ballDirectionX = -1;
                        score++;
                        textScore.setText(String.valueOf(score));
                    }
                }
            })                
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();      
    }

    public static void main(String[] args) {
        launch();
    }

}