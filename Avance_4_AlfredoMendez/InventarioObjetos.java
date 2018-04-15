package tarea_programación;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hypoc
 */
public class InventarioObjetos {
    private ArrayList<ObjetoEquipable> listaObjeto;
    
    private void esperar(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(InventarioObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ejecutar(){
        System.out.println("AGREGAR OBJETOS\n"
                        +  "---------------");
        for(int i = 0; i < 15; i++){
            agregarObjeto();
        }
        
        esperar();
        
        System.out.println("MOSTRAR OBJETOS\n"
                        +  "---------------");
        mostrarObjetos(listaObjeto);
        
        esperar();
        
        System.out.println("ELIMINAR OBJETOS\n"
                        +  "---------------");
        System.out.println("Posición: 4");
        eliminarObjeto(3);   
        
        esperar();
        
        System.out.println("MOSTRAR OBJETOS\n"
                        +  "---------------");
        mostrarObjetos(listaObjeto);
        
        ArrayList<ObjetoEquipable> listaFiltrada = generarListaFiltrada();
        System.out.println("MOSTRAR FILTRO\n"
                        +  "---------------");
        filtrarRango(listaFiltrada, 3);
        System.out.println("Rango Filtrado: 3");
        mostrarObjetos(listaFiltrada);
    }
    
    //CONSTRUCTOR
    public InventarioObjetos(){
        listaObjeto = new ArrayList<>();
    }
    
    public ArrayList<ObjetoEquipable> getListaObjeto(){
        return listaObjeto;
    }
    
    private void agregarObjeto(){ //Si la lista no excede los 10 objetos agrega uno nuevo
        if(listaObjeto.size()<10){
            listaObjeto.add(new ObjetoEquipable());
            System.err.println("Se ha agregado un objeto\n");
        } else {
            System.err.println("Ha alcanzado el máximo de objetos\n");
        }
    }
    
    private void eliminarObjeto(int posicion){ //Verifica si el objeto (posicion) ingresado se encuentra en la lista y lo elimina
        if(buscarObjeto(posicion)){
            listaObjeto.remove(posicion);
            System.err.println("Se ha eliminado el objeto\n");
        } else {
            System.err.println("El objeto no está dentro de la lista\n");
        }
    }
    
    private boolean buscarObjeto(int posicion){ //Recorre la lista y verifica si el objeto se encuentra
        boolean existe = false;
        for(int i = 0; i < listaObjeto.size(); i++){
            if(i == posicion){
                existe = true;
            }
        }
        return existe;
    }
    
    private ArrayList<ObjetoEquipable> generarListaFiltrada(){ //Genera una lista con el filtro que corresponda
        ArrayList<ObjetoEquipable> listaFiltrada = new ArrayList<>();
        return listaFiltrada;
    }
    
    private void filtrarRango(ArrayList<ObjetoEquipable> listaFiltrada, int rango){ //Filtra la lista de luchadores por el Rango que ingresa el usuario
        for(int i = 0; i < listaObjeto.size(); i++){
            if(listaObjeto.get(i).getEstrellas() == rango){
                listaFiltrada.add(listaObjeto.get(i));
            }
        }
    }
    
    public void mostrarObjetos(ArrayList<ObjetoEquipable> lista){
        for(int i = 0; i < lista.size(); i++){
            lista.get(i).mostrarObjeto();
        }
    }
    
    
    
}
