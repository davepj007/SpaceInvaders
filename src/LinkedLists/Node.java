
package LinkedLists;

import SpaceInvaders.EnemyShip;

/**
 * Node : Clase nodo
 * 
 * @author david
 */

public class Node {
    private Node prev;
    private Node next;
    private EnemyShip data;
    
    /**
     * Constructor Node
     * @param data : Nave enemiga
     */
    public Node(EnemyShip data){
        this.data = data;
    }
    
    /**
     * Constructor Node
     * @param data : Nave enemiga
     * @param next : Referencia al siguiente nodo
     */
    public Node(EnemyShip data, Node next){
        this.data = data;
        this.next = next;
    }
    
    /**
    * Constructor Node
    * @param data : Nave enemiga
    * @param prev : Referencia al nodo anterior
    * @param next : Referencia al nodo siguiente
    */
    public Node(EnemyShip data, Node prev, Node next){
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
    
    /**
     * Setter, establece la referencia al nodo anterior
     * @param prev : Nodo anterior
     */
    public void setPrev(Node prev){
        this.prev = prev;
    }
    
    /**
     * Getter, retorna el nodo anterior
     * @return Node prev : nodo anterior
     */
    public Node getPrev(){
        return this.prev;
    }
    
    /**
     * Setter, establece la referencia al nodo siguiente
     * @param next : Nodo siguiente
     */
    public void setNext(Node next){
        this.next = next;
    }
    
    /**
     * Getter, retorna el nodo siguiente
     * @return Node next : Nodo siguiente
     */
    public Node getNext(){
        return this.next;
    }
     /**
      * Setter, establece la nave que almacenara el nodo
      * @param data : Nave enemiga
      */
    public void setData(EnemyShip data){
        this.data = data;
    }
     /**
      * Getter, retorna la nave almacenada en el nodo
      * @return EnemyShip data : Nave enemiga
      */
    public EnemyShip getData(){
        return this.data;
    }
}
