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
public class Dado {
    
    private int cara;
    private int numero;
    
    public Dado(int cara){
        this.cara = cara;
        tirarDado();
    }
    
    private int generarAleatorio(int min, int max){
        Random aleatorio = new Random();

        int rango = max-min;

        int azar = aleatorio.nextInt(rango+1)+min;
        return azar;
    }
    
    public void tirarDado(){
        this.numero = generarAleatorio(1,cara);
    }
    
    public int getNumero(){
        return numero;
    }
    
    public int getCara(){
        return cara;
    }
}
