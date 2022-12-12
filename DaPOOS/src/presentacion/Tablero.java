package presentacion;
import dominio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
/**
 * Representa el tablero grafico donde se va jugar con las configuraciones dadas
 * @author Erika Juliana Castro Romero y Mariana Pulido Moreno
 * @version 3.0
 */
public class Tablero extends JPanel {
    protected JButton terminar;
    private JButton[][] boton;
    private DamaPoos juego;
    public Jugador jugadorEspecial = null; //Que jugador gano el comodin
    public Casilla casilla;

    /**
     * Constructor de la clase Tablero
     * @param layout
     * @param juego el juego nuevo que se crea
     */

    public Tablero(CardLayout layout,DamaPoos juego) {
        super(layout);
        this.juego = juego;
        juego.setTablero();
        removeAll();
        crearTablero();
        juego.addEventListener(new MiEventoEscuchador() {
            @Override
            public void onJuegoTerminado(Jugador jugador) {
                //JOptionPane.showMessageDialog(null,"Gano el Jugador:"+jugador.getNombre());
                //System.out.println("Juego Terminado"+jugador.getColor());
            }
            @Override
            public void onJugarCambio(Jugador jugador){
                //JOptionPane.showMessageDialog(null,"Turno de:"+jugador.getColor());
                //setTitulo("DAMAPOOS - " + jugador.getColor());
            }

            @Override
            public void onComodinGun(Jugador jugador) {
//                System.out.println("Si funciono Gun");
                jugadorEspecial = jugador;
            }

            @Override
            public void onCuentaRegresivaTurno(int cuentaRegresiva) {

            }
        });
    }

    BoardSquare casillaInicial;

    /**
     * Crea la interfaz de las casillas
     */
    public class BoardSquare extends JButton{
        public Casilla casilla;
        public int x;
        public int y;
        public Ficha ficha;
        public Color emptyColor;
    }

    /**
     * Crea lo visible del tablero apartir de la clase DamaPoos
     */
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
                            if(casillaInicial != null){
                                int moviento = juego.movimientoRequerido(juego.getTurno(),casillaInicial.casilla,casillaFutura.casilla);
                                if(moviento == 2){
                                    int opciones = opcionesFichas();
                                    juego.fichaSeConvierteEnEspecial(casillaFutura.casilla,opciones);
                                }
                               actulizarTablero();
                            }
                        }else {
                            if(juego.eliminarPorGun){
                                juego.setTurno(jugadorEspecial);
                                if(juego.removerFichaContricante(casillaFutura.casilla,jugadorEspecial)){
                                    jugadorEspecial = null;
                                    juego.eliminarPorGun = false;
                                    juego.cambioTurno();
                                    actulizarTablero();
                                }
                                System.out.println("Llego al gun");
                            }else {
                                casillaInicial = (casillaFutura == casillaInicial) ? null : casillaFutura;
                            }
                        }
                    }
                });
                Casilla casilla = juego.getTablero()[i][j];
                if (casilla.getColor().equalsIgnoreCase( "Negro")) {
                    boardSquare.setBackground(colorNegro);
                    boardSquare.emptyColor = colorNegro;
                }else {
                    if (casilla instanceof Mine){
                        boardSquare.setBackground(Color.ORANGE);
                    }else if(casilla instanceof Jail){
                        boardSquare.setBackground(Color.BLUE);
                    }else {
                        boardSquare.setBackground(Color.WHITE);
                    }
                }
                if (casilla.getFicha() != null){
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

    /**
     * Actualiza el tablero para que muestre lo que esta pasado y borre lo anterior
     */
    public void actulizarTablero(){
        removeAll();
        revalidate();
        repaint();
        crearTablero();
    }

    /**
     * Sirve para que cuando el jugador llegue a la primera fila del otro jugador pueda elegir la ficha especial en la que se
     * quiere convertir
     * @return el numero que para la ficha especial
     */
    public int opcionesFichas(){
        String[] botones = {"Ninja", "Reina", "Zombie"};
        int ventana = JOptionPane.showOptionDialog(null,
                "Elije tu ficha:",
                "FICHAS ",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,
                botones, botones[0]);
        if(ventana == 0) {System.out.println("Ninja");}
        else if(ventana == 1) {System.out.println("Reina");}
        else if(ventana == 2) {System.out.println("Zombie");}
        return ventana;
    }


    /**
     * Para que guarde el juego
     * @param juego el juego que se va guardar
     * @param file el documento en donde se guarda el juego
     * @throws IOException
     */
    public void guardar(DamaPoos juego,File file) throws IOException {
            String nombre = file.getAbsolutePath();
        if(!nombre.endsWith(".ser") ) {
            file = new File(nombre + ".ser");
        }
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(juego);
            out.close();

    }



}

