package business;

public interface UseCasePokemon {
	void mostrarMensajeAtaque(Pokemon rival);

	void mostrarMensajeDebilitado();

	void mostrarMensajeCambioEstado(State estado);

	void mostrarMensajeRecuperacion(State estado);

	void mostrarMensajeEstaDormido();

	void mostrarMensajeEstaParalizado();

	void mostrarMensajeYaEstaEnvenenado();

	void mostrarMensajeYaEstaParalizado();

	void mostrarMensajeYaEstaDormido();

	void mostrarMensajeSufreEfectosVeneno();

	void mostrarMensajeHaFallado();

	void mostrarMensajeEfectividad(double efectividad);

}
