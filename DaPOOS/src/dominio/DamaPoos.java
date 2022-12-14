package dominio;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import java.util.ListIterator;
import javax.swing.Timer;

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
    public boolean eliminadoPorStomp = false;
    public boolean permiteQuicktime;
    private int tiempoDeTurno;
    private Timer tiempo;
    private int cuentaRegresiva;
    public boolean maquina;
    private List<MiEventoEscuchador> eventoEscuchadors = new ArrayList<MiEventoEscuchador>();
    private static boolean ok;
    private ArrayList<Zombie> zombieAparece = new ArrayList<Zombie>();



    public void addEventListener(MiEventoEscuchador eventoEscuchador) {
        this.eventoEscuchadors.add(eventoEscuchador);
    }

    /**
     * Asigna el juego
     * @param juego el juego que se crea
     */
    public static void setJuego(DamaPoos juego) {
        DamaPoos.juego = juego;
        ok = true;
    }

    /**
     * Asiga el tiempo del turno teniendo en cuenta que este en modo de juego Quicktime
     * @param segundos los segundos de cada turno
     */
    public void setTiempoDeTurno(int segundos){this.tiempoDeTurno = (segundos+1)*1000;}

    /**
     * Retorna un arreglo de los jugadores
     * @return Jugador[] Arreglo de los jugadores dados
     */
    public Jugador[] getJugadores() {return jugadores;}

    /**
     * Retorna un jugador dependiento del color de la ficha de este
     * @param color correspondiente al Jugador
     * @return jugador el jugador que tiene ese color
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
     * @param jugadores el arreglo de jugadores
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
     * @param turno el jugador que tiene le turno
     */
    public void setTurno(Jugador turno){
        this.turno = turno;
        eventoEscuchadors.forEach((el) -> el.onJugarCambio(turno));
    }


    /**
     * Retorna el turno al jugador correspodiente
     * @return turno da el jugador que tiene el turno
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
                Casilla casillaEspecial = tablero[xAleatorio][yAleatorio];
                if(!casillaEspecial.getColor().equalsIgnoreCase("Blanco")){
                    int escogerTipo = getNumerosAleatorias(1,100);
                    Especial especial;
                    if(escogerTipo > 50 ){
                        especial = new Mine(xAleatorio,yAleatorio);
                    }else {
                        especial = new Jail(xAleatorio,yAleatorio);
                    }
                    tablero[xAleatorio][yAleatorio] = especial;
                    casillasAleatorio--;
                }
            }
        }
        ponerFichas();
        enProgreso = true;
        if(permiteQuicktime){
            cuentaRegresiva = tiempoDeTurno;
            tiempoDeJuego();
        }

    }

    /**
     * El tiempo de turno de cada jugador cuando elige QuickTime
     */
    public void tiempoDeJuego(){
        tiempo = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cuentaRegresiva -= 1000;
                eventoEscuchadors.forEach((el) -> el.onCuentaRegresivaTurno(cuentaRegresiva));
                if (cuentaRegresiva <= 0){
                    cambioTurno();
                    // La cuenta regresiva resetear en el metodo de cambio de turno
                }
            }
        });
        tiempo.setRepeats(true);
        tiempo.start();
    }

    /**
     * Retorna un numero entre el min y el max que se le de
     * @param min el valor minimo que va se puede elejir
     * @param max el valor maximo que va se puede elejir
     * @return el valor que se eligio de min a max
     */
    public int getNumerosAleatorias(int min, int max) { return (int) ((Math.random() * (max - min)) + min); }

    /**
     * Retorna el tablero del juego
     * @return tablero el tablero del juego
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
     * @param jugador  el que este en el turno correspodiente
     * @param inicio  la casillas actual
     * @param fin  la casilla en la que va quedar
     * @return false cuando cumple uno de los momientos no aceptados
     */
    public boolean posiblesMovimientos(Jugador jugador,Casilla inicio,Casilla fin) {
        if(inicio.getFicha() == null){return false;}
        if(fin.getFicha() != null){return false;}
        if(jugador.getColor() != inicio.getFicha().getColor()){return false;}
        if (fin.getColor().equalsIgnoreCase("Blanco")){return false;}
        if(abs(inicio.getX() - fin.getX()) != abs(inicio.getY() - fin.getY()) ){return false;}
        Ficha pieza = inicio.getFicha();
        if (pieza instanceof  Reina){
        }else{
            if(abs(inicio.getX() - fin.getX())>2){return false;}//No se puede mover mas de dos casillas
        }
        if(pieza instanceof Ninja || pieza instanceof  Reina || pieza instanceof Zombie ){ //La fichas  no se puede mover de para atras
        }else{
            if (pieza.getColor().equalsIgnoreCase("Rojo") && fin.getX() > inicio.getX()) {return false;}
            if (pieza.getColor().equalsIgnoreCase("Negro") && fin.getX() < inicio.getX()) {return false;}
        }
        if(inicio instanceof Jail){
            Jail jail = (Jail) inicio;
            if(inicio.getFicha() != null && ((Jail) inicio).getCuentaTurnosParaSalirCarcel() > 0){
                return false;
            }
        }
        return true;
    }

    /**
     * Elimina las fichas cuando una paso por encima otra ficha
     * @param casilla casilla donde esta la ficha que se va eliminar
     * @param jugador el jugador que va eliminar la ficha
     */
    public void eliminarFichas(Casilla casilla,Jugador jugador){
        if(casilla.getFicha() != null){
            Jugador enemigo;
            if(jugador.getColor() == "Rojo") {
                enemigo = getJugadorByColor("Negro");
                ok = true;
            }else {
                enemigo = getJugadorByColor("Rojo");
                ok = true;
            }
            if(casilla.getFicha() instanceof Gun){
                eventoEscuchadors.forEach((el) -> el.onComodinGun(jugador));
                eliminarPorGun = true;
                ok = true;
            }
            if (casilla.getFicha() instanceof OneMoreTime){
                //eventoEscuchadors.forEach((el) -> el.onComodinOneMoreTime(jugador));
                cuentaRegresiva += tiempoDeTurno;
                ok = true;
            }
            Ficha ficha = casilla.getFicha();
            if(ficha instanceof Zombie){
                Zombie soyZombie = (Zombie) ficha;
                soyZombie.turnoQueHanPasado = 0;
                zombieAparece.add(soyZombie);
                ok = true;
            }
            ficha.fichaEnJuego = false;
            casilla.setFicha(null);
            if(enemigo != null && enemigo.contarFichasEnJuego() == 0){
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
     * @param casilla casilla donde esta la ficha que se va eliminar
     * @param jugador el jugador que va eliminar la ficha
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
     * @param jugador el jugador que tiene el turno
     * @param inicio la casilla de donde etsa la ficha
     * @param fin la casilla a donde va llegar
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
                Casilla casillaDelMedio = getTablero()[xDelMedio][yDelMedio];
                Ficha fichaMedio = casillaDelMedio.getFicha();
                if(fichaMedio != null) {//Verifica si hay una ficha en la casilla y la elimina
                    if(fichaMedio instanceof Ninja){ //Excepto si es Ninja cuando tiene una vida
                        Ninja soyNinja = (Ninja) fichaMedio;
                        if (soyNinja.getTieneVidas()){
                            soyNinja.removerVidas();
                        }else {
                            eliminarFichas(casillaDelMedio,jugador); // Elimina la ficha del juego
                        }
                    } else {
                        if(!jugador.getColor().equalsIgnoreCase(fichaMedio.getColor())){
                            eliminarFichas(casillaDelMedio,jugador);
                        }else {
                            return 0;
                        }
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
            if(fin instanceof Jail){
                ((Jail)fin).setCuentaTurnosParaSalirCarcel(3);
            }
            fin.getFicha().setX(fin.getX()); // Le ubicacion actual de la ficha
            fin.getFicha().setY(fin.getY());
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
     * @param casilla donde esta ubicada la ficha especial
     * @param tipo para escoger que tipo de ficha especial quiere el jugador
     */
    public void fichaSeConvierteEnEspecial(Casilla casilla,int tipo){
         // 0 es Ninja, 1 es Reina y 2 Zombie
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
     * Saber cuando cambia el turno para el otro jugador
     */
    public void cambioTurno(){
        esZombie();
        estaEnJail();
        for (Jugador siguienteJugador : getJugadores()) {
            if (turno.getColor() != siguienteJugador.getColor()) {
                if(permiteQuicktime){ // Reseteado el timepo cuando cambia el turno del jugador
                    cuentaRegresiva = tiempoDeTurno;
                    ok = true;
                }
                turno = siguienteJugador;
                comodines();
                eventoEscuchadors.forEach((el) -> el.onJugarCambio(turno));
                if(maquina && siguienteJugador.getColor().equalsIgnoreCase("Rojo")){
                    autoMovimiento();
                    ok = true;

                }return;
            }
        }
    }

    /**
     * Verifica si la ficha que con la que se va jugar es de tipo Zombie
     */
    private void esZombie(){
        ListIterator<Zombie> zombieI = zombieAparece.listIterator();
        while (zombieI.hasNext()){
            Zombie zombie = zombieI.next();
            if(zombie.turnoQueHanPasado >= 3){
                Casilla casillaZombie = getTablero()[zombie.getX()][ zombie.getY()];
                if(casillaZombie.getFicha() == null){
                    casillaZombie.setFicha(zombie);
                    zombieI.remove();
                }
            }
            zombie.turnoQueHanPasado ++;
        }
    }

    /**
     * Verifica que la ficha esta en un casilla tipo Jail
     */
    private void estaEnJail(){

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if(tablero[i][j] instanceof Jail){
                    Jail jail = (Jail) tablero[i][j];
                    if(jail.getFicha() != null) {
                        jail.setCuentaTurnosParaSalirCarcel(jail.getCuentaTurnosParaSalirCarcel() - 1);
                    }
                }
            }
        }

    }
    /**
     * Que eliga alzar una ficha especial cuando en el modo Maquina VS Jugador la maquina llegue a la ultima linea
     * @param casilla donde esta ubicada la ficha especial
     */
    private void fichaEspecialMaquina(Casilla casilla){
        int numeroAletorio = getNumerosAleatorias(0,2);
        fichaSeConvierteEnEspecial(casilla,numeroAletorio);
    }

    /**
     * Lo movimiento que hace la maquina cuando se elige el modo Maquina VS Jugador
     */
    public void autoMovimiento(){
        Jugador maquina = getJugadorByColor("Rojo");
        for (Ficha ficha: maquina.getFichas()){
            Ficha fichaTablero = getTablero()[ficha.getX()][ficha.getY()].getFicha();
            if(fichaTablero != null && fichaTablero.fichaEnJuego){
                Casilla posibleMovimiento1 = getTableroPorXyY((ficha.getX()-1),(ficha.getY()-1));
                Casilla posibleMovimiento2 = getTableroPorXyY((ficha.getX()-1),(ficha.getY()+1));
                Casilla posibleSalto1 = getTableroPorXyY((ficha.getX()-2),(ficha.getY()-2));
                Casilla posibleSalto2 = getTableroPorXyY((ficha.getX()-2),(ficha.getY()+2));
                Casilla posibleMovimiento3 = null;
                Casilla posibleMovimiento4 = null;
                Casilla posibleSalto3 = null;
                Casilla posibleSalto4 = null;
                if(fichaTablero instanceof Reina || fichaTablero instanceof Ninja || fichaTablero instanceof Zombie){
                    posibleMovimiento3 = getTableroPorXyY(ficha.getX()+1,ficha.getY()-1);
                    posibleMovimiento4 = getTableroPorXyY(ficha.getX()+1,ficha.getY()+1);
                    posibleSalto3 = getTableroPorXyY(ficha.getX()+2,ficha.getY()-2);
                    posibleSalto4 = getTableroPorXyY(ficha.getX()+2,ficha.getY()+2);
                }
                Casilla casillaLugarFicha = getTableroPorXyY(ficha.getX(),ficha.getY());
                if(saltoSiEsPosible(maquina,posibleSalto1,posibleMovimiento1,casillaLugarFicha)){return;}
                if(saltoSiEsPosible(maquina,posibleSalto2,posibleMovimiento2,casillaLugarFicha)){return;}
                if(saltoSiEsPosible(maquina,posibleSalto3,posibleMovimiento3,casillaLugarFicha)){return;}
                if(saltoSiEsPosible(maquina,posibleSalto4,posibleMovimiento4,casillaLugarFicha)){return;}
            }
        }
        for (Ficha ficha: maquina.getFichas()){
            if(ficha.fichaEnJuego){
                Casilla posibleMovimiento1 = getTableroPorXyY((ficha.getX()-1),(ficha.getY()-1));
                Casilla posibleMovimiento2 = getTableroPorXyY((ficha.getX()-1),(ficha.getY()+1));
                Casilla posibleMovimiento3 = null;
                Casilla posibleMovimiento4 = null;
                Casilla casillaLugarFicha = getTableroPorXyY(ficha.getX(),ficha.getY());
                if(ficha instanceof Reina || ficha instanceof Ninja || ficha instanceof Zombie){
                    posibleMovimiento3 = getTableroPorXyY(ficha.getX()+1,ficha.getY()-1);
                    posibleMovimiento4 = getTableroPorXyY(ficha.getX()+1,ficha.getY()+1);
                }
                if(movioSiEsPosible(maquina,posibleMovimiento1,casillaLugarFicha)){return;}
                if(movioSiEsPosible(maquina,posibleMovimiento2,casillaLugarFicha)){return;}
                if(movioSiEsPosible(maquina,posibleMovimiento3,casillaLugarFicha)){return;}
                if(movioSiEsPosible(maquina,posibleMovimiento4,casillaLugarFicha)){return;}
            }
        }
    }

    /**
     * Retorna la posion X y Y del talero para el modo Maquina VS Jugador la maquina puede
     * @param x la posicion en x
     * @param y la posicion en y
     * @return
     */
    public Casilla getTableroPorXyY(int x, int y){
        if(x>9 || y >9 || x< 0 || y<0){
            return null;
        }return tablero[x][y];
    }

    /**
     * Si en el modo Maquina VS Jugador la maquina puede matar una ficha
     * @param maquina que el jugador sea maquina
     * @param posibleSalto saltos que puede dar el jugador maquina si hay una ficha que pueda matar del otro jugador
     * @param posibleMovimiento movimientos que puede dar el jugador maquina
     * @param casillaLugarFicha en donde va quedar la ficha del jugador maquina
     * @return
     */
    private boolean saltoSiEsPosible(Jugador maquina,Casilla posibleSalto,Casilla posibleMovimiento,Casilla casillaLugarFicha){
        if(posibleSalto != null && posibleSalto.getFicha() == null && posibleMovimiento.getFicha() != null){
            int movio = movimientoRequerido(maquina,casillaLugarFicha,posibleSalto);
            if (movio > 0){
                if(movio == 2){
                    fichaEspecialMaquina(posibleMovimiento);
                }return true;
            }
        }return false;
    }

    /**
     * Si en el modo Maquina VS Jugador la maquina puede mover
     * @param maquina que el jugador sea maquina
     * @param posibleMovimiento movimientos que puede dar el jugador maquina
     * @param casillaLugarFicha en donde va quedar la ficha del jugador maquina
     * @return
     */
    private boolean movioSiEsPosible(Jugador maquina,Casilla posibleMovimiento,Casilla casillaLugarFicha){
        if(posibleMovimiento != null && posibleMovimiento.getFicha() == null){
            int movio = movimientoRequerido(maquina,casillaLugarFicha,posibleMovimiento);
            if (movio > 0){
                if(movio == 2){
                    fichaEspecialMaquina(posibleMovimiento);
                }return true;
            }
        }return false;
    }
    /**
     * Se crean los comodines y se tiene encuenta que tipo de comodin se va crear en el tablero
     */
    public void comodines(){
        int seCreoComodines= getNumerosAleatorias(0,2);
        if (seCreoComodines == 1){
            int numeroAleatorioComodines = getNumerosAleatorias(1,4);
            switch (numeroAleatorioComodines){
                case 1: // Hace refencia al comodin gun
                    setComodinesAleatoria(new Gun());
                    ok = true;
                    break;
                case 2: //Hace referencia al comodin one more time
                    if (permiteQuicktime){
                        setComodinesAleatoria(new OneMoreTime());
                    }
                    break;
                case 3: //Hace referencia el comodin stomp
                    // setComodinesAleatoria(new Stomp());
                    break;
            }
        }
    }

    /**
     * Se coloca el comodin en un posi??n aleatorio durante el juego para que los puedan obtener los jugadores
     * @param comodin el tipo de comodin que se va a colocar
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

    /**
     * Verica las clases que no retornan nada y retorna un boolenado para ver si se cumplio lo de esas clases
     * @return
     */
    public boolean ok(){
        return ok;
    }

}
