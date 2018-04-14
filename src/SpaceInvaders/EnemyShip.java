package SpaceInvaders;

import javafx.scene.image.Image;
/**
 * EnemyShip : Clase nave enemiga
 * 
 * @author david
 */

public class EnemyShip extends Ship {
    private int shipPos;
    private boolean isBoss;
    private int shootsRequired;
    private int shootsReceived;
    
    /**
     * Contructor EnemyShip 
     * 
     * @param logo : Logo de la nave enemiga
     * @param coordX : Coordenada X de la nave enemiga
     * @param coordY : Coordenada Y de la nave enemiga
     * @param shipPos : Posicion de la nave enemiga dentro de la hilera
     */
    public EnemyShip(String logo, double coordX, double coordY, int shipPos){
        this.logo = new Image (logo);
        this.coordX = coordX;
        this.coordY = coordY;
        this.shipPos = shipPos;
        this.isBoss = false;
        this.shootsRequired = 0;
        this.shootsReceived = 0;
    }
    
    /**
     * Getter, obtiene la posicion de la nave enemiga dentro de la hilera
     * 
     * @return int shipPos : Posicion de la nave en la hilera
     */
    public int getShipPos() {
        return shipPos;
    }
    
    /**
     * Setter, establece la posicion de la nave enemiga en la hilera
     * 
     * @param shipPos : Posicion de la nave enemiga en la hilera
     */
    public void setShipPos(int shipPos) {
        this.shipPos = shipPos;
    }

    /**
     * Getter, corrobora si la nave enemiga es jefe o no.
     * 
     * @return True si la nave es jefe, False si no lo es
     */
    public boolean getIsBoss() {
        return isBoss;
    }

    /**
     * Setter, establece si la nave enemiga es jefe o no.
     * 
     * @param isBoss : True si es jefe, False si no.
     */
    public void setIsBoss(boolean isBoss) {
        this.isBoss = isBoss;
    }

    /**
     * Getter, Obtiene los disparos recibidos por el jefe
     * 
     * @return int shootsReceives : Disparos recibidos.
     */
    public int getShootsReceived() {
        return shootsReceived;
    }

    /**
     * Setter, establece la cantidad de disparos recibidos por el jefe de la hilera
     * 
     * @param shootsReceived : Disparos rcibidos por el jefe.
     */
    public void setShootsReceived(int shootsReceived) {
        this.shootsReceived = shootsReceived;
    }
    
    /**
     * Getter, obtiene la cantidad de disparos requeridos para destruir al jefe
     * de la hilera enemiga
     * 
     * @return int shootsRequired : Cantidad de disparos requeridos.
     */
    public int getShootsRequired(){
        return shootsRequired;
    }
    
    /**
     * Setter, establece de forma aleatoria la cantidad de disparos que se 
     * requieren para destruir al jefe de la hilera enemiga.
     */
    public void setShootsRequired(){
        this.shootsRequired = 2 + (int)(Math.random() * ((5-2)+1));
        System.out.println("Tiros necesarios " + this.shootsRequired);
    }
}
