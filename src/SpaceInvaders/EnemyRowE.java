package SpaceInvaders;

import LinkedLists.CircularDoublyLinkedList;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author david
 */

public class EnemyRowE extends EnemyRow {
    
    public EnemyRowE(){
        this.enemyRow = new CircularDoublyLinkedList();
    }
    
    @Override
    public void createEnemyRow(GraphicsContext gc){
    
    }
}
