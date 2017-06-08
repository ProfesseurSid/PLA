package Engine;

public class PointVirgule implements Operateurs {

	int x, y;

	/**
	 * Constructeur de point virgule
	 * 
	 * @disclamer not sure of this constructor
	 * @since Version 1.0
	 */
	public PointVirgule() {
		x = 0;
		y = 0;
	}

	/**
	 * Constructeur de point virgule
	 * 
	 * @since Version 1.0
	 */
	public PointVirgule(int x, int y) {
		this.x = x;
		this.y = y;
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
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	/**
	 * Ajoute l'operateur a l'inventaire du personnage
	 * 
	 * @since Version 1.0
	 */
	public void stock(Personnages p) {
		p.addOperator(';');
	}

	public boolean doable(Robots nono) {
		return true;
	}

	@Override
	public void action(Automates a, Robots nono) {
		a.realExec(nono);
	}

	@Override
	public String toString() {
		return ";";
	}
}
