package Engine;

import Exception.PanicException;
import Visual.Terrain;

public class Robots implements Vivante {

	Automates behavior;
	int x, y;
	int equipe;

	/**
	 * crée un robot et le place dans l'équipe e
	 * 
	 * @param e
	 */
	public Robots(int e) {
		if (e == 0) {
			x = Terrain.getTuileY() / 2;
			y = 1;
		} else if (e == 1) {
			x = Terrain.getTuileY() / 2;
			y = Terrain.getTuileX() - 1;
		} else
			throw new PanicException("Numéro d'équipe incorrect");
		equipe = e;
		behavior = new Automates();
	}

	/**
	 * Crée un robot de comportement a, et le place dans l'équipe e
	 * 
	 * @param e
	 * @param a
	 */
	public Robots(int e, Automates a) {
		if (e == 0) {
			x = Terrain.getTuileY() / 2;
			y = 1;
		} else if (e == 1) {
			x = Terrain.getTuileY() / 2;
			y = Terrain.getTuileX() - 1;
		} else
			throw new PanicException("Numéro d'équipe incorrect");
		equipe = e;
		behavior = new Automates(a);
	}

	public String toString() {
		return "R(" + x + "," + y + ") : " + behavior.toString();
	}

	@Override
	public void detruire() {
		// TODO Auto-generated method stub

	}

	@Override
	public void apparaitre() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouvement(PointCardinal p, int nb) {
		// TODO Auto-generated method stub
	}

	/**
	 * Simple getter de coordonée
	 */
	public int getX() {
		return x;
	}

	/**
	 * Simple getter de coordonée
	 */
	public int getY() {
		return y;
	}

	public int getEquipe() {
		return equipe;
	}

	public boolean memeEquipe(Vivante v) {
		return equipe == v.getEquipe();
	}

	@Override
	public void mouvement(PointCardinal p) {
		// TODO Auto-generated method stub
		
	}
}
