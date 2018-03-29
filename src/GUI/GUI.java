package GUI;

import LinkedLists.SimpleLinkedList;
import SpaceInvaders.DefenderShip;
import SpaceInvaders.EnemyShip;

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
    
    double enemyCoordX = 5;
    double enemyCoordY = 50;
    double enemyXSpeed = 0.25;
    double enemyYSpeed = 5;
    
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
        root.getChildren().add(bg);
        
        int um = 4 + (int)(Math.random() * ((8-4)+1));//Obtiene un numero al azar entre 4 y 8 incluyendo el 8
        SimpleLinkedList row = new SimpleLinkedList();
        for(int i = 1; i <= um; i++){
            EnemyShip enemy = new EnemyShip("/GUI/enemie.png", enemyCoordX, enemyCoordY, i, false);
            row.insertEnd(enemy);
            enemyCoordX += WIDTH/um;
        }
        
        int count = 1;
        while(count <= row.getSize()){
            EnemyShip enemy = row.getData(count);
            ImageView ene = new ImageView(enemy.getLogo());
            ene.setX(enemy.getCoordX());
            ene.setY(enemy.getCoordY());
            ene.setFitHeight(iconSize);
            ene.setFitWidth(iconSize);
            root.getChildren().add(ene);
            count++;
            animateEnemyBattleShip(ene, um);
        }
        
        DefenderShip defender = new DefenderShip("/GUI/defender.png", defCoordX, defCoordY);
        ImageView def = new ImageView(defender.getLogo());
        def.setX(defCoordX);
        def.setY(defCoordY);
        def.setFitHeight(iconSize);
        def.setFitWidth(iconSize);
        
        controlDefenderShip(theScene, def);
        root.getChildren().add(def);
        
        theStage.setScene(theScene);
        theStage.show();
    }
    
    private void animateEnemyBattleShip(ImageView iv, int um){
        AnimationTimer animator = new AnimationTimer(){
            @Override
            public void handle(long arg0){
                enemyCoordX += enemyXSpeed;
                if(enemyCoordX  >= WIDTH/(2*um)){
                    enemyCoordX = WIDTH/(2*um) - enemyXSpeed;
                    enemyCoordY += enemyYSpeed;
                    enemyXSpeed *= -1;
                }
                else if(enemyCoordX < 0){
                    enemyCoordX = 0;
                    enemyCoordY += enemyYSpeed;
                    enemyXSpeed *= -1;
                }
                else if(enemyCoordY == (HEIGHT - iconSize*2)){
                    stop();
                }
                iv.setTranslateX(enemyCoordX);
                iv.setTranslateY(enemyCoordY);
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
