package presentacion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import dominio.*;
import javax.swing.Timer;

/**
 * Representa la interfa grafica del usuario
 * @author Erika Juliana Castro Romero y Mariana Pulido Moreno
 * @version 3.0
 */
public class DamasGUI extends JFrame {
    private PantallaInicio inicio;
    private Tablero tablero;
    private JMenuBar menu;
    private JMenu file;
    private JMenuItem open, save, exit,terminar;
    private JFileChooser fileChooser;
    private  CardLayout layout;
    private JPanel pantalla;

    private DamaPoos juego = new DamaPoos();

    /**
     * Da el titulo que va tener la ventana
     * @param titulo
     */
    public void setTitulo(String titulo){
        this.setTitle(titulo);
    }

    /**
     * Constructor de la clase DamasGUI
     */
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
                int confirm = JOptionPane.showConfirmDialog(null, "El ganador es: "+jugador.getNombre() , "Se acabo el juego ",-1);
                if (JOptionPane.OK_OPTION == confirm) {
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    System.exit(0);
                }

                //JOptionPane.showMessageDialog(null,"Gano el Jugador:"+jugador.getNombre());
                //System.out.println("Juego Terminado"+jugador.getColor());
            }
            @Override
            public void onJugarCambio(Jugador jugador){
                //JOptionPane.showMessageDialog(null,"Turno de:"+jugador.getColor());
                setTitulo("DAMAPOOS - " + jugador.getColor() + " Fichas restantes: " + jugador.contarFichasEnJuego());
            }

            @Override
            public void onComodinGun(Jugador jugador) {

            }


            @Override
            public void onCuentaRegresivaTurno(int cuentaRegresiva) {
                setTitulo("DAMAPOOS - " + juego.getTurno().getColor() + " ["+ cuentaRegresiva/1000 + "] " +"  "+ " Fichas restantes: " + juego.getTurno().contarFichasEnJuego());
            }
        });
    }


    /**
     * Prepara la venta para mostarse
     */
    private void prepareElementos() {
        setTitle("DaPOOS");
        setResizable(false);
        setSize(900,540);
        prepareElementsMenu();
        fileChooser = new JFileChooser();
        fileChooser.setVisible(false);
        prepareElementosIncio();
    }

    /**
     * Pera las acciones para emepezar el juego
     */
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

    /**
     * Prepara las acciones para eleguir las cosas del menu como guardar, abrir y sali
     */
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


    }

    /**
     * Accion para abir archivos que tienen guadardo el juego
     * @throws IOException
     */
    private void accionOpenFile() throws IOException {
        fileChooser = new JFileChooser("C:");
        fileChooser.setVisible(true);
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

    /**
     * Accion para guardar el juego como un archivo
     */

    private void accionSaveFile() {
        fileChooser.setVisible(true);
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

    /**
     * Para salirse del juego
     */
    private void setDefaultCloseOperation() {
        int confirm = JOptionPane.showConfirmDialog(exit, "Are you sure you want to exit?");
        if (JOptionPane.OK_OPTION == confirm) {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            System.exit(0);
        }
    }

    /**
     * Prepara los elementos que va tener el menu
     */
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

    /**
     * Prepara y llama los elementos de la clase pantalla de inicio
     */
    public void prepareElementosIncio(){
        layout = new CardLayout();
        setSize(new Dimension(900, 540));
        pantalla = new JPanel(layout);
        inicio = new PantallaInicio(PantallaInicio.fondoI,juego);
        pantalla.add(inicio,"t");
        layout.show(pantalla,"t");
        add(pantalla);
    }

    /**
     * Prepara y llama los elementos de la clase tablero
     */
    public void prepareElementosTablero(){
        setSize(900,540);
        tablero = new Tablero(layout,juego);
        pantalla.add(tablero,"t");
        layout.show(tablero,"t");
        getContentPane().removeAll();
        getContentPane().add(tablero);

    }

    /**
     * Prepara las acciones para mostrar la pantalla depues de darle click al boton Jugador VS Jugador
     */
    private void prepareAccionesJugadores(){
        inicio.jugadores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inicio.areasDates();
                prepareAccionTablero();
                prepareAccionAtras3();
            }
        });
    }

    /**
     * Prepara las acciones para mostrar la pantalla depues de darle click al boton Jugador VS Maquina
     */
    private void prepareAccionesMaquina(){
        inicio.maquina.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               juego.maquina = true;
               inicio.areasDates();
               prepareAccionTablero();

            }
        });
    }


    /**
     * Prepara las acciones para mostrar la pantalla depues de darle click al boton continuar
     */
    private void prepareAccionModo(){
        inicio.continuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inicio.prepararElementosJugadores();
                prepareAccionesJugadores();
                prepareAccionesMaquina();
                prepareAccionAtras2();
            }
        });
    }

    /**
     * Prepara las acciones para mostrar el tablero depues de darle click al boton emepzar donde realmente se llaman los
     * elementos y la informacion para jugar
     */
    private void prepareAccionTablero(){
        inicio.empezar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                juego.permitirEspeciales = inicio.radio3.isSelected();
                juego.porcentajeCasillasEspeciales = Integer.parseInt(inicio.porc.getText());
                juego.formaDeAparecen = inicio.forma.getSelectedIndex();
                juego.permiteQuicktime = inicio.radio2.isSelected();
                if(juego.permiteQuicktime && inicio.tim != null && !inicio.tim.getText().equalsIgnoreCase(" ")){
                    try{
                        juego.setTiempoDeTurno(Integer.parseInt(inicio.tim.getText()));
                    }catch (Exception ex){
                        juego.setTiempoDeTurno(20); //Tiempo establecido al usuario y es posible agregar una ventana de error
                    }

                }
                Jugador jugador1 = new Jugador(inicio.nombre1.getText(),inicio.Color1.getSelectedItem().toString());
                Jugador jugador2 = new Jugador(inicio.nombre2.getText(),inicio.Color2.getSelectedItem().toString());
                Jugador[] jugadors = {jugador1,jugador2};
                juego.setJugadores(jugadors);
                prepareElementosTablero();
                if (juego.formaDeAparecen == 2){
                    limpiarCasillasEspeciales();
                }
            }
        });

    }

    /**
     * Cuando se le da que las casillas se vean de forma relampago se muetsre por un momento
     */
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

    /**
     * Muestra la pantalla anterior
     */
    private void prepareAccionAtras1(){
        inicio.atras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSize(new Dimension(900, 590));
                inicio.prepareElemtosIn();
                prepareActions();
            }
        });
    }

    /**
     * Muestra la pantalla anterior
     */
    private void prepareAccionAtras2(){
        inicio.atras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSize(new Dimension(900, 590));
                inicio.prepararElementosModo();
                prepareAccionModo();
            }
        });
    }

    /**
     * Muestra la pantalla anterior
     */
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