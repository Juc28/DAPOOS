package dominio;

import java.io.Serializable;

public class Jail extends Especial implements Serializable {
    public Jail(String color, int x, int y) {
        super(color, x, y);
    }
}
