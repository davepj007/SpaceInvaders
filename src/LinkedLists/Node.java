
package LinkedLists;

import SpaceInvaders.Ship;

/**
 *
 * @author david
 */
public class Node {
    private Node prev;
    private Node next;
    private Ship data;
    
    public Node(Ship data){
        this.data = data;
    }
    
    public Node(Ship data, Node next){
        this.data = data;
        this.next = next;
    }
    
    public Node(Ship data, Node prev, Node next){
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
    
    public void setData(Ship data){
        this.data = data;
    }
    
    public Ship getData(){
        return this.data;
    }
}
