package dominio;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
/**
 * Esta clase contiene las características de una ficha en el juego de Dapoos
 * @author Erika Juliana Castro Romero y Mariana Pulido Moreno
 * @version 5.0
 */

public class Ficha extends Elemento implements Serializable {

    private ImageIcon icon;
    public Boolean fichaEnJuego = true;

    /**
     * Constructo de la clase Ficha
     * @param color
     * @param x
     * @param y
     */
    public Ficha(String color,Integer x, Integer y) {
        super(color, x, y);
    }

    /**
     * Segundo constructor de la clase ficha
     */
    public Ficha(){}

    /**
     * Tercer constructor de la clase ficha
     * @param x
     * @param y
     */
    public Ficha(int x, int y) {
        super.setX(x);
        super.setY(y);
    }

    /**
     * Coloca la imagen correspondiente a la ficha
     * @param icon
     */
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    /**
     * Restorna la imagen que tiene la ficha
     * @return
     */
    public ImageIcon getIcon(){
        return icon;
    }



}
