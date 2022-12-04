package dominio;

import java.io.Serializable;

public class Especial extends Casilla implements Serializable {
    public Especial(String color, int x, int y) {
        super(color, x, y);
    }
}
