package ui;

import business.Entrenador;
import business.Pokemon;
import data.*;
import static keyboard.Keyboard.*;

public class Consola implements View {

	//******************************POKEMON******************************//
	@Override
	public void mostrarMensajeDebilitado(Pokemon pokemon) {
		System.out.printf("%s se ha debilitado%n", pokemon.getEspecie().getNombre());
	}

	@Override
	public void mostrarMensajeCambioEstadoDormido(Pokemon pokemon) {
		System.out.printf("%s ha sido dormido%n", pokemon.getEspecie().getNombre());
	}

	@Override
	public void mostrarMensajeCambioEstadoParalizado(Pokemon pokemon) {
		System.out.printf("%s ha sido paralizado%n", pokemon.getEspecie().getNombre());
	}

	@Override
	public void mostrarMensajeCambioEstadoEnvenenado(Pokemon pokemon) {
		System.out.printf("%s ha sido envenenado%n", pokemon.getEspecie().getNombre());
	}

	@Override
	public void mostrarMensajeSeHaDespertado(Pokemon pokemon) {
		System.out.printf("%s se ha despertado%n", pokemon.getEspecie().getNombre());
	}

	@Override
	public void mostrarMensajeCuradoVeneno(Pokemon pokemon) {
		System.out.printf("%s se ha curado del veneno%n", pokemon.getEspecie().getNombre());
	}

	@Override
	public void mostrarMensajeAtaque(Pokemon pokemon, Movimiento movimiento, Pokemon rival) {
		System.out.printf("%s usó %s contra %s%n", pokemon.getEspecie().getNombre(), movimiento.getNombre(),
				rival.getEspecie().getNombre());
	}

	@Override
	public void mostrarMensajeEstaDormido(Pokemon pokemon) {
		System.out.printf("%s está dormido y no puede atacar%n", pokemon.getEspecie().getNombre());
	}

	@Override
	public void mostrarMensajeEstaParalizado(Pokemon pokemon) {
		System.out.printf("%s está paralizado y no puede atacar%n", pokemon.getEspecie().getNombre());
	}

	@Override
	public void mostrarMensajeYaEstaEnvenenado(Pokemon pokemon) {
		System.out.printf("%s está envenenado y pierde %d puntos de vida%n", pokemon.getEspecie().getNombre(),
				pokemon.getEspecie().getVida() / 8);
	}

	@Override
	public void mostrarMensajeYaEstaParalizado(Pokemon pokemon) {
		System.out.printf("%s ya está paralizado%n", pokemon.getEspecie().getNombre());
	}

	@Override
	public void mostrarMensajeYaEstaDormido(Pokemon pokemon) {
		System.out.printf("%s ya está dormido%n", pokemon.getEspecie().getNombre());
	}

	@Override
	public void mostrarMensajeSufreEfectosVeneno(Pokemon pokemon) {
		System.out.printf("%s sufre los efectos del veneno%n", pokemon.getEspecie().getNombre());
	}

	@Override
	public void mostrarMensajeHaFallado(Pokemon pokemon) {
		System.out.printf("%s ha fallado%n", pokemon.getEspecie().getNombre());
	}

	@Override
	public void mostrarMensajeEfectividad0() {
		System.out.println("El rival es inmune a este ataque");
	}

	@Override
	public void mostrarMensajeEfectividad025() {
		System.out.println("Es muy poco eficaz");
	}

	@Override
	public void mostrarMensajeEfectividad05() {
		System.out.println("Es poco eficaz");
	}

	@Override
	public void mostrarMensajeEfectividad2() {
		System.out.println("Es muy eficaz");
	}

	@Override
	public void mostrarMensajeEfectividad4() {
		System.out.println("Es fucking eficaz");
	}

	//******************************JUGADOR******************************//
	@Override
	public String pedirNombre() {
		return readString("Introduzca nombre del entrenador");
	}

	@Override
	public int elegirQueHacer(Menu menuElegir) {
		return showMenu(menuElegir);
	}

	@Override
	public int elegirAtaque(Menu menuAtaque) {
		return showMenu(menuAtaque);
	}

	@Override
	public int showMenu(Menu menu) {
		int size = menu.getSize(), option;
		System.out.println(menu);
		option = readRange(1, size, Range.II);
		return option;
	}

	//******************************JUEGO******************************//

	@Override
	public void mostrarMensajeComienza() {
		System.out.println("Comienza el combate");
	}

	@Override
	public void mostrarMensajeTurno(int turno) {
		System.out.printf("Turno %d%n", turno);
	}

	@Override
	public void mostrarEstadoEntrenadores(Entrenador entrenador1, Entrenador entrenador2) {
		System.out.printf("%s%n%s%n", entrenador1, entrenador2);
	}

	@Override
	public void mostrarMensajeHaGanado(Entrenador entrenador1, Entrenador entrenador2) {
		System.out.printf("El entrenador %s ha ganado. El entrenador %s ha perdido%n", entrenador1.getNombre(),
				entrenador2.getNombre());
	}

	@Override
	public void mostrarMensajeHaEmpatado(Entrenador entrenador1, Entrenador entrenador2) {
		System.out.printf("El entrenador %s y el entrenador %s han empatado%n", entrenador1.getNombre(),
				entrenador2.getNombre());
	}

	@Override
	public void mostrarMensajeCambioPokemon(Entrenador entrenador) {
		System.out.printf("%s cambia de pokémon y saca a %s%n", entrenador.getNombre(),
				entrenador.getListaPokemon().get(entrenador.getPokemonActual()).getEspecie().getNombre());
	}

	@Override
	public void mostrarMensajeEntrenadorSeRinde(Entrenador entrenador) {
		System.out.printf("%s se ha rendido%n", entrenador.getNombre());
	}

}
