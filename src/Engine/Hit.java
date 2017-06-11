package Engine;

/**
 * Classe de l'operateur Hit. Lorsque un robot rencontre un ennemi il le frappe.
 */
public class Hit implements Operateurs {

	private int x, y;

	/**
	 * Constructeur de hit
	 * 
	 * @disclamer not sure of this constructor
	 */
	public Hit() {
		x = 0;
		y = 0;
	}

	/**
	 * Constructeur de hit
	 */
	public Hit(int x, int y) {
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
		p.addOperator('H');
	}

	/**
	 * Methode qui fait executer l'action Hit à un robot.
	 * 
	 * @param nono
	 *            Robot qui va executer l'action.
	 */
	public void action(Robots nono) {
		nono.hit();
	}

	public String toString() {
		return "H";
	}

	/**
	 * Methode qui teste si l'action est possible ou efficace a un moment
	 * donné.
	 * 
	 * @param nono
	 *            Robot qui doit executer l'action.
	 * @return true si l'action est possible false sinon.
	 */
	public boolean isPossible(Robots nono) {
		return (nono.ennemiAdjacent() != null);
	}

}
