package dominio;

import java.io.Serializable;
import java.util.ArrayList;

public class DamaPoos implements Serializable {
    private static DamaPoos juego = null;
    private Jugador[] jugadores;
    private Ficha[] fichasNegras;
    private Ficha[] fichasRojas;
    private int tablero[][] ;
    private Elemento[] elementos;
    private ArrayList<Comodin> comodines;
    private ArrayList<Casilla> casillas;

    public static void setJuego(DamaPoos juego) {
        DamaPoos.juego = juego;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
    }

    public Ficha[] getFichasNegras() {
        return fichasNegras;
    }

    public void setFichasNegras(Ficha[] fichasNegras) {
        this.fichasNegras = fichasNegras;
    }
    public Ficha[] getFichasRojas() {
        return fichasRojas;
    }

    public void setFichasRojas(Ficha[] fichasRojas) {
        this.fichasRojas = fichasRojas;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void setTablero(int[][] tablero) {
        this.tablero = tablero;
    }

    public Elemento[] getElementos() {
        return elementos;
    }

    public void setElementos(Elemento[] elementos) {
        this.elementos = elementos;
    }

    public ArrayList<Comodin> getComodines() {
        return comodines;
    }

    public void setComodines(ArrayList<Comodin> comodines) {
        this.comodines = comodines;
    }

    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }

    public void setCasillas(ArrayList<Casilla> casillas) {
        this.casillas = casillas;
    }

    public DamaPoos(){
        casillas = new ArrayList<Casilla>();
        this.elementos = elementos;
        tablero = new int[10][10];
    }
    public static DamaPoos getJuego(){
        if(juego == null){
            juego = new DamaPoos();
        }
        return juego;
    }
    public static void nuevoJuego(){
        juego = new DamaPoos();
    }


}
