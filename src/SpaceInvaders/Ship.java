package SpaceInvaders;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author david
 */

public abstract class Ship {
    protected Image logo;
    protected double coordX;
    protected double coordY;

    public double getCoordX() {
        return coordX;
    }

    public void setCoordX(double coordX){
        this.coordX = coordX;
    }

    public double getCoordY(){
        return this.coordY;
    }
    
    public void setCoordY(double coordY){
        this.coordY = coordY;
    }
    
    public void setLogo(Image logo){
        this.logo = logo;
    }
    
    public Image getLogo(){
        return logo;
    }    
    
    public void render(GraphicsContext gc){
        gc.drawImage(logo, coordX, coordY, 75, 75);
    }
    
    public Rectangle2D getBounds(){
        return new Rectangle2D(coordX, coordY, 70, 75);
    }
    
    public boolean intersects(EnemyShip enemy){
        return enemy.getBounds().intersects(this.getBounds());
    }
}
