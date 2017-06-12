package UserInterface;

import Engine.Personnages;
import Engine.PointCardinal;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 **************************************************************
 * Keyboard reagit a un evenement exterieur venant du clavier *
 **************************************************************
 */
public class Keyboard implements EventHandler<KeyEvent> {
	Personnages personnage1;
	Personnages personnage2;

	public Keyboard(Personnages personnage1, Personnages personnage2) {
		this.personnage1 = personnage1;
		this.personnage2 = personnage2;
	}

	/**
	 * Recupere un evenement du clavier afin de l'associer au joueur
	 * correspondant Le joueur 1 se trouvant a droite du terrain, utilise les
	 * fleches pour deplacer son avatar le joueur 2, quant a lui, utilise les
	 * touches Q,Z,S,D pour se deplacer
	 */
	public void handle(KeyEvent event) {
		if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.LEFT
				|| event.getCode() == KeyCode.RIGHT) {

			// Player 1
			if (event.getCode() == KeyCode.UP) {
				personnage2.mouvement(PointCardinal.NORD);
			} else if (event.getCode() == KeyCode.DOWN) {
				personnage2.mouvement(PointCardinal.SUD);
			} else if (event.getCode() == KeyCode.LEFT) {
				personnage2.mouvement(PointCardinal.OUEST);
			} else {
				personnage2.mouvement(PointCardinal.EST);
			}
		} else if (event.getCode() == KeyCode.Q || event.getCode() == KeyCode.Z || event.getCode() == KeyCode.S
				|| event.getCode() == KeyCode.D) {

			// Player 2
			if (event.getCode() == KeyCode.Z) {
				personnage1.mouvement(PointCardinal.NORD);
			} else if (event.getCode() == KeyCode.S) {
				personnage1.mouvement(PointCardinal.SUD);
			} else if (event.getCode() == KeyCode.Q) {
				personnage1.mouvement(PointCardinal.OUEST);
			} else {
				personnage1.mouvement(PointCardinal.EST);
			}
		}
	}
}
