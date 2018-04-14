package SpaceInvaders;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Ship : Clase Nave
 * 
 * @author david
 */

public abstract class Ship {
    protected Image logo;
    protected double coordX;
    protected double coordY;
    
    /**
     * Getter, obtiene la coordenada X de la nave
     * 
     * @return double coordX : coordenada X de la nave 
     */
    public double getCoordX() {
        return coordX;
    }
    
    /**
     * Setter, establece la coordenada X de la nave
     * 
     * @param coordX : coordenada X de la nava
     */
    public void setCoordX(double coordX){
        this.coordX = coordX;
    }
    
    /**
     * Getter, obtiene la coordenada Y de la nave
     * 
     * @return double coordY : coordenada Y de la nave 
     */
    public double getCoordY(){
        return this.coordY;
    }
    
    /**
     * Setter, establece la coordenada Y de la nave
     * 
     * @param coordY : coordenada Y de la nava
     */
    public void setCoordY(double coordY){
        this.coordY = coordY;
    }
    
    /**
     * Setter, establece el logo de la nave
     * 
     * @param logo : Imagen de la nave
     */
    public void setLogo(Image logo){
        this.logo = logo;
    }
    
    /**
     * Getter, obtiene el logo de la nave
     * 
     * @return Image logo : Imagen de la nave
     */
    public Image getLogo(){
        return logo;
    }    
    
    /**
     * Método que se encarga de dibujar la nave dentro del GraphicsContext
     * 
     * @param gc : GraphicContext 
     */
    public void render(GraphicsContext gc){
        gc.drawImage(logo, coordX, coordY, 75, 75);
    }
    /**
     * Getter, obtiene los limites de la nave
     * 
     * @return Rectangle2D : Rectangulo que limita la nave
     */
    public Rectangle2D getBounds(){
        return new Rectangle2D(coordX, coordY, 70, 75);
    }
    
    /**
     * Método encargado de corroborar si la nave interseca a otro objeto utilizando
     * sus limites
     * 
     * @param enemy : Nave Enemiga
     * @return Boolean : True si interseca, False en caso contrario
     */
    public boolean intersects(EnemyShip enemy){
        return enemy.getBounds().intersects(this.getBounds());
    }
}
