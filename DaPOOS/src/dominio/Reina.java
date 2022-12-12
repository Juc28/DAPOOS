package dominio;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
/**
 * Esta clase contiene las características de una ficha reina que es un tipo especial en el juego de Dapoos
 * @author Erika Juliana Castro Romero y Mariana Pulido Moreno
 * @version 5.0
 */
public class Reina extends Ficha implements Serializable {
    /**
     * Constructor de la clase Reina
     * @param color que va tener la ficha reina
     * @param x la posicion x de la ficha reina
     * @param y la posicion y de la ficha reina
     */
    public Reina(String color, int x, int y) {
        super(color, x, y);
        Image imagen;
        if(color.equalsIgnoreCase("Negro")){
            imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/FichaReinaG.gif")).getImage();
        }else {
            imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/FichaReinaR.gif")).getImage();
        }
        Image newimag = imagen.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon imagenx = new ImageIcon(newimag);
        setIcon(imagenx);
    }
}
