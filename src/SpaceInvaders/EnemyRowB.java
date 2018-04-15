package SpaceInvaders;

import LinkedLists.DoublyLinkedList;
import LinkedLists.Node;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * EnemyRowB : Hilera enemiga clase B
 * 
 * @author david
 */

public class EnemyRowB extends EnemyRow{
    
    /**
     * Constructor
     * EnemyRowB
     */
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
            this.setEnemyYSpeed(this.getEnemyYSpeed() + 0.05);
            enemyRow = this.getEnemyRow();
            bool = true;
        }
        
        if(bool == true){
            if(this.getBoss() != null){
                this.getBoss().setLogo(new Image("GUI/enemie.png"));
                this.getBoss().setIsBoss(false);
            }    
                
            this.setBoss(this.chooseBoss(enemyRow));
            this.getBoss().setShootsRequired();
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
    
    @Override
    public void animateEnemyRow(EnemyShip enemy, GraphicsContext gc){
        double posIni = enemy.getCoordX();
        AnimationTimer animator = new AnimationTimer(){
            private long lastUpdate = 0;
            @Override
            public void handle(long arg0){
                gc.clearRect(enemy.getCoordX(), enemy.getCoordY(), 900, 700);
                    
                if(arg0 - lastUpdate >= (2056*1000000)){
                    if(enemy.getIsBoss()){
                        EnemyShip newBoss =  enemyRow.chooseRandomNode().getData();
                        if(!newBoss.getIsBoss()){
                            changeBoss(enemy, newBoss);
                        }
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
                else if(enemy.getCoordY() >= 515){
                    stop();
                }
                enemy.render(gc);
            }
            
        };
        animator.start();
    }
    
    /**
     * Método encargado de cambiar al jefe de posición con una nave enemiga aleatoria
     * 
     * @param oldBoss : jefe original
     * @param newBoss : nuevo jefe
     */
    public void changeBoss(EnemyShip oldBoss, EnemyShip newBoss){
        if(enemyRow.getSize() > 1){
            int shootsReceived = oldBoss.getShootsReceived();
            int shootsRequired = oldBoss.getShootsRequired();
            Image bossLogo = oldBoss.getLogo();
            Image enemyLogo = newBoss.getLogo();

            oldBoss.setIsBoss(false);
            oldBoss.setLogo(enemyLogo);

            newBoss.setIsBoss(true);
            newBoss.setLogo(bossLogo);
            newBoss.setShootsReceived(shootsReceived);
            newBoss.setShootsRequired(shootsRequired);
            this.setBoss(newBoss);
        }
    }
    
    /**
     * Método encargado de buscar el nodo que contiene la nave jefe de la hilera
     * enemiga
     * 
     * @return Node boss : Nodo que almacena al jefe de la hilera. 
     */
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
