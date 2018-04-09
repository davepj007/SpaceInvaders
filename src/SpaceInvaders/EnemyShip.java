package SpaceInvaders;

import javafx.scene.image.Image;
/**
 *
 * @author david
 */

public class EnemyShip extends Ship {
    private int shipPos;
    private boolean isBoss;
    private int shootsRequired;
    private int shootsReceived;
    
    public EnemyShip(String logo, double coordX, double coordY, int shipPos){
        this.logo = new Image (logo);
        this.coordX = coordX;
        this.coordY = coordY;
        this.shipPos = shipPos;
        this.isBoss = false;
        this.shootsRequired = 0;
        this.shootsReceived = 0;
    }
    
    public int getShipPos() {
        return shipPos;
    }

    public void setShipPos(int shipPos) {
        this.shipPos = shipPos;
    }

    public boolean getIsBoss() {
        return isBoss;
    }

    public void setIsBoss(boolean isBoss) {
        this.isBoss = isBoss;
    }

    public int getShootsReceived() {
        return shootsReceived;
    }

    public void setShootsReceived(int shootsReceived) {
        this.shootsReceived = shootsReceived;
    }
    
    public int getShootsRequired(){
        return shootsRequired;
    }
    
    public void setShootsRequired(){
        this.shootsRequired = 2 + (int)(Math.random() * ((5-2)+1));
        System.out.println(this.shootsRequired);
    }
}
