package business;

import ui.Presenter;

public class Jugador extends Entrenador implements UseCaseJugador {

	private final Presenter presenter = new Presenter();

	public Jugador(String nom, Factoria factoria) {
		super(nom, factoria);
	}

	@Override
	public String pedirNombre() {
		return presenter.pedirNombre();
	}

	@Override
	public int elegirQueHacer() {
		return presenter.elegirQueHacer();
	}

	@Override
	public int elegirAtaque() {
		return presenter.elegirAtaque(this.getListaPokemon().get(this.getPokemonActual()).getEspecie());
	}

	@Override
	public int elegirPokemon() {
		return presenter.elegirPokemon(this.getListaPokemon());
	}

}
