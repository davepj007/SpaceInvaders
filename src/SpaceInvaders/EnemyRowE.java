package SpaceInvaders;

import LinkedLists.CircularDoublyLinkedList;
import LinkedLists.Node;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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
        this.boss = null;
        this.enemyXSpeed = 1.5;
        this.enemyYSpeed = 0.3;
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
            int centro = (this.enemyRow.getSize()/2) + 1;
            EnemyShip theBoss = this.enemyRow.getData(centro);
            theBoss.setIsBoss(true);
            theBoss.setLogo(new Image("GUI/boss.png"));
            this.setBoss(theBoss);
            this.getBoss().setShootsRequired();
        }
        
        Node current = enemyRow.getFlag();
        for(int i = 1; i <= enemyRow.getSize(); i++){
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
    public void setEnemyRow() {
        int size = 5;
        int enemyCoordX = 220;
        int enemyCoordY = 175;
        for(int i = 1; i <= size; i++){
            EnemyShip enemy = new EnemyShip("/GUI/enemie.png", enemyCoordX, enemyCoordY, i);
            enemyRow.insertEnd(enemy);
            enemyCoordX += 90;
        }
    }
    
    @Override
    public void animateEnemyRow(EnemyShip enemy, GraphicsContext gc){
        double distance = Math.abs(boss.getShipPos()-enemy.getShipPos());
        double radSqrt = Math.pow(distance*90, 2);
        AnimationTimer animator = new AnimationTimer(){
            double speed = distance;
            long lastUpdate = 0;
            @Override
            public void handle(long arg0){
                gc.clearRect(enemy.getCoordX(), enemy.getCoordY(), 70, 75);
                
                if(enemy.getIsBoss()){
                    enemy.setCoordY(enemy.getCoordY()+enemyYSpeed);
                }
                
                enemy.setCoordX(enemy.getCoordX() + speed);

                if(enemy.getCoordX()  >= (boss.getCoordX() + distance*90)){
                    enemy.setCoordX((boss.getCoordX() + distance*90));
                    speed *= -1;
                }
                else if(enemy.getCoordX() <= (boss.getCoordX() - distance*90)){
                    enemy.setCoordX((boss.getCoordX() - distance*90));
                    speed *= -1;
                }

                if(speed > 0){
                    enemy.setCoordY(-Math.sqrt(radSqrt-Math.pow((enemy.getCoordX()-boss.getCoordX()), 2))+boss.getCoordY());
                }else{
                    enemy.setCoordY(Math.sqrt(radSqrt-Math.pow((enemy.getCoordX()-boss.getCoordX()), 2))+boss.getCoordY());
                }

                if(enemy.getCoordY() >= (540)){
                    setEndOfGame(true);
                    stop();
                }
                
                if(enemy.getLogo() == null){
                    stop();
                }
                
                enemy.render(gc);
            }
        };
        animator.start();
    }
}
