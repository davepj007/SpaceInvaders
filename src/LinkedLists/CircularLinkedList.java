package LinkedLists;

import SpaceInvaders.EnemyShip;

/**
 *
 * @author david
 */
public class CircularLinkedList extends LinkedList{
    
    public CircularLinkedList(){
        this.tail = null;
        this.size = 0;
    }
    
    @Override
    public Node getFlag(){
        return this.tail;
    }
    
    @Override
    public int getSize(){
        return this.size;
    }
    
    @Override
    public EnemyShip getData(int shipPos){
        Node current = tail;
        if(current.getData().getShipPos() == shipPos){
            return current.getData();
        }
        else{
            do{
               if(current.getData().getShipPos() == shipPos){
                   return current.getData();
               }
               else{
                   current.getNext();
               }
            }while(current != tail);
        }
        return null;
    }
    
    @Override
    public void insertEnd(EnemyShip ship){
        Node newNode = new Node(ship, null);
        if(tail != null){
            newNode.setNext(tail.getNext());
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }
    
    @Override
    public void deleteNode(EnemyShip ship){
        try{
            Node current = tail;
            if(current.getData() == ship && current.getNext() == tail){
                tail = null;
                size--;
            }
            else{
                do{
                    if(current.getNext().getData() == ship){
                        current.setNext(current.getNext().getNext());
                        size--;
                    }
                    else{
                        current = current.getNext();
                    }
                }while(current != tail);
            }
        }catch(NullPointerException ex){
        }
    }
    
    @Override
    public void changeNodePosition(Node nodeA, Node nodeB){
    
    }
}
