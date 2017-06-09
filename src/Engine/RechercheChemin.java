package Engine;

import java.util.ArrayList;
import java.util.Arrays;

import Visual.PersonnagesVisual;
import Visual.Plateau;
import Visual.RobotVisual;
import javafx.scene.image.ImageView;

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
	 Personnages p = new Personnages(plat, 0, new PersonnagesVisual(new ImageView(), 0, plat));
	 Personnages p2 = new Personnages(plat, 1, new PersonnagesVisual(new ImageView(), 1, plat));
	 Robots r = new Robots(plat, p, 0, new RobotVisual(new ImageView(), 0, plat));
	 plat.toString();
	 RechercheChemin maze = new RechercheChemin(plat, 1, 5, 20, 5);
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
		this.height = plate.nbLignes();
		this.width = plate.nbColonnes();
		this.destX = destX;
		this.destY = destY;
		this.origX = origX;
		this.origY = origY;
		System.out.println("Cherche " + destX + " " + destY + " Depuis " + origX + " " + origY);

		grid = new int[height][width];
		for (int i = 0; i < plate.nbLignes(); i++)
			for (int j = 0; j < plate.nbColonnes(); j++)
				if (plate.unsafeGet(j, i) instanceof Vivante)
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
		System.out.println("JE SUIS LA");
		if (solve()) {
			System.out.println("ET LA");
			for (int i = 0; i < nbPas; i++)
				if (y > 0 && map[x][y - 1] == PATH)
					retour.add(PointCardinal.OUEST);
				else if (y < width-1 && map[x][y + 1] == PATH)
					retour.add(PointCardinal.EST);
				else if (x < height-1 && map[x + 1][y] == PATH)
					retour.add(PointCardinal.SUD);
				else if (x > 0 && map[x - 1][y] == PATH)
					retour.add(PointCardinal.NORD);
			System.out.println("LENGTH : " + retour.size());
		}
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
	 * la case (i,j) est-elle le depart ?
	 * 
	 * @param i
	 *            colonne de la case a tester
	 * @param j
	 *            ligne de la case a tester
	 * @return booleen : la case est le depart
	 */
	private boolean isStart(int i, int j) {
		return i == origX && j == origY;
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
		return isStart(i, j) || isEnd(i, j) || grid[i][j] == 1;
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
		return i>=0 && i<height && (i >= origX && i <= destX) || (i <= origX && i >= destX);
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
		return j>=0 && j<width && (j >= origY && j <= destY) || (j <= origY && j >= destY);
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
