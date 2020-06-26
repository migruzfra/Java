package data;

import business.Pokemon;

public class EstadoEnv implements Categoria {
	@Override
	public int dañoYEfectoDeAtaque(Pokemon atacante, Pokemon rival, Movimiento movimiento) {
		if (rival.getEstado() != rival.getEnvenenado()) {
			rival.cambiarEstado(rival.getEnvenenado());
		} else {
			rival.mostrarMensajeYaEstaEnvenenado();
		}
		return 0;
	}

}
