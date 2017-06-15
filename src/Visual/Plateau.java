package Visual;

import Engine.Entite;
import Engine.Operateurs;
import Engine.Personnages;
import Engine.Robots;
import Engine.Vivante;
import Exception.PanicException;

public class Plateau {

	int grilleWidth;
	int grilleHeight;

	public Entite[][] plateau;

	Terrain t;

	public Plateau(Terrain t) {
		grilleWidth = Terrain.getTuileX();
		grilleHeight = Terrain.getTuileY();
		plateau = new Entite[grilleHeight][grilleWidth];
		this.t = t;
	}

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
	public Entite unsafeGet(int c, int l) {
		if (l >= grilleHeight)
			System.out.println("ICI" + l);
		return plateau[l][c];
	}

	/**
	 * verifie la possibilite d'un deplacement vers la case (x,y)
	 * 
	 * @param x
	 *            la colonne de la case a verifier
	 * @param y
	 *            la ligne de la case a verifier
	 * @return 0 si la case (x,y) est accessible, 1 sinon
	 */
	public int verification(int x, int y) {
		if (plateau[y][x] instanceof Vivante) {
			return 1;
		} else {
			return 0;
		}
	}

	public boolean verifCaseVide(int x, int y) {
		if (plateau[y][x] == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * deplace le contenu de la case (oldX,oldY) dans la case (newX,newY)
	 * 
	 * @param oldX
	 *            la colonne de la case d'origine
	 * @param oldY
	 *            la logne de la case d'origine
	 * @param newX
	 *            la colonne de la case d'arrivee
	 * @param newY
	 *            la colonne de la case d'arrivee
	 */
	public void move(int oldX, int oldY, int newX, int newY) {
		plateau[newY][newX] = plateau[oldY][oldX];
		plateau[oldY][oldX] = null;
	}

	/**
	 * place une entite dans la case (x,y)
	 * 
	 * @param x
	 *            la colonne de la case
	 * @param y
	 *            la ligne de la case
	 * @param e
	 *            l'entite a placer
	 * 
	 * @require la case (x,y) est vide
	 */
	public void put(int x, int y, Entite e) {
		if (plateau[y][x] == null)
			plateau[y][x] = e;
		else
			throw new PanicException("Tentative de put dans case non vide");
	}

	/**
	 * remplace le contenu de la case (x,y) par l'entite E
	 * 
	 * @param x
	 *            la colonne de la case
	 * @param y
	 *            la ligne de la case
	 * @param e
	 *            l'entite a placer
	 */
	public void remove(int x, int y, Entite e) {
		if (plateau[y][x] == e)
			plateau[y][x] = null;
	}

	/**
	 * @return la largeur du plateau
	 */
	public int nbLignes() {
		return grilleHeight;
	}

	/**
	 * @return la hauteur du plateau
	 */
	public int nbColonnes() {
		return grilleWidth;
	}

	public String toString() {
		for (int i = 0; i < grilleWidth; i++) {
			for (int j = 0; j < grilleHeight; j++) {
				if (plateau[j][i] == null)
					System.out.print("_");
				else if (plateau[j][i] instanceof Operateurs)
					System.out.print(plateau[j][i].toString());
				else if (plateau[j][i] instanceof Robots)
					System.out.print("R");
				else if (plateau[j][i] instanceof Personnages)
					System.out.print("P");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
		System.out.print("\n");
		return "";
	}

}
