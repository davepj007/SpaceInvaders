package SpaceInvaders;

import javafx.scene.image.Image;
/**
 *
 * @author david
 */
public class Ship {
    private Image logo;
    private int shipPos;
    private boolean defender;
    private boolean enemy;
    private boolean boss;
    private int shootsReceived;
    
    public Ship(String logo, boolean defender, boolean enemy, boolean boss){
        this.logo = new Image(logo);
        this.defender = defender;
        this.enemy = enemy;
        this.boss = boss;
        this.shootsReceived = 0;
    }

    public int getShipPos() {
        return shipPos;
    }

    public void setShipPos(int shipPos) {
        this.shipPos = shipPos;
    }
    
    public boolean isDefender() {
        return defender;
    }

    public void setDefender(boolean defender) {
        this.defender = defender;
    }

    public boolean isEnemy() {
        return enemy;
    }

    public void setEnemy(boolean enemy) {
        this.enemy = enemy;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }
    
    public void setLogo(Image logo){
        this.logo = logo;
    }
    
    public Image getLogo(){
        return logo;
    }
    
    public void setShootsReceived(int shoots){
        this.shootsReceived = shoots;
    }
    public int getShootsReceived(){
        return this.shootsReceived;
    }
}
