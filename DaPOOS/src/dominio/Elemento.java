package dominio;

import javax.swing.*;
import java.io.Serializable;
/**
 * Esta clase contiene las características de los elementos que hay en el juego de Dapoos
 * @author Erika Juliana Castro Romero y Mariana Pulido Moreno
 * @version 5.0
 */
public class Elemento implements Serializable {
    private String color;
    private Integer x;
    private Integer y;

    /**
     * Constructor de la clase Elemento
     * @param color
     * @param x
     * @param y
     */
    public Elemento(String color,Integer x, Integer y) {
        this.x = x;
        this.y = y;
        setColor(color);
    }

    /**
     * Segundo constructor de la clase Elemento
     */
    public Elemento() {
    }

    /**
     * Retorna el color del elemento
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * Asigna el color del elemento
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Retorna la posición x
     * @return x
     */
    public Integer getX() {
        return x;
    }

    /**
     * Asigna la posición x
     * @param x
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * Retorna la posición y
     * @return y
     */
    public Integer getY() {
        return y;
    }

    /**
     * Asigna la posición y
     * @param y
     */
    public void setY(Integer y) {
        this.y = y;
    }





}
