package Engine;

public class AccoladeO implements Operateurs {

	private int x, y;

	/**
	 * Constructeur de accolade ouvrante
	 * 
	 * @disclamer not sure of this constructor
	 * @since Version 1.0
	 */
	public AccoladeO() {
		x = 0;
		y = 0;
	}

	/**
	 * Constructeur de accolade ouvrante
	 * 
	 * @since Version 1.0
	 */
	public AccoladeO(int x, int y) {
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
		p.addOperator('{');
	}

	/**
	 * Si l'accolade ouvre, on ne fait rien
	 */
	@Override
	public void action(Robots nono) {
		return;
	}

	@Override
	public String toString() {
		return "{";
	}

	@Override
	/**
	 * Accolade ouvrante toujours possible
	 */
	public boolean isPossible(Robots nono) {
		return true;
	}
}
