package SpaceInvaders;

import LinkedLists.CircularLinkedList;
import LinkedLists.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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
        this.boss = null;
        this.enemyXSpeed = 2;
        this.enemyYSpeed = 0.5;
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
            Node current = enemyRow.getFlag();
            for (int i = 1; i <= enemyRow.getSize(); i++) {
                current.getData().setShootsRequired();
                current = current.getNext();
            }
        }
        
        bubbleSort();
        
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
    /**
     * Método encargado de realizar el ordenamiento de las naves según su nivel
     * de resistencia, el cual en este caso corresponde a la cantidad de disparos
     * que puede recibir
     */
    public void bubbleSort(){
        int temp;
        for (int i = 1; i <= enemyRow.getSize(); i++) {
            for (int j = 2; j < enemyRow.getSize(); j++) {
                EnemyShip dataA = enemyRow.getData(j-1);
                EnemyShip dataB = enemyRow.getData(j);
                if(dataA.getShootsRequired() < dataB.getShootsRequired()){
                    temp = dataA.getShootsRequired();
                    dataA.setShootsRequired(dataB.getShootsRequired());
                    dataB.setShootsRequired(temp);
                }
            }
        }
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
            bubbleSort();
        }else{
            enemyRow.deleteNode(this.boss);
        }
    }
}
