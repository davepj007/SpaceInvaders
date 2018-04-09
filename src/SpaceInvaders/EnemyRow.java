/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpaceInvaders;

import LinkedLists.LinkedList;
import LinkedLists.Node;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author david
 */
public abstract class EnemyRow {
    protected LinkedList enemyRow;
    protected EnemyShip boss;
    protected double enemyXSpeed;
    protected double enemyYSpeed;
    protected boolean bool;
    
    public abstract void createEnemyRow(GraphicsContext gc);
    
    public void animateEnemyRow(EnemyShip enemy, GraphicsContext gc){
        double posIni = enemy.getCoordX();
        AnimationTimer animator = new AnimationTimer(){
            @Override
            public void handle(long arg0){ 
                gc.clearRect(enemy.getCoordX(), enemy.getCoordY(), 900, 700);
                
                enemy.setCoordX(enemy.getCoordX() + enemyXSpeed);
                enemy.setCoordY(enemy.getCoordY() + enemyYSpeed);

                if(enemy.getCoordX()  > (posIni+75)){
                    enemy.setCoordX((posIni+75)-enemyXSpeed);
                    enemyXSpeed *= -1;
                }
                else if(enemy.getCoordX() < posIni){
                    enemy.setCoordX(posIni);
                    enemyXSpeed *= -1;
                }
                else if(enemy.getCoordY() == (525)){
                    stop();
                }      
                
                enemy.render(gc);
            }
        };
        animator.start();
    }
    
    public void setEnemyRow() {
        int enemyCoordX = 10;
        int enemyCoordY = 50;
        int size = 4 + (int)(Math.random() * ((6-4)+1));//Obtiene un num random entre 4 y 6 incluyendo 6     
        for(int i = 1; i <= size; i++){
            EnemyShip enemy = new EnemyShip("/GUI/enemie.png", enemyCoordX, enemyCoordY, i);
            enemyRow.insertEnd(enemy);
            enemyCoordX += 900/size;
        }
    }
    
    public EnemyShip chooseBoss(LinkedList enemyRow){
        int shipPos = 1 + (int)(Math.random() * ((enemyRow.getSize()-1)+1));
        Node current = enemyRow.getFlag();
        while(current != null){
            if(current.getData().getShipPos() == shipPos){
                current.getData().setIsBoss(true);
                current.getData().setLogo(new Image("/GUI/boss.png"));
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }
    
    public LinkedList getEnemyRow() {
        return enemyRow;
    }
    
    public void setBoss(EnemyShip boss){
        this.boss = boss;
    }
    
    public EnemyShip getBoss(){
        return boss;
    }
    
    public double getEnemyXSpeed() {
        return enemyXSpeed;
    }

    public void setEnemyXSpeed(double enemyXSpeed) {
        this.enemyXSpeed = enemyXSpeed;
    }

    public double getEnemyYSpeed() {
        return enemyYSpeed;
    }

    public void setEnemyYSpeed(double enemyYSpeed) {
        this.enemyYSpeed = enemyYSpeed;
    }
    
    public boolean getBool(){
        return bool;
    }
    
    public void setBool(boolean bool){
        this.bool = bool;
    }

}
