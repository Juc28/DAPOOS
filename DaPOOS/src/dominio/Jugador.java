package dominio;

import javax.swing.*;
import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private String color;
    private ArrayList<Ficha> fichasJugador1;
    private ArrayList<Ficha> fichasJugador2;
    private int[] estado;

    public Jugador(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public ArrayList<Ficha> getFichasJugador1() {
        fichasJugador1 = new ArrayList<Ficha>();
        for(int i=0 ; i< 20; i++){
            Ficha ficha = new Ficha();
            ficha.setIcon(new ImageIcon("src/presentacion/imagen/FichaGg"));
            fichasJugador1.add(ficha);
        }
        return fichasJugador1;
    }
    public ArrayList<Ficha> getFichasJugador2(){
        fichasJugador2 = new ArrayList<Ficha>();
        for(int i=0 ; i< 20; i++){
            Ficha ficha = new Ficha();
            ficha.setIcon(new ImageIcon("src/presentacion/imagen/FichaRr"));
            fichasJugador2.add(ficha);

            fichasJugador2.add(ficha);
        }
        return  fichasJugador2;
    }



    public int[] getEstado() {
        return estado;
    }

    public void setEstado(int[] estado) {
        this.estado = estado;
    }
}
