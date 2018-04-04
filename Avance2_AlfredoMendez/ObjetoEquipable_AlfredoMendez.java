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
public class ObjetoEquipable {
    private int mejoraBase;
    private int estrellas;
    private int mejoraFinal;
    
    public ObjetoEquipable(){
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
    
    public void mejorarCaracteristica(int opcion, Luchador luchador){
        switch(opcion){
            case 0:
                luchador.setHp(luchador.getHp() * this.mejoraFinal);
                System.out.println("Se ha mejorado HP");
                break;
            case 1:
                luchador.setAtk(luchador.getAtk() * this.mejoraFinal);
                System.out.println("Se ha mejorado ATK");
                break;
            case 2:
                luchador.setDef(luchador.getDef() * this.mejoraFinal);
                System.out.println("Se ha mejorado DEF");
                break;
            case 3:
                luchador.setSpd(luchador.getSpd() * this.mejoraFinal);
                System.out.println("Se ha mejorado SPD");
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
    
    public void mostrarMejoraFinal(){
        System.out.println("La mejora que proporciona el objeto es de: " + mejoraFinal);
    }
}
