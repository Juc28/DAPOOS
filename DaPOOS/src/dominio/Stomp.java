package dominio;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Stomp extends Comodin implements Serializable {

    public Stomp() {
        Image imagen;
        imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/stomp.gif")).getImage();
        Image newimag = imagen.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon imagenx = new ImageIcon(newimag);
        setIcon(imagenx);
    }
}
