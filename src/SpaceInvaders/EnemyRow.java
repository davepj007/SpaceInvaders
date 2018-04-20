package SpaceInvaders;

import LinkedLists.LinkedList;
import LinkedLists.Node;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * EnemyRow : Hilera enemiga
 * 
 * @author david
 */
public abstract class EnemyRow {
    protected LinkedList enemyRow;
    protected EnemyShip boss;
    protected double enemyXSpeed;
    protected double enemyYSpeed;
    protected boolean bool;
    protected boolean endOfGame;

    /**
     * Método encargado de crear la hilera enemiga a partir de una lista enlazada.
     * 
     * @param gc : GraphicsContext donde se dibujara la hilera.
     */
    public abstract void createEnemyRow(GraphicsContext gc);
    
    /**
     * Método encargado de animar la hilera enemiga.
     * 
     * @param enemy : Nave enemiga
     * @param gc : GraphicsContext donde se dibujará la nave recolocada
     */
    public void animateEnemyRow(EnemyShip enemy, GraphicsContext gc){
        double posIni = enemy.getCoordX();
        AnimationTimer animator = new AnimationTimer(){
            @Override
            public void handle(long arg0){                 
                gc.clearRect(enemy.getCoordX(), enemy.getCoordY(), 900, 700);
                
                enemy.setCoordX(enemy.getCoordX() + enemyXSpeed);
                enemy.setCoordY(enemy.getCoordY() + enemyYSpeed);

                if(enemy.getCoordX()  > (posIni+75)){
                    enemy.setCoordX((posIni+75)-enemyXSpeed);
                    enemyXSpeed *= -1;
                }
                else if(enemy.getCoordX() < posIni){
                    enemy.setCoordX(posIni);
                    enemyXSpeed *= -1;
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
    
    /**
     * Setter, se encarga de crear la lista enlazada en la cual se almacenarán las
     * naves enemigas de la hilera.
     */
    public void setEnemyRow() {
        int size = 4 + (int)(Math.random() * ((6-4)+1));//Obtiene un num random entre 4 y 6 incluyendo 6 
        int enemyCoordX = -10*size + 60;
        int enemyCoordY = 50;
        for(int i = 1; i <= size; i++){
            EnemyShip enemy = new EnemyShip("/GUI/enemie.png", enemyCoordX, enemyCoordY, i);
            enemyRow.insertEnd(enemy);
            enemyCoordX += 900/size;
        }
    }
    
    /**
     * Método encargado de escoger al jefe de la hilera al azar.
     * 
     * @param enemyRow : Lista enlazada donde se almacenan las naves
     * @return EnemyShip : Nave enemiga
     */
    public EnemyShip chooseBoss(LinkedList enemyRow){
        int shipPos = 1 + (int)(Math.random() * ((enemyRow.getSize()-1)+1));
        Node current = enemyRow.getFlag();
        while(current != null){
            if(current.getData().getShipPos() == shipPos){
                current.getData().setIsBoss(true);
                current.getData().setLogo(new Image("/GUI/boss.png"));
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }
    
    /**
     * Método encargado de ejecutar la destrucción del jefe.
     */
    public void executeBossDestroy(){
        enemyRow.deleteAllNodes();
    }
    
    /**
     * Getter, retorna la lista enlazada donde están almacenadas las naves enemigas
     * 
     * @return LinkedList enemyRow : Lista enlazada.
     */
    public LinkedList getEnemyRow() {
        return enemyRow;
    }
    
    /**
     * Setter, establece el jefe de la hilera enemiga.
     * 
     * @param boss : Jefe de la hilera.
     */
    public void setBoss(EnemyShip boss){
        this.boss = boss;
    }
     /**
      * Getter, retorna el jefe de la hilera enemiga
      * 
      * @return EnemyShip boss : Jefe de la hilera
      */
    public EnemyShip getBoss(){
        return boss;
    }
    
    /**
     * Getter, retorna la velocidad en la coordenada X de la hilera
     * 
     * @return double enemyXSpeed : Velocidad en X de la hilera 
     */
    public double getEnemyXSpeed() {
        return enemyXSpeed;
    }
    
    /**
     * Setter, extablece la velocidad en la coordenada X de la hilera
     * 
     * @param enemyXSpeed : Velocidad en X de la hilera
     */
    public void setEnemyXSpeed(double enemyXSpeed) {
        this.enemyXSpeed = enemyXSpeed;
    }

    /**
     * Getter, retorna la velocidad en la coordenada Y de la hilera
     * 
     * @return double enemyYSpeed : Velocidad en Y de la hilera 
     */
    public double getEnemyYSpeed() {
        return enemyYSpeed;
    }

    /**
     * Setter, establece la velocidad en la coordenada Y de la hilera
     * 
     * @param enemyYSpeed : Velocidad en Y de la hilera.
     */
    public void setEnemyYSpeed(double enemyYSpeed) {
        this.enemyYSpeed = enemyYSpeed;
    }
    
    /**
     * Getter, retorna el valor del booleano 
     * 
     * @return boolean bool : Valor booleano
     */
    public boolean getBool(){
        return bool;
    }
    
    /**
     * Setter, establece el valor booleano a la variable bool
     * 
     * @param bool : Valor booleano
     */
    public void setBool(boolean bool){
        this.bool = bool;
    }
    
    /**
     * Getter, obtiene el valor booleano de la variable para determinar si el 
     * juego termina o no termina
     * 
     * @return boolean endOfGame : true si termina, false si no termina
     */
    public boolean isEndOfGame() {
        return endOfGame;
    }
    
    /**
     * Setter, establece el valor booleano de la variable endOfGame
     * 
     * @param endOfGame : True si el juego termina, false de lo contrario
     */
    public void setEndOfGame(boolean endOfGame) {
        this.endOfGame = endOfGame;
    }
    
}
