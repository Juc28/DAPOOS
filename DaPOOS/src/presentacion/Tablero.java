package presentacion;
//clase
import dominio.DamaPoos;
import dominio.Elemento;
import dominio.Ficha;
import dominio.VariablesConstantes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Tablero extends JPanel {
    private List<Ficha> fichas = DamaPoos.getJuego().getFichas(VariablesConstantes.COLOR_FICHA_NEGRA);
    protected JButton terminar;
    private JButton[][] boton;
    private BufferedImage fondo;


    public Tablero(CardLayout layout) {
        super(layout);
        removeAll();
        setFondo(PantallaInicio.fondoI3);
        //prepararElementosTerminar();
        //prepareElementosInformacion();
        crearBotonesJuego(fichas);
    }

    private void crearBotonesJuego(List<Ficha> fichas) {
        Image imagen = new ImageIcon(getClass().getResource("imagenes/EspacionNegro.png")).getImage();
        Image newimag = imagen.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon imagenx = new ImageIcon(newimag);
        JPanel juego = new JPanel(new GridLayout(10, 10));
        boton = new JButton[10][10];
        juego.setBounds(5, 10, 700, 500);
        juego.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        for (int i = 0; i < boton.length; i++) {
            for (int j = 0; j < boton[0].length; j++) {
                boton[i][j] = new JButton();
                boton[i][j].setBackground(Color.WHITE);
                boton[i][j].setBorderPainted(false);
                boton[i][j].setContentAreaFilled(false);
                boton[i][j].setOpaque(false);
                juego.add(boton[i][j]);

                if (((j % 2 == 0) && (i % 2 != 0)) || ((j % 2 != 0) && (i % 2 == 0))) {
                    boton[i][j].setIcon(imagenx);
                }if ((((i == 0)  || (i==2))&&(j % 2 != 0)) || (((i == 1)|| (i==3))&&(j % 2 == 0))){
                    Ficha ficha = new Ficha();
                    ficha.setImagen("src/presentacion/imagenes/fichaNn.jpg");
                    //boton[i][j].setIcon(new ImageIcon(ficha.getImagen()));
                    boton[i][j].setIcon(new ImageIcon("src/presentacion/imagenes/fichaNn.jpg"));
                }
            }

        }
        add(juego, BorderLayout.CENTER);
        repaint();
    }


    void prepareElementosInformacion() {
        JLabel c = new JLabel("Información del juego actual");
        c.setBounds(720, 5, 220, 20);
        add(c);
        JLabel inf1 = new JLabel("Jugador 1");
        inf1.setBounds(720, 30, 220, 20);
        add(inf1);
        JLabel inf2 = new JLabel("Jugador 2");
        inf2.setBounds(720, 125, 220, 20);
        add(inf2);
        JLabel turno = new JLabel("TURNO");
        turno.setBounds(720, 220, 220, 20);
        add(turno);
    }

    private void prepararElementosTerminar() {
        terminar = new JButton("Terminar Juego");
        terminar.setBounds(720, 400, 140, 40);
        terminar.setIcon(new ImageIcon("src/presentacion/imagenes/terminar.png"));
        add(terminar);
    }

    private void setFondo(String root) {
        try {
            fondo = ImageIO.read(new File(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

