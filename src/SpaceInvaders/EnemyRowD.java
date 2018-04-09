package SpaceInvaders;

import LinkedLists.CircularLinkedList;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author david
 */

public class EnemyRowD extends EnemyRow {
    
    public EnemyRowD(){
        this.enemyRow = new CircularLinkedList();
    }
    
    @Override
    public void createEnemyRow(GraphicsContext gc){
    
    }
}
