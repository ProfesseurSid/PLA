package Engine;

public class Rapport implements Operateurs {

	int x, y;

	/**
	 * Constructeur de rapport
	 * 
	 * @disclamer not sure of this constructor
	 * @since Version 1.0
	 */
	public Rapport() {
		x = 0;
		y = 0;
	}

	/**
	 * Constructeur de rapport
	 * 
	 * @since Version 1.0
	 */
	public Rapport(int x, int y) {
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
		p.addOperator('J');
	}

	public boolean doable(Robots nono) {
		return nono.allieAdjacent() == null;
	}

	@Override
	public void action(Automates a, Robots nono) {
		if (a.realAction())
			nono.versJoueur(a.nbExec());
		else
			a.opeAExec(this, nono);
	}

	@Override
	public String toString() {
		return "J";
	}
}
