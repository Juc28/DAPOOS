package dominio;

import java.awt.*;
import java.io.Serializable;
/**
 * Esta clase contiene las características de una casilla mine que es un tipo especial de casilla en el juego de Dapoos
 * @author Erika Juliana Castro Romero y Mariana Pulido Moreno
 * @version 5.0
 */
public class Mine extends Especial implements Serializable {
    /**
     * Constructor de la clase Mine
     * @param x la posicion x de la mine
     * @param y la posicion y de la mine
     */
    public Mine( int x, int y) {
        super("ORANGE", x, y);
    }

}
