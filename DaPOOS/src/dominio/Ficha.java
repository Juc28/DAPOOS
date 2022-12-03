package dominio;

import javax.swing.*;
import java.awt.*;

public class Ficha extends Elemento {

    private ImageIcon icon;
    private String[][] tablero;

    public Ficha(int x, int y, String imagen) {
        super( x, y, imagen);
    }
    public Ficha(){}


    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }




}
