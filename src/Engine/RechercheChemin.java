package Engine;

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
		RechercheChemin maze = new RechercheChemin(plat, 6, 0, 6, 5);
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

	public RechercheChemin(Plateau plate, int origX, int origY, int destX, int destY) {
        this.height = plate.nblignes;
        this.width = plate.nbcolonnes;
        this.destX = destX;
        this.destY = destY;
        this.origX = origX;
        this.origY = origY;
        grid = new int[height][width];
		for(int i=0; i<plate.nblignes; i++)
			for(int j=0; j<plate.nbcolonnes; j++)
				if(plate.unsafeGet(i, j) instanceof Vivante)
					grid[i][j] = 0;
				else
					grid[i][j] = 1;

        this.map = new int[height][width];
    }

	public boolean solve() {
		return traverse(origX, origY);
	}

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

	private boolean isEnd(int i, int j) {
		return i == destY && j == destX;
	}

	private boolean isValid(int i, int j) {
		if (inRange(i, j) && isOpen(i, j) && !isTried(i, j)) {
			return true;
		}

		return false;
	}

	private boolean isOpen(int i, int j) {
		return grid[i][j] == 1;
	}

	private boolean isTried(int i, int j) {
		return map[i][j] == TRIED;
	}

	private boolean inRange(int i, int j) {
		return inHeight(i) && inWidth(j);
	}

	private boolean inHeight(int i) {
		return (i >= origX && i <= destX) || (i <= origX && i >= destX);
	}

	private boolean inWidth(int j) {
		return (j >= origY && j <= destY) || (j <= origY && j >= destY);
	}

	public String toString() {
		String s = "";
		for (int[] row : map) {
			s += Arrays.toString(row) + "\n";
		}

		return s;
	}
}
