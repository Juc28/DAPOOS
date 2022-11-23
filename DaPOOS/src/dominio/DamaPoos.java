package dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    private static List<Ficha> fichas;

    public DamaPoos(){
        fichas = getFichas(VariablesConstantes.COLOR_FICHA_NEGRA);
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

    public List<Ficha> getFichas(String color){
        List<Ficha> fichas = new ArrayList<>();
        for(int i=0 ; i< 20; i++){
            Ficha ficha = new Ficha();
            if(color == "negro"){
                ficha.setColor("negro");
                ficha.setImagen("src/presentacion/imagenes/fichaNn.jpg");
            }
            fichas.add(ficha);
        }
        return fichas;
    }


}
