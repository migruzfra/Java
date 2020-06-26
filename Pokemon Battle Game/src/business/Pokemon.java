package business;

import data.*;
import ui.Presenter;

public class Pokemon implements UseCasePokemon {
	private final Sano sano = new Sano();
	private final Dormido dormido = new Dormido();
	private final Envenenado envenenado = new Envenenado();
	private final Paralizado paralizado = new Paralizado();

	private final Presenter presenter = new Presenter();
	private State estado;
	private final Especie especie;
	private int vida;
	private int velocidad;
	private Movimiento movActual;

	public Pokemon(Especie especie) {
		estado = sano;
		this.especie = especie;
		vida = especie.getVida();
		velocidad = especie.getVelocidad();
		movActual = especie.getListaMov().get(0);
	}

	public int getVida() {
		return vida;
	}

	public Especie getEspecie() {
		return especie;
	}

	public Sano getSano() {
		return sano;
	}

	public Dormido getDormido() {
		return dormido;
	}

	public Envenenado getEnvenenado() {
		return envenenado;
	}

	public Paralizado getParalizado() {
		return paralizado;
	}

	public Movimiento getMovActual() {
		return movActual;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public State getEstado() {
		return estado;
	}

	public Presenter getPresenter() {
		return presenter;
	}

	public void setMovActual(int indexMovimiento) {
		movActual = especie.getListaMov().get(indexMovimiento - 1);
	}

	public void cambiarEstado(State estado) {
		cambioVelocidadYMensajesSetEstado(estado);
		this.estado = estado;
	}

	private void cambioVelocidadYMensajesSetEstado(State estado) {
		cambioVelocidadSiParalisis(estado);
		if (estado == sano) {
			mostrarMensajeRecuperacion(this.estado); //Se muestra el mensaje de que el pokemon se ha curado del estado actual
		} else {
			mostrarMensajeCambioEstado(estado); //Se muestra el estado al que ha cambiado
		}
	}

	boolean estaDebilitado() {
		return vida <= 0;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	void restarVida(int daño) {
		this.vida -= daño;
	}

	void atacar(Pokemon rival) {
		estado.atacar(this, rival);
	}

	void efectoPostTurno() {
		estado.efectoPostTurno(this);
	}

	private void cambioVelocidadSiParalisis(State estado) {
		if (this.estado == paralizado) {
			setVelocidad(especie.getVelocidad());
		} else if (estado == paralizado) {
			setVelocidad((int) Math.floor(especie.getVelocidad() * 0.5));
		}
	}

	@Override
	public void mostrarMensajeAtaque(Pokemon rival) {
		presenter.mostrarMensajeAtaque(this, movActual, rival);
	}

	@Override
	public void mostrarMensajeDebilitado() {
		presenter.mostrarMensajeDebilitado(this);
	}

	@Override
	public void mostrarMensajeCambioEstado(State estado) {
		if (estado == dormido) {
			presenter.mostrarMensajeCambioEstadoDormido(this);
		} else if (estado == paralizado) {
			presenter.mostrarMensajeCambioEstadoParalizado(this);
		} else if (estado == envenenado) {
			presenter.mostrarMensajeCambioEstadoEnvenenado(this);
		}
	}

	@Override
	public void mostrarMensajeRecuperacion(State estado) {
		if (estado == dormido) {
			presenter.mostrarMensajeSeHaDespertado(this);
		} else if (estado == envenenado) {
			presenter.mostrarMensajeCuradoVeneno(this);
		}
	}

	@Override
	public void mostrarMensajeEstaDormido() {
		presenter.mostrarMensajeEstaDormido(this);
	}

	@Override
	public void mostrarMensajeEstaParalizado() {
		presenter.mostrarMensajeEstaParalizado(this);
	}

	@Override
	public void mostrarMensajeYaEstaEnvenenado() {
		presenter.mostrarMensajeYaEstaEnvenenado(this);
	}

	@Override
	public void mostrarMensajeYaEstaParalizado() {
		presenter.mostrarMensajeYaEstaParalizado(this);
	}

	@Override
	public void mostrarMensajeYaEstaDormido() {
		presenter.mostrarMensajeYaEstaDormido(this);
	}

	@Override
	public void mostrarMensajeSufreEfectosVeneno() {
		presenter.mostrarMensajeSufreEfectosVeneno(this);
	}

	@Override
	public void mostrarMensajeHaFallado() {
		presenter.mostrarMensajeHaFallado(this);
	}

	@Override
	public void mostrarMensajeEfectividad(double efectividad) {
		presenter.mostrarMensajeEfectividad(efectividad);
	}

	@Override
	public String toString() {
		return String.format("%s Estado: %s Vida: %d Velocidad: %d", especie.getNombre(),
				estado.getClass().getSimpleName(), vida, velocidad);
	}

}
