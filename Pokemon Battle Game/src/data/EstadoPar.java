package data;

import business.Pokemon;

public class EstadoPar implements Categoria {
	@Override
	public int dañoYEfectoDeAtaque(Pokemon atacante, Pokemon rival, Movimiento movimiento) {
		if (rival.getEstado() != rival.getParalizado()) {
			rival.cambiarEstado(rival.getParalizado());
		} else {
			rival.mostrarMensajeYaEstaParalizado();
		}
		return 0;
	}

}
