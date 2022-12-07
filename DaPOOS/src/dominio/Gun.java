package dominio;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Gun extends Comodin implements Serializable {

    public Gun() {
        Image imagen;
        imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/gun.gif")).getImage();
        Image newimag = imagen.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon imagenx = new ImageIcon(newimag);
        setIcon(imagenx);
    }
}
