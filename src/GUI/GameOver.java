/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author david
 */
public class GameOver extends Application {

    @Override
    public void start(Stage GOStage) {
        
        GOStage.setTitle("Game Over");
        GOStage.setWidth(500);
        GOStage.setHeight(300);
        GOStage.setResizable(false);
        GOStage.centerOnScreen();
        
        Group root = new Group();
        Scene theScene = new Scene(root);
        theScene.setFill(Color.BLACK);
        GOStage.setScene(theScene);                
        
        Label label = new Label("GAME OVER!");
        label.setFont(Font.font("Agency FB", 75));
        label.setTextFill(Color.RED);
        label.setTranslateX(110);
        label.setTranslateY(45);
        root.getChildren().add(label);
        
        Label label2 = new Label("Score: " + 1500 + " Reached Level: " + 4);
        label2.setFont(Font.font("Agency FB", 25));
        label2.setTextFill(Color.WHITE);
        label2.setTranslateX(130);
        label2.setTranslateY(130);
        root.getChildren().add(label2);
        
        Button button = new Button("Try Again!");
        button.setFont(Font.font("Agency FB", 20));
        button.setMaxWidth(100);
        button.setMaxHeight(20);
        button.setTranslateX(195);
        button.setTranslateY(175);
        root.getChildren().add(button);
        
        button.setOnAction(e -> {
            GUI gui = new GUI();
            try {
                gui.start(new Stage());
                GOStage.close();
            } catch (Exception ex) {
            }
        });
       
        GOStage.show();
    }
    
    public void stopGame(Stage theStage){
        theStage.close();
    }
}
