package dominio;

import java.io.Serializable;
/**
 * Esta interfaz srive para tiner eventos escuchadores para saber que ciertas acciones pasaron
 * @author Erika Juliana Castro Romero y Mariana Pulido Moreno
 * @version 5.0
 */
public interface MiEventoEscuchador extends Serializable{
    /**
     * Esta sirve para saber cuando el juego acabo
     * @param jugadorGanador
     */
    public void onJuegoTerminado(Jugador jugadorGanador);

    /**
     * Esta sirve para saber cuando el juego cambio de jugador
     * @param jugador
     */
    public void onJugarCambio(Jugador jugador);

    /**
     * Esta sirve para saber cuando un jugador tiene un comodin Gun
     * @param jugador
     */
    public void onComodinGun(Jugador jugador);

    /**
     * Esta sirve para saber cuando el jueego esta en modo QuickTime y cuanto tiempo hay en cada turno
     * @param cuentaRegresiva
     */

    public void onCuentaRegresivaTurno(int cuentaRegresiva);



}
