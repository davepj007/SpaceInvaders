package SpaceInvaders;

import javafx.geometry.Rectangle2D;
import javafx.scene.effect.MotionBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
/**
 *
 * @author david
 */

public class Lasser {
    private Ellipse lasser;
    private double coordX;
    private double coordY;
    private final double height = 37.5;
    private final double width = 3;
    MotionBlur mb = new MotionBlur(0, 10);
    
    public Lasser(double coordX, double coordY){
        this.coordX = coordX;
        this.coordY = coordY;
        this.lasser = new Ellipse(this.coordX, this.coordY,  width, height);
        this.lasser.setFill(Color.CYAN);
        this.lasser.setEffect(mb);
    }
    
    
    
    public void deleteLasser(){
        lasser.setVisible(false);
    }

    public Ellipse getLasser() {
        return lasser;
    }

    public void setLasser(Ellipse lasser) {
        this.lasser = lasser;
    }

    public double getCoordX() {
        return coordX;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }
    
    public Rectangle2D getBounds(){
        return new Rectangle2D(coordX, coordY, width, height);
    }
    
    public boolean intersects(EnemyShip enemy){
        return enemy.getBounds().intersects(this.getBounds());
    }
}
