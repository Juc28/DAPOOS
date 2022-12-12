package dominio;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Esta clase contiene las características del jugador en el juego de Dapoos
 * @author Erika Juliana Castro Romero y Mariana Pulido Moreno
 * @version 5.0
 */
public class Jugador implements Serializable {
    private String nombre;
    private String color;
    private ArrayList<Ficha> fichas;

    /**
     * Constructor de la case jugador
     * @param nombre que tiene el jugador
     * @param color que tienen las fichas del jugador
     */
    public Jugador(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;
    }

    /**
     * Retorna el color de las fichas que tiene el jugador
     * @return color que tienen las fichas del jugador
     */
    public String getColor(){return color;}

    /**
     * Retorna el nombre del jugador
     * @return nombre que tiene el jugador
     */
    public String getNombre(){return nombre;}
    /**
     * Le da el color a las ficahs del jugador
     * @param color que tienen las fichas del jugador
     */
    public void setColor(String color){this.color = color;}

    /**
     * Le da las fichas a cada jugador depentiendo el color que escoja el jugador
     * @param color que tienen las fichas del jugador
     */
    public void setFichas(String color) {
        fichas = new ArrayList<Ficha>();
        for(int i=0 ; i< 20; i++){
            Ficha ficha = new Ficha();
            ficha.setColor(color);
            Image imagen;
            //System.out.println("Working Directory = " + System.getProperty("user.dir"));
            if(color.equalsIgnoreCase("Negro")){
                imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/FichaGg.gif")).getImage();
            }else {
                imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/FichaRr.gif")).getImage();
            }
            Image newimag = imagen.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            ImageIcon imagenx = new ImageIcon(newimag);
            ficha.setIcon(imagenx);
            fichas.add(ficha);
        }
    }

    /**
     * Retornas las fichas que tiene el jugador
     * @return El arreglo de fichas que tiene los jugadores
     */
    public ArrayList<Ficha> getFichas(){return fichas;}

    /**
     * Retorna las fichas del jugador que no han puesto en el tablero
     * @return ficha que no se ha colocado en el tablero
     */
    public Ficha getFichaNoEnTablero(){
        for (Ficha ficha:fichas){
            if(ficha.getX() == null && ficha.getY() == null){return ficha;}
        }
        return null;
    }

    /**
     * Cuenta cuantas fichas no han sido eliminadas
     * @return las fichas que aun no han sido eliminadas
     */
    public int contarFichasEnJuego(){
        int contadorFichasEnJuego= 0;
        for (Ficha ficha:fichas){
            if(ficha.fichaEnJuego){
                contadorFichasEnJuego++;
            }
        }
        return contadorFichasEnJuego;
    }

}
