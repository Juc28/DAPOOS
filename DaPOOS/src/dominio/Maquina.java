package dominio;

import java.io.Serializable;


public class Maquina extends Jugador implements Serializable {

    public Maquina(String nombre, String color) {
        super(nombre, color);
    }
}
