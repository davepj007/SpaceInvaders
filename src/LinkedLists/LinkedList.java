package LinkedLists;

import SpaceInvaders.EnemyShip;

/**
 *
 * @author david
 */
public abstract class LinkedList {
    protected Node head;
    protected Node tail;
    protected int size;
    
    public abstract Node getFlag();
    
    public abstract int getSize();
    
    public abstract EnemyShip getData(int shipCode);
    
    public abstract void insertEnd(EnemyShip ship);
    
    public abstract void deleteNode(EnemyShip ship);
    
    public void deleteAllNodes(){
        try{
            while (this.getFlag() != null) {
                Node current = this.getFlag();
                this.deleteNode(current.getData());
            }
        }catch(NullPointerException ex){
        }
    }
}
