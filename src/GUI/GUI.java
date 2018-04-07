package GUI;

import LinkedLists.CircularDoublyLinkedList;
import LinkedLists.CircularLinkedList;
import LinkedLists.DoublyLinkedList;
import LinkedLists.LinkedList;
import LinkedLists.Node;
import LinkedLists.SimpleLinkedList;
import SpaceInvaders.DefenderShip;
import SpaceInvaders.EnemyShip;
import SpaceInvaders.Lasser;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.image.Image;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
/**
 *
 * @author david
 */

public class GUI extends Application {
    
    Scene theScene;
    Group root;
    Canvas canvas = new Canvas(900,700);
    GraphicsContext gc;
    
    LinkedList enemyRow;
    
    double WIDTH = 900;
    double HEIGHT = 700;
    
    double enemyCoordX = 10;
    double enemyCoordY = 50;
    double enemyXSpeed = 2;
    double enemyYSpeed = 0.25;
    
    double defCoordX = 400;
    double defCoordY = 590;
    double lasserSpeed = 10;
    
    double iconSize = 75;
    
    boolean bool;
    int level = 1;

    public static void main (String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage theStage){
        
        theStage.setTitle("SpaceInvaders");
        theStage.setMaxHeight(HEIGHT);
        theStage.setMaxWidth(WIDTH);
        theStage.setResizable(false);
        
        root = new Group();
        theScene = new Scene(root);
        theStage.setScene(theScene);
        
        
        Image background = new Image("/GUI/space.jpg");
        ImageView bg = new ImageView(background);
        bg.setFitWidth(WIDTH);
        bg.setFitHeight(HEIGHT);
        root.getChildren().add(bg);
        
        //********************************* CREA LA NAVE DEFENSORA **********************************//
        DefenderShip defender = new DefenderShip("/GUI/defender.png", defCoordX, defCoordY);
        ImageView def = new ImageView(defender.getLogo());
        def.setX(defCoordX);
        def.setY(defCoordY);
        def.setFitHeight(iconSize);
        def.setFitWidth(iconSize);
        controlDefenderShip(theScene, def);
        //*******************************************************************************************//
        
        root.getChildren().add(def);
               
        //******************************** CREA LAS HILERAS ENEMIGAS ********************************//
        createLinkedList();
        
        createEnemyRow();
        
        //*******************************************************************************************//
        root.getChildren().add(canvas); 
        theStage.show();
    }
    
    public void createLinkedList(){
        int size = 4 + (int)(Math.random() * ((6-4)+1));//Obtiene un num random entre 4 y 6 incluyendo 6
        chooseList();       
        for(int i = 1; i <= size; i++){
            EnemyShip enemy = new EnemyShip("/GUI/enemie.png", enemyCoordX, enemyCoordY, i, false);
            enemyRow.insertEnd(enemy);
            enemyCoordX += WIDTH/size;
        }
        bool = true;
    }
    
    public void chooseList(){
        int list = 1 + (int)(Math.random() * ((3-1)+1)); 
        switch(level){
            case 1:
                enemyRow = new SimpleLinkedList();
                break;
            case 2:
                if (list == 1){
                    enemyRow = new SimpleLinkedList();
                }else{
                    enemyRow = new DoublyLinkedList();
                }
                break;
            case 3:
                switch (list) {
                    case 1:
                        enemyRow = new SimpleLinkedList();
                        break;
                    case 2:
                        enemyRow = new DoublyLinkedList();
                        break;
                    case 3:
                        enemyRow = new CircularLinkedList();
                        break;
                }
                break;
            default:
                switch(list){
                    case 1:
                        enemyRow = new DoublyLinkedList();
                        break;
                    case 2:
                        enemyRow = new CircularLinkedList();
                        break;
                    case 3:
                        enemyRow = new CircularDoublyLinkedList();
                }
        }
    }
    
    private void createEnemyRow(){
        gc = canvas.getGraphicsContext2D();
        Node current = enemyRow.getFlag();
        while(current != null){
            EnemyShip enemy = current.getData();
            enemy.render(gc);
            if(bool == true){
                animateEnemyBattleShip(enemy, gc);
            }
            current = current.getNext();
        }
        bool = false;
        
    }
    
    private void animateEnemyBattleShip(EnemyShip enemy, GraphicsContext gc){
        double posIni = enemy.getCoordX();
        AnimationTimer animator = new AnimationTimer(){
            @Override
            public void handle(long arg0){ 
                gc.clearRect(enemy.getCoordX(), enemy.getCoordY(), 900, 700);
                
                enemy.setCoordX(enemy.getCoordX() + enemyXSpeed);
                enemy.setCoordY(enemy.getCoordY() + enemyYSpeed);

                if(enemy.getCoordX()  >= (posIni+iconSize)){
                    enemy.setCoordX((posIni+iconSize)-enemyXSpeed);
                    enemyXSpeed *= -1;
                }
                else if(enemy.getCoordX() < posIni){
                    enemy.setCoordX(posIni);
                    enemyXSpeed *= -1;
                }
                else if(enemy.getCoordY() == (HEIGHT - iconSize*2)){
                    stop();
                }      
                
                enemy.render(gc);
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
            else if(arg0.getCode() == KeyCode.UP){
                Lasser lasser = new Lasser(defCoordX+iconSize/2, defCoordY - iconSize/2);
                root.getChildren().add(lasser.getLasser());
                animateLasser(enemyRow, lasser);
            }
        });
    }
    
    public void animateLasser(LinkedList enemyRow, Lasser lasser){
        AnimationTimer animator = new AnimationTimer(){
            @Override
            public void handle(long arg0) {
                lasser.setCoordY(lasser.getCoordY() - lasserSpeed);
                lasser.getLasser().setCenterY(lasser.getCoordY());
                if(lasser.getCoordY() <= 0){
                    lasser.deleteLasser();
                    stop();
                }
                else{
                    Node current = enemyRow.getFlag();
                    while(current != null){
                        EnemyShip enemy = current.getData();
                        if(lasser.intersects(enemy)){
                            lasser.deleteLasser();
                            stop();
                            //enemy.setLogo(new Image("/GUI/explosion.gif"));
                            enemy.setLogo(null);
                            enemyRow.deleteNode(enemy);
                            createEnemyRow();
                            System.out.println(enemyRow.getSize());
                            System.out.println("Impacto!");
                        }
                        current = current.getNext();
                    }
                }
            }
        };
        animator.start();
    }
}
