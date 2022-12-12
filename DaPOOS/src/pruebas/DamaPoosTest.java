package pruebas;

import dominio.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DamaPoosTest {
    @Test
    public void deberiaAsignarUnDamaPoos(){
        DamaPoos juego = new DamaPoos();
        DamaPoos.setJuego(juego);
        assertTrue(juego.ok());
    }
    @Test
    public void deberiaAsignarElTiempoDeTurno(){
        DamaPoos juego = new DamaPoos();
        DamaPoos.setJuego(juego);
        juego.setTiempoDeTurno(5);
        assertTrue(juego.ok());
    }
    @Test
    public void deberiaAsignarElTurno(){
        DamaPoos juego = new DamaPoos();
        Jugador jugador  = new Jugador("Pedro","Negro");
        Jugador jugadorE = new Jugador("Alfonso","Rojo");
        Jugador[] jugadores = {jugador,jugadorE};
        juego.setJugadores(jugadores);
        juego.setTurno(jugador);
        assertTrue(juego.ok());
    }
    @Test
    public void deberiaObtenerUnJugadorPorColor(){
        DamaPoos juego = new DamaPoos();
        Jugador jugador  = new Jugador("Pedro","Negro");
        Jugador jugadorE = new Jugador("Alfonso","Rojo");
        Jugador[] jugadores = {jugador,jugadorE};
        juego.setJugadores(jugadores);
        Jugador jugador1 = juego.getJugadorByColor("Negro");
        assertEquals(jugador,jugador1);

    }
    @Test
    public void deberiaAsignarLosJugadores(){
        DamaPoos juego = new DamaPoos();
        Jugador jugador  = new Jugador("Pedro","Negro");
        Jugador jugadorE = new Jugador("Alfonso","Rojo");
        Jugador[] jugadores = {jugador,jugadorE};
        juego.setJugadores(jugadores);
        assertTrue(juego.ok());

    }

    @Test
    public void deberiaCrearTableroYPonerFichas(){
        DamaPoos juego = new DamaPoos();
        Jugador jugador  = new Jugador("Pedro","Negro");
        Jugador jugadorE = new Jugador("Alfonso","Rojo");
        Jugador[] jugadores = {jugador,jugadorE};
        juego.setJugadores(jugadores);
        juego.setTablero();
        juego.ponerFichas();
        assertTrue(juego.ok());

    }
    @Test
    public void deberiaQuickTime(){
        DamaPoos juego = new DamaPoos();
        Jugador jugador  = new Jugador("Pedro","Negro");
        Jugador jugadorE = new Jugador("Alfonso","Rojo");
        Jugador[] jugadores = {jugador,jugadorE};
        juego.setJugadores(jugadores);
        juego.setTablero();
        juego.ponerFichas();
        juego.tiempoDeJuego();
        assertTrue(juego.ok());

    }
    @Test
    public void deberiaPoderVerificarLosPosiblesMovimientos(){
        DamaPoos juego = new DamaPoos();
        Jugador jugador  = new Jugador("Pedro","Rojo");
        Ficha ficha = new Ficha();
        Casilla inicio = new Casilla("Negro",1,0);
        inicio.setFicha(ficha);
        Casilla fin = new Casilla("Negro",2,1);
        assertEquals(false,juego.posiblesMovimientos(jugador,inicio,fin));

    }
    @Test
    public void deberiaPoderTenerElGanador(){
        DamaPoos juego = new DamaPoos();
        Jugador jugador  = new Jugador("Pedro","Negro");
        Jugador jugadorE = new Jugador("Alfonso","Rojo");
        Jugador[] jugadores = {jugador,jugadorE};
        Casilla inicio = new Casilla("Negro",1,0);
        juego.setJugadores(jugadores);
        juego.setTablero();
        juego.ponerFichas();
        juego.eliminarFichas(inicio,jugador);
        assertTrue(juego.ok());

    }
    @Test
    public void deberiaPoderEliminarFichas(){
        DamaPoos juego = new DamaPoos();
        Jugador jugador  = new Jugador("Pedro","Negro");
        Jugador jugadorE = new Jugador("Alfonso","Rojo");
        Jugador[] jugadores = {jugador,jugadorE};
        Ficha ficha = new Ficha("Negro",1,0);
        Casilla inicio = new Casilla("Negro",1,0);
        inicio.setFicha(ficha);
        juego.setJugadores(jugadores);
        juego.setTablero();
        juego.ponerFichas();
        assertEquals(true,juego.removerFichaContricante(inicio,jugadorE));

    }
    @Test
    public void deberiaPoderMoverLasFichas(){
        DamaPoos juego = new DamaPoos();
        Jugador jugador  = new Jugador("Pedro","Negro");
        Jugador jugadorE = new Jugador("Alfonso","Rojo");
        Jugador[] jugadores = {jugador,jugadorE};
        Ficha ficha = new Ficha("Negro",1,0);
        Casilla inicio = new Casilla("Negro",1,0);
        inicio.setFicha(ficha);
        Casilla fin = new Casilla("Negro",2,1);
        juego.setJugadores(jugadores);
        juego.setTablero();
        juego.ponerFichas();
        assertEquals(1,juego.movimientoRequerido(jugador,inicio,fin));

    }
    @Test
    public void deberiaVerFichaEnMina(){
        DamaPoos juego = new DamaPoos();
        Jugador jugador  = new Jugador("Pedro","Negro");
        Jugador jugadorE = new Jugador("Alfonso","Rojo");
        Jugador[] jugadores = {jugador,jugadorE};
        Ficha ficha = new Ficha("Negro",1,0);
        Casilla inicio = new Casilla("Negro",1,0);
        inicio.setFicha(ficha);
        Mine fin = new Mine(2,1);
        juego.setJugadores(jugadores);
        juego.setTablero();
        juego.ponerFichas();
        assertEquals(3,juego.movimientoRequerido(jugador,inicio,fin));

    }
    @Test
    public void deberiaPoderFichasEspecialesReina(){
        DamaPoos juego = new DamaPoos();
        Jugador jugador  = new Jugador("Pedro","Negro");
        Jugador jugadorE = new Jugador("Alfonso","Rojo");
        Jugador[] jugadores = {jugador,jugadorE};
        Ficha ficha = new Ficha("Negro",1,0);
        Casilla inicio = new Casilla("Negro",1,0);
        inicio.setFicha(ficha);
        juego.setJugadores(jugadores);
        juego.setTablero();
        juego.ponerFichas();
        juego.fichaSeConvierteEnEspecial(inicio,1);
        assertTrue(juego.ok());

    }
    @Test
    public void deberiaPoderFichasEspecialesNinja(){
        DamaPoos juego = new DamaPoos();
        Jugador jugador  = new Jugador("Pedro","Negro");
        Jugador jugadorE = new Jugador("Alfonso","Rojo");
        Jugador[] jugadores = {jugador,jugadorE};
        Ficha ficha = new Ficha("Negro",1,0);
        Casilla inicio = new Casilla("Negro",1,0);
        inicio.setFicha(ficha);
        juego.setJugadores(jugadores);
        juego.setTablero();
        juego.ponerFichas();
        juego.fichaSeConvierteEnEspecial(inicio,0);
        assertTrue(juego.ok());

    }

    @Test
    public void deberiaPoderFichasEspecialesZombie(){
        DamaPoos juego = new DamaPoos();
        Jugador jugador  = new Jugador("Pedro","Negro");
        Jugador jugadorE = new Jugador("Alfonso","Rojo");
        Jugador[] jugadores = {jugador,jugadorE};
        Ficha ficha = new Ficha("Negro",1,0);
        Casilla inicio = new Casilla("Negro",1,0);
        inicio.setFicha(ficha);
        juego.setJugadores(jugadores);
        juego.setTablero();
        juego.ponerFichas();
        juego.fichaSeConvierteEnEspecial(inicio,2);
        assertTrue(juego.ok());

    }
    @Test
    public void deberiaCambiarTurno(){
        DamaPoos juego = new DamaPoos();
        Jugador jugador  = new Jugador("Pedro","Negro");
        Jugador jugadorE = new Jugador("Alfonso","Rojo");
        Jugador[] jugadores = {jugador,jugadorE};
        juego.setJugadores(jugadores);
        juego.setTablero();
        juego.ponerFichas();
        juego.cambioTurno();
        assertTrue(juego.ok());

    }
    @Test
    public void deberiaJugarMaquina(){
        DamaPoos juego = new DamaPoos();
        Jugador jugador  = new Jugador("Pedro","Negro");
        Jugador jugadorE = new Jugador("Alfonso","Rojo");
        Jugador[] jugadores = {jugador,jugadorE};
        Ficha ficha = new Ficha("Negro",1,0);
        Casilla inicio = new Casilla("Negro",1,0);
        inicio.setFicha(ficha);
        juego.setJugadores(jugadores);
        juego.setTablero();
        juego.ponerFichas();
        juego.autoMovimiento();
        assertTrue(juego.ok());

    }
    @Test
    public void deberiaCrearComodines(){
        DamaPoos juego = new DamaPoos();
        Jugador jugador  = new Jugador("Pedro","Negro");
        Jugador jugadorE = new Jugador("Alfonso","Rojo");
        Jugador[] jugadores = {jugador,jugadorE};
        Ficha ficha = new Ficha("Negro",1,0);
        Casilla inicio = new Casilla("Negro",1,0);
        inicio.setFicha(ficha);
        juego.setJugadores(jugadores);
        juego.setTablero();
        juego.ponerFichas();
        juego.comodines();
        assertTrue(juego.ok());

    }
    @Test
    public void deberiaPonerComodines(){
        DamaPoos juego = new DamaPoos();
        Jugador jugador  = new Jugador("Pedro","Negro");
        Jugador jugadorE = new Jugador("Alfonso","Rojo");
        Jugador[] jugadores = {jugador,jugadorE};
        Ficha ficha = new Ficha("Negro",1,0);
        Casilla inicio = new Casilla("Negro",1,0);
        inicio.setFicha(ficha);
        Comodin comodin = new Comodin();
        juego.setJugadores(jugadores);
        juego.setTablero();
        juego.ponerFichas();
        juego.comodines();
        juego.setComodinesAleatoria(comodin);
        assertTrue(juego.ok());

    }
    @Test
    public void deberiaNoVerCasillasEspeciales(){
        DamaPoos juego = new DamaPoos();
        Jugador jugador  = new Jugador("Pedro","Negro");
        Jugador jugadorE = new Jugador("Alfonso","Rojo");
        Jugador[] jugadores = {jugador,jugadorE};
        Ficha ficha = new Ficha("Negro",1,0);
        Casilla inicio = new Casilla("Negro",1,0);
        inicio.setFicha(ficha);
        juego.setJugadores(jugadores);
        juego.setTablero();
        juego.ponerFichas();
        juego.comodines();
        juego.noVerCasillaEspeciales();
        assertTrue(juego.ok());

    }
    @Test
    public void deberiaAssignarEltiempoTurno(){
        DamaPoos juego = new DamaPoos();
        Jugador jugador  = new Jugador("Pedro","Negro");
        Jugador jugadorE = new Jugador("Alfonso","Rojo");
        Jugador[] jugadores = {jugador,jugadorE};
        Ficha ficha = new Ficha("Negro",1,0);
        Casilla inicio = new Casilla("Negro",1,0);
        inicio.setFicha(ficha);
        juego.setJugadores(jugadores);
        juego.setTablero();
        juego.ponerFichas();
        juego.comodines();
        juego.noVerCasillaEspeciales();
        assertTrue(juego.ok());

    }





}
