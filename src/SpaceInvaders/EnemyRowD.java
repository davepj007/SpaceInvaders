package SpaceInvaders;

import LinkedLists.CircularLinkedList;
import javafx.scene.canvas.GraphicsContext;

/**
 * EnemyRowD : Hilera enemiga clase D
 * 
 * @author david
 */

public class EnemyRowD extends EnemyRow {
    
    /**
     * Constructor
     * EnemyRowD
     */
    public EnemyRowD(){
        this.enemyRow = new CircularLinkedList();
    }
    
    @Override
    public void createEnemyRow(GraphicsContext gc){
    
    }
}
