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
        gc.clearRect(0, 0, 900, 700);
        
        if(enemyRow.getFlag() == null){
            this.setEnemyRow();
            this.setEnemyXSpeed(this.getEnemyXSpeed() + 0.10);
            this.setEnemyYSpeed(this.getEnemyYSpeed() + 0.05);
            enemyRow = this.getEnemyRow();
            bool = true;
        }
        
        if(bool == true){
            this.setBoss(this.chooseBoss(this.enemyRow));
            this.getBoss().setShootsRequired();
        }
        
        Node current = enemyRow.getFlag();
        while(current != null){
            EnemyShip enemy = current.getData();
            enemy.render(gc);
            if(bool == true){
                animateEnemyRow(enemy, gc);
            }
            current = current.getNext();
        }
        
        this.bool = false;
    }
    
    @Override
    public void animateEnemyRow(EnemyShip enemy, GraphicsContext gc){
        double posIni = enemy.getCoordX();
        AnimationTimer animator = new AnimationTimer(){
            private long lastUpdate = 0;
            @Override
            public void handle(long arg0){
                gc.clearRect(enemy.getCoordX(), enemy.getCoordY(), 900, 700);
                    
                if(arg0 - lastUpdate >= (1512*1000000)){
                    if(enemy.getIsBoss()){
                        Node oldBoss = searchBoss();
                        Node newBoss =  enemyRow.chooseRandomNode();
                        enemyRow.changeNodePosition(oldBoss, newBoss);
                    }
                lastUpdate = arg0;
                }
                
                enemy.setCoordX(enemy.getCoordX() + enemyXSpeed);
                enemy.setCoordY(enemy.getCoordY() + enemyYSpeed);

                if(enemy.getCoordX()  > (posIni+85)){
                    enemy.setCoordX((posIni+85)-enemyXSpeed);
                    enemyXSpeed *= -1;
                }
                else if(enemy.getCoordX() < posIni){
                    enemy.setCoordX(posIni);
                    enemyXSpeed *= -1;
                }
                else if(enemy.getCoordY() == 515){
                    stop();
                }
                enemy.render(gc);
                }
            
        };
        animator.start();
    }
    
    public Node searchBoss(){
        Node current = enemyRow.getFlag();
        while(current.getNext() != null){
            if(current.getData().getIsBoss()){
                return current;
            }else{
                current = current.getNext();
            }
        }
        return enemyRow.getFlag();
    }
}
