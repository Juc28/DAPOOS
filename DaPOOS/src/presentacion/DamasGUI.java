package presentacion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

import dominio.DamaPoos;
import dominio.Ficha;
import dominio.VariablesConstantes;


public class DamasGUI extends JFrame {
    private PantallaInicio inicio;
    private JMenuBar menu;
    private JMenu file;
    private JMenuItem open, save, saveas, exit;
    private JFileChooser fileChooser;

    private  CardLayout layout;

    private Icon icono;
    private DamaPoos juego;


    public DamasGUI() {
        super("DamaPoos");
        prepareElementos();
        prepareActions();
        ImageIcon icono = new ImageIcon("src/presentacion/imagenes/log.png");
        Image icon = icono.getImage();
        setIconImage(icon);
    }

    public void comenzarJuego(){
       DamaPoos.nuevoJuego();
       juego = DamaPoos.getJuego();

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
                accionOpenFile();
            }
        });
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                accionSaveFile();
            }
        });
        saveas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                accionSaveFile();
            }
        });
    }

    private void accionOpenFile() {
        fileChooser.setVisible(true);
        int seleccion = fileChooser.showOpenDialog(open);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(open, "El archivo " + fichero + " no se puede abrir porque las funcionalidades estan en construccion ");
        }
    }

    private void accionSaveFile() {
        fileChooser.setVisible(true);
        int seleccion = fileChooser.showSaveDialog(save);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(save, "El archivo en la ruta " + fichero + " no se puede guardar porque las funcionalidades estan en construccion ");
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
        saveas = new JMenuItem("Save As...");
        file.add(saveas);
        exit = new JMenuItem("Exit");
        file.add(exit);
    }


    public void prepareElementosIncio(){
        layout = new CardLayout();
        setSize(new Dimension(900, 540));
        JPanel p = new JPanel(layout);
        inicio = new PantallaInicio(PantallaInicio.fondoI);
        p.add(inicio,"t");
        layout.show(p,"t");
        add(p);
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
                inicio.tablero();
                setSize(new Dimension(900, 590));
                prepareAccionEmpezarN();
                comenzarJuego();

            }
        });

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
                juego.ponerfichas();
            }
        });
    }
    private void prepareAccionEmpezarN(){
        inicio.terminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSize(new Dimension(900, 590));
                inicio.prepareElemtosIn();
                prepareActions();
            }
        });
    }
    public static void main(String[] args) {
        DamasGUI gui = new DamasGUI();
        gui.setVisible(true);
    }

}