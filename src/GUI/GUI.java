package GUI;

import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
/**
 *
 * @author david
 */

public class GUI extends Application{
    double WIDTH = 800;
    double HEIGHT = 700;
    double cordX = 300;
    double cordY = 100;
    double xSpeed = 15;
    double ySpeed = 100;
    
    public static void main (String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage theStage){
        
        theStage.setTitle("SpaceInvaders");
        
        Group root = new Group();
        Scene theScene = new Scene(root, WIDTH, HEIGHT);
        
        Image enemie = new Image("/GUI/enemie.png");
        ImageView iv = new ImageView(enemie);
        iv.setX(cordX);
        iv.setY(cordY);
        iv.setFitHeight(75);
        iv.setFitWidth(75);
        
        Image background = new Image("/GUI/space.jpg");
        ImageView bg = new ImageView(background);
        iv.autosize();
        
        root.getChildren().addAll(bg, iv);
        
        theStage.setScene(theScene);
        theStage.show();
        
        AnimationTimer animator = new AnimationTimer(){
            @Override
            public void handle(long arg0){
                cordX += xSpeed;
                
                if(cordX + 75 >= WIDTH){
                    cordX = WIDTH - 75;
                    cordY += ySpeed;
                    xSpeed *= -1;
                }
                else if(cordX  < 0){
                    cordX = 0;
                    cordY += ySpeed;
                    xSpeed *= -1;
                }
                else if(cordY == (HEIGHT - 75)){
                    stop();
                }
                
                iv.relocate(cordX, cordY);
            }
        };
        animator.start();
        
    }
}
