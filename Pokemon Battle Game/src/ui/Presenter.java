package ui;

import java.util.List;

import business.*;
import data.*;

public class Presenter {
	View view = new Consola();

	//******************************POKEMON******************************//
	public void mostrarMensajeAtaque(Pokemon pokemon, Movimiento movimiento, Pokemon rival) {
		view.mostrarMensajeAtaque(pokemon, movimiento, rival);
	}

	public void mostrarMensajeDebilitado(Pokemon pokemon) {
		view.mostrarMensajeDebilitado(pokemon);
	}

	public void mostrarMensajeCambioEstadoDormido(Pokemon pokemon) {
		view.mostrarMensajeCambioEstadoDormido(pokemon);
	}

	public void mostrarMensajeCambioEstadoParalizado(Pokemon pokemon) {
		view.mostrarMensajeCambioEstadoParalizado(pokemon);
	}

	public void mostrarMensajeCambioEstadoEnvenenado(Pokemon pokemon) {
		view.mostrarMensajeCambioEstadoEnvenenado(pokemon);
	}

	public void mostrarMensajeSeHaDespertado(Pokemon pokemon) {
		view.mostrarMensajeSeHaDespertado(pokemon);
	}

	public void mostrarMensajeCuradoVeneno(Pokemon pokemon) {
		view.mostrarMensajeCuradoVeneno(pokemon);
	}

	public void mostrarMensajeEstaDormido(Pokemon pokemon) {
		view.mostrarMensajeEstaDormido(pokemon);
	}

	public void mostrarMensajeEstaParalizado(Pokemon pokemon) {
		view.mostrarMensajeEstaParalizado(pokemon);
	}

	public void mostrarMensajeYaEstaEnvenenado(Pokemon pokemon) {
		view.mostrarMensajeYaEstaEnvenenado(pokemon);
	}

	public void mostrarMensajeYaEstaParalizado(Pokemon pokemon) {
		view.mostrarMensajeYaEstaParalizado(pokemon);
	}

	public void mostrarMensajeYaEstaDormido(Pokemon pokemon) {
		view.mostrarMensajeYaEstaDormido(pokemon);
	}

	public void mostrarMensajeSufreEfectosVeneno(Pokemon pokemon) {
		view.mostrarMensajeSufreEfectosVeneno(pokemon);
	}

	public void mostrarMensajeHaFallado(Pokemon pokemon) {
		view.mostrarMensajeHaFallado(pokemon);
	}

	public void mostrarMensajeEfectividad(double efectividad) {
		if (efectividad == 0.0) {
			view.mostrarMensajeEfectividad0();
		} else if (efectividad == 0.25) {
			view.mostrarMensajeEfectividad025();
		} else if (efectividad == 0.5) {
			view.mostrarMensajeEfectividad05();
		} else if (efectividad == 2.0) {
			view.mostrarMensajeEfectividad2();
		} else if (efectividad == 4.0) {
			view.mostrarMensajeEfectividad4();
		}
	}

	//******************************JUGADOR******************************//
	public String pedirNombre() {
		return view.pedirNombre();
	}

	public int elegirQueHacer() {
		final Menu menuPrincipal;

		menuPrincipal = new Menu("--Menú principal--", new MenuItem("Atacar"), new MenuItem("Cambiar de Pokémon"),
				new MenuItem("Rendirse"));
		return view.elegirQueHacer(menuPrincipal);
	}

	public int elegirAtaque(Especie especie) {
		final Menu menuAtaque;

		menuAtaque = new Menu("--Atacar--", new MenuItem(especie.getListaMov().get(0).getNombre()),
				new MenuItem(especie.getListaMov().get(1).getNombre()),
				new MenuItem(especie.getListaMov().get(2).getNombre()),
				new MenuItem(especie.getListaMov().get(3).getNombre()));
		return view.elegirAtaque(menuAtaque);
	}

	public int elegirPokemon(List<Pokemon> listaPokemon) {
		final Menu menuPokemon;

		if (listaPokemon.size() == 3) {
			menuPokemon = new Menu("--Cambiar pokémon--", new MenuItem(listaPokemon.get(0).getEspecie().getNombre()),
					new MenuItem(listaPokemon.get(1).getEspecie().getNombre()),
					new MenuItem(listaPokemon.get(2).getEspecie().getNombre()));
		} else if (listaPokemon.size() == 2) {
			menuPokemon = new Menu("--Cambiar pokémon--", new MenuItem(listaPokemon.get(0).getEspecie().getNombre()),
					new MenuItem(listaPokemon.get(1).getEspecie().getNombre()));
		} else {
			menuPokemon = new Menu("--Cambiar pokémon--", new MenuItem(listaPokemon.get(0).getEspecie().getNombre()));
		}

		return view.showMenu(menuPokemon) - 1;
	}

	//******************************JUEGO******************************//
	public void mostrarMensajeComienza() {
		view.mostrarMensajeComienza();
	}

	public void mostrarMensajeTurno(int turno) {
		view.mostrarMensajeTurno(turno);
	}

	public void mostrarEstadoEntrenadores(Entrenador entrenador1, Entrenador entrenador2) {
		view.mostrarEstadoEntrenadores(entrenador1, entrenador2);
	}

	public void mostrarMensajeHaGanado(Entrenador entrenador1, Entrenador entrenador2) {
		view.mostrarMensajeHaGanado(entrenador1, entrenador2);
	}

	public void mostrarMensajeHaEmpatado(Entrenador entrenador1, Entrenador entrenador2) {
		view.mostrarMensajeHaEmpatado(entrenador1, entrenador2);
	}

	public void mostrarMensajeCambioPokemon(Entrenador entrenador) {
		view.mostrarMensajeCambioPokemon(entrenador);
	}

	public void mostrarMensajeEntrenadorSeRinde(Entrenador entrenador) {
		view.mostrarMensajeEntrenadorSeRinde(entrenador);
	}
}