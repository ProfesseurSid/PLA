package Visual;

import Engine.Entite;

public class Plateau {

	int grilleWidth = Terrain.getGrilleWidth();
	int grilleHeight = Terrain.getGrilleHeight();

	public Entite[][] plateau = new Entite[grilleHeight][grilleWidth];

	/**
	 * recupere l'entite contenue dans la case d'indices l,c
	 * 
	 * @param l
	 *            la ligne recherchée
	 * @param c
	 *            la colonne recherchée
	 * @require l E [0, nblignes-1] ; c E [0, nbcolonnes-1]
	 * @return l'elemment contenu dans le plateau
	 */
	public Entite unsafeGet(int l, int c) {
		return plateau[l][c];
	}

	public int verification(int newX, int newY) {
		if (plateau[newY][newX] == null) {
			return 0;
		} else {
			return 1;
		}
	}

	public void move(int oldX, int oldY, int newX, int newY) {
		plateau[newY][newX] = plateau[oldY][oldX];
		plateau[oldY][oldX] = null;
	}

	public void put(int newX, int newY, Entite e) {
		plateau[newY][newX] = e;
	}

	public void remove(int x, int y, Entite e) {
		plateau[y][x] = e;
	}

	/**
	 * @return la largeur du plateau
	 */
	public int width() {
		return grilleWidth;
	}

	/**
	 * @return la hauteur du plateau
	 */
	public int height() {
		return grilleWidth;
	}

}
