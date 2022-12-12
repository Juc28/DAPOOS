package dominio;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Jail extends Especial implements Serializable {
    private int cuentaTurnosParaSalirCarcel;
    private ImageIcon imagenAntesDeCarcel;
    /**
     * Constructo de la clase Jail
     * @param x la posicion x de la casilla jail
     * @param y la posicion y de la casilla jail
     */
    public Jail( int x, int y) {
        super("BLUE", x, y);
    }


    /**
     * Le da los turnos para contar lo que faltan para salir de la casilla jail
     * @param cuentaTurnosParaSalirCarcel los turnos que han pasado desde que esta una ficha en esta casilla jair
     */
    public void setCuentaTurnosParaSalirCarcel(int cuentaTurnosParaSalirCarcel){

        this.cuentaTurnosParaSalirCarcel = cuentaTurnosParaSalirCarcel;
        setFichaEnCarcel(getFicha());

    }

    /**
     * Retorna los turnos que le faltan para poder moverse de la casilla jail
     * @return los turnos que han pasado desde que esta una ficha en esta casilla jair
     */
    public int getCuentaTurnosParaSalirCarcel(){
        return cuentaTurnosParaSalirCarcel;
    }

    /**
     * Cambia la imagen de la ficha cuando esta en una casilla de tipo jail para saber cuanto le falta para salir y que se distinga
     * @param ficha la ficha que esta en una casilla jair
     */
    private void setFichaEnCarcel(Ficha ficha){
        if(ficha != null) {
            Image imagen;
            if (cuentaTurnosParaSalirCarcel >= 2) {
                if (getFicha().getColor().equalsIgnoreCase("Rojo")) {
                    imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/fichaCarcel2R.gif")).getImage();
                } else {
                    imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/fichaCarcel2G.gif")).getImage();
                }
                Image newimag = imagen.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
                ImageIcon imagenx = new ImageIcon(newimag);
                getFicha().setIcon(imagenx);

            }else if (cuentaTurnosParaSalirCarcel == 1){
                if (getFicha().getColor().equalsIgnoreCase("Rojo")) {
                    imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/fichaCarcel1R.gif")).getImage();

                } else {
                    imagen = new ImageIcon(getClass().getResource("../presentacion/imagenes/fichaCarcel1G.gif")).getImage();
                }
                Image newimag = imagen.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
                ImageIcon imagenx = new ImageIcon(newimag);
                getFicha().setIcon(imagenx);
            }else {
                removerDeLaCarcel();
            }

        }
    }
    @Override
    public void setFicha(Ficha ficha){
        super.setFicha(ficha);
        if(ficha != null) {
            cuentaTurnosParaSalirCarcel = 2;
            imagenAntesDeCarcel = ficha.getIcon();
            setFichaEnCarcel(ficha);
        }
    }

    /**
     * Quita la ficha de la carcel depues de cumplir los 2 turnos
     */
    private void removerDeLaCarcel(){
        if(getFicha() != null){
            getFicha().setIcon(imagenAntesDeCarcel);
            cuentaTurnosParaSalirCarcel = 0;
        }


    }

}
