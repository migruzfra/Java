package data;

import java.util.Map;
import java.util.Random;

import business.Pokemon;

public class Movimiento {

	private final String nombre;
	private final int potencia;
	private final int precision;
	private Categoria categoria;
	private final Tipo tipo;
	private final Map<Tipo, Double> efectividades;

	public Movimiento(String nombre, int potencia, int precision, String categoriaDesconocida, Tipo tipo,
			Map<Tipo, Double> efectividades) {
		this.nombre = nombre;
		this.potencia = potencia;
		this.precision = precision;
		categoria = asignarCategoria(categoriaDesconocida);
		this.tipo = tipo;
		this.efectividades = efectividades;
	}

	private Categoria asignarCategoria(String categoriaEntrada) {
		if (categoriaEntrada.equals("FISICO")) {
			categoria = new Fisico();
		} else if (categoriaEntrada.equals("ESPECIAL")) {
			categoria = new Especial();
		} else if (categoriaEntrada.equals("ESTADOENV")) {
			categoria = new EstadoEnv();
		} else if (categoriaEntrada.equals("ESTADOPAR")) {
			categoria = new EstadoPar();
		} else {
			categoria = new EstadoDor();
		}
		return categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public int getPotencia() {
		return potencia;
	}

	public int getPrecision() {
		return precision;
	}

	public Map<Tipo, Double> getEfectividades() {
		return efectividades;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public int dañoYEfectoDeAtaque(Pokemon atacante, Pokemon rival) {
		Random random = new Random();
		int daño;
		if (random.nextInt(101) > atacante.getMovActual().getPrecision()) {
			atacante.getPresenter().mostrarMensajeHaFallado(atacante);
			daño = 0;
		} else {
			daño = categoria.dañoYEfectoDeAtaque(atacante, rival, this);
		}
		return daño;
	}

	@Override
	public String toString() {
		return String.format("%s", nombre);
	}

}
