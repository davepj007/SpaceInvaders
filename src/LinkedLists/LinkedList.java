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
    
    public abstract int getSize();
    
    public abstract EnemyShip getData(int shipCode);
    
    public abstract void insertEnd(EnemyShip ship);
    
    public abstract void deleteNode(EnemyShip ship);
}
