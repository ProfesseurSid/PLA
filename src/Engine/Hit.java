package Engine;

public class Hit implements Operateurs {

	int x, y;

	/**
	 * Constructeur de hit
	 * 
	 * @disclamer not sure of this constructor
	 * @since Version 1.0
	 */
	public Hit() {
		x = 0;
		y = 0;
	}

	/**
	 * Constructeur de hit
	 * 
	 * @since Version 1.0
	 */
	public Hit(int x, int y) {
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
		p.addOperator('H');
	}

	public boolean doable(Robots nono) {
		return nono.ennemiAdjacent() != null;
	}

	@Override
	public void action(Automates a, Robots nono) {
		if (a.realAction())
			nono.hit(a.nbExec());
		else
			a.opeAExec(this, nono);
	}

	@Override
	public String toString() {
		return "H";
	}

}
