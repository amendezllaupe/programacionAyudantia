/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea_programación;

import java.util.Random;

/**
 *
 * @author hypoc
 */
public class Monstruo extends Personaje{
    private InventarioObjetos objetosDrop;
    
    //CONSTRUCTOR
    public Monstruo(){
        crearMonstruo();
    }
    
    private void crearMonstruo(){
        this.hp = generarAleatorio(3500,4000);
        this.atk = generarAleatorio(1000,1500);
        this.def = generarAleatorio(5,25);
        this.spd = generarAleatorio(10,100);
        this.faccion = elegirFaccion(listaFaccion());
        crearObjetoDropeable();
        obtenerDrop();
    }
    
    private int generarAleatorio(int min, int max){
        Random aleatorio = new Random();
            
        int rango = max-min;

        int azar = aleatorio.nextInt(rango+1)+min;
        return azar;
    }
    
    private void crearObjetoDropeable(){
        objetosDrop = new InventarioObjetos();
        for(int i=0;i<3;i++){
            objetosDrop.getListaObjeto().add(new ObjetoEquipable());
        }
        objetosDrop.getListaObjeto().get(0).setEstrellas(1);
        objetosDrop.getListaObjeto().get(1).setEstrellas(3);
        objetosDrop.getListaObjeto().get(2).setEstrellas(5);
        
        for(int i=0;i<3;i++){
            System.out.println(objetosDrop.getListaObjeto().get(i));
        }
        
    }
    
    private ObjetoEquipable obtenerDrop(){
        int rango = generarAleatorio(0,100);
        ObjetoEquipable drop;
        if(rango <= 60){
            drop = objetosDrop.getListaObjeto().get(0);
        } else if(rango <= 90){
            drop = objetosDrop.getListaObjeto().get(1);
        } else {
            drop = objetosDrop.getListaObjeto().get(2);
        }
        return drop;
    }
    
    private String elegirFaccion(String[] matriz){
        int posicion = generarAleatorio(0,matriz.length-1);
        String datoMostrar = matriz[posicion];
        return datoMostrar;
    }
    
    private String[] listaFaccion(){
        String[] lista = {"Fuego","Agua","Planta"};
        return lista;
    }
    
    public double getHp() {
        return hp;
    }

    public double getAtk() {
        return atk;
    }

    public double getDef() {
        return def;
    }

    public double getSpd() {
        return spd;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setAtk(double atk) {
        this.atk = atk;
    }

    public void setDef(double def) {
        this.def = def;
    }

    public void setSpd(double spd) {
        this.spd = spd;
    }

    public void setFaccion(String faccion) {
        this.faccion = faccion;
    }
    
    public String getFaccion(){
        return faccion;
    }
    
}
