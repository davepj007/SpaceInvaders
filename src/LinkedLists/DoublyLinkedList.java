package LinkedLists;

import SpaceInvaders.EnemyShip;
import javafx.scene.image.Image;

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
    
    @Override
    public void changeNodePosition(Node nodeA, Node nodeB){
        EnemyShip dataA = nodeA.getData();
        Image logoA = dataA.getLogo();
        EnemyShip dataB = nodeB.getData();
        Image logoB = dataB.getLogo();
        
        nodeA.setData(dataB);
        nodeB.setData(dataA);
        
        dataA.setIsBoss(false);
        dataA.setLogo(logoB);
        dataB.setIsBoss(true);
        dataB.setLogo(logoA);
    }
    
    public void printList(){
        Node current = this.head;
        System.out.print("[ ");
        while(current.getNext() != null){
            System.out.print(current.getData().getShipPos() + ", ");
            current = current.getNext();
        }
        System.out.println(current.getData().getShipPos() + " ]");
    }
}
