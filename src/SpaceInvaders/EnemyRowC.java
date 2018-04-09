package SpaceInvaders;

import LinkedLists.CircularLinkedList;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author david
 */

public class EnemyRowC extends EnemyRow {
    
    public EnemyRowC(){
        this.enemyRow = new CircularLinkedList();
    }
    
    @Override
    public void createEnemyRow(GraphicsContext gc){
    
    }
}
