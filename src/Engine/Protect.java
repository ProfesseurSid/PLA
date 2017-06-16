package Engine;

import Exception.PanicException;
import Visual.OperateursVisual;
import Visual.Plateau;
import Visual.Terrain;
import javafx.scene.Parent;

/**
 * Classe de l'operateur Protect. Permet au robot de se proteger des coups.
 */
public class Protect implements Operateurs {

	private int x, y;
	Plateau plateau;
	OperateursVisual visuel;
	

	/**
	 * Constructeur de protect
	 * 
	 * @disclamer not sure of this constructor
	 */
	public Protect() {
		x = 0;
		y = 0;
	}

	/**
	 * Constructeur de protect
	 */
	public Protect(Terrain t, int x, int y, Plateau plateau, OperateursVisual visuel) {
		this.x = x;
		this.y = y;
		this.plateau = plateau;
		this.visuel = visuel;
		plateau.put(x, y, this);
		t.addVisual(visuel);
	}

	/**
	 * Getter de x
	 * 
	 * @return x;
	 */
	public int getX() {
		return x;
	}

	/**
	 * Getter de y
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Ajoute l'operateur a l'inventaire du personnage et le retire du plateau
	 * 
	 * @since Version 1.0
	 */
	public void stock(Personnages p) {
		p.addOperator('P');
		plateau.remove(x, y, this);
		visuel.remove();
	}

	/**
	 * Methode qui fait executer l'action Protect à un robot.
	 * 
	 * @param nono
	 *            Robot qui va executer l'action.
	 */
	public void action(Robots nono) {
		nono.protect(1);
	}

	@Override
	public String toString() {
		return "P";
	}

	/**
	 * Methode qui teste si l'action est possible ou efficace a un moment donné.
	 * 
	 * @param nono
	 *            Robot qui doit executer l'action.
	 * @return true si l'action est possible false sinon.
	 */
	public boolean isPossible(Robots nono) {
		return nono.robotAdjacent() != PointCardinal.NONE;
	}

	public Parent getVisual() {
		return visuel;
	}

}
