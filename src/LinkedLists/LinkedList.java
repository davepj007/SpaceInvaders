package LinkedLists;

import SpaceInvaders.Ship;

/**
 *
 * @author david
 */
public abstract class LinkedList {
    protected Node head;
    protected Node tail;
    protected int size;
    
    public abstract Ship getData(int shipCode);
    
    public abstract void insertEnd(Ship ship);
    
    public abstract void deleteNode(Ship ship);
}
