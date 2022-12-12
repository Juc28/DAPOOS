package dominio;

import java.io.Serializable;
/**
 * Esta clase contiene las características de una casilla especial  en el juego de Dapoos
 * @author Erika Juliana Castro Romero y Mariana Pulido Moreno
 * @version 5.0
 */
public class Especial extends Casilla implements Serializable {
    /**
     * Constructor de la clase especial
     * @param color que va tener la casilla especial
     * @param x la posicion x de la casilla especial
     * @param y la posicion y de la casilla especial
     */
    public Especial(String color, int x, int y) {
        super(color, x, y);
    }
}
