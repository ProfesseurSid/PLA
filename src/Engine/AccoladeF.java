package Engine;

import Engine.Personnages;

public class AccoladeF implements Operateurs {

	private int x, y;

	/**
	 * Constructeur de accolade fermante
	 * 
	 * @disclamer not sure of this constructor
	 * @since Version 1.0
	 */
	public AccoladeF() {
		x = 0;
		y = 0;
	}

	/**
	 * Constructeur de accolade fermante
	 * 
	 * @since Version 1.0
	 */
	public AccoladeF(int x, int y) {
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
		p.addOperator('}');
	}

	@Override
	/**
	 * Si l'accolade ferme, on ne fait rien
	 */
	public void action(Robots nono) {
		return;
	}

	@Override
	public String toString() {
		return "}";
	}

	@Override
	/**
	 * Accolade fermante toujours possible
	 */
	public boolean isPossible(Robots nono) {
		return true;
	}

}
