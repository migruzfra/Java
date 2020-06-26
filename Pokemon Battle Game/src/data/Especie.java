package data;

import java.util.List;

public class Especie {
	final private int id;
	final private String nombre;
	final private int vida;
	final private int ataque;
	final private int defensa;
	final private int atqesp;
	final private int defesp;
	final private int velocidad;
	final private Tipo tipo1;
	final private Tipo tipo2;
	final private List<Movimiento> listaMov;

	public Especie(int id, String nombre, int vida, int ataque, int defensa, int atqesp, int defesp, int velocidad,
			Tipo tipo1, Tipo tipo2, Movimiento mov1, Movimiento mov2, Movimiento mov3, Movimiento mov4) {
		this.id = id;
		this.nombre = nombre;
		this.vida = vida;
		this.ataque = ataque;
		this.defensa = defensa;
		this.atqesp = atqesp;
		this.defesp = defesp;
		this.velocidad = velocidad;
		this.tipo1 = tipo1;
		this.tipo2 = tipo2;
		this.listaMov = List.of(mov1, mov2, mov3, mov4);
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getVida() {
		return vida;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public Tipo getTipo1() {
		return tipo1;
	}

	public Tipo getTipo2() {
		return tipo2;
	}

	public int getAtaque() {
		return ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public int getAtqesp() {
		return atqesp;
	}

	public int getDefesp() {
		return defesp;
	}

	public List<Movimiento> getListaMov() {
		return listaMov;
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = false;

		if (obj instanceof Especie && this.id == ((Especie) obj).id) {
			equals = true;
		}
		return equals;
	}

	@Override
	public String toString() {
		return "Especie [id=" + id + ", nombre=" + nombre + ", vida=" + vida + ", ataque=" + ataque + ", defensa="
				+ defensa + ", atqesp=" + atqesp + ", defesp=" + defesp + ", velocidad=" + velocidad + ", tipo1="
				+ tipo1 + ", tipo2=" + tipo2 + ", movimientos=" + listaMov + "]";
	}

}
