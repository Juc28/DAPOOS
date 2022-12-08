package dominio;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
/**
 * Esta clase contiene las características del comodin Gun en el juego de Dapoos
 * @author Erika Juliana Castro Romero y Mariana Pulido Moreno
 * @version 5.0
 */
public class Gun extends Comodin implements Serializable {

    public Gun() {
        Image imagen;
        imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/gun.gif")).getImage();
        Image newimag = imagen.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon imagenx = new ImageIcon(newimag);
        setIcon(imagenx);
    }
}
