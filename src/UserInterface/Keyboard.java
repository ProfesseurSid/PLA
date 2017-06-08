package UserInterface;

import Visual.PersonnagesVisual;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 **************************************************************
 * Keyboard reagit a un evenement exterieur venant du clavier *
 **************************************************************
 */
public class Keyboard implements EventHandler<KeyEvent> {
	PersonnagesVisual personnage1;
	PersonnagesVisual personnage2;
	ImageView p1;
	ImageView p2;

	public Keyboard(PersonnagesVisual personnage1, PersonnagesVisual personnage2, ImageView p1, ImageView p2) {
		this.personnage1 = personnage1;
		this.personnage2 = personnage2;
		this.p1 = p1;
		this.p2 = p2;
	}

	/**
	 * Recupere un evenement du clavier afin de l'associer au joueur
	 * correspondant Le joueur 1 se trouvant a droite du terrain, utilise les
	 * fleches pour deplacer son avatar le joueur 2, quant a lui, utilise les
	 * touches Q,Z,S,D pour se deplacer
	 */
	public void handle(KeyEvent event) {
		if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.B
				|| event.getCode() == KeyCode.N) {
			// player 1

			if (event.getCode() == KeyCode.UP) {
				personnage2.Haut(p2);
				personnage2.requestFocus();
			} else if (event.getCode() == KeyCode.DOWN) {
				personnage2.Bas(p2);
				personnage2.requestFocus();
			} else if (event.getCode() == KeyCode.B) {
				personnage2.Gauche(p2);
				personnage2.requestFocus();
			} else {
				personnage2.Droite(p2);
				personnage2.requestFocus();
			}
		} else if (event.getCode() == KeyCode.Q || event.getCode() == KeyCode.Z || event.getCode() == KeyCode.S
				|| event.getCode() == KeyCode.D) {
			// Player 2
			if (event.getCode() == KeyCode.Z) {
				personnage1.Haut(p1);
				personnage1.requestFocus();
			} else if (event.getCode() == KeyCode.S) {
				personnage1.Bas(p1);
				personnage1.requestFocus();
			} else if (event.getCode() == KeyCode.Q) {
				personnage1.Gauche(p1);
				personnage1.requestFocus();
			} else {
				personnage1.Droite(p1);
				personnage1.requestFocus();
			}
		}
	}
}
