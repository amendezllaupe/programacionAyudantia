/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea_programación;

/**
 *
 * @author hypoc
 */
public class Main {
    public static void main(String[]args){
        InventarioLuchadores inventario = new InventarioLuchadores();
        ObjetoEquipable objeto = new ObjetoEquipable();
        
        objeto.mostrarEstrella();
        objeto.mostrarMejoraFinal();
        System.out.println("");
        inventario.menu();
        
    }
}
