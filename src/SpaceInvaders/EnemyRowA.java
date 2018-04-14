/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpaceInvaders;

import LinkedLists.Node;
import LinkedLists.SimpleLinkedList;
import javafx.scene.canvas.GraphicsContext;
/**
 * EnemyRowA : Hilera enemiga clase A
 * 
 * @author david
 */
public class EnemyRowA extends EnemyRow {
    
    /**
     * Constructor
     * EnemyRowA
     */
    public EnemyRowA(){
        this.enemyRow = new SimpleLinkedList();
        this.boss = null;
        this.enemyXSpeed = 2;
        this.enemyYSpeed = 0.5;
    }
    
    @Override
    public void createEnemyRow(GraphicsContext gc){
        if(enemyRow.getFlag() == null){
            this.setEnemyRow();
            this.setEnemyXSpeed(this.getEnemyXSpeed() + 0.10);
            this.setEnemyYSpeed(this.getEnemyYSpeed() + 0.05);
            enemyRow = this.getEnemyRow();
            bool = true;
        }
        
        if(bool == true){
            this.setBoss(this.chooseBoss(enemyRow));
            this.getBoss().setShootsRequired();
        }
        
        Node current = enemyRow.getFlag();
        while(current != null){
            EnemyShip enemy = current.getData();
            enemy.render(gc);
            if(bool == true){
                this.animateEnemyRow(enemy, gc);
            }
            current = current.getNext();
        }
        this.bool = false;
    }
}
