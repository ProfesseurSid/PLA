package Engine;

public class Others implements Operateurs {

	int x, y;

	/**
	 * Constructeur de others
	 * 
	 * @disclamer not sure of this constructor
	 * @since Version 1.0
	 */
	public Others() {
		x = 0;
		y = 0;
	}

	/**
	 * Constructeur de others
	 * 
	 * @since Version 1.0
	 */
	public Others(int x, int y) {
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

	@Override
	/**
	 * Ajoute l'operateur a l'inventaire du personnage
	 * 
	 * @since Version 1.0
	 */
	public void stock(Personnages p) {
		p.addOperator('O');
	}

	public boolean doable(Robots nono) {
		return nono.ennemiAdjacent() == null;
	}

	@Override
	public void action(Automates a, Robots nono) {
		if (a.realAction())
			nono.versEnnemi(a.nbExec());
		else
			a.opeAExec(this, nono);
	}

	@Override
	public String toString() {
		return "O";
	}

}
