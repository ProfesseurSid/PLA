package UserInterface;

import Engine.Personnages;
import Engine.PointCardinal;
import Visual.Boite;
import Visual.PersonnagesVisual;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
	int ligne1 = 0;
	int ligne2 = 0;
	String c;
	boolean ok1 = false;
	boolean ok2 = false;
	TextField expression;

	public Keyboard(Personnages personnage1, Personnages personnage2) {
		this.personnage1 = personnage1;
		this.personnage2 = personnage2;
	}

	/**
	 * Recupere un evenement du clavier afin de l'associer au joueur
	 * correspondant Le joueur 1 se trouvant a droite du terrain, utilise les
	 * fleches pour deplacer son avatar. Le joueur 2, quant a lui, utilise les
	 * touches Q,Z,S,D pour se deplacer à l'interieur du terrain Lorsque le
	 * joueur 1 est dans sa base, la touche Q
	 */
	public void handle(KeyEvent event) {

		if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.LEFT
				|| event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.SEMICOLON
				|| event.getCode() == KeyCode.COLON || event.getCode() == KeyCode.EXCLAMATION_MARK) {
			// player 2
			if (!ok2) {
				if (event.getCode() == KeyCode.RIGHT && personnage2.getX() == 0
						&& (personnage2.getY() == 4 || personnage2.getY() == 5 || personnage2.getY() == 6)) {
					ok2 = true;
				} else {
					if (event.getCode() == KeyCode.UP) {
						personnage2.mouvement(PointCardinal.NORD);
					} else if (event.getCode() == KeyCode.DOWN) {
						personnage2.mouvement(PointCardinal.SUD);
					} else if (event.getCode() == KeyCode.LEFT) {
						personnage2.mouvement(PointCardinal.OUEST);
					} else if (event.getCode() == KeyCode.RIGHT) {
						personnage2.mouvement(PointCardinal.EST);
					}
				}
			} else {
				if (event.getCode() == KeyCode.UP && ligne2 > 0) {
					ligne2--;
				} else if (event.getCode() == KeyCode.DOWN && ligne2 < Boite.getNb() - 1) {
					ligne2++;
				} else if (event.getCode() == KeyCode.SEMICOLON) {
					getOperateur(ligne2, 1);
				} else if (event.getCode() == KeyCode.COLON) {
					getOperateur(ligne2, 2);
				} else if (event.getCode() == KeyCode.EXCLAMATION_MARK) {
					getOperateur(ligne2, 3);
				} else if (event.getCode() == KeyCode.LEFT) {
					ok2 = false;
				}
			}
		} else if (event.getCode() == KeyCode.Q || event.getCode() == KeyCode.Z || event.getCode() == KeyCode.S
				|| event.getCode() == KeyCode.D || event.getCode() == KeyCode.DIGIT1
				|| event.getCode() == KeyCode.DIGIT2 || event.getCode() == KeyCode.DIGIT3) {
			// Player 1
			if (!ok1) {
				if (event.getCode() == KeyCode.Q && personnage1.getX() == 0
						&& (personnage1.getY() == 4 || personnage1.getY() == 5 || personnage1.getY() == 6)) {
					ok1 = true;
				} else {
					if (event.getCode() == KeyCode.Z) {
						personnage1.mouvement(PointCardinal.NORD);
					} else if (event.getCode() == KeyCode.S) {
						personnage1.mouvement(PointCardinal.SUD);
					} else if (event.getCode() == KeyCode.Q) {
						personnage1.mouvement(PointCardinal.OUEST);
					} else if (event.getCode() == KeyCode.D) {
						personnage1.mouvement(PointCardinal.EST);
					}
				}
			} else {
				if (event.getCode() == KeyCode.Z && ligne2 > 0) {
					ligne1--;
				} else if (event.getCode() == KeyCode.S && ligne2 < Boite.getNb() - 1) {
					ligne1++;
				} else if (event.getCode() == KeyCode.DIGIT1) {
					getOperateur(ligne1, 1);
				} else if (event.getCode() == KeyCode.DIGIT2) {
					getOperateur(ligne1, 2);
				} else if (event.getCode() == KeyCode.DIGIT3) {
					getOperateur(ligne1, 3);
				} else if (event.getCode() == KeyCode.D) {
					ok1 = false;
				}
			}
		}
	}

	public void getOperateur(int ligne, int number) {
		switch (ligne) {
		case 0:
			if (number == 1) {
				c = "*";
				// TODO
				// il faut decrementer le chiffre affiche plus l'operateur dans
				// l'inventaire
			} else if (number == 2) {
				c = "{";
			} else {
				c = "}";
			}
			break;
		case 1:
			if (number == 1) {
				c = ";";
				// TODO
				// il fau decrementer le chiffre affiche plus l'operateur dans
				// l'inventaire
			} else if (number == 2) {
				c = "|";
			} else {
				c = ":";
			}
			break;
		case 2:
			if (number == 1) {
				c = ">";
				// TODO
				// il faut decrementer le chiffre affiché plus l'operateur dans
				// l'inventaire
			} else if (number == 2) {
				c = "H";
			} else {
				c = "K";
			}
			break;
		case 3:
			if (number == 1) {
				c = "O";
				// TODO
				// il faut decrementer le chiffre affiché plus l'operateur dans
				// l'inventaire
			} else if (number == 2) {
				c = "J";
			} else {
				c = "P";
			}
			break;
		case 4:
			if (number == 1) {
				// TODO
				// Se deplacer d'un caractere a gauche
			} else if (number == 2) {
				// TODO
				// Se deplacer d'un caractere a droite
			} else {
				// TODO
				// supprimer le caractere
			}
		}
	}

}
