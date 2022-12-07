package dominio;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Zombie extends Ficha implements Serializable {
    /**
     * Construtor de la clase Zombie
     * @param color
     * @param x
     * @param y
     */
    public Zombie(String color,int x, int y) {
        super(color,x, y);
        Image imagen;
        if(color.equalsIgnoreCase("Negro")){
            imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/FichaZombieG.gif")).getImage();
        }else {
            imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/FichaZombierR.gif")).getImage();
        }
        Image newimag = imagen.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon imagenx = new ImageIcon(newimag);
        setIcon(imagenx);
    }
}
