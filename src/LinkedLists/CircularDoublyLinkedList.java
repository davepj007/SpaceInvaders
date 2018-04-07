package LinkedLists;

import SpaceInvaders.EnemyShip;

/**
 *
 * @author david
 */
public class CircularDoublyLinkedList extends LinkedList {
    
    public CircularDoublyLinkedList(){
        this.head = null;
        this.size = 0;
    }
    
    @Override
    public Node getFlag(){
        return this.head;
    }
    
    @Override
    public int getSize(){
        return this.size;
    }
    
    @Override
    public EnemyShip getData(int shipPos){
        Node current = head;
        while(current != null){
            if(current.getData().getShipPos() == shipPos){
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }
    
    @Override
    public void insertEnd(EnemyShip ship){
        Node newNode = new Node(ship, null, null);
        if(head == null){
            head = newNode;
        }
        else{
            Node current = head;
            while(current != head){
                if(current.getNext() == head){
                    newNode.setPrev(current);
                    current.setNext(newNode);
                    newNode.setNext(head);
                    head.setPrev(newNode);
                }
                else{
                    current = current.getNext();
                }
            }
        }
        size++;
    }
    
    @Override
    public void deleteNode(EnemyShip ship){
        try{
            Node current = head;
            if(current.getNext() == head && current.getData() == ship){
                head = null;
                size--;
            }
            else if(current.getNext() != head && current.getData() == ship){
                current = current.getPrev();
                current.setNext(head.getNext());
                head.getNext().setPrev(current);
                head.setNext(null);
                head.setPrev(null);
                head = current.getNext();
                size--;
            }
            else{
                while(current.getNext() != head){
                    if(current.getNext().getData() == ship){
                        current.getNext().setPrev(null);
                        current.setNext(current.getNext().getNext());
                        current.getNext().getPrev().setNext(null);
                        current.getNext().setPrev(current);
                        size--;
                    }
                    else{
                        current = current.getNext();
                    }
                }
            }
        }catch(NullPointerException ex){
        }
    }
}
