package GUI;

import SpaceInvaders.Ship;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.image.Image;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author david
 */

public class GUI extends Application {
    
    double WIDTH = 900;
    double HEIGHT = 700;
    
    double enemyCoordX = 100;
    double enemyCoordY = 25;
    double enemyXSpeed = 7;
    double enemyYSpeed = 20;
    
    double defCoordX = 400;
    double defCoordY = 590;
    
    double iconSize = 75;
    
    public static void main (String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage theStage){
        
        theStage.setTitle("SpaceInvaders");
        theStage.setMaxHeight(HEIGHT);
        theStage.setMaxWidth(WIDTH);
        theStage.setResizable(false);
         
        Group root = new Group();
        Scene theScene = new Scene(root);
        
        Image background = new Image("/GUI/space.jpg");
        ImageView bg = new ImageView(background);
        bg.setFitWidth(WIDTH);
        bg.setFitHeight(HEIGHT);
        
        int um = 5 + (int)(Math.random() * ((10-5)+1));//Obtiene un numero al azar entre 5 y 10 incluyendo el 10
        Ship enemy = new Ship("/GUI/enemie.png", false, true, false);
        ImageView ene = new ImageView(enemy.getLogo());
        ene.setX(enemyCoordX);
        ene.setY(enemyCoordY);
        ene.setFitHeight(iconSize);
        ene.setFitWidth(iconSize);
        
        animateEnemyBattleShip(ene);
        
        Ship defender = new Ship("/GUI/defender.png", true, false, false);
        ImageView def = new ImageView(defender.getLogo());
        def.setX(defCoordX);
        def.setY(defCoordY);
        def.setFitHeight(iconSize);
        def.setFitWidth(iconSize);
        
        controlDefenderShip(theScene, def);

        root.getChildren().addAll(bg, ene, def);
        
        theStage.setScene(theScene);
        theStage.show();
    }
    
    private void animateEnemyBattleShip(ImageView iv){
        AnimationTimer animator = new AnimationTimer(){
            @Override
            public void handle(long arg0){
                enemyCoordX += enemyXSpeed;
                
                if(enemyCoordX + iconSize >= WIDTH){
                    enemyCoordX = WIDTH - iconSize;
                    enemyCoordY += enemyYSpeed;
                    enemyXSpeed *= -1;
                }
                else if(enemyCoordX  < 0){
                    enemyCoordX = 0;
                    enemyCoordY += enemyYSpeed;
                    enemyXSpeed *= -1;
                }
                else if(enemyCoordY == (HEIGHT - iconSize*2)){
                    stop();
                }
                
                iv.relocate(enemyCoordX, enemyCoordY);
            }
        };
        animator.start();
    }
    
    private void controlDefenderShip(Scene scene, ImageView def){
        scene.setOnKeyPressed((KeyEvent arg0) -> {
            if(arg0.getCode() == KeyCode.RIGHT && defCoordX+iconSize < WIDTH){
                defCoordX += 10;
                def.setX(defCoordX);
            }
            else if(arg0.getCode() == KeyCode.LEFT && defCoordX > 0){
                defCoordX -= 10;
                def.setX(defCoordX);
            }
        });
        
    }
    
}
