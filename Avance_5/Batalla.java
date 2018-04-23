/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea_programación;
import java.util.ArrayList;
/**
 *
 * @author hypoc
 */
public class Batalla {
    
    private double danoPeleador;
    private double danoMonstruo;
    private InventarioLuchadores peleadores;
    private Monstruo monstruo;
    private int restaDados;
    
    public Batalla(){
        this.peleadores = new InventarioLuchadores();
        this.monstruo = new Monstruo();
        this.restaDados = obtenerResta();
        pelear();
    }
    
    public void generarEscuadron(){
        for(int i = 0 ; i < 6 ; i++){
            peleadores.agregarLuchador();
        }
    }
    
    public InventarioLuchadores ordenarLuchadores(){
        InventarioLuchadores luchadoresOrdenados;
        luchadoresOrdenados = this.peleadores;
        
        for(int i = 0 ; i < luchadoresOrdenados.getListaLuchadores().size(); i++){
            
            for(int j = 0; j < luchadoresOrdenados.getListaLuchadores().size(); j++){
                
                if(luchadoresOrdenados.getListaLuchadores().get(i).getSpd() > luchadoresOrdenados.getListaLuchadores().get(j).getSpd()){
                    
                    Luchador aux = luchadoresOrdenados.getListaLuchadores().get(i);
                    luchadoresOrdenados.getListaLuchadores().set(i, luchadoresOrdenados.getListaLuchadores().get(j));
                    luchadoresOrdenados.getListaLuchadores().set(j, aux);
                }
            }
        }
        return luchadoresOrdenados;
    }
    
    public void pelear(){
        InventarioLuchadores luchadoresOrdenados = ordenarLuchadores();
        do{
            for(int i = 0; i < luchadoresOrdenados.getListaLuchadores().size();i++){
                if(luchadoresOrdenados.getListaLuchadores().get(i).getHp() <= 0){
                    luchadoresOrdenados.getListaLuchadores().remove(i);
                }
                compararFaccion(i,luchadoresOrdenados);
                calcularDaño(i,luchadoresOrdenados);
                if(luchadoresOrdenados.getListaLuchadores().get(i).getSpd() > monstruo.getSpd()){
                    monstruo.setHp(monstruo.getHp() - this.danoPeleador);
                } else if(luchadoresOrdenados.getListaLuchadores().get(i).getSpd() < monstruo.getSpd()){
                    luchadoresOrdenados.getListaLuchadores().get(i).setHp(luchadoresOrdenados.getListaLuchadores().get(i).getHp() - this.danoMonstruo);
                }
            }
        }while(this.monstruo.getHp() > 0 || luchadoresOrdenados.getListaLuchadores().size() > 0);
        
        if(this.monstruo.getHp() <= 0 ){
            System.out.println("Tu Equipo ha ganado");
        } else if (luchadoresOrdenados.getListaLuchadores().size() <= 0){
            System.out.println("Ha ganado el monstruo");
        }
        
    }
    
    public int obtenerResta(){
        Dado dado8 = new Dado(8);
        Dado dado6 = new Dado(6);
        int resta = dado8.getNumero() - dado6.getNumero();
        
        return resta;
    }
    
    public void calcularDaño(int posicion , InventarioLuchadores inventario){
        if(restaDados > 0){ 
            this.danoPeleador = (inventario.getListaLuchadores().get(posicion).getAtk() - monstruo.getDef()) * restaDados;
            this.danoMonstruo = (monstruo.getAtk() - peleadores.getListaLuchadores().get(posicion).getDef());
        } else if(restaDados < 0){
            this.danoMonstruo = (monstruo.getAtk() - peleadores.getListaLuchadores().get(posicion).getDef()) * restaDados * -1;
            this.danoPeleador = (inventario.getListaLuchadores().get(posicion).getAtk() - monstruo.getDef());
        } else {
            this.danoPeleador = (inventario.getListaLuchadores().get(posicion).getAtk() - monstruo.getDef());
            this.danoMonstruo = (monstruo.getAtk() - peleadores.getListaLuchadores().get(posicion).getDef());
        }
    }
    
    public void compararFaccion(int posicion , InventarioLuchadores inventario){
        String faccionMonstruo = monstruo.getFaccion();
        String faccionPeleador = inventario.getListaLuchadores().get(posicion).getFaccion();
        
        if(faccionMonstruo.equals("Agua") && faccionPeleador.equals("Fuego")){
            
            inventario.getListaLuchadores().get(posicion).setAtk(inventario.getListaLuchadores().get(posicion).getAtk()* 0.75);
            this.monstruo.setAtk(monstruo.getAtk()*1.5);
            
        } else if(faccionMonstruo.equals("Agua") && faccionPeleador.equals("Planta")){
            
            inventario.getListaLuchadores().get(posicion).setAtk(inventario.getListaLuchadores().get(posicion).getAtk()* 1.5);
            this.monstruo.setAtk(monstruo.getAtk()*0.75);
            
        } else if(faccionMonstruo.equals("Fuego") && faccionPeleador.equals("Planta")){
            
            inventario.getListaLuchadores().get(posicion).setAtk(inventario.getListaLuchadores().get(posicion).getAtk()* 0.75);
            this.monstruo.setAtk(monstruo.getAtk()*1.5);
            
        } else if(faccionMonstruo.equals("Fuego") && faccionPeleador.equals("Agua")){
            
            inventario.getListaLuchadores().get(posicion).setAtk(inventario.getListaLuchadores().get(posicion).getAtk()* 1.5);
            this.monstruo.setAtk(monstruo.getAtk()*0.75);
            
        } else if(faccionMonstruo.equals("Planta") && faccionPeleador.equals("Fuego")){
            
            inventario.getListaLuchadores().get(posicion).setAtk(inventario.getListaLuchadores().get(posicion).getAtk()* 1.5);
            this.monstruo.setAtk(monstruo.getAtk()*0.75);
            
        } else if(faccionMonstruo.equals("Planta") && faccionPeleador.equals("Agua")){
            
            inventario.getListaLuchadores().get(posicion).setAtk(inventario.getListaLuchadores().get(posicion).getAtk()* 0.75);
            this.monstruo.setAtk(monstruo.getAtk()*1.5);
        }
    }
}
