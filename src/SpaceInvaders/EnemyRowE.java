package SpaceInvaders;

import LinkedLists.CircularDoublyLinkedList;
import javafx.scene.canvas.GraphicsContext;

/**
 * EnemyRowE : Hilera enemiga clase E
 * 
 * @author david
 */

public class EnemyRowE extends EnemyRow {
    
    /**
     * Consatructor
     * EnemyRowE
     */
    public EnemyRowE(){
        this.enemyRow = new CircularDoublyLinkedList();
    }
    
    @Override
    public void createEnemyRow(GraphicsContext gc){
    
    }
}
