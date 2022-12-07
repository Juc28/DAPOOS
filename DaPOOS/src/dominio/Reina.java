package dominio;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Reina extends Ficha implements Serializable {
    /**
     * Constructor de la clase Reina
     * @param color
     * @param x
     * @param y
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
