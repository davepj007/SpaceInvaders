package SpaceInvaders;

import LinkedLists.Node;
import LinkedLists.SimpleLinkedList;
import javafx.scene.canvas.GraphicsContext;

/**
 * BasicEnemyRow : Hilera enemiga basica
 * 
 * @author david
 */

public class BasicEnemyRow extends EnemyRow{
    
    /**
     * Constructor
     * BasicEnemyRow
     */
    public BasicEnemyRow(){
        this.enemyRow = new SimpleLinkedList();
        this.enemyXSpeed = 2;
        this.enemyYSpeed = 0.25;
    }
    
    @Override
    public void createEnemyRow(GraphicsContext gc){
        if(enemyRow.getFlag() == null){
            this.setEnemyRow();
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
        this.bool = false;
    }
}
