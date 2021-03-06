package Engine;

//import Visual.*;
import javafx.scene.Parent;
import Visual.OperateursVisual;
import Visual.Plateau;
import Visual.Terrain;


public class AccoladeF implements Operateurs {

	private int x, y;
	Plateau plateau;
	OperateursVisual visuel;

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
	public AccoladeF(Terrain t, int x, int y, Plateau plateau, OperateursVisual visuel) {
		this.x = x;
		this.y = y;
		this.plateau = plateau;
		this.visuel = visuel;
		plateau.put(x, y, this);
		t.addVisual(visuel);
	}

//	@Override
	public int getX() {
		return x;
	}

//	@Override
	public int getY() {
		return y;
	}

//	@Override
	/**
	 * Ajoute l'operateur a l'inventaire du personnage et le retire du plateau
	 * 
	 * @since Version 1.0
	 */
	public void stock(Personnages p) {
		p.addOperator('}');
		plateau.remove(x, y, this);
		visuel.remove();
	}

//	@Override
	/**
	 * Si l'accolade ferme, on ne fait rien
	 */
	public void action(Robots nono) {
		return;
	}

//	@Override
	public String toString() {
		return "}";
	}

//	@Override
	/**
	 * Accolade fermante toujours possible
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
