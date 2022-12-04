package dominio;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Ficha extends Elemento implements Serializable {

    private ImageIcon icon;

    public Ficha(String color,Integer x, Integer y) {
        super(color, x, y);
    }
    public Ficha(){}


    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    public ImageIcon getIcon(){
        return icon;
    }



}
