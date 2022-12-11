package dominio;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
/**
 * Esta clase contiene las características del comodin stomp en el juego de Dapoos
 * @author Erika Juliana Castro Romero y Mariana Pulido Moreno
 * @version 5.0
 */
public class Stomp extends Comodin implements Serializable {
    /**
     * Constructor de la clase stomp
     */
    public Stomp() {
        Image imagen;
        imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/stomp.gif")).getImage();
        Image newimag = imagen.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon imagenx = new ImageIcon(newimag);
        setIcon(imagenx);
    }
}
