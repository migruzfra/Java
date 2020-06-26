package business;

public interface State {
	void atacar(Pokemon atacante, Pokemon rival);

	void efectoPostTurno(Pokemon atacante);

}
