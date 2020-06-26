package data;

import java.util.Random;

import business.Pokemon;

public class Especial implements Categoria {

	@Override
	public int dañoYEfectoDeAtaque(Pokemon atacante, Pokemon rival, Movimiento movimiento) {
		atacante.mostrarMensajeEfectividad(calcularEfectividad(rival, movimiento));
		return calcularDaño(atacante, rival, movimiento);
	}

	private double calcularEfectividad(Pokemon rival, Movimiento movimiento) {
		double efectividad = movimiento.getEfectividades().get(rival.getEspecie().getTipo1());
		if (rival.getEspecie().getTipo2() != null) { //por si el rival tiene también tipo 2
			efectividad *= movimiento.getEfectividades().get(rival.getEspecie().getTipo2());
		}
		return efectividad;
	}

	private int calcularDaño(Pokemon atacante, Pokemon rival, Movimiento movimiento) {
		Random random = new Random();
		double bonificacion = (atacante.getEspecie().getTipo1().equals(movimiento.getTipo())) ? 1.5 : 1;
		int variacion = random.nextInt(16) + 85;
		double efectividad = calcularEfectividad(rival, movimiento);
		if (bonificacion != 1.5 && atacante.getEspecie().getTipo2() != null) {
			if (atacante.getEspecie().getTipo2().equals(movimiento.getTipo())) {
				bonificacion = 1.5;
			}
		}

		return (int) Math.floor(0.01 * bonificacion * efectividad * variacion
				* ((0.2 * 51 * atacante.getEspecie().getAtqesp() * movimiento.getPotencia())
						/ (25 * rival.getEspecie().getDefesp()) + 2));
	}

}
