package data;

public class Tipo implements Comparable<Tipo> {

	private final String nombre;

	public Tipo(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return String.format("%s", nombre);
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = false;

		if (obj instanceof Tipo && this.nombre.equals(((Tipo) obj).nombre)) {
			equals = true;
		}
		return equals;
	}

	@Override
	public int compareTo(Tipo tipo) {
		return nombre.compareTo(tipo.nombre);
	}
}
