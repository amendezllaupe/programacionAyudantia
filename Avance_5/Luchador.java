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
public class Luchador {
    private String nombre;
    private double hp;
    private double atk;
    private double def;
    private double spd;
    private int rango;
    private String faccion;
    private ObjetoEquipable objetoEquipado;
    
        public Luchador(String nombre, double hp, double atk, double def, double spd, int rango, String faccion){
            this.nombre = nombre;
            this.hp = hp;
            this.atk = atk;
            this.def = def;
            this.spd = spd;
            this.rango = rango;
            this.faccion = faccion;
        }
    
        public Luchador(){
            this.rango = determinarEstrella();
            this.nombre = elegirLista(listaNombre());
            this.hp = generarAleatorio(200,500) * rango;
            this.atk = generarAleatorio(20,70) * rango;
            this.def = generarAleatorio(5,25) * rango;
            this.spd = generarAleatorio(10,100) * rango;
            this.faccion = elegirLista(listaFaccion());
            mejorarStat();
        }
        
        public void mejorarStat(){
            objetoEquipado = new ObjetoEquipable();
            objetoEquipado.mejorarCaracteristica(objetoEquipado.getTipoObjeto(), this);
        }
        
        private int generarAleatorio(int min, int max){
            Random aleatorio = new Random();
            
            int rango = max-min;
            
            int azar = aleatorio.nextInt(rango+1)+min;
            return azar;
        }
        
        private String[] listaNombre(){
            String[] lista = {"Tonyo Ers","Pfeifer","Lautaro","Escorpión","Gabri-El","Onyot","Oratual","Lobezno","Manu-el","Lebrecht","Tcherbel","Zap","Barrabas","Yisus","Messi"};
            return lista;
        }
        
        private String[] listaFaccion(){
            String[] lista = {"Fuego","Agua","Planta"};
            return lista;
        }
        
        private String elegirLista(String[] matriz){
            int posicion = generarAleatorio(0,matriz.length-1);
            String datoMostrar = matriz[posicion];
        
            return datoMostrar;
        }
        
        private int determinarEstrella(){
            int estrella = 0;
            int porciento = generarAleatorio(1,100);
            
            if(porciento <= 40){
                estrella = 1;
            } else if(porciento <= 70){
                estrella = 2;
            } else if(porciento <= 85){
                estrella = 3;
            } else if(porciento <= 95){
                estrella = 4;
            } else {
                estrella = 5;
            }
            
            return estrella;
        }
        
        public String getNombre(){
            return nombre;
        }
        
        public double getHp(){
            return hp;
        }
        
        public double getAtk(){
            return atk;
        }
        
        public double getDef(){
            return def;
        }
        
        public double getSpd(){
            return spd;
        }
        
        public int getRango(){
            return rango;
        }
        
        public String getFaccion(){
            return faccion;
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
            
        public void mostrarTodo(){
            System.out.println("Nombre: "+ this.nombre + "\n" +
                    "HP: " + this.hp + "\n" +
                    "ATK: " + this.atk + "\n" +
                    "DEF: " + this.def + "\n" +
                    "SPD: " + this.spd + "\n" +
                    "Rango: " + this.rango + "\n" +
                    "Facción: " + this.faccion + "\n");
        }
        
        public void mostrarNRF(){
            System.out.println("Nombre: "+ this.nombre + "\n" +
                    "Rango: " + this.rango + "\n" +
                    "Facción: " + this.faccion + "\n");
        }
}
