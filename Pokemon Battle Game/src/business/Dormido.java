package business;

import java.util.Random;

public class Dormido implements State {
	private int turnosDormido;
	private boolean recienDormido = true;

	@Override
	public void atacar(Pokemon atacante, Pokemon rival) {
		if (recienDormido) {
			turnosDormido = calcularTurnosDormido();
			recienDormido = false;
			atacante.mostrarMensajeEstaDormido();
		} else if (turnosDormido > 0) {
			atacante.mostrarMensajeEstaDormido();
		} else {
			recienDormido = true;
			atacante.cambiarEstado(atacante.getSano());
			atacante.atacar(rival);
		}
		turnosDormido--;
	}

	@Override
	public void efectoPostTurno(Pokemon atacante) {

	}

	private int calcularTurnosDormido() {
		Random random = new Random();
		return random.nextInt(4) + 2;
	}
}
