package Engine;

public class Barre implements Operateurs {

	private int x, y;

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
	 * Si on a un "|", on execute rien
	 */
	@Override
	public void action(Robots nono) {
		return;
	}

	@Override
	public String toString() {
		return "|";
	}

	@Override
	/**
	 * Barre toujours possible
	 */
	public boolean isPossible(Robots nono) {
		return true;
	}

}
