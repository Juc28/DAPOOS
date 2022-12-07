package dominio;

import java.io.Serializable;

public class Casilla extends Elemento implements Serializable {
    private Ficha ficha;
    public Casilla(String color, int x, int y) {
        super(color,x, y);

    }

    /**
     * Coloca la ficha en un casilla
     * @param ficha
     */
    public void setFicha(Ficha ficha){
        this.ficha = ficha;
    }

    /**
     * Retorna la ficha que esta en la casilla
     * @return
     */
    public Ficha getFicha(){return ficha;}
}
