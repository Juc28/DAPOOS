package dominio;

import java.io.Serializable;
import java.util.ArrayList;

public class Maquina extends Jugador implements Serializable {

    public Maquina(String nombre, String color) {
        super(nombre, color);
    }
}
