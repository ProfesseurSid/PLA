package Engine;

public class Barre implements Operateurs {

	int x, y;

	/**
	 * Constructeur de barre
	 * 
	 * @disclamer not sure of this constructor
	 * @since Version 1.0
	 */
	public Barre() {
		x = 0;
		y = 0;
	}

	/**
	 * Constructeur de barre
	 * 
	 * @since Version 1.0
	 */
	public Barre(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	/**
	 * Ajoute l'operateur a l'inventaire du personnage
	 * 
	 * @since Version 1.0
	 */
	public void stock(Personnages p) {
		p.addOperator('|');
	}

	public boolean doable(Robots nono) {
		return true;
	}

	/**
	 * Si cette barre n'a pas encore été testée, lance l'aléatoire
	 * 
	 * @param a
	 *            l'automate d'où provient la barre
	 * @param nono
	 *            le robot contenant ledit automate
	 */
	public void action(Automates a, Robots nono) {
		if (!a.isBarreExec(this))
			a.random(nono);
		else
			a.skipToPointVirgule();
	}

	@Override
	public String toString() {
		return "|";
	}

}
