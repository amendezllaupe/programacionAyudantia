/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desarrollo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hypoc
 */
public class InventarioLuchadores extends Inventario{
    private ArrayList<Luchador> listaLuchadores;
    
    public InventarioLuchadores(){
        super();
        this.listaLuchadores = this.elementos;
    }
    
    private String pedirDato(){                         //Almacena el dato que se ingreso
        Scanner teclado = new Scanner(System.in);
        
        String dato = teclado.nextLine();
        return dato;
    }
    private int verificarEntero(){                      //Verfica si el dato ingresado es un Entero y lo retorna, si no lo es manda un mensaje por consola
        int entero = 0;                                 //y reinicia la solicitud
        
        try{
            entero = Integer.parseInt(pedirDato())-1;
        }catch(Exception e){
            System.out.println("El dato ingresado no es un entero");
            verificarEntero();
        }
        
        return entero;
    }
    
    public void menu(){ // Menú donde se encuentran los metodos de la clase que se pueden realizar
        System.out.println("Elige una de las opciones");
        
        System.out.println("1. Mostrar lista de Luchadores");
        System.out.println("2. Mostrar Cantidad");
        System.out.println("3. Agregar Luchador");
        System.out.println("4. Eliminar Luchador");
        System.out.println("5. Buscar Luchador");
        System.out.println("6. Filtrar luchadores por Facción");
        System.out.println("7. Filtrar luchadores por Rango");
        System.out.println("8. Salir del programa");
        System.out.println("");
        String opcion = pedirDato();
        System.out.println("");
        
        seleccion(opcion);
        
        menu(); //recursividad
    }
    
    private void seleccion(String opcion){  //Realiza la tarea dependiendo de la opicion ingresada por el usuario
        switch(opcion){
            case "1":
                System.out.println("LISTA DE LUCHADORES");
                System.out.println("-------------------");
                mostrarLuchadores(listaLuchadores);
                System.out.println("");
                break;
            case "2":
                System.err.println("La cantidad de luchadores es: ");
                mostrarCantidad();
                System.out.println("");
                break;
            case "3":
                agregarLuchador();

                System.out.println("");
                break;
            case "4":
                System.out.println("Ingrese la posicion del luchador a eliminar");
                eliminarLuchador(verificarEntero());
                
                System.out.println("");
                break;
            case "5":
                System.out.println("Ingrese la posición del luchador del cual quiere ver todas sus estadísticas");
                estadisticasLuchador(verificarEntero());
                System.out.println("");
                break;
            case "6":
                ArrayList<Luchador> listaFiltrada = generarListaFiltrada(); //Crea una lista temporal en donde se almacenaran los luchadores con el filtro ingresado*
                System.out.println("Ingrese la facción que desea filtrar");
                filtrarFaccion(listaFiltrada ,pedirDato());
                System.out.println("LISTA FILTRADA POR FACCIÓN");
                System.out.println("--------------------------");
                mostrarLuchadores(listaFiltrada); //Después de aplicado el filtro muestra la lista filtrada**
                System.out.println("");
                break;
            case "7":
                ArrayList<Luchador> listaFiltrada2 = generarListaFiltrada(); //* (No se me ocurrió otra solución)
                System.out.println("Ingrese el rango que desea filtrar");
                filtrarRango(listaFiltrada2 , verificarEntero());
                System.out.println("LISTA FILTRADA POR RANGO");
                System.out.println("--------------------------");
                mostrarLuchadores(listaFiltrada2); //**
                System.out.println("");
                break;
            case "8":
                System.err.println("El programa se cerrará");
                System.exit(0);
        }
    }
    
    private boolean cantidadMinima(){
        if(listaLuchadores.size() < 1){
            System.err.println("No hay luchadores que mostrar");
            return false;
        } else {
            return true;
        }
    }

    private void mostrarCantidad(){ //Muestra la cantidad de luchadores presentes en total
        System.out.println(listaLuchadores.size());
    }
    
    public void agregarLuchador(){ //Si la lista no excede los 25 luchadores agrega uno nuevo
        if(listaLuchadores.size()<25){
            listaLuchadores.add(new Luchador());
            System.err.println("Se ha agregado un luchador");
        } else {
            System.err.println("Ha alcanzado el máximo de luchadores");
        }
    }
    
    private boolean buscarLuchador(int posicion){ //Recorre la lista y verifica si el luchador se encuentra
        boolean existe = false;
        for(int i = 0; i < listaLuchadores.size(); i++){
            if(i == posicion){
                existe = true;
            }
        }
        return existe;
    }
    
    private void eliminarLuchador(int posicion){ //Verifica si el luchador (posicion) ingresado se encuentra en la lista y lo elimina
        if(buscarLuchador(posicion)){
            listaLuchadores.remove(posicion);
            System.err.println("Se ha eliminado el luchador");
        } else {
            System.err.println("El luchador no está dentro de la lista");
        }
    }
    
    private ArrayList<Luchador> generarListaFiltrada(){ //Genera una lista con el filtro que corresponda
        ArrayList<Luchador> listaFiltrada = new ArrayList<>();
        return listaFiltrada;
    }
    
    private void filtrarFaccion(ArrayList<Luchador> listaFiltrada, String faccion){ //FIltra la lista de luchadores por la Faccion ingresada por el usaurio
        for(int i = 0; i < listaLuchadores.size(); i++){
            if(listaLuchadores.get(i).getFaccion().equals(faccion)){
                listaFiltrada.add(listaLuchadores.get(i));
            }
        }
    }
    
    private void filtrarRango(ArrayList<Luchador> listaFiltrada, int rango){ //Filtra la lista de luchadores por el Rango que ingresa el usuario
        for(int i = 0; i < listaLuchadores.size(); i++){
            if(listaLuchadores.get(i).getRango() == rango){
                listaFiltrada.add(listaLuchadores.get(i));
            }
        }
    }
    
    private void mostrarLuchadores(ArrayList<Luchador> lista){ //Muestra solo el Nombre, Rango y Faccion
        if(cantidadMinima()){
            for(int i = 0; i < lista.size(); i++){
                lista.get(i).mostrarNRF();
            }
        }
    }
    
    private void estadisticasLuchador(int posicion){    //Muestrra todas las estadísticas del luchador escogido
        if(buscarLuchador(posicion)){
            listaLuchadores.get(posicion).mostrarTodo();
        } else { 
            System.err.println("Ese luchador no se encuentra");
        }
    }

    public ArrayList<Luchador> getListaLuchadores() {
        return listaLuchadores;
    }

    public void setListaLuchadores(ArrayList<Luchador> listaLuchadores) {
        this.listaLuchadores = listaLuchadores;
    }
    
}
