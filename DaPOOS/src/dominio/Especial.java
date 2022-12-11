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
     * @param color
     * @param x
     * @param y
     */
    public Especial(String color, int x, int y) {
        super(color, x, y);
    }
}
