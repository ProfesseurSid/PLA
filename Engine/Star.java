package Engine;

import Visual.*;
import javafx.scene.Parent;

public class Star implements Operateurs {

	private int x, y;
	Plateau plateau;
	OperateursVisual visuel;

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
	public Star(Terrain t, int x, int y, Plateau plateau, OperateursVisual visuel) {
		this.x = x;
		this.y = y;
		this.plateau = plateau;
		this.visuel = visuel;
		plateau.put(x, y, this);
		t.addVisual(visuel);
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
	 * Ajoute l'operateur a l'inventaire du personnage et le retire du plateau
	 * 
	 * @since Version 1.0
	 */
	public void stock(Personnages p) {
		p.addOperator('*');
		plateau.remove(x, y, this);
		visuel.remove();
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

	/**
	 * Getter de visuel
	 */
	public Parent getVisual() {
		return visuel;
	}
}
