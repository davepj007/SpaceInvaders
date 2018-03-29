
package LinkedLists;

import SpaceInvaders.EnemyShip;

/**
 *
 * @author david
 */

public class Node {
    private Node prev;
    private Node next;
    private EnemyShip data;
    
    public Node(EnemyShip data){
        this.data = data;
    }
    
    public Node(EnemyShip data, Node next){
        this.data = data;
        this.next = next;
    }
    
    public Node(EnemyShip data, Node prev, Node next){
        this.data = data;
        this.prev = prev;
        this.next = next;
    }
    
    public void setPrev(Node prev){
        this.prev = prev;
    }
    
    public Node getPrev(){
        return this.prev;
    }
    
    public void setNext(Node next){
        this.next = next;
    }
    
    public Node getNext(){
        return this.next;
    }
    
    public void setData(EnemyShip data){
        this.data = data;
    }
    
    public EnemyShip getData(){
        return this.data;
    }
}
