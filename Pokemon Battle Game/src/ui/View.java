package ui;

import business.Entrenador;
import business.Pokemon;
import data.Movimiento;

public interface View {

	//******************************POKEMON******************************//
	void mostrarMensajeAtaque(Pokemon pokemon, Movimiento movimiento, Pokemon rival);

	void mostrarMensajeDebilitado(Pokemon pokemon);

	void mostrarMensajeCambioEstadoDormido(Pokemon pokemon);

	void mostrarMensajeCambioEstadoParalizado(Pokemon pokemon);

	void mostrarMensajeCambioEstadoEnvenenado(Pokemon pokemon);

	void mostrarMensajeSeHaDespertado(Pokemon pokemon);

	void mostrarMensajeCuradoVeneno(Pokemon pokemon);

	void mostrarMensajeEstaDormido(Pokemon pokemon);

	void mostrarMensajeYaEstaDormido(Pokemon pokemon);

	void mostrarMensajeEstaParalizado(Pokemon pokemon);

	void mostrarMensajeYaEstaParalizado(Pokemon pokemon);

	void mostrarMensajeYaEstaEnvenenado(Pokemon pokemon);

	void mostrarMensajeSufreEfectosVeneno(Pokemon pokemon);

	void mostrarMensajeHaFallado(Pokemon pokemon);

	public void mostrarMensajeEfectividad0();

	public void mostrarMensajeEfectividad025();

	public void mostrarMensajeEfectividad05();

	public void mostrarMensajeEfectividad2();

	public void mostrarMensajeEfectividad4();

	//******************************JUGADOR******************************//
	String pedirNombre();

	int elegirQueHacer(Menu menu);

	int elegirAtaque(Menu menu);

	//******************************JUEGO******************************//
	void mostrarMensajeComienza();

	void mostrarMensajeTurno(int turno);

	int showMenu(Menu menu);

	void mostrarEstadoEntrenadores(Entrenador entrenador1, Entrenador entrenador2);

	void mostrarMensajeHaGanado(Entrenador entrenador1, Entrenador entrenador2);

	void mostrarMensajeHaEmpatado(Entrenador entrenador1, Entrenador entrenador2);

	void mostrarMensajeCambioPokemon(Entrenador entrenador);

	void mostrarMensajeEntrenadorSeRinde(Entrenador entrenador);
}
