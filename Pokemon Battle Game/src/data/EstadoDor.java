package data;

import business.Pokemon;

public class EstadoDor implements Categoria {

	@Override
	public int dañoYEfectoDeAtaque(Pokemon atacante, Pokemon rival, Movimiento movimiento) {
		if (rival.getEstado() != rival.getDormido()) {
			rival.cambiarEstado(rival.getDormido());
		} else {
			rival.mostrarMensajeYaEstaDormido();
		}

		return 0;
	}

}
