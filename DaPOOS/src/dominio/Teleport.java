package dominio;

import java.io.Serializable;

public class Teleport extends Especial implements Serializable {
    public Teleport(String color, int x, int y) {
        super(color, x, y);
    }
}
