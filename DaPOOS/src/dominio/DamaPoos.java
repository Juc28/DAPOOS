package dominio;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import static java.lang.Math.abs;

/**
 * Clase que ejecuta el juego DAMAPOOS, el cual consiste en el juego clasico de damas con otro adicionales que es mover
 * fichas en diagonal en un tablero 10*10
 * @author Erika Juliana Castro Romero y Mariana Pulido Moreno
 * @version 5.0
 */
public class DamaPoos implements Serializable {
    private static DamaPoos juego = null;
    public Casilla[][] tablero;
    private Jugador[] jugadores;
    private Jugador turno;
    public boolean enProgreso;
    public int porcentajeCasillasEspeciales;
    public boolean permitirEspeciales;
    public int formaDeAparecen;
    public boolean eliminarPorGun = false;
    private List<MiEventoEscuchador> eventoEscuchadors = new ArrayList<MiEventoEscuchador>();
    private static boolean ok;

    /**
     *
     * @param eventoEscuchador
     */
    public void addEventListener(MiEventoEscuchador eventoEscuchador) {
        this.eventoEscuchadors.add(eventoEscuchador);
    }

    /**
     * Asigna el juego
     * @param juego
     */
    public static void setJuego(DamaPoos juego) {
        DamaPoos.juego = juego;
        ok = true;
    }

    /**
     * Retorna un arreglo de los jugadores
     * @return Jugador[] jugadores
     */
    public Jugador[] getJugadores() {return jugadores;}

    /**
     * Retorna un jugador dependiento del color de la ficha de este
     * @param color correspondiente al Jugador
     * @return jugador
     */
    public Jugador getJugadorByColor(String color){
        for(Jugador jugador:jugadores){
            String getColor = jugador.getColor();
            if (getColor.equalsIgnoreCase(color)){return jugador;}
        }
        return null;
    }

    /**
     * Asigna el arreglo de jugadores
     * @param jugadores
     */
    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
        for(Jugador jugador: jugadores){
            jugador.setFichas(jugador.getColor());
            if (jugador.getColor().equalsIgnoreCase("Negro")){
                turno = jugador;
                ok = true;
            }
        }
    }

    /**
     * Asigna el turno que va tener el juego
     * @param turno
     */
    public void setTurno(Jugador turno){
        this.turno = turno;
        eventoEscuchadors.forEach((el) -> el.onJugarCambio(turno));
    }


    /**
     * Retorna el turno al jugador correspodiente
     * @return turno
     */
    public Jugador getTurno(){return turno;}

    /**
     * Asigna el Tablero al juego el cual se forma a partir de casillas
     */
    public void setTablero(){
        if (enProgreso){return;}
        tablero = new Casilla[10][10];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (((j % 2 == 0) && (i % 2 != 0)) || ((j % 2 != 0) && (i % 2 == 0))) {
                    Casilla casilla = new Casilla("Negro",i,j);
                    tablero[i][j] = casilla;
                    ok = true;
                }else {
                    Casilla casilla = new Casilla("Blanco",i,j);
                    tablero[i][j] = casilla;
                    ok = true;
                }
            }
        }
        if(permitirEspeciales){
            int casillasAleatorio = getNumerosAleatorias(0,porcentajeCasillasEspeciales/2);
            while (casillasAleatorio > 0){
                int xAleatorio = getNumerosAleatorias(0,9);
                int yAleatorio = getNumerosAleatorias(0,9);
                Casilla casillaMine = tablero[xAleatorio][yAleatorio];
                if(!casillaMine.getColor().equalsIgnoreCase("Blanco")){
                    Mine mine = new Mine(xAleatorio,yAleatorio);
                    tablero[xAleatorio][yAleatorio] = mine;
                    casillasAleatorio--;
                }
            }
        }
        ponerFichas();
        enProgreso = true;
    }

    /**
     *
     * @param min
     * @param max
     * @return
     */
    public int getNumerosAleatorias(int min, int max) { return (int) ((Math.random() * (max - min)) + min); }

    /**
     * Retorna el tablero del juego
     * @return tablero
     */
    public Casilla[][] getTablero(){
        return tablero;
    }

    /**
     * Asigan las fichas de cada jugador en el tablero en sus correspondientes lugares de partida
     */
    public void ponerFichas(){
        Jugador jugadorNegro = getJugadorByColor("Negro");
        Jugador jugadorRojo = getJugadorByColor("Rojo");
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if ((((i == 0)  || (i==2))&&(j % 2 != 0)) || (((i == 1)|| (i==3))&&(j % 2 == 0))){
                    Ficha ficha = jugadorNegro.getFichaNoEnTablero();
                    if (ficha != null){
                        tablero[i][j].setFicha(ficha);
                        ficha.setX(i);
                        ficha.setY(j);
                        ok = true;
                    }

                }if ((((i == 8)  || (i==6))&&(j % 2 != 0)) || (((i == 7)|| (i==9))&&(j % 2 == 0))){
                    Ficha ficha = jugadorRojo.getFichaNoEnTablero();
                    if(ficha != null){
                        tablero[i][j].setFicha(ficha);
                        ficha.setX(i);
                        ficha.setY(j);
                    }
                }
            }
        }
    }

    /**
     * Se contepla que movimiento no pueden hacer la fichas es el tablero
     * @param jugador - el que este en el turno correspodiente
     * @param inicio - la casillas actual
     * @param fin - la casilla en la que va quedar
     * @return false cuando cumple uno de los momientos no aceptados
     */
    public boolean posiblesMovimientos(Jugador jugador,Casilla inicio,Casilla fin) {
        if(jugador.getColor() != inicio.getFicha().getColor()){return false;}
        if (fin.getColor().equalsIgnoreCase("Blanco")){return false;}
        if(inicio.getFicha() == null){return false;}
        if(fin.getFicha() != null){return false;}
        if(abs(inicio.getX() - fin.getX()) != abs(inicio.getY() - fin.getY()) ){return false;}
        Ficha pieza = inicio.getFicha();
        if (pieza instanceof  Reina){
        }else{
            if(abs(inicio.getX() - fin.getX())>2){return false;}//No se puede mover mas de dos casillas
        }
        if(pieza instanceof Ninja || pieza instanceof  Reina){ //La fichas  no se puede mover de para atras
        }else{
            if (pieza.getColor().equalsIgnoreCase("Rojo") && fin.getX() > inicio.getX()) {return false;}
            if (pieza.getColor().equalsIgnoreCase("Negro") && fin.getX() < inicio.getX()) {return false;}
        }
        return true;
    }

    /**
     * Elimina las fichas cuando una paso por encima otra ficha
     * @param casilla
     */
    public void eliminarFichas(Casilla casilla,Jugador jugador){
        if(casilla.getFicha() != null){
            Jugador enemigo = getJugadorByColor(casilla.getFicha().getColor());
            ok = true;
            if(casilla.getFicha() instanceof Gun){
                eventoEscuchadors.forEach((el) -> el.onComodinGun(jugador));
                eliminarPorGun = true;
                casilla.getFicha().fichaEnJuego = false;
                casilla.setFicha(null);
                ok = true;
            }
            casilla.getFicha().fichaEnJuego = false;
            casilla.setFicha(null);
            if(enemigo.contarFichasEnJuego() == 0){
                Jugador jugadorGanador;
                if(enemigo.getColor() == "Rojo") {
                    jugadorGanador = getJugadorByColor("Negro");
                    ok = true;
                }else {
                    jugadorGanador = getJugadorByColor("Rojo");
                    ok = true;
                }
                eventoEscuchadors.forEach((el) -> el.onJuegoTerminado(jugadorGanador));
            }
        }

    }

    /**
     * Elimina la ficha del contricante
     * @param casilla
     * @param jugador
     * @return true si la elimino
     */
    public boolean removerFichaContricante(Casilla casilla,Jugador jugador){
        if(casilla.getFicha() != null ){
            if(!casilla.getFicha().getColor().equalsIgnoreCase(jugador.getColor())){
                eliminarFichas(casilla,jugador);
                return true;
            }
        }
        return false;
    }
    /**
     * Mueve las fichas de un jugador correspodiente teniendo encuenta la ficha en la que estaba a la que va llegar
     * @param jugador
     * @param inicio
     * @param fin
     * @return un numero para saber en que parte queda la ficha
     */
    public int movimientoRequerido(Jugador jugador,Casilla inicio,Casilla fin){
        int retorno = 0;
        System.out.println(inicio.getX()+":"+inicio.getY()+" - "+fin.getX()+":"+fin.getY());
        if(posiblesMovimientos(jugador,inicio,fin)){
            if (fin instanceof Mine){
                retorno = 3; // 3 es que cayo en una casilla Mine
                Casilla matadaPorMina1 = getTablero()[abs(fin.getX()-1)][abs(fin.getY()-1)];
                Casilla matadaPorMina2 = getTablero()[abs(fin.getX()-1)][abs(fin.getY()+1)];
                Casilla matadaPorMina3 = getTablero()[abs(fin.getX()+1)][abs(fin.getY()-1)];
                Casilla matadaPorMina4 = getTablero()[abs(fin.getX()+1)][abs(fin.getY()+1)];
                eliminarFichas(matadaPorMina1,jugador);
                eliminarFichas(matadaPorMina2,jugador);
                eliminarFichas(matadaPorMina3,jugador);
                eliminarFichas(matadaPorMina4,jugador);
                eliminarFichas(fin,jugador);
                eliminarFichas(inicio,jugador);
                cambioTurno();
                return 3;
            }//La ficha salto una casilla
            if(abs(inicio.getX() - fin.getX()) == 2 && abs(inicio.getY() - fin.getY())==2){
                int es = fin.getX()-inicio.getX();
                int es2 = es/2;
                int x = inicio.getX();
                int est = fin.getY()-inicio.getY();
                int est2 = est/2;
                int y = inicio.getY();
                int xDelMedio = abs(x+es2);
                int yDelMedio = abs(y+est2);
                //System.out.println(inicio.getX()+":"+inicio.getY()+" - "+fin.getX()+":"+fin.getY());
                Casilla casillaDelMedio = getTablero()[xDelMedio][yDelMedio];
                //System.out.println(casillaDelMedio.getX()+":"+casillaDelMedio.getY());
                if(casillaDelMedio.getFicha() != null) {//Verifica si hay una ficha en la casilla y la elimina
                    if(casillaDelMedio.getFicha() instanceof Ninja){ //Excepto si es Ninja cuando tiene una vida
                        Ninja soyNinja = (Ninja) casillaDelMedio.getFicha();
                        if (soyNinja.getTieneVidas()){
                            soyNinja.removerVidas();
                        }else {
                            eliminarFichas(casillaDelMedio,jugador); // Elimina la ficha del juego
                        }
                    }else {
                        eliminarFichas(casillaDelMedio,jugador);
                    }
                }
            }//Si la ficha es negra y esta en la ultima linea (primera linea del competidor)
            if(inicio.getFicha().getColor().equalsIgnoreCase("Negro")&& fin.getX() == 9){
                retorno = 2; // 2 significa que la ficha esta en la ultima linea
            }
            if(inicio.getFicha().getColor().equalsIgnoreCase("Rojo")&& fin.getX() == 0){
                retorno = 2; // 2 significa que la ficha esta en la ultima linea
            }
            fin.setFicha(inicio.getFicha());
            inicio.setFicha(null);
            cambioTurno();
            retorno = (retorno == 0) ? 1 : retorno; // 1 significa que la ficha se movio
        }else {
            retorno = 0; // 0 significa que la ficha no se movio
        }
        return retorno;
    }

    /**
     * Para saber cuando una ficha es diferente a las fichas normales
     * @param casilla
     * @param tipo
     */
    public void fichaSeConvierteEnEspecial(Casilla casilla,int tipo){
        Image imagen;// 0 es Ninja, 1 es Reina y 2 Zombie
        if(tipo == 0){
            Ninja fichaNinja = new Ninja(casilla.getFicha().getColor(), casilla.getX(),casilla.getY());
            casilla.setFicha(fichaNinja);
            ok = true;
            System.out.println(casilla.getFicha().getClass());
        }if(tipo == 1){
            Reina fichaReina = new Reina(casilla.getFicha().getColor(), casilla.getX(),casilla.getY());
            casilla.setFicha(fichaReina);
            ok = true;
            System.out.println(casilla.getFicha().getClass());
        }if(tipo == 2){
            Zombie fichaZombie = new Zombie(casilla.getFicha().getColor(), casilla.getX(),casilla.getY());
            casilla.setFicha(fichaZombie);
            ok = true;
            System.out.println(casilla.getFicha().getClass());
        }
    }

    /**
     * Saber cuando cambia el turno para
     */
    public void cambioTurno(){
        for (Jugador siguienteJugador : getJugadores()) {
            if (turno.getColor() != siguienteJugador.getColor()) {
                turno = siguienteJugador;
                comodines();
                eventoEscuchadors.forEach((el) -> el.onJugarCambio(turno));
                return;
            }
        }

    }

    /**
     * Se crean los comodines y se tiene encuenta que tipo de comodin se va crear en el tablero
     */
    public void comodines(){
        int seCreoComodines=1;//getNumerosAleatorias(0,1);
        if (seCreoComodines == 1){
            int numeroAleatorioComodines = getNumerosAleatorias(1,3);
            switch (numeroAleatorioComodines){
                case 1: // Hace refencia al comodin gun
                    setComodinesAleatoria(new Gun());
                    ok = true;
                    break;
                case 2: //
                    break;
                case 3:
                    break;
            }
        }
    }

    /**
     * Se coloca el comodin en un posión aleatorio durante el juego para que los puedan obtener los jugadores
     * @param comodin
     */
    public void setComodinesAleatoria(Comodin comodin){
        boolean comodinPositivo = true;
        while (comodinPositivo) {
            int xAleatorio = getNumerosAleatorias(0, 9);
            int yAleatorio = getNumerosAleatorias(0, 9);
            Casilla casilla = tablero[xAleatorio][yAleatorio];
            if (!casilla.getColor().equalsIgnoreCase("Blanco") && casilla.getFicha() == null) {
                ok = true;
                comodin.setX(xAleatorio);
                comodin.setY(yAleatorio);
                tablero[xAleatorio][yAleatorio].setFicha(comodin);
                comodinPositivo = false;
            }
        }
    }

    /**
     * Oculta las casillas especiales en el tablero
     */
    public void noVerCasillaEspeciales(){
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                Casilla casilla = tablero[i][j];
                if(casilla instanceof Mine){
                    casilla.setColor("Negro");
                    ok = true;
                }
            }
        }
    }
    public boolean ok(){
        return ok;
    }

}
