package dominio;

import java.io.Serializable;

public class Mine extends Especial implements Serializable {

    public Mine(String color, int x, int y) {
        super(color, x, y);
    }
}
