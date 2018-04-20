package SpaceInvaders;

import LinkedLists.CircularLinkedList;
import LinkedLists.LinkedList;
import LinkedLists.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * EnemyRowC : Hilera enemiga clase C
 * 
 * @author david
 */

public class EnemyRowC extends EnemyRow {
    
    /**
     * Constructor
     * EnemyRowC
     */
    public EnemyRowC(){
        this.enemyRow = new CircularLinkedList();
        this.boss = null;
        this.enemyXSpeed = 2;
        this.enemyYSpeed = 0.6;
    }
    
    @Override
    public void createEnemyRow(GraphicsContext gc){
        if(enemyRow.getFlag() == null){
            this.setEnemyRow();
            this.setEnemyYSpeed(this.getEnemyYSpeed() + 0.05);
            enemyRow = this.getEnemyRow();
            bool = true;
        }
        
        if(bool == true){
            this.setBoss(this.chooseBoss(this.enemyRow));
            this.getBoss().setShootsRequired();
        }
        
        Node current = enemyRow.getFlag().getNext();
        while(current != enemyRow.getFlag()){
            EnemyShip enemy = current.getData();
            enemy.render(gc);
            if(bool == true){
                this.animateEnemyRow(enemy, gc);
            }
            current = current.getNext();
        }
        EnemyShip enemy = current.getData();
        enemy.render(gc);
        if(bool == true){
            this.animateEnemyRow(enemy, gc);
        }
        this.bool = false;
    }
    
    @Override
    public EnemyShip chooseBoss(LinkedList enemyRow){
        int shipPos = 1 + (int)(Math.random() * ((enemyRow.getSize()-1)+1));
        Node current = enemyRow.getFlag();
        do{
            if(current.getData().getShipPos() == shipPos){
                current.getData().setIsBoss(true);
                current.getData().setLogo(new Image("/GUI/boss.png"));
                return current.getData();
            }
            current = current.getNext();
        }while(current != enemyRow.getFlag());
        return current.getData();
    }
    
    @Override
    public void executeBossDestroy(){    
        if(enemyRow.getSize() > 1){
            EnemyShip oldBoss = this.boss;
            Image logoBoss = this.boss.getLogo();
            Node newBoss = enemyRow.chooseRandomNode();
            
            oldBoss.setLogo(null);
            enemyRow.deleteNode(oldBoss);

            this.setBoss(newBoss.getData());
            newBoss.getData().setShootsRequired();
            newBoss.getData().setIsBoss(true);
            newBoss.getData().setLogo(logoBoss);
        }else{
            enemyRow.deleteNode(this.boss);
        }
    }
}
