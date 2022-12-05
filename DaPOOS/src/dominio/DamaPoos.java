package dominio;

import javax.swing.*;
import java.awt.*;
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

    public int porcentajeCasillasEspeciales;
    public boolean permitirEspeciales;
    public int formaDeAparecen;


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
        if(permitirEspeciales){
            int casillasAleatorio = getNumerosAleatorias(0,porcentajeCasillasEspeciales/2);
            while (casillasAleatorio > 0){
                int xAleatorio = getNumerosAleatorias(0,9);
                int yAleatorio = getNumerosAleatorias(0,9);
                Casilla casillaMine = tablero[xAleatorio][yAleatorio];
                if(!casillaMine.getColor().equalsIgnoreCase("Blanco")){
                    Mine mine = new Mine(xAleatorio,yAleatorio);
                    tablero[xAleatorio][yAleatorio] = mine;
                    casillasAleatorio--;
                }

            }
        }

        ponerFichas();
        enProgreso = true;
    }
    public int getNumerosAleatorias(int min, int max) { return (int) ((Math.random() * (max - min)) + min); }
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
        if (fin.getColor().equalsIgnoreCase("Blanco")){
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
        //No se puede mover mas de dos casillas
        if(abs(inicio.getX() - fin.getX())>2){
            return false;
        }
        Ficha pieza = inicio.getFicha();
        if(pieza instanceof Ninja ){
            System.out.println("Se pudo mover");
        }
        else  {
            //La ficha roja no se puede mover de para atras
            if (pieza.getColor().equalsIgnoreCase("Rojo") && fin.getX() > inicio.getX()) {
                return false;
            }
            //La ficha negra no se puede mover de para atras
            if (pieza.getColor().equalsIgnoreCase("Negro") && fin.getX() < inicio.getX()) {
                return false;
            }
        }

        return true;
    }
    public void eliminarFichas(Casilla casilla){
        if(casilla.getFicha() != null){
            casilla.setFicha(null);
        }
    }
    public int movimientoRequerido(Jugador jugador,Casilla inicio,Casilla fin){
        int retorno = 0;
        System.out.println(inicio.getX()+":"+inicio.getY()+" - "+fin.getX()+":"+fin.getY());
        if(posiblesMovimientos(jugador,inicio,fin)){
            if (fin instanceof Mine){
                retorno = 3; // 3 es que cayo en una casilla Mine
                Casilla matadaPorMina1 = getTablero()[abs(fin.getX()-1)][abs(fin.getY()-1)];
                Casilla matadaPorMina2 = getTablero()[abs(fin.getX()-1)][abs(fin.getY()+1)];
                Casilla matadaPorMina3 = getTablero()[abs(fin.getX()+1)][abs(fin.getY()-1)];
                Casilla matadaPorMina4 = getTablero()[abs(fin.getX()+1)][abs(fin.getY()+1)];
                eliminarFichas(matadaPorMina1);
                eliminarFichas(matadaPorMina2);
                eliminarFichas(matadaPorMina3);
                eliminarFichas(matadaPorMina4);
                eliminarFichas(fin);
                eliminarFichas(inicio);
                cambioTurno();
                return 3;

            }
            //La ficha salto una casilla
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
                //Verifica si hay una ficha en la casilla y la elimina
                if(casillaDelMedio.getFicha() != null) {
                    //Excepto si es Ninja cuando tiene una vida
                    if(casillaDelMedio.getFicha() instanceof Ninja){
                        Ninja soyNinja = (Ninja) casillaDelMedio.getFicha();
                        if (soyNinja.getTieneVidas()){
                            soyNinja.removerVidas();
                        }else {
                            eliminarFichas(casillaDelMedio); // Elimina la ficha del juego
                        }
                    }else {
                        eliminarFichas(casillaDelMedio);
                    }
                }
            }
            //Si la ficha es negra y esta en la ultima linea (primera linea del competidor)
            if(inicio.getFicha().getColor().equalsIgnoreCase("Negro")&& fin.getX() == 9){
                retorno = 2; // 2 significa que la ficha esta en la ultima linea
            }
            if(inicio.getFicha().getColor().equalsIgnoreCase("Rojo")&& fin.getX() == 0){
                retorno = 2; // 2 significa que la ficha esta en la ultima linea
            }
            fin.setFicha(inicio.getFicha());
            inicio.setFicha(null);
            cambioTurno();
            retorno = (retorno == 0) ? 1 : retorno; // 1 significa que la ficha se movio
        }else {
            retorno = 0; // 0 significa que la ficha no se movio
        }

        return retorno;
    }

    public void fichaSeConvierteEnEspecial(Casilla casilla,int tipo){
        Image imagen;
        // 0 es Ninja, 1 es Reina y 2 Zombie
        if(tipo == 0){
            Ninja fichaNinja = new Ninja(casilla.getFicha().getColor(), casilla.getX(),casilla.getY());
            if(casilla.getFicha().getColor().equalsIgnoreCase("Negro")){
                imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/FichaNinjaG.gif")).getImage();
            }else {
                imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/FichaNinjaR.gif")).getImage();
            }
            Image newimag = imagen.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            ImageIcon imagenx = new ImageIcon(newimag);
            fichaNinja.setIcon(imagenx);
            casilla.setFicha(fichaNinja);
            System.out.println(casilla.getFicha().getClass());

        }

    }
    public void cambioTurno(){
        for (Jugador siguienteJugador:getJugadores()){
            if(turno.getColor() != siguienteJugador.getColor()){
                turno = siguienteJugador;
                return;
            }
        }
    }

    public void noVerCasillaEspeciales(){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                Casilla casilla = tablero[i][j];
                if(casilla instanceof Mine){
                    casilla.setColor("Negro");
                }
            }
        }
    }
}
