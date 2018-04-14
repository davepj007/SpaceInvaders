package LinkedLists;

import SpaceInvaders.EnemyShip;
import javafx.scene.image.Image;

/**
 * LinkedList: Clase abstracta de lista enlazada 
 * 
 * @author david
 */
public abstract class LinkedList {
    protected Node head;
    protected Node tail;
    protected int size;
    
    /**
     * Getter encargado de obtener el indicador bandera de la lista enlazada.
     * 
     * @return Node Flag: El nodo indicador de la lista, ya sea el head o el tail.
     */
    public abstract Node getFlag();
    
    /**
     * Getter, retorna el tamaño de la lista enlazada.
     * 
     * @return int size: Tamaño de la lista enlazada
     */
    public abstract int getSize();
    
    /**
     * Getter, retorna el dato que contiene el nodo con el código pasado como 
     * parámetro
     * 
     * @param shipCode : código único de cada nave
     * @return EnemyShip data: Dato almacenado por el nodo
     */
    public abstract EnemyShip getData(int shipCode);
    
    /**
     * Método encargado de insertar un nuevo nodo al final de la lista enzada.
     * 
     * @param ship : Nave que irá en el nodo a insertar
     */
    public abstract void insertEnd(EnemyShip ship);
    
    /**
     * Método encargado de borrar el nodo que contenga el dato pasado por parámetro
     * 
     * @param ship : Nave que se encuentra en el nodo a eliminar.
     */
    public abstract void deleteNode(EnemyShip ship);
    
    /**
     * Se encarga de cambiar la posición de los nodos pasados por paramétros dentro
     * de la lista enlazada. 
     * 
     * @param nodeA : Nodo en donde se encuentra el jefe.
     * @param nodeB : Nodo con quien se intercambiará el jefe.
     */
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
    
    /**
     * Escoge un nodo de la lista de forma aleatoria.
     * 
     * @return Node : Nodo escogido
     */
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
    
    /**
     * Borra todos los nodos de la lista enlazada
     */
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
