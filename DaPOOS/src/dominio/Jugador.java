package dominio;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Jugador implements Serializable {
    private String nombre;
    private String color;
    private ArrayList<Ficha> fichas;
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

    public String getColor(){return color;}

    public void setColor(String color){this.color = color;}
    public void setFichas(String color) {
        fichas = new ArrayList<Ficha>();
        for(int i=0 ; i< 20; i++){
            Ficha ficha = new Ficha();
            ficha.setColor(color);
            Image imagen;
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            if(color.equalsIgnoreCase("Negro")){
                imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/FichaGg.gif")).getImage();
            }else {
                imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/FichaRr.gif")).getImage();
            }
            Image newimag = imagen.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            ImageIcon imagenx = new ImageIcon(newimag);
            ficha.setIcon(imagenx);
            fichas.add(ficha);
        }
    }
    public ArrayList<Ficha> getFichas(){return fichas;}
    public Ficha getFichaNoEnTablero(){
        for (Ficha ficha:fichas){
            if(ficha.getX() == null && ficha.getY() == null){
                return ficha;
            }
        }
        return null;
    }

    public int[] getEstado() {
        return estado;
    }

    public void setEstado(int[] estado) {
        this.estado = estado;
    }
}
