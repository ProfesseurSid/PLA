package Engine;

import Visual.*;
import javafx.scene.Parent;

/**
 * Classe de l'operateur Others. Permet au robot de se deplacer vers les
 * ennemis.
 */
public class Others implements Operateurs {

	private int x, y;
	Plateau plateau;
	OperateursVisual visuel;

	/**
	 * Constructeur de others
	 * 
	 * @disclamer not sure of this constructor
	 */
	public Others() {
		x = 0;
		y = 0;
	}

	/**
	 * Constructeur de others
	 */
	public Others(Terrain t, int x, int y, Plateau plateau, OperateursVisual visuel) {
		this.x = x;
		this.y = y;
		this.plateau = plateau;
		this.visuel = visuel;
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
		p.addOperator('O');
		plateau.remove(x, y, this);
		visuel.remove();
	}

	/**
	 * Methode qui fait executer l'action Others à un robot.
	 * 
	 * @param nono
	 *            Robot qui va executer l'action.
	 */
	public void action(Robots nono) {
		nono.versEnnemi(1);
	}

	@Override
	public String toString() {
		return "O";
	}

	/**
	 * Methode qui teste si l'action est possible ou efficace a un moment donné.
	 * 
	 * @param nono
	 *            Robot qui doit executer l'action.
	 * @return true si l'action est possible false sinon.
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
