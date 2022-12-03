package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DamaPoos implements Serializable {
    private static DamaPoos juego = null;
    private Ficha[][] tablero;
    private Jugador[] jugadores;
    private Elemento[] elementos;
    private ArrayList<Comodin> comodines;
    private ArrayList<Casilla> casillas;
    private ArrayList<Ficha> fichasJugador1;
    private ArrayList<Ficha> fichasJugador2;

    public static void setJuego(DamaPoos juego) {
        DamaPoos.juego = juego;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
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
    public static DamaPoos getJuego(){
        if(juego == null){
            juego = new DamaPoos();
        }
        return juego;
    }
    public static void nuevoJuego(){
        juego = new DamaPoos();
    }

    public boolean posiblesMovimientos(Jugador jugador,int x,int y,int x1,int y1) {
        boolean seMovio = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tablero = new Ficha[i][j];
                if((((i == 0)  || (i==2))&&(j % 2 != 0)) || (((i == 1)|| (i==3))&&(j % 2 == 0))){
                    fichasJugador1 = jugador.getFichasJugador1();
                    for (int z=0;z<20;z++){
                        tablero[i][j] = fichasJugador1.get(z);
                    }
                }
                if ((((i == 8)  || (i==6))&&(j % 2 != 0)) || (((i == 7)|| (i==9))&&(j % 2 == 0))){
                    fichasJugador2 = jugador.getFichasJugador2();
                    for (int l=0;l<20;l++){
                        tablero[i][j] = fichasJugador2.get(l);
                    }
                }
                if(tablero[x1][y1]!= null || tablero[x][y] != tablero[x1][y1]  ){
                    seMovio = true;
                }if(((((i == 0) || (i==2))&&(j % 2 != 0)) || (((i == 1)|| (i==3))&&(j % 2 == 0))) && (((((i == 8)  || (i==6))&&(j % 2 != 0)) || (((i == 7)|| (i==9))&&(j % 2 == 0))))){
                    seMovio = true;
                }
            }
        }
        return seMovio;
    }


}
