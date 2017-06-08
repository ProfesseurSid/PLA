package Engine;

import java.util.ArrayList;
import java.util.Arrays;

import Visual.Plateau;

/**
 * Implémente les fonctions propres a la recherche du plus court chemin en
 * utilisant le plateau
 * 
 * @author CHANET Zoran
 *
 */
public class RechercheChemin {
	final static int TRIED = 2;
	final static int PATH = 3;

	public static void main(String[] args) {
		Plateau plat = new Plateau();
		RechercheChemin maze = new RechercheChemin(plat, 10, 0, 7, 5);
		boolean solved = maze.solve();
		System.out.println("Solved: " + solved);
		System.out.println(maze.toString());
	}

	private int[][] grid;
	private int height;
	private int width;
	private int destX;
	private int destY;
	private int origX;
	private int origY;

	private int[][] map;

	/**
	 * Initialise la grille de recherche et les points de départ et d'arrivée
	 * 
	 * @param plate
	 *            le plateau sur lequel on recherche le chemin
	 * @param origX
	 *            la colonne de depart
	 * @param origY
	 *            la ligne de depart
	 * @param destX
	 *            la colonne d'arrivee
	 * @param destY
	 *            la ligne d'arrivee
	 */
	public RechercheChemin(Plateau plate, int origY, int origX, int destY, int destX) {
		this.height = plate.nblignes;
		this.width = plate.nbcolonnes;
		this.destX = destX;
		this.destY = destY;
		this.origX = origX;
		this.origY = origY;
		grid = new int[height][width];
		for (int i = 0; i < plate.nblignes; i++)
			for (int j = 0; j < plate.nbcolonnes; j++)
				if (plate.unsafeGet(i, j) instanceof Vivante)
					grid[i][j] = 0;
				else
					grid[i][j] = 1;

		this.map = new int[height][width];
	}

	/**
	 * Cherche les x premiers pas du plus court chemin de l'origine à la
	 * destination
	 * 
	 * @param x
	 *            le nombre de pas a effectuer
	 * @return la liste des x premiers pas
	 */
	public ArrayList<PointCardinal> xPas(int nbPas) {
		ArrayList<PointCardinal> retour = new ArrayList<PointCardinal>();
		int x = origX;
		int y = origY;
		if (solve())
			for (int i = 0; i < nbPas; i++)
				if (map[x][y - 1] == PATH)
					retour.add(PointCardinal.NORD);
				else if (map[x][y + 1] == PATH)
					retour.add(PointCardinal.SUD);
				else if (map[x + 1][y] == PATH)
					retour.add(PointCardinal.EST);
				else if (map[x - 1][y] == PATH)
					retour.add(PointCardinal.OUEST);
		return retour;
	}

	/**
	 * Lance la recherche de plus court chemin.
	 * 
	 * @return booleen : un chemin a ete trouve
	 */
	public boolean solve() {
		return traverse(origX, origY);
	}

	/**
	 * Recherche recursivement le plus court chemin de (i,j) à (destX,destY)
	 * 
	 * @param i
	 *            la ligne de depart
	 * @param j
	 *            la colonne de depart
	 * @return booleen : un chemin a ete trouve
	 */
	private boolean traverse(int i, int j) {
		if (!isValid(i, j)) {
			return false;
		}

		if (isEnd(i, j)) {
			map[i][j] = PATH;
			return true;
		} else {
			map[i][j] = TRIED;
		}

		// North
		if (traverse(i - 1, j)) {
			map[i - 1][j] = PATH;
			return true;
		}
		// East
		if (traverse(i, j + 1)) {
			map[i][j + 1] = PATH;
			return true;
		}
		// South
		if (traverse(i + 1, j)) {
			map[i + 1][j] = PATH;
			return true;
		}
		// West
		if (traverse(i, j - 1)) {
			map[i][j - 1] = PATH;
			return true;
		}

		return false;
	}

	/**
	 * la case (i,j) est-elle la destination ?
	 * 
	 * @param i
	 *            colonne de la case a tester
	 * @param j
	 *            ligne de la case a tester
	 * @return booleen : la case est la destination
	 */
	private boolean isEnd(int i, int j) {
		return i == destX && j == destY;
	}

	/**
	 * la case (i,j) est-elle valide pour la recherche ?
	 * 
	 * @param i
	 *            la colonne de la case a tester
	 * @param j
	 *            la ligne de la case a tester
	 * @return booleen : la case est valide pour la recherche
	 */
	private boolean isValid(int i, int j) {
		if (inRange(i, j) && isOpen(i, j) && !isTried(i, j)) {
			return true;
		}

		return false;
	}

	/**
	 * la case (i,j) est-elle libre (traversable) ?
	 * 
	 * @param i
	 *            la colonne de la case a tester
	 * @param j
	 *            la ligne de la case a tester
	 * @return booleen : la case est libre
	 */
	private boolean isOpen(int i, int j) {
		return grid[i][j] == 1;
	}

	/**
	 * la case (i,j) a-t-elle deja ete testee ?
	 * 
	 * @param i
	 *            la colonne de la case a tester
	 * @param j
	 *            la ligne de la case a tester
	 * @return booleen : la case a deja ete testee
	 */
	private boolean isTried(int i, int j) {
		return map[i][j] == TRIED;
	}

	/**
	 * la case (i,j) est-elle entre l'origine et la destination ?
	 * 
	 * @param i
	 *            la colonne de la case a tester
	 * @param j
	 *            la ligne de la case a tester
	 * @return booleen : la case est entre l'origine et la destination
	 */
	private boolean inRange(int i, int j) {
		return inHeight(i) && inWidth(j);
	}

	/**
	 * la colonne est-elle entre la colonne de l'origine et celle de la
	 * destination ?
	 * 
	 * @param i
	 *            la colonne a tester
	 * @return booleen : la colonne est entre celle de l'origine et celle de la
	 *         destination
	 */
	private boolean inHeight(int i) {
		return (i >= origX && i <= destX) || (i <= origX && i >= destX);
	}

	/**
	 * la ligne est-elle entre la ligne de l'origine et celle de la destination
	 * ?
	 * 
	 * @param i
	 *            la ligne a tester
	 * @return booleen : la ligne est entre celle de l'origine et celle de la
	 *         destination
	 */
	private boolean inWidth(int j) {
		return (j >= origY && j <= destY) || (j <= origY && j >= destY);
	}

	/**
	 * affiche le plateau de recherche du chemin (affiche les case libres,
	 * obstacles, visitées et retenues pour le chemin)
	 */
	public String toString() {
		String s = "";
		for (int[] row : map) {
			s += Arrays.toString(row) + "\n";
		}

		return s;
	}
}
