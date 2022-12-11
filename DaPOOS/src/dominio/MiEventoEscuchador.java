package dominio;

import java.io.Serializable;

public interface MiEventoEscuchador extends Serializable{

    public void onJuegoTerminado(Jugador jugadorGanador);
    public void onJugarCambio(Jugador jugador);

    public void onComodinGun(Jugador jugador);
    public void onComodinStomp(Jugador juegodor,Casilla casillaFin);
    public void onComodinOneMoreTime(Jugador jugador);
    public void onCuentaRegresivaTurno(int cuentaRegresiva);



}
