package Engine;

public class Kamikaze implements Operateurs {

	int x, y;

	/**
	 * Constructeur de kamikaze
	 * 
	 * @disclamer not sure of this constructor
	 * @since Version 1.0
	 */
	public Kamikaze() {
		x = 0;
		y = 0;
	}

	/**
	 * Constructeur de kamikaze
	 * 
	 * @since Version 1.0
	 */
	public Kamikaze(int x, int y) {
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
		p.addOperator('K');
	}

	@Override
	public void action(Automates a, Robots nono) {
		// TODO Auto-generated method stub
		a.opeAExec(this, nono);
	}

	@Override
	public String toString() {
		return "K";
	}

}
