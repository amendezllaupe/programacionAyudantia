/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desarrollo;
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
    private String resultados;
    
    public Batalla(){
        this.peleadores = new InventarioLuchadores();
        this.monstruo = new Monstruo();
        this.restaDados = obtenerResta();
    }
    
    public void generarEscuadron(int numLuchadores){
        for(int i = 0 ; i < numLuchadores ; i++){
            peleadores.agregarLuchador();
        }
    }

    public InventarioLuchadores getPeleadores() {
        return peleadores;
    }

    public void setPeleadores(InventarioLuchadores peleadores) {
        this.peleadores = peleadores;
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
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
    
    public void pelear(int numLuchadores){
        InventarioLuchadores luchadoresOrdenados = ordenarLuchadores();
        int turno = 0;
        int auxLargo = numLuchadores;
        int contador = 0;
        
        resultados=mostrarHPTodos()+"\n";
        
        do{
            turno++;
            resultados+="\n"+"TURNO " + turno + "\n" +
            "================================================"+"\n";
            boolean golpeMonstruo = false;
            for(int i = 0; i < peleadores.getListaLuchadores().size();i++){
                
                compararFaccion(i,luchadoresOrdenados);
                calcularDaño(i,luchadoresOrdenados);
                
                
                
                //LOS LUCHADORES ATACAN
                if(luchadoresOrdenados.getListaLuchadores().get(i).getSpd() > monstruo.getSpd() && peleadores.getListaLuchadores().get(i).getHp() > 0|| golpeMonstruo){
                    resultados+=peleadores.getListaLuchadores().get(i).getNombre() + ": ha atacado"+"\n";
                    monstruoDañado();
                    
                    contador++;
                    if(this.monstruo.getHp() <= 0 ){
                        break;
                    }
                    
                    
                //EL MONSTRUO ATACA
                } else if(luchadoresOrdenados.getListaLuchadores().get(i).getSpd() < monstruo.getSpd() && !golpeMonstruo){
                    resultados+="¡Que golpe ha dado el monstruo!"+"\n";
                    luchadorDañado(i);
                    
                    golpeMonstruo = true;
                    
                    if(golpeMonstruo){
                        contador = 0;
                    }
                    
                    if(comprobarHPLuchadores(numLuchadores)){
                        break;
                    }
                    
                    //CONTRAATACA EL LUCHADOR
                    if(auxLargo > peleadores.getListaLuchadores().size() && (i-1>=0)){
                        auxLargo--;
                        resultados+=peleadores.getListaLuchadores().get(i-1).getNombre() + ": ha atacado"+"\n";
                    } else {
                        resultados+=peleadores.getListaLuchadores().get(i).getNombre() + ": ha atacado"+"\n";
                    }
                    
                    monstruoDañado();
                    
                    if(this.monstruo.getHp() <= 0 ){
                        break;
                    }
                    
                }
                
            }
            
            resultados+=mostrarHPTodos();
            
            //EL MONSTRUO RECIBE 6 GOLPES SEGUIDOS Y ATACA EN AREA
            if(!golpeMonstruo || contador == 6){
                resultados+="¡El monstruo se ha enfurecido!"+"\n";
                for(int i = 0; i < peleadores.getListaLuchadores().size(); i++){
                    luchadorDañado(i);
                }
                
                resultados+="¡Que golpe ha dado el monstruo!"+"\n";
                golpeMonstruo = true;
                
                resultados+=mostrarHPTodos();
            }
            
            if(this.monstruo.getHp() <= 0 ){
                resultados+="Ha muerto el monstruo"+"\n"+
                "Tu Equipo ha ganado"+"\n"+
                "El monstruo ha dropeado un: \n" + monstruo.obtenerDrop()+"\n";
                break;
            } else if (comprobarHPLuchadores(numLuchadores)){
                resultados+="Ha ganado el monstruo"+"\n";
                break;
            }
            
        }while(this.monstruo.getHp() > 0 || peleadores.getListaLuchadores().size() > 0);
        
    }
    
    public String mostrarHPTodos(){
        String texto = "";
        texto+="================================================"+"\n"+
        "HP MONSTRUO: " + monstruo.getHp() + "\n"+"\n";

        for(int j = 0; j < peleadores.getListaLuchadores().size();j++){
            texto+= "HP " + peleadores.getListaLuchadores().get(j).getNombre() + ": " + peleadores.getListaLuchadores().get(j).getHp() + "\n";
        }
        texto+= "================================================"+"\n";
        
        return texto;
    }
    
    public boolean comprobarHPLuchadores(int numLuchadores){
        boolean derrota = false;
        int derrotados = 0;
        
        for(int i = 0; i < peleadores.getListaLuchadores().size(); i++){
            if(peleadores.getListaLuchadores().get(i).getHp() == 0){
                derrotados++;
            }
        }
            
        if (derrotados == numLuchadores){
            derrota = true;
        }
        
        return derrota;
    }
        
        
    
    public void luchadorDañado(int posicion){
        peleadores.getListaLuchadores().get(posicion).setHp(peleadores.getListaLuchadores().get(posicion).getHp() - this.danoMonstruo);
        if(peleadores.getListaLuchadores().get(posicion).getHp() <= 0){
            System.out.println("\n*Ha muerto: " + peleadores.getListaLuchadores().get(posicion).getNombre() + "*\n");
            peleadores.getListaLuchadores().get(posicion).setHp(0);
        }
    }
    
    public void monstruoDañado(){
        monstruo.setHp(monstruo.getHp() - this.danoPeleador);
        if(monstruo.getHp() <= 0){
            monstruo.setHp(0);
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
        
        System.out.println(danoPeleador);
        System.out.println(danoMonstruo);
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
