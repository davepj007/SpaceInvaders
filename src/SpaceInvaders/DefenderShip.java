/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpaceInvaders;

import javafx.scene.image.Image;

/**
 * DefenderShip : Nave defensora 
 * 
 * @author david
 */

public class DefenderShip extends Ship{
     /**
      * Constructor DefenderShip
      * 
      * @param logo : Logo de la nave defensora
      * @param coordX : Coordenada en X de la nave defensora
      * @param coordY : Coordenada en Y de la nave defensora
      */
    public DefenderShip(String logo, double coordX, double coordY){
        this.logo = new Image(logo);
        this.coordX = coordX;
        this.coordY = coordY;
    }
}
