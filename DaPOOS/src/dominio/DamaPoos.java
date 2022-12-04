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
    private Jugador turno;
    public boolean enProgreso;


    public static void setJuego(DamaPoos juego) {
        DamaPoos.juego = juego;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }
    public Jugador getJugadorByColor(String color){
        for(Jugador jugador:jugadores){
            String getColor = jugador.getColor();
            if (getColor.equalsIgnoreCase(color)){
                return jugador;
            }
        }
        return null;
    }
    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
        for(Jugador jugador: jugadores){
            jugador.setFichas(jugador.getColor());
            if (jugador.getColor().equalsIgnoreCase("Negro")){
                turno = jugador;
            }
        }
    }
    public void setTurno(Jugador turno){this.turno = turno;}
    public Jugador getTurno(){return turno;}
    public void setTablero(){
        if (enProgreso){return;}
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
        enProgreso = true;
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
        if(fin.getFicha() != null){
            return false;
        }

        if(abs(inicio.getX() - fin.getX()) != abs(inicio.getY() - fin.getY()) ){
            return false;
        }
        if(abs(inicio.getX() - fin.getX())>2){
            return false;
        }
        Ficha pieza = inicio.getFicha();
        if(pieza.getColor().equalsIgnoreCase("Rojo") && fin.getX()>inicio.getX()){
            return false;
        }else {
            if(pieza.getColor().equalsIgnoreCase("Rojo")){
                System.out.println(inicio.getX()+":"+inicio.getY()+" - "+fin.getX()+":"+fin.getY());
            }
        }

        if(pieza.getColor().equalsIgnoreCase("Negro") && fin.getX()<inicio.getX()){
            return false;
        }else {
            if(pieza.getColor().equalsIgnoreCase("Negro")){
                System.out.println(inicio.getX()+":"+inicio.getY()+" - "+fin.getX()+":"+fin.getY());
            }
        }

        return true;
    }

    public Integer movimientoRequerido(Jugador jugador,Casilla inicio,Casilla fin){
        Integer retorno;
        System.out.println(inicio.getX()+":"+inicio.getY()+" - "+fin.getX()+":"+fin.getY());
        if(posiblesMovimientos(jugador,inicio,fin)){
            if(abs(inicio.getX() - fin.getX()) == 2 && abs(inicio.getY() - fin.getY())==2){
                int es = fin.getX()-inicio.getX();
                int es2 = es/2;
                int x = inicio.getX();
                int est = fin.getY()-inicio.getY();
                int est2 = est/2;
                int y = inicio.getY();
                int xDelMedio = abs(x+es2);
                int yDelMedio = abs(y+est2);
                System.out.println(inicio.getX()+":"+inicio.getY()+" - "+fin.getX()+":"+fin.getY());
                Casilla casillaDelMedio = getTablero()[xDelMedio][yDelMedio];
                System.out.println(casillaDelMedio.getX()+":"+casillaDelMedio.getY());
                if(casillaDelMedio.getFicha() != null) {
                     casillaDelMedio.setFicha(null);
                }
            }
            if(inicio.getFicha().getColor().equalsIgnoreCase("Negro")&& inicio.getX() == 9){
                retorno = 2;

            }
            fin.setFicha(inicio.getFicha());
            inicio.setFicha(null);
            for (Jugador siguienteJugador:jugadores){
                if(jugador.getColor() != siguienteJugador.getColor()){
                    turno = siguienteJugador;

                }
            }
            retorno = 1;
        }else {
            retorno = null;
        }

        return retorno;
    }

    public void puedeMatar(Jugador jugador,Casilla inicio,Casilla enemigo,Casilla fin){
        if (jugador.getColor() == inicio.getFicha().getColor()){
            if(enemigo.getFicha() != null && fin.getFicha() == null){
                fin.setFicha(inicio.getFicha());
            }
        }
    }
}
