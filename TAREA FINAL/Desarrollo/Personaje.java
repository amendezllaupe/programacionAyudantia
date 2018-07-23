/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desarrollo;

/**
 *
 * @author hypoc
 */
public class Personaje {
    protected double hp;
    protected double atk;
    protected double def;
    protected double spd;
    protected String faccion;

    public Personaje(double hp, double atk, double def, double spd, String faccion) {
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
        this.faccion = faccion;
    }
    
    public Personaje(){
        
    }
}
