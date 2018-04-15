package LinkedLists;

import SpaceInvaders.EnemyShip;

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
     * Escoge un nodo de la lista de forma aleatoria.
     * 
     * @return Node : Nodo escogido
     */
    public Node chooseRandomNode(){
        int random = 1 + (int)(Math.random() * ((size-1)+1));
        Node current = this.getFlag();
        for (int i = 1; i <= this.size; i++) {
            if(current.getData().getShipPos() == random){                
                if(current.getData().getIsBoss()){
                    if(current.getNext() == null){
                        current = current.getPrev();
                    }else{
                        current = current.getNext();
                    }
                    return current;
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
            while(this.getFlag() != null) {
                Node current = this.getFlag();
                this.deleteNode(current.getData());
            }
        }catch(NullPointerException ex){
        }
    }
    
    /**
     * Método encargado de buscar el nodo que contenga el dato pasado por parámetro
     * 
     * @param enemy : Dato que debe contener el nodo buscado
     * @return Node : El nodo encontrado.
     */
    public Node searchNode(EnemyShip enemy){
        Node current = this.getFlag();
        for (int i = 1; i <= this.getSize(); i++) {
            if(current.getData() == enemy){
               break; 
            }else{
                current = current.getNext();
            }
        }
        return current;
    }
}
