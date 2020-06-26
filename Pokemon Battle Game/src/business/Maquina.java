package business;

import java.util.Random;

public class Maquina extends Entrenador {
	private final Random random = new Random();

	public Maquina(String nom, Factoria factoria) {
		super(nom, factoria);
	}

	@Override
	public int elegirQueHacer() {
		return random.nextInt(2) + 1;
	}

	@Override
	public int elegirAtaque() {
		return random.nextInt(4) + 1;
	}

	@Override
	public int elegirPokemon() {
		return random.nextInt(getListaPokemon().size());
	}

}
