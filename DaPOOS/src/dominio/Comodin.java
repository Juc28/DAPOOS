package dominio;

import java.io.Serializable;

public class Comodin extends Casilla implements Serializable {
    public Comodin(String color, int x, int y) {
        super(color, x, y);
    }
}
