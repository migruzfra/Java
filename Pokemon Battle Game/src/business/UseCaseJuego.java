package business;

public interface UseCaseJuego {
	void mostrarMensajeComienza();

	void mostrarMensajeTurno(int turno);

	void mostrarEstadoEntrenadores(Entrenador entrenador1, Entrenador entrenador2);

	void mostrarMensajeHaGanado(Entrenador entrenador1, Entrenador entrenador2);

	void mostrarMensajeHaEmpatado(Entrenador entrenador1, Entrenador entrenador2);

	void mostrarMensajeCambioPokemon(Entrenador entrenador);

	void mostrarMensajeEntrenadorSeRinde(Entrenador entrenador);

}
