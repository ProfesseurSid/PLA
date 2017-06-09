package Engine;

public class Star implements Operateurs {

	private int x, y;

	/**
	 * Constructeur de *
	 * 
	 * @disclamer not sure of this constructor
	 * @since Version 1.0
	 */
	public Star() {
		x = 0;
		y = 0;
	}

	/**
	 * Constructeur de *
	 * 
	 * @since Version 1.0
	 */
	public Star(int x, int y) {
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
		p.addOperator('*');
	}

	/**
	 * Si on a un "*", on execute rien
	 */
	@Override
	public void action(Robots nono) {
		return;
	}

	@Override
	public String toString() {
		return "*";
	}

	@Override
	/**
	 * * toujours possible
	 */
	public boolean isPossible(Robots nono) {
		return true;
	}
}
