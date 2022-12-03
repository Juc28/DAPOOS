package presentacion;
import dominio.DamaPoos;
import dominio.Elemento;
import dominio.Ficha;
import dominio.VariablesConstantes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Tablero extends JPanel {
    //private List<Ficha> fichas = DamaPoos.getJuego().getFichas(VariablesConstantes.COLOR_FICHA_NEGRA);
    protected JButton terminar;
    private JButton[][] boton;
    private BufferedImage fondo;


    public Tablero(CardLayout layout,DamaPoos juego) {
        super(layout);
        juego.setTablero();
        removeAll();
        setFondo(PantallaInicio.fondoI3);
        //prepararElementosTerminar();
        //prepareElementosInformacion();
        crearTablero();
    }
    BoardSquare previouslySelected;
    public class BoardSquare extends JButton{
        public int x;
        public int y;
        public Ficha ficha;
        public Color emptyColor;
    }
    private void crearTablero() {
        Image imagen = new ImageIcon(getClass().getResource("imagenes/FichaGg.gif")).getImage();
        Image newimag = imagen.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon imagenx = new ImageIcon(newimag);
        Image imagen1 = new ImageIcon(getClass().getResource("imagenes/FichaRr.gif")).getImage();
        Image newimag1 = imagen1.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        ImageIcon imagenx1 = new ImageIcon(newimag1);
        JPanel juego = new JPanel(new GridLayout(10, 10));
        boton = new BoardSquare[10][10];
        Color color1 = new Color(50,35,9);
        juego.setBounds(5, 10, 700, 500);
        juego.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        for (int i = 0; i < boton.length; i++) {
            for (int j = 0; j < boton[0].length; j++) {
                BoardSquare boardSquare = new BoardSquare();
                boardSquare.x = i;
                boardSquare.y = j;
                boardSquare.setBorderPainted(false);
                boardSquare.setContentAreaFilled(false);
                boardSquare.setOpaque(true);
                boardSquare.emptyColor = color1;
                boardSquare.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        BoardSquare s = (BoardSquare)e.getSource();
                        if(s.ficha == null){
                            System.out.println("No hay una ficha");
                            if(previouslySelected != null){
                                previouslySelected.setBackground(previouslySelected.emptyColor);
                                previouslySelected.setIcon(null);
                                s.ficha = previouslySelected.ficha;
                                s.setIcon(imagenx);
                                previouslySelected.ficha = null;
                            }
                        }else {
                            previouslySelected = (s == previouslySelected)? null: s;
                        }
                    }
                });

                if (((j % 2 == 0) && (i % 2 != 0)) || ((j % 2 != 0) && (i % 2 == 0))) {
                    boardSquare.setBackground(color1);
                    boardSquare.emptyColor = color1;
                }if ((((i == 0)  || (i==2))&&(j % 2 != 0)) || (((i == 1)|| (i==3))&&(j % 2 == 0))){
                    Ficha ficha = new Ficha();
                    ficha.setX(i);
                    ficha.setY(j);
                    ficha.setIcon(imagenx);
                    boardSquare.ficha = ficha;
                    boardSquare.setIcon(imagenx);
                }if ((((i == 8)  || (i==6))&&(j % 2 != 0)) || (((i == 7)|| (i==9))&&(j % 2 == 0))){
                    Ficha ficha = new Ficha();
                    ficha.setX(i);
                    ficha.setY(j);
                    ficha.setIcon(imagenx1);
                    boardSquare.ficha = ficha;
                    boardSquare.setIcon(imagenx1);
                }

                boton[i][j] = boardSquare;
                juego.add(boton[i][j]);
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

