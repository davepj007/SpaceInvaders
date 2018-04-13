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
    
    public abstract void changeNodePosition(Node nodeA, Node nodeB);
    
    public Node chooseRandomNode(){
        int random = 1 + (int)(Math.random() * ((size-1)));
        Node current = this.getFlag();
        while(current.getNext() != null){
            if(current.getData().getShipPos() == random){
                if(current.getData().getIsBoss()){
                    return current.getNext();
                }else{
                    return current;
                }
            }
            current = current.getNext();
        }
        return current;
    }
    
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
