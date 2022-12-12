package dominio;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
/**
 * Esta clase contiene las características de una ficha ninja que es un tipo especial en el juego de Dapoos
 * @author Erika Juliana Castro Romero y Mariana Pulido Moreno
 * @version 5.0
 */
public class Ninja extends Ficha implements Serializable {
    private boolean tieneVidas = true;
    /**
     * Constructor de la clase ninja
     * @param color  que va tener la ficha ninja
     * @param x la posicion x de la ficha ninja
     * @param y la posicion y de la ficha ninja
     */
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

    /**
     * Retorna si esta ficha perdio la vida
     * @return si aun tiene vidas la ficha ninja
     */
    public boolean getTieneVidas(){
        return tieneVidas;
    }

    /**
     * Verifica si la ficha ninja ya perdio una vez la vida para cambiar y saber que solo tiene otra vida más
     */
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

