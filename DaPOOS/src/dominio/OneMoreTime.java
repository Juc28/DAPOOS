package dominio;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
/**
 * Esta clase contiene las características del comodin oneMoreTime en el modo QuickTime en el juego de Dapoos
 * @author Erika Juliana Castro Romero y Mariana Pulido Moreno
 * @version 5.0
 */
public class OneMoreTime extends Comodin implements Serializable {
    /**
     * Constructor de la clase OneMoreTime
     */
    public OneMoreTime()  {

        Image imagen;
        imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/OneMoreTime.gif")).getImage();
        Image newimag = imagen.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon imagenx = new ImageIcon(newimag);
        setIcon(imagenx);
    }
}
