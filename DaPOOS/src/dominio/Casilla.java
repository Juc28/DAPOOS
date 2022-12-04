package dominio;

import java.io.Serializable;

public class Casilla extends Elemento implements Serializable {
    private Ficha ficha;
    public Casilla(String color, int x, int y) {
        super(color,x, y);

    }

    public void setFicha(Ficha ficha){
        this.ficha = ficha;
    }

    public Ficha getFicha(){return ficha;}
}
