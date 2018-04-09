package SpaceInvaders;

import LinkedLists.DoublyLinkedList;
import LinkedLists.Node;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author david
 */

public class EnemyRowB extends EnemyRow{
    
    public EnemyRowB(){
        this.enemyRow = new DoublyLinkedList();
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
        Node current = enemyRow.getFlag();
        while(current != null){
            EnemyShip enemy = current.getData();
            enemy.render(gc);
            if(bool == true){
                this.animateEnemyRow(enemy, gc);
            }
            current = current.getNext();
        }
        if(bool == true){
            this.setBoss(this.chooseBoss(enemyRow));
            this.getBoss().setShootsRequired();
        }
        this.bool = false;
    }
}
