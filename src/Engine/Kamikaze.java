package Engine;

import Exception.PanicException;

/**
 * Classe de l'operateur Kamikaze. Lorsque un robot rencontre un ennemi il
 * explose.
 */
public class Kamikaze implements Operateurs {

	private int x, y;

	/**
	 * Constructeur de kamikaze
	 * 
	 * @disclamer not sure of this constructor
	 */
	public Kamikaze() {
		x = 0;
		y = 0;
	}

	/**
	 * Constructeur de kamikaze
	 */
	public Kamikaze(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter de x
	 * 
	 * @return x;
	 */
	public int getX() {
		return x;
	}

	/**
	 * Getter de y
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Ajoute l'operateur a l'inventaire du personnage
	 * 
	 * @param p
	 *            Personnage qui doit recevoir l'operateur.
	 */
	public void stock(Personnages p) {
		p.addOperator('K');
	}

	/**
	 * Methode qui fait executer l'action Kamikaze à un robot.
	 * 
	 * @param nono
	 *            Robot qui va executer l'action.
	 */
	public void action(Robots nono) {
		nono.boom();
	}

	@Override
	public String toString() {
		return "K";
	}

	/**
	 * Methode qui teste si l'action est possible ou efficace a un moment donné.
	 * 
	 * @param nono
	 *            Robot qui doit executer l'action.
	 * @return true si l'action est possible false sinon.
	 */
	public boolean isPossible(Robots nono) {
		return (nono.ennemiAdjacent() != null);
	}

}
