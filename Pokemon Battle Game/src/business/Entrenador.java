package business;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public abstract class Entrenador {
	private final String nombre;
	private List<Pokemon> listaPokemon;
	private int pokemonActual;

	public Entrenador(String nombre, Factoria factoria) {
		this.nombre = nombre;
		listaPokemon = obtenerLista(factoria);
	}

	private List<Pokemon> obtenerLista(Factoria factoria) {
		return factoria.crear();
	}

	public List<Pokemon> getListaPokemon() {
		return listaPokemon;
	}

	public int getPokemonActual() {
		return pokemonActual;
	}

	public String getNombre() {
		return nombre;
	}

	public void setPokemonActual(int nuevoPokemon) {
		pokemonActual = nuevoPokemon;
	}

	void eliminarPokemonSiDebilitado() {
		if (listaPokemon.get(pokemonActual).estaDebilitado()) {
			listaPokemon.remove(pokemonActual);
		}
	}

	public abstract int elegirQueHacer();

	public abstract int elegirAtaque();

	public abstract int elegirPokemon();

	Entrenador entrenadorMasRapido(Entrenador entrenador) {
		Random random = new Random();
		int velocidadEntrenador1 = this.getListaPokemon().get(this.getPokemonActual()).getVelocidad(),
				velocidadEntrenador2 = entrenador.getListaPokemon().get(entrenador.getPokemonActual()).getVelocidad();
		Entrenador rapido;
		if (velocidadEntrenador1 == velocidadEntrenador2) {
			if (random.nextBoolean()) {
				rapido = this;
			} else {
				rapido = entrenador;
			}
		} else {
			if (velocidadEntrenador1 > velocidadEntrenador2) {
				rapido = this;
			} else {
				rapido = entrenador;
			}

		}
		return rapido;
	}

	void cambiarPokemon(int nuevoPokemon) {
		this.setPokemonActual(nuevoPokemon);
	}

	void atacar(Entrenador entrenador) {
		this.getListaPokemon().get(this.getPokemonActual())
				.atacar(entrenador.getListaPokemon().get(entrenador.getPokemonActual()));
	}

	@Override
	public String toString() {
		return String.format("Entrenador: %s Lista de pokemon: %s%n Pokemon actual: %s", nombre,
				listaPokemon.stream().map(x -> x.getEspecie().getNombre()).collect(Collectors.toList()),
				listaPokemon.get(pokemonActual));
	}
}
