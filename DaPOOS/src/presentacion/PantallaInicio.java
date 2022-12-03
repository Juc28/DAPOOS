package presentacion;

import dominio.DamaPoos;
import dominio.Ficha;
import dominio.Jugador;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PantallaInicio extends JPanel {
    public static final String fondoI = "src/presentacion/imagenes/fondo1.png";
    public static final String fondoI2 = "src/presentacion/imagenes/fondo3.png";
    public static final String fondoI3 = "src/presentacion/imagenes/fondo4.png";
    public static final String fondoI4 = "src/presentacion/imagenes/fondo2.png";
    public static final String fondoI5 = "src/presentacion/imagenes/fondo5.png";
    protected JButton n,jugadores,maquina,continuar,empezar,atras;
    private BufferedImage fondo;
    public JComboBox<Object> Color1, Color2,forma;
    private JLabel jugador1, jugador2,titulo1,titulo2;
    public JTextField nombre1, nombre2,tim,porc;

    private JRadioButton radio1,radio2,radio3,radio4;
    private ButtonGroup bg;
    private DamaPoos juego;
    public PantallaInicio(String imagen, DamaPoos juego){
        super(null);
        this.juego = juego;
        prepareElemtosIn();
        setFondo(imagen);

    }


    private void setFondo(String root) {
        try {
            fondo = ImageIO.read(new File(root));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void prepareElemtosIn(){
        removeAll();
        setFondo(PantallaInicio.fondoI);
        n = new JButton("JUGAR");
        n.setBounds(374,283,150,50);
        n.setIcon(new ImageIcon("src/presentacion/imagenes/jugar.png"));
        add(n);
        repaint();
    }
    public void prepararElementosModo(){
        removeAll();
        setFondo(PantallaInicio.fondoI);
        continuar = new JButton("CONTINUAR");
        continuar.setBounds(600,400,170,50);
        continuar.setIcon(new ImageIcon("src/presentacion/imagenes/Continuar.png"));
        add(continuar);
        atras = new JButton("atras");
        atras.setBounds(5,10,80,20);
        add(atras);
        titulo1 = new JLabel("DIFICULTAL DEL JUEGO");
        titulo1.setForeground(Color.WHITE);
        titulo1.setBounds(245,220,150,30);
        add(titulo1);
        bg=new ButtonGroup();
        radio1=new JRadioButton("Normal");
        radio1.setBounds(235,270,100,30);
        add(radio1);
        bg.add(radio1);
        radio2=new JRadioButton("Quicktime");
        radio2.setBounds(235,320,100,30);
        add(radio2);
        bg.add(radio2);
        tim = new JTextField("tiempo de turno");
        tim.setBounds(340,320,100,30);
        add(tim);
        titulo2 = new JLabel("CASILLAS ESPECIALES");
        titulo2.setForeground(Color.WHITE);
        titulo2.setBounds(480,220,150,30);
        add(titulo2);
        radio3=new JRadioButton("Si");
        radio3.setBounds(480,270,40,30);
        add(radio3);
        bg.add(radio3);
        porc = new JTextField("porcentaje de casillas");
        porc.setBounds(530,270,120,30);
        add(porc);
        forma = new JComboBox<>();
        forma.addItem("");
        forma.addItem("Permanete");
        forma.addItem("Relampago");
        forma.setBounds(660,270,150,30);
        add(forma);
        radio4=new JRadioButton("No");
        radio4.setBounds(480,320,50,30);
        add(radio4);
        repaint();
    }
    public void prepararElementosJugadores(){
        removeAll();
        setFondo(PantallaInicio.fondoI5);
        atras = new JButton("atras");
        atras.setBounds(5,10,80,20);
        add(atras);
        jugadores = new JButton("Jugador VS Jugador");
        jugadores.setBounds(374,250,170,70);
        jugadores.setIcon(new ImageIcon("src/presentacion/imagenes/Jugador.png"));
        add(jugadores);
        maquina = new JButton("Jugador VS Maquina");
        maquina.setBounds(374,350,170,70);
        maquina.setIcon(new ImageIcon("src/presentacion/imagenes/maquina.png"));
        add(maquina);
        repaint();
    }

    public void areasDates() {
        removeAll();
        setFondo(PantallaInicio.fondoI4);
        empezar = new JButton("EMPEZAR JUEGO");
        empezar.setBounds(400,350,140,50);
        empezar.setIcon(new ImageIcon("src/presentacion/imagenes/empezar.png"));

        atras = new JButton("atras");
        atras.setBounds(5,10,80,20);
        add(atras);
        jugador1 = new JLabel("JUGADOR 1");
        jugador1.setForeground(Color.WHITE);
        jugador1.setBounds(250,200,80,30);
        add(jugador1);
        jugador2 = new JLabel("JUGADOR 2");
        jugador2.setForeground(Color.WHITE);
        jugador2.setBounds(250,280,80,30);
        add(jugador2);
        nombre1 = new JTextField();
        nombre1.setBounds(350,200,150,30);
        add(nombre1);
        nombre2 = new JTextField();
        nombre2.setBounds(350,280,150,30);
        add(nombre2);
        Color1 = new JComboBox<>();
        Color1.addItem("");
        Color1.addItem("Rojo");
        Color1.addItem("Negro");
        Color1.setBounds(520,200,150,30);
        add(Color1);
        Color2 = new JComboBox<>();
        Color2.addItem("");
        Color2.addItem("Rojo");
        Color2.addItem("Negro");
        Color2.setBounds(520,280,150,30);
        add(Color2);
        add(empezar);
        nombre1.setText("Jugador 1");
        nombre2.setText("Jugador 2");
        Color1.setSelectedIndex(1);
        Color2.setSelectedIndex(2);

        repaint();
    }




    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, this);
    }




}
