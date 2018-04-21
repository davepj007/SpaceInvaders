package GUI;

import LinkedLists.LinkedList;
import LinkedLists.Node;
import SpaceInvaders.BasicEnemyRow;
import SpaceInvaders.DefenderShip;
import SpaceInvaders.EnemyRow;
import SpaceInvaders.EnemyRowA;
import SpaceInvaders.EnemyRowB;
import SpaceInvaders.EnemyRowC;
import SpaceInvaders.EnemyRowD;
import SpaceInvaders.EnemyRowE;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

/**
 * GUI: Interfaz Grafica
 * 
 * @author David Pereira Jimenez
 * @version 1.0
 */

public class GUI extends Application {
    Scene theScene;
    Group root;
    Canvas canvas = new Canvas(900,700);
    GraphicsContext gc;
    
    EnemyRow row = null;
    LinkedList enemyRow = null;
    
    double WIDTH = 900;
    double HEIGHT = 725;
  
    double defCoordX = 400;
    double defCoordY = 615;
    double lasserSpeed = 10;
    
    double iconSize = 75;
    
    String currentRow = "";
    String nextRow = "Unknown";
    int countRows = 0;
    int level = 0;
    int score = 0;

    public static void main (String[] args){
        launch(args);
    }
    
    /**
     * Inicializador de la interfaz grafica
     * 
     * @param theStage : La ventana donde se colocarán los objetos gráficos
     */
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
        
        Line line = new Line();
        line.setStartX(0);
        line.setStartY(615);
        line.setEndX(WIDTH);
        line.setEndY(615);
        line.setStroke(Color.DARKCYAN);
        root.getChildren().add(line);
       
        //********************************* CREA LA NAVE DEFENSORA **********************************//
        DefenderShip defender = new DefenderShip("/GUI/defender.png", defCoordX, defCoordY);
        ImageView def = new ImageView(defender.getLogo());
        def.setX(defCoordX);
        def.setY(defCoordY);
        def.setFitHeight(iconSize);
        def.setFitWidth(iconSize);
        controlDefenderShip(theScene, def);
        root.getChildren().add(def);
        
        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0, WIDTH, HEIGHT);
        
        Label label = new Label(""); 
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Agency FB", 20));
        label.setTranslateX(10);
        label.setTranslateY(10);
        
        new AnimationTimer(){
            @Override
            public void handle(long arg0){  
                if(row != null){
                    if(row.isEndOfGame()){
                        stop();
                        GameOver go = new GameOver();
                        go.setLevel(level);
                        go.setScore(score);
                        go.stopGame(theStage);
                        try {
                            go.start(new Stage());
                        }catch (Exception ex) {
                        }
                    }
                }    
                if(enemyRow != null){
                    try{
                        if(enemyRow.getFlag().getData().getCoordY() == 50){
                            countRows += 1;
                        }
                    }catch(NullPointerException ex){
                        countRows += 1;
                    }
                }
                
                if(countRows%4 == 0){
                    level += 1;
                    chooseEnemyRow();
                    if(countRows == 4){
                        countRows = 0;
                    }
                }
                label.setText("Level: " + level + "  |  Score : " + score + 
                        "  |  Current Row: " + currentRow + "  |  Next Row: " + nextRow);                                     
            } 
        }.start();
            
        HBox hBox = new HBox(10);
        hBox.setSpacing(10);
        hBox.getChildren().add(label);
        root.getChildren().add(hBox);
        
        root.getChildren().add(canvas); 
        theStage.show(); 
        
    }
    
    /**
     * Se encarga de escoger la hilera según el nivel en el que se encuentre el juego.
     * Además de llamar a crear la lista correspondiente al tipo de hilera del nivel.
     * Y por último llama al método encargado de crear la hilera enemiga. 
     */
    public void chooseEnemyRow(){
        int list = 1 + (int)(Math.random() * ((3-1)+1)); 
        switch(level){
            case 1:
                row = new BasicEnemyRow();
                currentRow = "Basic";
                break;
            case 2:
                if (list != 3){
                    row = new EnemyRowA();
                    currentRow = "Class A";
                }else{
                    row = new EnemyRowB();
                    currentRow = "Class B";
                }
                break;
            case 3:
                if(list != 3){
                    row = new EnemyRowB();
                    currentRow = "Class B";
                }else{
                    row = new EnemyRowC();
                    currentRow = "Class C";
                }
                break;
            default:
                switch(list){
                    case 1:
                        row = new EnemyRowC();
                        currentRow = "Class C";
                        break;
                    case 2:
                        row = new EnemyRowD();
                        currentRow = "Class D";
                        break;
                    case 3:
                        row = new EnemyRowE();
                        currentRow = "Class E";
                        break;
                }
        }
        row.setEnemyRow();
        enemyRow = row.getEnemyRow();
        row.setBool(true);
        row.createEnemyRow(gc);
    }
    
    /**
     * Método encargado de controlar el movimiento y los disparos de la nave defensora.
     * 
     * @param scene : Escena donde aparecen los diferentes objetos gráficos.
     * @param def : Imagen de la nave defensora que se mostrará.
     */
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
    
    /**
     * Método encargado de animar el laser de la nave defensora.
     * 
     * @param enemyRow : Lista enlazada donde se almacenaran las naves de la hilera
     * @param lasser : Figura del lasser a dibujar
     */
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
                    try{
                        Node current = enemyRow.getFlag();
                        for (int i = 1; i <= enemyRow.getSize(); i++) {
                            EnemyShip enemy = current.getData();
                            if(lasser.intersects(enemy)){
                                lasser.deleteLasser();
                                stop();
                                if(enemy.getIsBoss()){
                                    enemy.setShootsReceived(enemy.getShootsReceived()+1);
                                    if(enemy.getShootsRequired() == enemy.getShootsReceived()){                                        
                                        row.executeBossDestroy();
                                        score += 75;
                                    }
                                }else{
                                    if(enemy.getShootsRequired() != 0){
                                        enemy.setShootsReceived(enemy.getShootsReceived()+1);
                                        if(enemy.getShootsRequired() == enemy.getShootsReceived()){
                                            enemy.setLogo(null);
                                            enemyRow.deleteNode(enemy);
                                            score += 50;
                                        }
                                    }else{
                                        enemy.setLogo(null);
                                        enemyRow.deleteNode(enemy);
                                        score += 25;
                                    }
                                }
                                if((countRows+1)%4 != 0 && enemyRow.getFlag() == null){
                                    System.out.println("Entro");
                                    chooseEnemyRow();
                                }
                            }
                            current = current.getNext();
                        }        
                    }catch(NullPointerException ex){}
                }
            }
        };
        animator.start();
    }
}
