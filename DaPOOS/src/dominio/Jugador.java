package dominio;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private ArrayList<Ficha> fichas;
    private int[] estado;

    public Jugador(String nombre, ArrayList<Ficha> fichas, int[] estado) {
        this.nombre = nombre;
        this.fichas = fichas;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(ArrayList<Ficha> fichas) {
        this.fichas = fichas;
    }

    public int[] getEstado() {
        return estado;
    }

    public void setEstado(int[] estado) {
        this.estado = estado;
    }
}
