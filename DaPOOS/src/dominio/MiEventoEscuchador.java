package dominio;

import java.io.Serializable;

public interface MiEventoEscuchador {

    public void onJuegoTerminado(Jugador jugadorGanador);
    public void onJugarCambio(Jugador jugador);


}
