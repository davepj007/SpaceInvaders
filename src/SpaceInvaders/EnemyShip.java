package SpaceInvaders;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 *
 * @author david
 */

public class EnemyShip extends Ship {
    private int shipPos;
    private boolean boss;
    private int shootsReceived;
    
    public EnemyShip(String logo, double coordX, double coordY, int shipPos, boolean boss){
        this.logo = new Image (logo);
        this.coordX = coordX;
        this.coordY = coordY;
        this.shipPos = shipPos;
        this.boss = boss;
        this.shootsReceived = 0;
    }
    
    public int getShipPos() {
        return shipPos;
    }

    public void setShipPos(int shipPos) {
        this.shipPos = shipPos;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public int getShootsReceived() {
        return shootsReceived;
    }

    public void setShootsReceived(int shootsReceived) {
        this.shootsReceived = shootsReceived;
    }
    
    public Rectangle2D getBounds(){
        return new Rectangle2D(coordX, coordY, 70, 75);
    }
    
    public void render(GraphicsContext gc){
        gc.drawImage(this.logo, coordX, coordY, 75, 75);
    }
}
