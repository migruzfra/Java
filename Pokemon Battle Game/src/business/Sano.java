package business;

public class Sano implements State {

	@Override
	public void atacar(Pokemon atacante, Pokemon rival) {
		atacante.mostrarMensajeAtaque(rival);
		rival.restarVida(atacante.getMovActual().dañoYEfectoDeAtaque(atacante, rival));
		if (rival.estaDebilitado()) {
			rival.mostrarMensajeDebilitado();
		}
	}

	@Override
	public void efectoPostTurno(Pokemon atacante) {

	}
}
