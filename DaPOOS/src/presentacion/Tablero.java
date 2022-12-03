package presentacion;
import dominio.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tablero extends JPanel {
    //private List<Ficha> fichas = DamaPoos.getJuego().getFichas(VariablesConstantes.COLOR_FICHA_NEGRA);
    protected JButton terminar;
    private JButton[][] boton;
    private BufferedImage fondo;
    private DamaPoos juego;


    public Tablero(CardLayout layout,DamaPoos juego) {
        super(layout);
        this.juego = juego;
        juego.setTablero();
        removeAll();
        setFondo(PantallaInicio.fondoI3);
        //prepararElementosTerminar();
        //prepareElementosInformacion();
        crearTablero();
    }
    BoardSquare casillaInicial;
    public class BoardSquare extends JButton{
        public Casilla casilla;
        public int x;
        public int y;
        public Ficha ficha;
        public Color emptyColor;
    }
    private void crearTablero() {
        JPanel juegoPanel = new JPanel(new GridLayout(10, 10));
        boton = new BoardSquare[10][10];
        Color colorNegro = new Color(50,35,9);
        juegoPanel.setBounds(5, 10, 700, 500);
        juegoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        for (int i = 0; i < juego.getTablero().length; i++) {
            for (int j = 0; j < juego.getTablero()[0].length; j++) {
                BoardSquare boardSquare = new BoardSquare();
                boardSquare.x = i;
                boardSquare.y = j;
                boardSquare.casilla = juego.getTablero()[i][j];
                boardSquare.setBorderPainted(false);
                boardSquare.setContentAreaFilled(false);
                boardSquare.setOpaque(true);
                boardSquare.emptyColor = colorNegro;
                boardSquare.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        BoardSquare casillaFutura = (BoardSquare)e.getSource();
                        if(casillaFutura.ficha == null){
                            System.out.println("No hay una ficha");
                            if(casillaInicial != null){
                                juego.movimientoRequerido(juego.getJugadorByColor("Negro"),casillaInicial.casilla,casillaFutura.casilla);
                                removeAll();
                                repaint();
                                crearTablero();
                            }
                        }else {
                            casillaInicial = (casillaFutura == casillaInicial)? null: casillaFutura;
                        }
                    }
                });
                Casilla casilla = juego.getTablero()[i][j];
                if (casilla.getColor() == "Negro") {
                    boardSquare.setBackground(colorNegro);
                    boardSquare.emptyColor = colorNegro;
                }if (casilla.getFicha() != null){
                    boardSquare.ficha = casilla.getFicha();
                    boardSquare.setIcon(casilla.getFicha().getIcon());
                }

                boton[i][j] = boardSquare;
                juegoPanel.add(boton[i][j]);
            }

        }
        add(juegoPanel, BorderLayout.CENTER);
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

