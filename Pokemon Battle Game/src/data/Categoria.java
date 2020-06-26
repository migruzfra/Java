package data;

import business.Pokemon;

public interface Categoria {
	int dañoYEfectoDeAtaque(Pokemon atacante, Pokemon rival, Movimiento movimiento);
}
