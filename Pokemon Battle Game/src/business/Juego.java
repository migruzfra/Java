package business;

import ui.Presenter;

public class Juego implements UseCaseJuego {
	private final Presenter presenter = new Presenter();
	private Entrenador jugador, maquina;
	private boolean salir = false, elLentoNoHaSidoDebilitado;

	public void jugar() {
		crearEntrenadores();
		combate(jugador, maquina);
	}

	private void crearEntrenadores() {
		Factoria factoria = new FactoriaAleatoria();
		String nom;
		nom = presenter.pedirNombre();
		jugador = new Jugador(nom, factoria);
		nom = presenter.pedirNombre();
		maquina = new Jugador(nom, factoria);

	}

	private void combate(Entrenador entrenador1, Entrenador entrenador2) {
		int turno = 0, opcionEntrenador1, opcionEntrenador2;
		mostrarMensajeComienza();
		do {
			mostrarMensajeTurno(++turno);
			mostrarEstadoEntrenadores(entrenador1, entrenador2);
			opcionEntrenador1 = elegirQueHacer(entrenador1);
			opcionEntrenador2 = elegirQueHacer(entrenador2);
			elLentoNoHaSidoDebilitado = false;
			if (!algunoSeRinde(entrenador1, opcionEntrenador1, entrenador2, opcionEntrenador2)) { //comprobar si se rinden porque se acaba el combate
				if (!algunoCambia(entrenador1, opcionEntrenador1, entrenador2, opcionEntrenador2)) {//Comprobar si cambian porque lo harán antes
					losDosAtacan(entrenador1, entrenador2);
				}
				if (!salir) {
					efectosPostTurno(entrenador1, entrenador2);
				}
			}
		} while (!salir);
	}

	private boolean algunoCambia(Entrenador entrenador1, int opcionEntrenador1, Entrenador entrenador2,
			int opcionEntrenador2) {
		boolean algunoCambia = false;
		Entrenador rapido, lento;
		if (opcionEntrenador1 == 2 || opcionEntrenador2 == 2) {
			algunoCambia = true;
			if (opcionEntrenador1 == 2 && opcionEntrenador2 == 2) {
				ambosCambian(entrenador1, entrenador2);
			} else {
				if (opcionEntrenador1 == 2) {
					rapido = entrenador1;
					lento = entrenador2;
				} else {
					rapido = entrenador2;
					lento = entrenador1;
				}
				lento.getListaPokemon().get(lento.getPokemonActual()).setMovActual(lento.elegirAtaque());
				cambiarPokemon(rapido);
				atacarYComprobarSiSeDebilita(lento, rapido);
			}
		}
		return algunoCambia;
	}

	private void losDosAtacan(Entrenador entrenador1, Entrenador entrenador2) {
		Entrenador rapido, lento;
		entrenador1.getListaPokemon().get(entrenador1.getPokemonActual()).setMovActual(entrenador1.elegirAtaque());
		entrenador2.getListaPokemon().get(entrenador2.getPokemonActual()).setMovActual(entrenador2.elegirAtaque());
		rapido = entrenador1.entrenadorMasRapido(entrenador2);
		if (rapido == entrenador1) {
			lento = entrenador2;
		} else {
			lento = entrenador1;
		}
		if (!atacarYComprobarSiSeDebilita(rapido, lento)) {
			if (elLentoNoHaSidoDebilitado) {
				atacarYComprobarSiSeDebilita(lento, rapido);
			}
		}
	}

	private int elegirQueHacer(Entrenador entrenador) {
		return entrenador.elegirQueHacer();
	}

	private boolean atacarYComprobarSiSeDebilita(Entrenador entrenador, Entrenador rival) {

		entrenador.atacar(rival);

		if (rival.getListaPokemon().get(rival.getPokemonActual()).estaDebilitado()) {
			rival.getListaPokemon().remove(rival.getPokemonActual());

			if (rival.getListaPokemon().isEmpty()) {//Si la lista está vacía, pierde el combate. Si no, cambia de pokemon
				salir = true;
				mostrarMensajeHaGanado(entrenador, rival);
			} else { //Si el 
				cambiarPokemon(rival);
			}
		} else {
			elLentoNoHaSidoDebilitado = true; //Para saber si pasa al siguiente turno o si continua al siguiente
		}
		return salir;
	}

	private void efectosPostTurno(Entrenador entrenador1, Entrenador entrenador2) {
		boolean entr1Debilitado = false, entr2Debilitado = false;
		Pokemon pokeEntrenador1 = entrenador1.getListaPokemon().get(entrenador1.getPokemonActual()),
				pokeEntrenador2 = entrenador2.getListaPokemon().get(entrenador2.getPokemonActual());
		pokeEntrenador1.efectoPostTurno();
		if (pokeEntrenador1.estaDebilitado()) {
			entrenador1.getListaPokemon().remove(entrenador1.getPokemonActual());
			entr1Debilitado = true;
		}
		pokeEntrenador2.efectoPostTurno();
		if (pokeEntrenador2.estaDebilitado()) {
			entrenador2.getListaPokemon().remove(entrenador2.getPokemonActual());
			entr2Debilitado = true;
		}

		if (entrenador1.getListaPokemon().isEmpty() || entrenador2.getListaPokemon().isEmpty()) {
			if (entrenador1.getListaPokemon().isEmpty() && entrenador2.getListaPokemon().isEmpty()) {
				mostrarMensajeHaEmpatado(entrenador1, entrenador2);
			} else if (entrenador1.getListaPokemon().isEmpty()) {
				mostrarMensajeHaGanado(entrenador2, entrenador1);
			} else {
				mostrarMensajeHaGanado(entrenador1, entrenador2);
			}

			salir = true;
		} else {
			if (entr1Debilitado) {
				cambiarPokemon(entrenador1);
			}
			if (entr2Debilitado) {
				cambiarPokemon(entrenador2);
			}
		}
	}

	private void cambiarPokemon(Entrenador entrenador) {
		entrenador.cambiarPokemon(entrenador.elegirPokemon());
		mostrarMensajeCambioPokemon(entrenador);
	}

	private void ambosCambian(Entrenador entrenador1, Entrenador entrenador2) {
		entrenador1.cambiarPokemon(entrenador1.elegirPokemon());
		entrenador2.cambiarPokemon(entrenador2.elegirPokemon());
		mostrarMensajeCambioPokemon(entrenador1);
		mostrarMensajeCambioPokemon(entrenador2);
	}

	private boolean algunoSeRinde(Entrenador entrenador1, int opcion1, Entrenador entrenador2, int opcion2) {
		if (opcion1 == 3 || opcion2 == 3) {
			salir = true;
			if (opcion1 == 3 && opcion2 == 3) {
				presenter.mostrarMensajeEntrenadorSeRinde(entrenador1);
				presenter.mostrarMensajeEntrenadorSeRinde(entrenador2);
				presenter.mostrarMensajeHaEmpatado(entrenador1, entrenador2);
			} else if (opcion1 == 3) {
				presenter.mostrarMensajeEntrenadorSeRinde(entrenador1);
				presenter.mostrarMensajeHaGanado(entrenador2, entrenador1);
			} else {
				presenter.mostrarMensajeEntrenadorSeRinde(entrenador2);
				presenter.mostrarMensajeHaGanado(entrenador1, entrenador2);
			}
		}
		return salir;
	}

	@Override
	public void mostrarMensajeComienza() {
		presenter.mostrarMensajeComienza();
	}

	@Override
	public void mostrarMensajeTurno(int turno) {
		presenter.mostrarMensajeTurno(turno);
	}

	@Override
	public void mostrarEstadoEntrenadores(Entrenador entrenador1, Entrenador entrenador2) {
		presenter.mostrarEstadoEntrenadores(entrenador1, entrenador2);
	}

	@Override
	public void mostrarMensajeHaEmpatado(Entrenador entrenador1, Entrenador entrenador2) {
		presenter.mostrarMensajeHaEmpatado(entrenador1, entrenador2);
	}

	@Override
	public void mostrarMensajeHaGanado(Entrenador entrenador1, Entrenador entrenador2) {
		presenter.mostrarMensajeHaGanado(entrenador1, entrenador2);
	}

	@Override
	public void mostrarMensajeCambioPokemon(Entrenador entrenador) {
		presenter.mostrarMensajeCambioPokemon(entrenador);
	}

	@Override
	public void mostrarMensajeEntrenadorSeRinde(Entrenador entrenador) {
		presenter.mostrarMensajeEntrenadorSeRinde(entrenador);
	}
}