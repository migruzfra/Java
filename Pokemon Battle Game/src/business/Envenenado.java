package business;

public class Envenenado implements State {
	private int turnosEnvenenado;
	private boolean recienEnvenenado = true;

	@Override
	public void atacar(Pokemon atacante, Pokemon rival) {
		atacante.mostrarMensajeAtaque(rival);
		rival.restarVida(atacante.getMovActual().dañoYEfectoDeAtaque(atacante, rival));
		if (rival.estaDebilitado()) {
			rival.mostrarMensajeDebilitado();
		}
	}

	@Override
	public void efectoPostTurno(Pokemon actual) {
		if (recienEnvenenado) {
			turnosEnvenenado = 5;
			recienEnvenenado = false;
			actual.mostrarMensajeYaEstaEnvenenado();
			actual.restarVida(actual.getEspecie().getVida() / 8);
		} else if (turnosEnvenenado > 0) {
			actual.mostrarMensajeYaEstaEnvenenado();
			actual.restarVida(actual.getEspecie().getVida() / 8);
		} else {
			recienEnvenenado = true;
			actual.cambiarEstado(actual.getSano());
		}
		turnosEnvenenado--;

		if (actual.estaDebilitado()) {
			actual.mostrarMensajeDebilitado();
		}
	}
}
