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

    public Ficha(String color,Integer x, Integer y) {
        super(color, x, y);
    }
    public Ficha(){}

    public Ficha(int x, int y) {
        super.setX(x);
        super.setY(y);
    }


    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    public ImageIcon getIcon(){
        return icon;
    }



}
