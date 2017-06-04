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
		p.addOperator('|');
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "|";
	}

}
