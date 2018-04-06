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
public class Monstruo {
    private int hp;
    private int atk;
    private int def;
    private int spd;
    private ObjetoEquipable objetoDropeable;
    
    //CONSTRUCTOR
    public Monstruo(){
        crearMonstruo();
    }
    
    private void crearMonstruo(){
        this.hp = generarAleatorio(3500,4000);
        this.atk = generarAleatorio(1000,1500);
        this.def = generarAleatorio(5,25);
        this.spd = generarAleatorio(10,100);
        crearObjetoDropeable();
    }
    
    private int generarAleatorio(int min, int max){
        Random aleatorio = new Random();
            
        int rango = max-min;

        int azar = aleatorio.nextInt(rango+1)+min;
        return azar;
    }
    
    private void crearObjetoDropeable(){
        objetoDropeable = new ObjetoEquipable();
    }
    
    public void mostrarObjetoDropeable(){
        objetoDropeable.mostrarObjeto();
    }

    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getSpd() {
        return spd;
    }

    public ObjetoEquipable getObjetoDropeable() {
        return objetoDropeable;
    }
    
}
