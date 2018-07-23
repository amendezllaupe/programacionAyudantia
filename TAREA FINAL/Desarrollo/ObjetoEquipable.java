    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desarrollo;
import java.util.Random;


/**
 *
 * @author hypoc
 */
public class ObjetoEquipable {
    private int mejoraBase;
    private int estrellas;
    private int mejoraFinal;
    private String tipoObjeto;
    
    public ObjetoEquipable(){
        elegirObjeto(elegirCaracteristica());
        mejoraBase =  generarAleatorio(1,9);
        estrellas = determinarEstrella();
        obtenerMejoraFinal();
    }
    
    private int generarAleatorio(int min, int max){
            Random aleatorio = new Random();
            
            int rango = max-min;
            
            int azar = aleatorio.nextInt(rango+1)+min;
            return azar;
        }
    
    private int determinarEstrella(){
            int estrella = 0;
            int porciento = generarAleatorio(1,100);
            
            if(porciento <= 20){
                estrella = 1;
            } else if(porciento <= 40){
                estrella = 2;
            } else if(porciento <= 60){
                estrella = 3;
            } else if(porciento <= 75){
                estrella = 4;
            } else if(porciento <= 85){
                estrella = 5;
            } else if(porciento <= 90){
                estrella = 6;
            } else if(porciento <= 94){
                estrella = 7;
            } else if(porciento <= 97){
                estrella = 8;
            } else if(porciento <= 99){
                estrella = 9;
            } else {
                estrella = 10;
            }
            
            return estrella;
        }
    
    public int getEstrellas(){
        return estrellas;
    }
    
    private int elegirCaracteristica(){
        return generarAleatorio(0,3);
    }
    
    private void elegirObjeto(int opcion){
        if(opcion == 0){
            tipoObjeto = "Armadura";
        } else if(opcion == 1){
            tipoObjeto = "Arma";
        } else if(opcion == 2){
            tipoObjeto = "Escudo";
        } else {
            tipoObjeto = "Botas";
        }
    }
    
    private String mensajeMejora(){
        String mensaje;
        if(tipoObjeto.equals("Armadura")){
            mensaje ="Se ha mejorado HP";
        } else if(tipoObjeto.equals("Arma")){
            mensaje = "Se ha mejorado ATK";
        } else if(tipoObjeto.equals("Escudo")){
            mensaje = "Se ha mejorado DEF";
        } else {
            mensaje = "Se ha mejorado SPD";
        }
        return mensaje;
    }
    
    public void mejorarCaracteristica(String objeto, Luchador luchador){
        switch(objeto){
            case "Armadura":
                luchador.setHp(luchador.getHp() * this.mejoraFinal);
                break;
            case "Arma":
                luchador.setAtk(luchador.getAtk() * this.mejoraFinal);
                break;
            case "Escudo":
                luchador.setDef(luchador.getDef() * this.mejoraFinal);
                break;
            case "Botas":
                luchador.setSpd(luchador.getSpd() * this.mejoraFinal);
                break;
        }
    }
    
    public void mostrarEstrella(){
        System.out.println("El rango del objeto es " + estrellas);
    }
    
    private int obtenerMejoraFinal(){
        mejoraFinal = mejoraBase*estrellas;
        return mejoraFinal;
    }
    
    public void setEstrellas(int estrellas){
        this.estrellas = estrellas;
    }
    
    public String getTipoObjeto(){
        return tipoObjeto;
    }
    
    @Override
    public String toString(){
        return "Objeto: " + this.tipoObjeto + "\n" +
                mensajeMejora() + "\n" +
                "Rango: " + this.estrellas + "\n" +
                "Mejora Final: " + this.mejoraFinal + "\n";
    }
}
