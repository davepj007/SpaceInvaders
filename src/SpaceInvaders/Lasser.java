package SpaceInvaders;

import javafx.geometry.Rectangle2D;
import javafx.scene.effect.MotionBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
/**
 * Lasser : Clase laser
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
    
    /**
     * Constructor Lasser
     * 
     * @param coordX : Coordenada X del laser
     * @param coordY : Coordenada Y del laser
     */
    public Lasser(double coordX, double coordY){
        this.coordX = coordX;
        this.coordY = coordY;
        this.lasser = new Ellipse(this.coordX, this.coordY,  width, height);
        this.lasser.setFill(Color.CYAN);
        this.lasser.setEffect(mb);
    }
    
    /**
     * Método encargado de eliminar el laser de la interfaz gráfica.
     */
    public void deleteLasser(){
        lasser.setVisible(false);
    }

    /**
     * Getter, retorna la figura del laser
     * @return Ellipse lasser : Laser
     */
    public Ellipse getLasser() {
        return lasser;
    }

    /**
     * Setter, establece la figura del laser.
     * 
     * @param lasser : figura del laser 
     */
    public void setLasser(Ellipse lasser) {
        this.lasser = lasser;
    }
    
    /**
     * Getter, obtiene la coordenada X del laser
     * 
     * @return double coordX : Coordenada X del laser
     */
    public double getCoordX() {
        return coordX;
    }

    /**
     * Setter, establece la coordenada X del laser
     * 
     * @param coordX : Coordenada X del laser
     */
    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    /**
     * Getter, obtiene la coordenada Y del lasser 
     * 
     * @return double coordY : Coordenada Y del laser
     */
    public double getCoordY() {
        return coordY;
    }

    /**
     * Setter, establece la coordenada Y del laser
     * 
     * @param coordY : Coordenada Y del lasser
     */
    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }
    
    /**
     * Getter, obtiene los limites del lasser
     * 
     * @return Rectangle2D : Rectangulo que delimita el laser
     */
    public Rectangle2D getBounds(){
        return new Rectangle2D(coordX, coordY, width, height);
    }
    
    /**
     * Método que se encarga de comprobar si los limites del lasser intersecan 
     * con los limites de otros objetos
     * 
     * @param enemy : Nave enemiga
     * @return Boolean : True si interseca, False en caso contrario
     */
    public boolean intersects(EnemyShip enemy){
        return enemy.getBounds().intersects(this.getBounds());
    }
}
