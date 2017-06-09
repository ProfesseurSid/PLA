package Engine;

import Exception.PanicException;
import Visual.*;
import javafx.scene.Parent;

/**
 * Classe de l'operateur Others. Permet au robot de se deplacer vers le
 * personnage.
 */
public class Rapport implements Operateurs {

	private int x, y;
	Plateau plateau;
	OperateursVisual visuel;

	/**
	 * Constructeur de rapport
	 * 
	 * @disclamer not sure of this constructor
	 */
	public Rapport() {
		x = 0;
		y = 0;
	}

	/**
	 * Constructeur de rapport
	 */
	public Rapport(int x, int y, Plateau plateau, OperateursVisual visuel) {
		this.x = x;
		this.y = y;
		this.plateau = plateau;
		this.visuel = visuel;
		plateau.put(x, y, this);
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
		p.addOperator('J');
		plateau.remove(x, y, this);
		visuel.remove();
	}

	/**
	 * Methode qui fait executer l'action Rapport à un robot.
	 * 
	 * @param nono
	 *            Robot qui va executer l'action.
	 */
	public void action(Robots nono) {
		nono.versJoueur(1);
	}

	@Override
	public String toString() {
		return "J";
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
