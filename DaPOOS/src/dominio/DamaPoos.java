package dominio;

import presentacion.Tablero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

import static java.lang.Math.abs;

public class DamaPoos implements Serializable {
    private static DamaPoos juego = null;
    private Casilla[][] tablero;
    private Jugador[] jugadores;
    private Elemento[] elementos;
    private ArrayList<Comodin> comodines;
    private ArrayList<Casilla> casillas;


    public static void setJuego(DamaPoos juego) {
        DamaPoos.juego = juego;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }
    public Jugador getJugadorByColor(String color){
        for(Jugador jugador:jugadores){
            if (jugador.getColor()==color){
                return jugador;
            }
        }
        return null;
    }
    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
        for(Jugador jugador: jugadores){
            jugador.setFichas(jugador.getColor());
            System.out.println("JUliana");
        }
    }

    public void setTablero(){
        tablero = new Casilla[10][10];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (((j % 2 == 0) && (i % 2 != 0)) || ((j % 2 != 0) && (i % 2 == 0))) {
                    Casilla casilla = new Casilla("Negro",i,j);
                    tablero[i][j] = casilla;
                }else {
                    Casilla casilla = new Casilla("Blanco",i,j);
                    tablero[i][j] = casilla;
                }
            }

        }
        ponerFichas();
    }
    public Casilla[][] getTablero(){
        return tablero;
    }
    public void ponerFichas(){
        Jugador jugadorNegro = getJugadorByColor("Negro");
        Jugador jugadorRojo = getJugadorByColor("Rojo");
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if ((((i == 0)  || (i==2))&&(j % 2 != 0)) || (((i == 1)|| (i==3))&&(j % 2 == 0))){
                    Ficha ficha = jugadorNegro.getFichaNoEnTablero();
                    tablero[i][j].setFicha(ficha);
                    ficha.setX(i);
                    ficha.setY(j);
                }if ((((i == 8)  || (i==6))&&(j % 2 != 0)) || (((i == 7)|| (i==9))&&(j % 2 == 0))){
                    Ficha ficha = jugadorRojo.getFichaNoEnTablero();
                    tablero[i][j].setFicha(ficha);
                    ficha.setX(i);
                    ficha.setY(j);
                }
            }
        }
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
    private static List<Ficha> fichas;


    public DamaPoos(){

    }


    private boolean posiblesMovimientos(Jugador jugador,Casilla inicio,Casilla fin) {

        if(jugador.getColor() != inicio.getFicha().getColor()){
            return false;
        }
        if (fin.getColor() != "Negro"){
            return false;
        }
        if(inicio.getFicha() == null){
            return false;
        }
        if(abs(inicio.getY() - fin.getY()) > 1){
            return false;
        }
        return true;
    }

    public void movimientoRequerido(Jugador jugador,Casilla inicio,Casilla fin){
        if(posiblesMovimientos(jugador,inicio,fin)){
            fin.setFicha(inicio.getFicha());
            inicio.setFicha(null);
        }
    }
}
