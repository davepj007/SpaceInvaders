package SpaceInvaders;

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
        return this.logo;
    }
}
