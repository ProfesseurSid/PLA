package UserInterface;

import Engine.*;
import Visual.*;
import Visual.Barre;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.shape.*;

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
	public String c = "";
	Group root;
	Text expr_rouge, expr_bleue;
	int marge;
	int tailleExpression;
	Rectangle selection;
	Boite boite1;
	Boite boite2;
	Team team1;
	Team team2;

	public Keyboard(Personnages personnage1, Personnages personnage2, Group root, Text expr_bleue, Text expr_rouge,
			int marge, int tailleExpression, Boite boite1, Boite boite2, Team team1, Team team2) {
		this.personnage1 = personnage1;
		this.personnage2 = personnage2;
		this.root = root;
		this.expr_rouge = expr_rouge;
		this.expr_bleue = expr_bleue;
		this.marge = marge;
		this.tailleExpression = tailleExpression;
		this.boite1 = boite1;
		this.boite2 = boite2;
		this.team1 = team1;
		this.team2 = team2;
	}

	/**
	 * Recupere un evenement du clavier afin de l'associer au joueur
	 * correspondant Le joueur 1 se trouvant a droite du terrain, utilise les
	 * fleches pour deplacer son avatar. Le joueur 2, quant a lui, utilise les
	 * touches Q,Z,S,D pour se deplacer � l'interieur du terrain Lorsque le
	 * joueur 1 est dans sa base, la touche Q
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
						ligne2--;
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
					if (focus != 4) {
						root.getChildren().remove(boite2);
						getOperateur(ligne2, 1, personnage2);
						updateExpression_rouge();
						boite2 = new Boite(personnage2);
						boite2.visible(focus);
						root.getChildren().add(boite2);
					}
				} else if (event.getCode() == KeyCode.COLON) {
					int focus = boite2.focused();
					if (focus != 4) {
						root.getChildren().remove(boite2);
						getOperateur(ligne2, 2, personnage2);
						updateExpression_rouge();
						boite2 = new Boite(personnage2);
						boite2.visible(focus);
						root.getChildren().add(boite2);
					}
				} else if (event.getCode() == KeyCode.EXCLAMATION_MARK) {
					int focus = boite2.focused();
					if (focus != 4) {
						root.getChildren().remove(boite2);
						getOperateur(ligne2, 3, personnage2);
						updateExpression_rouge();
						boite2 = new Boite(personnage2);
						boite2.visible(focus);
						root.getChildren().add(boite2);
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
						root.getChildren().remove(boite1);
						getOperateur(ligne1, 1, personnage1);
						updateExpression_bleue();
						boite1 = new Boite(personnage1);
						boite1.visible(focus);
						root.getChildren().add(boite1);
					}
				} else if (event.getCode() == KeyCode.DIGIT2) {
					int focus = boite1.focused();
					if (focus != 4) {
						root.getChildren().remove(boite1);
						getOperateur(ligne1, 2, personnage1);
						updateExpression_bleue();
						boite1 = new Boite(personnage1);
						boite1.visible(focus);
						root.getChildren().add(boite1);
					}
				} else if (event.getCode() == KeyCode.DIGIT3) {
					int focus = boite1.focused();
					if (focus != 4) {
						root.getChildren().remove(boite1);
						getOperateur(ligne1, 3, personnage1);
						updateExpression_bleue();
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

	public void getOperateur(int ligne, int number, Personnages personnage) {
		switch (ligne) {
		case 0:
			if (number == 1 && !personnage.isEmpty('*')) {
				c = c + "*";
				personnage.removeOperator('*');
			} else if (number == 2 && !personnage.isEmpty('{')) {
				c = c + "{";
				personnage.removeOperator('{');
			} else if (number == 3 && !personnage.isEmpty('}')) {
				c = c + "}";
				personnage.removeOperator('}');
			}
			break;
		case 1:
			if (number == 1 && !personnage.isEmpty(';')) {
				c = c + ";";
				personnage.removeOperator(';');
			} else if (number == 2 && !personnage.isEmpty('|')) {
				c = c + "|";
				personnage.removeOperator('|');
			} else if (number == 3 && !personnage.isEmpty(':')) {
				c = c + ":";
				personnage.removeOperator(':');
			}
			break;
		case 2:
			if (number == 1 && !personnage.isEmpty('>')) {
				c = c + ">";
				personnage.removeOperator('>');
			} else if (number == 2 && !personnage.isEmpty('H')) {
				c = c + "H";
				personnage.removeOperator('H');
			} else if (number == 3 && !personnage.isEmpty('K')) {
				c = c + "K";
				personnage.removeOperator('K');
			}
			break;
		case 3:
			if (number == 1 && !personnage.isEmpty('O')) {
				c = c + "O";
				personnage.removeOperator('O');
			} else if (number == 2 && !personnage.isEmpty('J')) {
				c = c + "J";
				personnage.removeOperator('J');
			} else if (number == 3 && !personnage.isEmpty('P')) {
				c = c + "P";
				personnage.removeOperator('P');
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

	public void updateExpression_bleue() {
		root.getChildren().remove(expr_bleue);
		Text expr_bleue = new Text(c);
		expr_bleue.setFont(new Font(Tuile.getTaille() - marge));
		expr_bleue.setFill(Color.rgb(72, 145, 220, 1.0));
		expr_bleue.setX(3 * marge + Barre.getDimX());
		expr_bleue.setY(marge + (Terrain.getTuileY() + 1) * Tuile.getTaille());
		root.getChildren().add(expr_bleue);
		/*
		 * TranslateTransition tt = new
		 * TranslateTransition(Duration.millis(30000),expr_bleue);
		 * tt.setFromX(0-expr_bleue.getWrappingWidth()-10);
		 * tt.setCycleCount(Timeline.INDEFINITE); tt.play();
		 */

	}

	public void updateExpression_rouge() {
		root.getChildren().remove(expr_rouge);
		Text expr_rouge = new Text(c);
		expr_rouge.setFont(new Font(Tuile.getTaille() - marge));
		expr_rouge.setFill(Color.rgb(72, 145, 220, 1.0));
		expr_rouge.setX(3 * marge + Barre.getDimX());
		expr_rouge.setY(marge + (Terrain.getTuileY() + 1) * Tuile.getTaille());
		root.getChildren().add(expr_rouge);
	}

}
