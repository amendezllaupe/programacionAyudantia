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
        generarEscuadron();
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
        System.out.println("================================================");
        InventarioLuchadores luchadoresOrdenados = ordenarLuchadores();
        int turno = 0;
        int auxLargo = 6;
        
        System.out.println("HP MONSTRUO: " + monstruo.getHp() + "");
                
        for(int j = 0; j < peleadores.getListaLuchadores().size();j++){
            System.out.println("HP " + peleadores.getListaLuchadores().get(j).getNombre() + ": " + peleadores.getListaLuchadores().get(j).getHp());
        }
        System.out.println("================================================");
        do{
            turno++;
            System.out.println("TURNO " + turno);
            System.out.println("================================================");
            boolean golpeMonstruo = false;
            for(int i = 0; i < peleadores.getListaLuchadores().size();i++){
                
                compararFaccion(i,luchadoresOrdenados);
                calcularDaño(i,luchadoresOrdenados);
                if(luchadoresOrdenados.getListaLuchadores().get(i).getSpd() > monstruo.getSpd() || golpeMonstruo){
                    monstruo.setHp(monstruo.getHp() - this.danoPeleador);
                    System.out.println(peleadores.getListaLuchadores().get(i).getNombre() + ": ha atacado");
                    
                    if(this.monstruo.getHp() <= 0 ){
                        break;
                    }
                    
                } else if(luchadoresOrdenados.getListaLuchadores().get(i).getSpd() < monstruo.getSpd() && !golpeMonstruo || !golpeMonstruo){
                    peleadores.getListaLuchadores().get(0).setHp(peleadores.getListaLuchadores().get(0).getHp() - this.danoMonstruo);
                    System.out.println("¡Que golpe ha dado el monstruo!");
                    golpeMonstruo = true;
                    
                    if(peleadores.getListaLuchadores().get(0).getHp() <= 0){
                        System.out.println("\n*Ha muerto: " + peleadores.getListaLuchadores().get(0).getNombre() + "*\n");
                        peleadores.getListaLuchadores().remove(0);
                        if (luchadoresOrdenados.getListaLuchadores().size() <= 0){
                            break;
                        }
                    }
                    
                    monstruo.setHp(monstruo.getHp() - this.danoPeleador);
                    
                    if(auxLargo > peleadores.getListaLuchadores().size() && auxLargo > 0){
                        auxLargo--;
                        System.out.println(peleadores.getListaLuchadores().get(i-1).getNombre() + ": ha atacado");
                    } else {
                        System.out.println(peleadores.getListaLuchadores().get(i).getNombre() + ": ha atacado");
                    }
                    
                    if(this.monstruo.getHp() <= 0 ){
                        break;
                    }
                    
                }
                
            }
            System.out.println("================================================");
            System.out.println("HP MONSTRUO: " + monstruo.getHp() + "");
                
            for(int j = 0; j < peleadores.getListaLuchadores().size();j++){
                System.out.println("HP " + peleadores.getListaLuchadores().get(j).getNombre() + ": " + peleadores.getListaLuchadores().get(j).getHp());
            }
            System.out.println("================================================");
            
            if(this.monstruo.getHp() <= 0 ){
                System.out.println("Ha muerto el monstruo");
                System.out.println("Tu Equipo ha ganado");
                break;
            } else if (luchadoresOrdenados.getListaLuchadores().size() <= 0){
                System.out.println("Ha ganado el monstruo");
                break;
            }
            
        }while(this.monstruo.getHp() > 0 || peleadores.getListaLuchadores().size() > 0);
        
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
