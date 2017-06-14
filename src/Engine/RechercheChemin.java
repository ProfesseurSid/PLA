package Engine;

import java.util.ArrayList;
import java.util.Arrays;

import Exception.PanicException;
import Visual.PersonnagesVisual;
import Visual.Plateau;
import Visual.RobotVisual;
import Visual.Terrain;
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
	static RechercheChemin maze;
	boolean invert;

	public static void main(String[] args) {
		Terrain t = new Terrain();
		Personnages p = new Personnages(t, 0, new PersonnagesVisual(new ImageView(), 0, t.getPlateau()));
		Personnages p2 = new Personnages(t, 1, new PersonnagesVisual(new ImageView(), 1, t.getPlateau()));
		Robots r = new Robots(t, p, 0, new RobotVisual(new ImageView(), 0, t.getPlateau()));
		Robots r2 = new Robots(t, p, 1, new RobotVisual(new ImageView(), 1, t.getPlateau()));
		t.toString();
		maze = new RechercheChemin(t.getPlateau(), 0, 5, 18, 5);
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
	private int nbExec = 0;

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
		this.destX = destX;
		this.destY = destY;
		this.origX = origX;
		this.origY = origY;
		this.height = plate.nbLignes();
		this.width = plate.nbColonnes();

		grid = new int[height][width];
		for (int i = 0; i < plate.nbLignes(); i++)
			for (int j = 0; j < plate.nbColonnes(); j++)
				if (plate.unsafeGet(j, i) instanceof Vivante)
					grid[i][j] = 0;
				else
					grid[i][j] = 1;

//		grid[5][17] = 5;

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
		ArrayList<PointCardinal> retourne = new ArrayList<PointCardinal>();
		int x = origX;
		int y = origY;
		// System.out.println("JE SUIS LA");
		if (solve()) {
			// System.out.println("ET LA");
			for (int i = 0; i < nbPas; i++) {
				if (y > 0 && map[x][y - 1] == PATH) {
					retour.add(PointCardinal.OUEST);
					map[x][y - 1] = 0;
				} else if (y < width - 1 && map[x][y + 1] == PATH) {
					retour.add(PointCardinal.EST);
					map[x][y + 1] = 0;
				} else if (x < height - 1 && map[x + 1][y] == PATH) {
					retour.add(PointCardinal.SUD);
					map[x + 1][y] = 0;
				} else if (x > 0 && map[x - 1][y] == PATH) {
					retour.add(PointCardinal.NORD);
					map[x - 1][y] = 0;
				}
			}
			// System.out.println("LENGTH : " + retour.size());
		}
		if (invert) {
			for (int i = retour.size() - 1; i >= 0; i--)
				switch (retour.get(i)) {
				case NORD:
					retourne.add(PointCardinal.SUD);
					break;
				case SUD:
					retourne.add(PointCardinal.NORD);
					break;
				case EST:
					retourne.add(PointCardinal.OUEST);
					break;
				case OUEST:
					retourne.add(PointCardinal.EST);
					break;
				default:
					throw new PanicException("RechercheChemin : PointCardinal incorrect");
				}
			return retourne;
		}
		return retour;
	}

	/**
	 * Lance la recherche de plus court chemin.
	 * 
	 * @return booleen : un chemin a ete trouve
	 */
	public boolean solve() {
		boolean found = traverse(origX, origY) <= height*width;

		while (!found && nbExec < width) {
			System.out.println("Exec : " + nbExec);
			this.map = new int[height][width];
			nbExec++;
			found = traverse(origX, origY) <= height*width;
		}
		return found;
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
	private int traverse(int i, int j) {
		if (!isValid(i, j)) {
			return height*width+1;
		}

		if (isEnd(i, j)) {
//			map[i][j] = PATH;
			return 1;
		} else {
			map[i][j] = TRIED;
		}
		
		int haut = traverse(i+1, j);
		int bas = traverse(i-1, j);
		int gauche = traverse(i, j-1);
		int droite = traverse(i, j+1);
		
		if(droite < gauche && droite < haut && droite < bas){
			map[i][j+1] = PATH;
			return droite+1;
		}
		else if(haut < bas && haut < gauche && haut < droite){
			map[i+1][j] = PATH;
			return haut+1;
		}
		else if(gauche < droite && gauche < haut && gauche < bas){
			map[i][j-1] = PATH;
			return gauche+1;
		}
		else if(bas <= height*width){
			map[i-1][j] = PATH;
			return bas+1;
		}
		
//		System.out.println("X : " + j + " Y : " + i);

//		if (isEnd(i, j)) {
//			map[i][j] = PATH;
//			return true;
//		} else {
//			map[i][j] = TRIED;
//		}
//
//		// North
//		if (traverse(i - 1, j)) {
//			map[i - 1][j] = PATH;
//			return true;
//		}
//		// East
//		if (traverse(i, j + 1)) {
//			map[i][j + 1] = PATH;
//			return true;
//		}
//		// South
//		if (traverse(i + 1, j)) {
//			map[i + 1][j] = PATH;
//			return true;
//		}
//		// West
//		if (traverse(i, j - 1)) {
//			map[i][j - 1] = PATH;
//			return true;
//		}
//
//		return false;
		return height*width+1;
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
		return i >= 0 && i < height
				&& ((i >= origX - nbExec && i <= destX + nbExec) || (i <= origX + nbExec && i >= destX - nbExec));
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
		return j >= 0 && j < width
				&& ((j >= origY - nbExec && j <= destY + nbExec) || (j <= origY + nbExec && j >= destY - nbExec));
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