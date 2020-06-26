package business;

import java.util.Random;

public class Paralizado implements State {
	private final Random random = new Random();

	@Override
	public void atacar(Pokemon atacante, Pokemon rival) {
		if (random.nextInt(4) == 0) {
			atacante.mostrarMensajeEstaParalizado();
		} else {
			atacante.mostrarMensajeAtaque(rival);
			rival.restarVida(atacante.getMovActual().dañoYEfectoDeAtaque(atacante, rival));
			if (rival.estaDebilitado()) {
				rival.mostrarMensajeDebilitado();
			}
		}
	}

	@Override
	public void efectoPostTurno(Pokemon atacante) {

	}
}
