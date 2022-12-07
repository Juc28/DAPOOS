package presentacion;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.List;

import dominio.*;

import javax.swing.Timer;


public class DamasGUI extends JFrame {
    private PantallaInicio inicio;
    private Tablero tablero;
    private JMenuBar menu;
    private JMenu file;
    private JMenuItem open, save, exit,terminar;
    private JFileChooser fileChooser;

    private  CardLayout layout;
    private JPanel pantalla;

    private Icon icono;
    private DamaPoos juego = new DamaPoos();


    public void setTitulo(String titulo){
        this.setTitle(titulo);
    }


    public DamasGUI() {
        super("DamaPoos");
        prepareElementos();
        prepareActions();
        ImageIcon icono = new ImageIcon("src/presentacion/imagenes/log.png");
        Image icon = icono.getImage();
        setIconImage(icon);
        juego.addEventListener(new MiEventoEscuchador() {
            @Override
            public void onJuegoTerminado(Jugador jugador) {
                //TODO:Agregar ventana
                //JOptionPane.showMessageDialog(null,"Gano el Jugador:"+jugador.getNombre());
                //System.out.println("Juego Terminado"+jugador.getColor());
            }
            @Override
            public void onJugarCambio(Jugador jugador){
                //JOptionPane.showMessageDialog(null,"Turno de:"+jugador.getColor());
                setTitulo("DAMAPOOS - " + jugador.getColor());
            }

            @Override
            public void onComodinGun(Jugador jugador) {

            }
        });
    }



    private void prepareElementos() {

        setTitle("DaPOOS");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setResizable(false);
        setSize(900,540);
        prepareElementsMenu();
        fileChooser = new JFileChooser();
        fileChooser.setVisible(false);
        prepareElementosIncio();


    }

    private void prepareActions() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                setDefaultCloseOperation();
            }
        });
        prepareActionsMenu();
        inicio.n.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inicio.prepararElementosModo();
                prepareAccionModo();
                prepareAccionAtras1();
            }
        });

    }

    private void prepareActionsMenu() {
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                setDefaultCloseOperation();
            }
        });
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    accionOpenFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                accionSaveFile();
            }
        });
//        terminar.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                setSize(new Dimension(900, 590));
//                inicio.prepareElemtosIn();
//                prepareActions();
//            }
//        });

    }

    private void accionOpenFile() throws IOException {
        fileChooser = new JFileChooser("C:");
        fileChooser.setVisible(true);
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("DamaPoos (.ser)", ".ser");
//        fileChooser.addChoosableFileFilter(filter);
        int seleccion = fileChooser.showOpenDialog(open);
        File file = null;
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            file= fileChooser.getSelectedFile();
        }
        FileInputStream fis;
        ObjectInputStream ois;
        fis = new FileInputStream(file);
        ois = new ObjectInputStream(fis);
        try {
            while (true) {
                juego = (DamaPoos) ois.readObject();

            }
        } catch (OptionalDataException e) {
//            if (!e.eof)
//                throw e;
        }
          catch (IOException e) {
            //throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            //throw new RuntimeException(e);
        } finally {
            ois.close();
            fis.close();
        }
        prepareElementosTablero();
    }

    private void accionSaveFile() {
        fileChooser.setVisible(true);
//        FileNameExtensionFilter filter = new FileNameExtensionFilter("DamaPoos (.ser)", ".ser");
//        fileChooser.setFileFilter(filter);
        int seleccion = fileChooser.showSaveDialog(save);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                tablero.guardar(juego,file);
            }catch (IOException e){
                System.out.println(e.toString());
            }
        }
    }

    private void setDefaultCloseOperation() {
        int confirm = JOptionPane.showConfirmDialog(exit, "Are you sure you want to exit?");
        if (JOptionPane.OK_OPTION == confirm) {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            System.exit(0);
        }
    }

    private void prepareElementsMenu() {
        menu = new JMenuBar();
        setJMenuBar(menu);
        file = new JMenu("File");
        menu.add(file);
        open = new JMenuItem("Open");
        file.add(open);
        save = new JMenuItem("Save");
        file.add(save);
        exit = new JMenuItem("Exit");
        file.add(exit);
        terminar = new JMenuItem("Terminar Juego");
        file.add(terminar);
    }


    public void prepareElementosIncio(){
        layout = new CardLayout();
        setSize(new Dimension(900, 540));
        pantalla = new JPanel(layout);
        inicio = new PantallaInicio(PantallaInicio.fondoI,juego);
        pantalla.add(inicio,"t");
        layout.show(pantalla,"t");
        add(pantalla);
    }

    public void prepareElementosTablero(){
        setSize(900,540);
        tablero = new Tablero(layout,juego);
        pantalla.add(tablero,"t");
        layout.show(tablero,"t");
        getContentPane().removeAll();
        getContentPane().add(tablero);

    }
    private void prepareAccionesJugadores(){
        inicio.jugadores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inicio.areasDates();
                prepareAccionTablero();
                prepareAccionAtras3();
            }
        });
    }
    private void prepareAccionModo(){
        inicio.continuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inicio.prepararElementosJugadores();
                prepareAccionesJugadores();
                prepareAccionAtras2();
            }
        });
    }
    private void prepareAccionTablero(){
        inicio.empezar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                juego.permitirEspeciales = inicio.radio3.isSelected();
                juego.porcentajeCasillasEspeciales = Integer.parseInt(inicio.porc.getText());
                juego.formaDeAparecen = inicio.forma.getSelectedIndex();
                Jugador jugador1 = new Jugador(inicio.nombre1.getText(),inicio.Color1.getSelectedItem().toString());
                Jugador jugador2 = new Jugador(inicio.nombre2.getText(),inicio.Color2.getSelectedItem().toString());
                Jugador[] jugadors = {jugador1,jugador2};
                juego.setJugadores(jugadors);
                prepareElementosTablero();
                limpiarCasillasEspeciales();

            }
        });

    }

    private void limpiarCasillasEspeciales(){
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                //System.out.println("timer 5");
                juego.noVerCasillaEspeciales();
            }
        });
        timer.setRepeats(false); // Only execute once
        timer.start(); // Go go go!
    }
    private void prepareAccionAtras1(){
        inicio.atras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSize(new Dimension(900, 590));
                inicio.prepareElemtosIn();
                prepareActions();
            }
        });
    }
    private void prepareAccionAtras2(){
        inicio.atras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSize(new Dimension(900, 590));
                inicio.prepararElementosModo();
                prepareAccionModo();
            }
        });
    }
    private void prepareAccionAtras3(){
        inicio.atras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSize(new Dimension(900, 590));
                inicio.prepararElementosJugadores();
                prepareAccionesJugadores();
            }
        });
    }

    public static void main(String[] args) {
        DamasGUI gui = new DamasGUI();
        gui.setVisible(true);
    }

}