package tarea_programación;
import java.util.Random;
/**
 *
 * @author Alfredo Mendez
 */
public class Luchador {
    private String nombre;
    private int hp;
    private int atk;
    private int def;
    private int spd;
    private int rango;
    private String faccion;
    
    public static void main(String[] args) {
        Luchador luchador = new Luchador();
        
        System.out.println(luchador);
    }
    
    public Luchador(String nombre, int hp, int atk, int def, int spd, int rango, String faccion){
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
    }
        
    public int generarAleatorio(int min, int max){
        Random aleatorio = new Random();
          
        int rango = max-min;
            
        int azar = aleatorio.nextInt(rango+1)+min;
        return azar;
    }
        
    public String[] listaNombre(){
        String[] lista = {"Tonyo Ers","Pfeifer","Lautaro","Escorpión","Gabri-El","Onyot","Oratual","Lobezno","Manu-el","Lebrecht","Tcherbel","Zap","Barrabas","Yisus","Messi"};
        return lista;
    }
        
    public String[] listaFaccion(){
        String[] lista = {"Fuego","Agua","Tierra"};
        return lista;
    }
        
    public String elegirLista(String[] matriz){
        int posicion = generarAleatorio(0,matriz.length-1);
        String datoMostrar = matriz[posicion];
        
        return datoMostrar;
    }
        
    public int determinarEstrella(){
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
        
    @Override
    public String toString(){
        return "Nombre: "+ this.nombre + "\n" +
                "HP: " + this.hp + "\n" +
                "ATK: " + this.atk + "\n" +
                "DEF: " + this.def + "\n" +
                "SPD: " + this.spd + "\n" +
                "Rango: " + this.rango + "\n" +
                "Facción: " + this.faccion;
    }
}
