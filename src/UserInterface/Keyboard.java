package UserInterface;

import Engine.Personnages;
import Engine.PointCardinal;
import Visual.Boite;
import Visual.Champexpr;
import Visual.Team;
import javafx.event.EventHandler;
import javafx.scene.Group;
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
	Boite boite1;
	Boite boite2;
	Group root;
	int ligne1;
	int ligne2;
	String exp1;
	String exp2;
	Team team1;
	Team team2;
	Champexpr champ1;
	Champexpr champ2;

	public Keyboard(Personnages personnage1, Personnages personnage2, Group root, Boite boite1, Boite boite2,
			Team team1, Team team2, Champexpr champ1, Champexpr champ2) {
		this.personnage1 = personnage1;
		this.personnage2 = personnage2;
		this.boite1 = boite1;
		this.boite2 = boite2;
		this.root = root;
		this.team1 = team1;
		this.team2 = team2;
	}

	/**
	 * Recupere un evenement du clavier afin de l'associer au joueur
	 * correspondant Le joueur 1 se trouvant a droite du terrain, utilise les
	 * fleches pour deplacer son avatar le joueur 2, quant a lui, utilise les
	 * touches Q,Z,S,D pour se deplacer
	 */
	public void handle(KeyEvent event) {
		if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.LEFT
				|| event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.SEMICOLON
				|| event.getCode() == KeyCode.COLON || event.getCode() == KeyCode.EXCLAMATION_MARK) {
			// player 2
			if (personnage2.dansBase()) {
				if (event.getCode() == KeyCode.UP) {
					root.getChildren().remove(boite2);
					boite2.invisible(ligne2);
					if (ligne2 == 0)
						ligne2 = 4;
					else
						ligne2 = ligne2 - 1;
					boite2.visible(ligne2);
					root.getChildren().add(boite2);
				} else if (event.getCode() == KeyCode.DOWN) {
					root.getChildren().remove(boite2);
					boite2.invisible(ligne2);
					if (ligne2 == 4)
						ligne2 = 0;
					else
						ligne2 = ligne2 + 1;
					boite2.visible(ligne2);
					root.getChildren().add(boite2);
				} else if (event.getCode() == KeyCode.LEFT) {
					personnage2.sortir();
					boite2.invisible(ligne2);
					root.getChildren().remove(boite2);
					boite2 = new Boite(personnage2);
					root.getChildren().add(boite2);
				} else if (event.getCode() == KeyCode.SEMICOLON) {
					int focus = boite2.focused();
					exp2 = champ2.getExpr();
					if (focus != 4) {
						personnage2.removeOperator(personnage2.toChar(focus, 0));
						root.getChildren().remove(champ2);
						root.getChildren().remove(boite2);
						champ2 = new Champexpr(exp2 + personnage2.toChar(focus, 0), 1);
						boite2 = new Boite(personnage2);
						boite2.visible(focus);
						root.getChildren().add(boite2);
						root.getChildren().add(champ2);
					}
				} else if (event.getCode() == KeyCode.COLON) {
					int focus = boite2.focused();
					String expr = champ2.getExpr();
					if (focus != 4) {
						personnage2.removeOperator(personnage2.toChar(focus, 1));
						root.getChildren().remove(champ2);
						root.getChildren().remove(boite2);
						champ2 = new Champexpr(expr + personnage2.toChar(focus, 1), 1);
						boite2 = new Boite(personnage2);
						boite2.visible(focus);
						root.getChildren().add(boite2);
						root.getChildren().add(champ2);
					}

				} else if (event.getCode() == KeyCode.EXCLAMATION_MARK) {
					int focus = boite2.focused();
					String expr = champ2.getExpr();
					if (focus != 4) {
						personnage2.removeOperator(personnage2.toChar(focus, 2));
						root.getChildren().remove(champ2);
						root.getChildren().remove(boite2);
						champ2 = new Champexpr(expr + personnage2.toChar(focus, 2), 1);
						boite2 = new Boite(personnage2);
						boite2.visible(focus);
						root.getChildren().add(boite2);
						root.getChildren().add(champ2);
					}

				}

			} else {
				if (event.getCode() == KeyCode.SEMICOLON) {
					if (team2.getVisible(0)) {
						root.getChildren().remove(team2);
						team2.invisible(0);
						root.getChildren().add(team2);
					} else {
						root.getChildren().remove(team2);
						team2.invisible(1);
						team2.invisible(2);
						team2.visible(0);
						root.getChildren().add(team2);
					}
				} else if (event.getCode() == KeyCode.COLON) {
					if (team2.getVisible(1)) {
						root.getChildren().remove(team2);
						team2.invisible(1);
						root.getChildren().add(team2);
					} else {
						root.getChildren().remove(team2);
						team2.invisible(0);
						team2.invisible(2);
						team2.visible(1);
						root.getChildren().add(team2);
					}
					;
				} else if (event.getCode() == KeyCode.EXCLAMATION_MARK) {
					if (team2.getVisible(2)) {
						root.getChildren().remove(team2);
						team2.invisible(2);
						root.getChildren().add(team2);
					} else {
						root.getChildren().remove(team2);
						team2.invisible(0);
						team2.invisible(1);
						team2.visible(2);
						root.getChildren().add(team2);
					}
				} else if (event.getCode() == KeyCode.UP) {
					personnage2.mouvement(PointCardinal.NORD);
					root.getChildren().remove(boite2);
					boite2 = new Boite(personnage2);
					root.getChildren().add(boite2);
				} else if (event.getCode() == KeyCode.DOWN) {
					personnage2.mouvement(PointCardinal.SUD);
					root.getChildren().remove(boite2);
					boite2 = new Boite(personnage2);
					root.getChildren().add(boite2);
				} else if (event.getCode() == KeyCode.LEFT) {
					personnage2.mouvement(PointCardinal.OUEST);
				} else {
					if (personnage2.getX() == 20
							&& (personnage2.getY() == 4 || personnage2.getY() == 5 || personnage2.getY() == 6)) {
						personnage2.rentrer();
						root.getChildren().remove(team2);
						team2.invisible(0);
						team2.invisible(1);
						team2.invisible(2);
						root.getChildren().add(team2);
						ligne2 = 0;
						root.getChildren().remove(boite2);
						boite2.visible(ligne2);
						root.getChildren().add(boite2);
					} else {
						personnage2.mouvement(PointCardinal.EST);
						root.getChildren().remove(boite2);
						boite2 = new Boite(personnage2);
						root.getChildren().add(boite2);
					}
				}
			}
		} else if (event.getCode() == KeyCode.Q || event.getCode() == KeyCode.Z || event.getCode() == KeyCode.S
				|| event.getCode() == KeyCode.D || event.getCode() == KeyCode.DIGIT1
				|| event.getCode() == KeyCode.DIGIT2 || event.getCode() == KeyCode.DIGIT3) {
			// player 1
			if (personnage1.dansBase()) {
				if (event.getCode() == KeyCode.Z) {
					root.getChildren().remove(boite1);
					boite1.invisible(ligne1);
					if (ligne1 == 0)
						ligne1 = 4;
					else
						ligne1 = ligne1 - 1;
					boite1.visible(ligne1);
					root.getChildren().add(boite1);
				} else if (event.getCode() == KeyCode.S) {
					root.getChildren().remove(boite1);
					boite1.invisible(ligne1);
					if (ligne1 == 4)
						ligne1 = 0;
					else
						ligne1 = ligne1 + 1;
					boite1.visible(ligne1);
					root.getChildren().add(boite1);
				} else if (event.getCode() == KeyCode.D) {
					personnage1.sortir();
					boite1.invisible(ligne1);
					root.getChildren().remove(boite1);
					boite1 = new Boite(personnage1);
					root.getChildren().add(boite1);
				} else if (event.getCode() == KeyCode.DIGIT1) {
					int focus = boite1.focused();
					if (focus != 4) {
						personnage1.removeOperator(personnage1.toChar(focus, 0));
						root.getChildren().remove(boite1);
						boite1 = new Boite(personnage1);
						boite1.visible(focus);
						root.getChildren().add(boite1);
					}
				} else if (event.getCode() == KeyCode.DIGIT2) {
					int focus = boite1.focused();
					if (focus != 4) {
						personnage1.removeOperator(personnage1.toChar(focus, 1));
						root.getChildren().remove(boite1);
						boite1 = new Boite(personnage1);
						boite1.visible(focus);
						root.getChildren().add(boite1);
					}
				} else if (event.getCode() == KeyCode.DIGIT3) {
					int focus = boite1.focused();
					if (focus != 4) {
						personnage1.removeOperator(personnage1.toChar(focus, 2));
						root.getChildren().remove(boite1);
						boite1 = new Boite(personnage1);
						boite1.visible(focus);
						root.getChildren().add(boite1);
					}
				}

			} else {
				if (event.getCode() == KeyCode.DIGIT1) {
					if (team1.getVisible(0)) {
						root.getChildren().remove(team1);
						team1.invisible(0);
						root.getChildren().add(team1);
					} else {
						root.getChildren().remove(team1);
						team1.invisible(1);
						team1.invisible(2);
						team1.visible(0);
						root.getChildren().add(team1);
					}
				} else if (event.getCode() == KeyCode.DIGIT2) {
					if (team1.getVisible(1)) {
						root.getChildren().remove(team1);
						team1.invisible(1);
						root.getChildren().add(team1);
					} else {
						root.getChildren().remove(team1);
						team1.invisible(0);
						team1.invisible(2);
						team1.visible(1);
						root.getChildren().add(team1);
					}
					;
				} else if (event.getCode() == KeyCode.DIGIT3) {
					if (team1.getVisible(2)) {
						root.getChildren().remove(team1);
						team1.invisible(2);
						root.getChildren().add(team1);
					} else {
						root.getChildren().remove(team1);
						team1.invisible(0);
						team1.invisible(1);
						team1.visible(2);
						root.getChildren().add(team1);
					}
				} else if (event.getCode() == KeyCode.Z) {
					personnage1.mouvement(PointCardinal.NORD);
					root.getChildren().remove(boite1);
					boite1 = new Boite(personnage1);
					root.getChildren().add(boite1);
				} else if (event.getCode() == KeyCode.S) {
					personnage1.mouvement(PointCardinal.SUD);
					root.getChildren().remove(boite1);
					boite1 = new Boite(personnage1);
					root.getChildren().add(boite1);
				} else if (event.getCode() == KeyCode.Q) {
					if (personnage1.getX() == 0
							&& (personnage1.getY() == 4 || personnage1.getY() == 5 || personnage1.getY() == 6)) {
						personnage1.rentrer();
						root.getChildren().remove(team1);
						team1.invisible(0);
						team1.invisible(1);
						team1.invisible(2);
						root.getChildren().add(team1);
						ligne1 = 0;
						root.getChildren().remove(boite1);
						boite1.visible(ligne1);
						root.getChildren().add(boite1);
					} else {
						personnage1.mouvement(PointCardinal.OUEST);
						root.getChildren().remove(boite1);
						boite1 = new Boite(personnage1);
						root.getChildren().add(boite1);
					}
				} else {
					personnage1.mouvement(PointCardinal.EST);
					root.getChildren().remove(boite1);
					boite1 = new Boite(personnage1);
					root.getChildren().add(boite1);
				}
			}
		}
	}
}
