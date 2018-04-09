package LinkedLists;

import SpaceInvaders.EnemyShip;

/**
 *
 * @author david
 */
public class DoublyLinkedList extends LinkedList {
   
    public DoublyLinkedList(){
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
            while(current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(newNode);
            newNode.setPrev(current);
        }
        size++;
    }
    
    @Override
    public void deleteNode(EnemyShip ship){
        try{
            Node current = head;
            if(current.getNext() == null && current.getData() == ship){
                head = null; 
                size--;
            }
            else if(current.getNext() != null && current.getData() == ship){
                head = current.getNext();
                current.setNext(null);
                head.setPrev(null);
                size--;
            }
            else{
                while(current.getNext() != null){
                    if(current.getNext().getData() == ship){
                        current.getNext().setPrev(null);
                        current.getNext().getNext().setPrev(current);
                        current.setNext(current.getNext().getNext());
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
    
    public void changeNodePosition(Node nodeA, Node nodeB){
        if(nodeA.getNext() == nodeB){
            nodeA.setNext(nodeB.getNext());
            nodeB.setPrev(nodeA.getPrev());
            
            if(nodeA.getNext() != null){
                nodeA.getNext().setPrev(nodeA);
            }
            if(nodeB.getPrev() != null){
                nodeB.getPrev().setNext(nodeB);
            }
            
            nodeB.setNext(nodeA);
            nodeA.setPrev(nodeB);
            
        }else{
            Node temp1 = nodeB.getPrev();
            Node temp2 = nodeB.getNext();
            
            nodeB.setPrev(nodeA.getPrev());
            nodeB.setNext(nodeA.getNext());
            
            nodeA.setPrev(temp1);
            nodeA.setNext(temp2);
            
            if(nodeB.getNext() != null){
                nodeB.getNext().setPrev(nodeB);
            }
            if(nodeB.getPrev() != null){
                nodeB.getPrev().setNext(nodeB);
            }
            
            if(nodeA.getNext() != null){
                nodeA.getNext().setPrev(nodeA);
            }
            if(nodeA.getPrev() != null){
                nodeA.getPrev().setNext(nodeA);
            }
        }
    }
}
