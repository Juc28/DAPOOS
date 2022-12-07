package dominio;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Ninja extends Ficha implements Serializable {
    public Ninja(String color,int x, int y) {
        super(color, x, y);
        Image imagen;
        if(color.equalsIgnoreCase("Negro")){
            imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/FichaNinjaG.gif")).getImage();
        }else {
            imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/FichaNinjaR.gif")).getImage();
        }
        Image newimag = imagen.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon imagenx = new ImageIcon(newimag);
        setIcon(imagenx);

    }

    private boolean tieneVidas = true;

    public boolean getTieneVidas(){
        return tieneVidas;
    }
    public void removerVidas(){
        tieneVidas = false;
        Image imagen;
        if(getColor().equalsIgnoreCase("Negro")){
            imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/FichaNinjaSinvidaG.gif")).getImage();
        }else {
            imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/FichaNinjaSinvidaR.gif")).getImage();
        }
        Image newimag = imagen.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon imagenx = new ImageIcon(newimag);
        setIcon(imagenx);
    }


    }

