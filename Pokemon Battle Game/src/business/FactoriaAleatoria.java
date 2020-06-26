package business;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import data.Database;
import data.Especie;

public class FactoriaAleatoria extends Factoria {
	private FactoriaAleatoria instance;
	private final int NUMPOKEMON = 3;

	public FactoriaAleatoria getInstance() {
		synchronized (FactoriaAleatoria.class) {
			if (instance == null) {
				instance = new FactoriaAleatoria();
			}
		}
		return instance;
	}

	@Override
	public List<Pokemon> crear() {
		List<Pokemon> listaPokemon = new ArrayList<>();
		Especie especie = especieAleatoria();
		listaPokemon.add(new Pokemon(especie));
		do {
			especie = especieAleatoria();
			if (!repetido(listaPokemon, especie)) {
				listaPokemon.add(new Pokemon(especie));
			}
		} while (listaPokemon.size() < NUMPOKEMON);
		return listaPokemon;

	}

	private Especie especieAleatoria() {
		Random random = new Random();
		return Database.INSTANCE.queryAllEspecies().get(random.nextInt(31) + 1);
	}

	private boolean repetido(List<Pokemon> listaPokemon, Especie especie) {
		boolean repetido = false;
		for (Pokemon pokemon : listaPokemon) {
			if (pokemon.getEspecie().equals(especie)) {
				repetido = true;
			}
		}
		return repetido;
	}
}
