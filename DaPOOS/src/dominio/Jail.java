package dominio;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Jail extends Especial implements Serializable {
    public Jail( int x, int y) {
        super("BLUE", x, y);
    }
    private int cuentaTurnosParaSalirCarcel;
    private ImageIcon imagenAntesDeCarcel;


    public void setCuentaTurnosParaSalirCarcel(int cuentaTurnosParaSalirCarcel){

        if(cuentaTurnosParaSalirCarcel >= 2){
            this.cuentaTurnosParaSalirCarcel = 2;
            setFichaEnCarcel(getFicha());

        }else {
            this.cuentaTurnosParaSalirCarcel = cuentaTurnosParaSalirCarcel;
            setFichaEnCarcel(getFicha());
        }
    }

    public int getCuentaTurnosParaSalirCarcel(){
        return cuentaTurnosParaSalirCarcel;
    }

    private void setFichaEnCarcel(Ficha ficha){
        if(ficha != null) {
            Image imagen;
            if (cuentaTurnosParaSalirCarcel == 2) {
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

    private void removerDeLaCarcel(){
        if(getFicha() != null){
            getFicha().setIcon(imagenAntesDeCarcel);
            cuentaTurnosParaSalirCarcel = 0;
        }


    }

}
