package dominio;

import java.io.Serializable;

/**
 * Esta clase contiene las características de una casilla en el juego de Dapoos
 * @author Erika Juliana Castro Romero y Mariana Pulido Moreno
 * @version 5.0
 */
public class Casilla extends Elemento implements Serializable {
    private Ficha ficha;

    /**
     * Constructor de la clase Casilla
     * @param color que va tener la casilla
     * @param x posicion x de esta
     * @param y posicion y de esta
     */
    public Casilla(String color, int x, int y) {
        super(color,x, y);

    }

    /**
     * Coloca la ficha en un casilla
     * @param ficha la ficha que se va colocar en la casilla
     */
    public void setFicha(Ficha ficha){
        this.ficha = ficha;
    }

    /**
     * Retorna la ficha que esta en la casilla
     * @return la ficha que tiene en la casilla
     */

    public Ficha getFicha(){return ficha;}
}
