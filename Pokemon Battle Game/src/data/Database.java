package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Database {
	INSTANCE;

	private Map<Integer, Especie> mapaEspecies;
	private Map<Integer, Movimiento> mapaMovimientos;
	private Map<Integer, Tipo> mapaTipos = new TreeMap<Integer, Tipo>();
	private Map<Tipo, Map<Tipo, Double>> mapaEfectividades = new TreeMap<Tipo, Map<Tipo, Double>>();

	private Database() {
		rellenarMapaTipos();
		rellenarMapaEfectividades();
		rellenarMapaMovimientos();
		rellenarMapaEspecies();
	}

	private void rellenarMapaTipos() {
		Stream<String> linea;
		try (BufferedReader objReader = new BufferedReader(new FileReader("FicheroTipos.csv"))) {
			linea = objReader.lines();
			mapaTipos = linea.map(string -> string.split(";"))
					.collect(Collectors.toMap(array -> Integer.parseInt(array[0]), array -> new Tipo(array[1])));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void rellenarMapaMovimientos() {
		Stream<String> linea;
		try (BufferedReader objReader = new BufferedReader(new FileReader("FicheroMovimientos.csv"))) {
			linea = objReader.lines();
			mapaMovimientos = linea.map(string -> string.split(";"))
					.collect(Collectors.toMap(array -> Integer.parseInt(array[0]),
							array -> new Movimiento(array[1], Integer.parseInt(array[2]), Integer.parseInt(array[3]),
									array[4], queryAllTipos().get(Integer.parseInt(array[5])),
									queryAllEfectividades().get(queryAllTipos().get(Integer.parseInt(array[5]))))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void rellenarMapaEspecies() {
		Stream<String> linea;
		try (BufferedReader objReader = new BufferedReader(new FileReader("FicheroEspecies.csv"))) {
			linea = objReader.lines();
			mapaEspecies = linea.map(string -> string.split(";"))
					.collect(
							Collectors.toMap(array -> Integer.parseInt(array[0]),
									array -> new Especie(Integer.parseInt(array[0]), //
											array[1], //
											Integer.parseInt(array[2]), //
											Integer.parseInt(array[3]), //
											Integer.parseInt(array[4]), //
											Integer.parseInt(array[5]), Integer.parseInt(array[6]),
											Integer.parseInt(array[7]), queryAllTipos().get(Integer.parseInt(array[8])),
											!array[9].equals("") ? queryAllTipos().get(Integer.parseInt(array[9]))
													: null,
											queryAllMovimientos().get(Integer.parseInt(array[10])),
											queryAllMovimientos().get(Integer.parseInt(array[11])),
											queryAllMovimientos().get(Integer.parseInt(array[12])),
											queryAllMovimientos().get(Integer.parseInt(array[13])))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void rellenarMapaEfectividades() {
		Stream<String> linea;
		try (BufferedReader objReader = new BufferedReader(new FileReader("FicheroEfectividades.csv"))) {
			linea = objReader.lines();
			mapaEfectividades = linea.map(string -> string.split(";"))
					.collect(Collectors.groupingBy(array -> queryAllTipos().get(Integer.parseInt(array[0])),
							Collectors.toMap(key -> queryAllTipos().get(Integer.parseInt(key[1])),
									value -> Double.parseDouble(value[2]))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<Integer, Especie> queryAllEspecies() {
		return mapaEspecies;
	}

	public Map<Integer, Movimiento> queryAllMovimientos() {
		return mapaMovimientos;
	}

	public Map<Integer, Tipo> queryAllTipos() {
		return mapaTipos;
	}

	public Map<Tipo, Map<Tipo, Double>> queryAllEfectividades() {
		return mapaEfectividades;
	}
}
