package dominio;

import javax.swing.*;
import java.awt.*;

public class Ficha extends Elemento {

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
