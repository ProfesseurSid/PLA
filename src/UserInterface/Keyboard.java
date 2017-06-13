
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
	Group root;
	Personnages personnage1, personnage2;
	Team team1, team2;
	Boite boite1, boite2;
	int ligne1 = 0; // Barre de selection de la boite Joueur 1
	int ligne2 = 0; // Barre de selection de la boite Joueur 2
	int tailleExpression;
	Text expr_rouge, expr_bleue;
	public String expression_rouge = "I";
	public String expression_bleue = "I";
	String expression_courante;
	int curseur;
	int curseur_rouge = 0;
	int curseur_bleu = 0;
	int marge;
	Rectangle selection;

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

			// Player 2
			if (personnage2.dansBase()) {
				if (event.getCode() == KeyCode.UP) {
					root.getChildren().remove(boite2);
					boite2.invisible(ligne2);
					if (ligne2 == 0)
						ligne2 = 4; // Si tout en haut, deplace a la derniere
									// ligne
					else
						ligne2--;
					boite2.visible(ligne2);
					root.getChildren().add(boite2);
				} else if (event.getCode() == KeyCode.DOWN) {
					root.getChildren().remove(boite2);
					boite2.invisible(ligne2);
					if (ligne2 == 4)
						ligne2 = 0; // Si tout en bas, deplace a la premiere
									// ligne
					else
						ligne2++;
					boite2.visible(ligne2);
					root.getChildren().add(boite2);
				} else if (event.getCode() == KeyCode.LEFT) {
					// Si on va a gauche on sort de la base
					personnage2.sortir();
					root.getChildren().remove(boite2);
					boite2.invisible(ligne2);
					boite2 = new Boite(personnage2);
					root.getChildren().add(boite2);
				} else if (event.getCode() == KeyCode.SEMICOLON) {
					root.getChildren().remove(boite2);
					getOperateur(ligne2, 1, personnage2, 2);
					updateExpression_rouge();
					boite2 = new Boite(personnage2);
					boite2.visible(ligne2);
					root.getChildren().add(boite2);
				} else if (event.getCode() == KeyCode.COLON) {
					root.getChildren().remove(boite2);
					getOperateur(ligne2, 2, personnage2, 2);
					updateExpression_rouge();
					boite2 = new Boite(personnage2);
					boite2.visible(ligne2);
					root.getChildren().add(boite2);
				} else if (event.getCode() == KeyCode.EXCLAMATION_MARK) {
					root.getChildren().remove(boite2);
					getOperateur(ligne2, 3, personnage2, 2);
					updateExpression_rouge();
					boite2 = new Boite(personnage2);
					boite2.visible(ligne2);
					root.getChildren().add(boite2);
				}
				// Sur le terrain
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
			// Player 1
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
					root.getChildren().remove(boite1);
					getOperateur(ligne1, 1, personnage1, 1);
					updateExpression_bleue();
					boite1 = new Boite(personnage1);
					boite1.visible(ligne1);
					root.getChildren().add(boite1);
				} else if (event.getCode() == KeyCode.DIGIT2) {
					root.getChildren().remove(boite1);
					getOperateur(ligne1, 2, personnage1, 1);
					updateExpression_bleue();
					boite1 = new Boite(personnage1);
					boite1.visible(ligne1);
					root.getChildren().add(boite1);
				} else if (event.getCode() == KeyCode.DIGIT3) {
					root.getChildren().remove(boite1);
					getOperateur(ligne1, 3, personnage1, 1);
					updateExpression_bleue();
					boite1 = new Boite(personnage1);
					boite1.visible(ligne1);
					root.getChildren().add(boite1);
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

	public void getOperateur(int ligne, int number, Personnages personnage, int team) {
		if (team == 1) {
			expression_courante = expression_bleue;
			curseur = curseur_bleu;
		} else if (team == 2) {
			expression_courante = expression_rouge;
			curseur = curseur_rouge;
		}
		switch (ligne) {
		case 0:
			if (number == 1 && !personnage.isEmpty('*')) {
				updateExpression("*");
				personnage.removeOperator('*');
			} else if (number == 2 && !personnage.isEmpty('{')) {
				updateExpression("{");
				personnage.removeOperator('{');
			} else if (number == 3 && !personnage.isEmpty('}')) {
				updateExpression("}");
				personnage.removeOperator('}');
			}
			break;
		case 1:
			if (number == 1 && !personnage.isEmpty(';')) {
				updateExpression(";");
				personnage.removeOperator(';');
			} else if (number == 2 && !personnage.isEmpty('|')) {
				updateExpression("|");
				personnage.removeOperator('|');
			} else if (number == 3 && !personnage.isEmpty(':')) {
				updateExpression(":");
				personnage.removeOperator(':');
			}
			break;
		case 2:
			if (number == 1 && !personnage.isEmpty('>')) {
				updateExpression(">");
				personnage.removeOperator('>');
			} else if (number == 2 && !personnage.isEmpty('H')) {
				updateExpression("H");
				personnage.removeOperator('H');
			} else if (number == 3 && !personnage.isEmpty('K')) {
				updateExpression("K");
				personnage.removeOperator('K');
			}
			break;
		case 3:
			if (number == 1 && !personnage.isEmpty('O')) {
				updateExpression("O");
				personnage.removeOperator('O');
			} else if (number == 2 && !personnage.isEmpty('J')) {
				updateExpression("J");
				personnage.removeOperator('J');
			} else if (number == 3 && !personnage.isEmpty('P')) {
				updateExpression("P");
				personnage.removeOperator('P');
			}
			break;
		case 4:
			if (number == 2) {
				// supprimer le caractere (a gauche du curseur)
				supprimeChar(personnage);
			} else {
				updateCurseur(number); // Decalage du curseur
			}
		}
		if (team == 1) {
			expression_bleue = expression_courante;
			curseur_bleu = curseur;
		} else if (team == 2) {
			expression_rouge = expression_courante;
			curseur_rouge = curseur;
		}
	}

	public void updateExpression_bleue() {
		String affichable;
		root.getChildren().remove(expr_bleue);
		affichable = exprAffichable(expression_bleue);
		expr_bleue = new Text(affichable);
		expr_bleue.setFont(Font.font("Courier New", Tuile.getTaille() - marge));
		expr_bleue.setFill(Color.rgb(72, 145, 220, 1.0));
		expr_bleue.setX(3 * marge + Barre.getDimX());
		expr_bleue.setY(marge + (Terrain.getTuileY() + 1) * Tuile.getTaille());
		root.getChildren().add(expr_bleue);
	}

	public void updateExpression_rouge() {
		String affichable;
		root.getChildren().remove(expr_rouge);
		affichable = exprAffichable(expression_rouge);
		expr_rouge = new Text(affichable);
		expr_rouge.setFont(Font.font("Courier New", Tuile.getTaille() - marge));
		expr_rouge.setFill(Color.rgb(220, 41, 30, 1.0));
		expr_rouge.setX(3 * marge + Barre.getDimX() + ((Terrain.getTuileX() + 1) / 2) * Tuile.getTaille());
		expr_rouge.setY(marge + (Terrain.getTuileY() + 1) * Tuile.getTaille());
		root.getChildren().add(expr_rouge);
	}

	public void updateExpression(String new_c) {
		expression_courante = expression_courante.substring(0, curseur) + new_c
				+ expression_courante.substring(curseur, expression_courante.length());
		curseur++;
	}

	public void supprimeChar(Personnages p) {
		// Si curseur tout a gauche on ne suprimme rien
		if (curseur != 0) {
			char old_c = expression_courante.charAt(curseur - 1);
			expression_courante = expression_courante.substring(0, curseur - 1)
					+ expression_courante.substring(curseur, expression_courante.length());
			curseur--;
			p.addOperator(old_c);
		}
	}

	public void updateCurseur(int decalage) {
		if (decalage == 3) { // Decalage a droite
			// Curseur deja tout a droite, on ne fait rien
			if (curseur != expression_courante.length() - 1) {
				expression_courante = expression_courante.substring(0, curseur)
						+ expression_courante.charAt(curseur + 1) + expression_courante.charAt(curseur)
						+ expression_courante.substring(curseur + 2, expression_courante.length());
				curseur++;
			}
		} else if (decalage == 1) { // Decalage a gauche
			// Curseur deja tout a gauche, on ne fait rien
			if (curseur != 0) {
				expression_courante = expression_courante.substring(0, curseur - 1)
						+ expression_courante.charAt(curseur) + expression_courante.charAt(curseur - 1)
						+ expression_courante.substring(curseur + 1, expression_courante.length());
				curseur--;
			}
		}
	}
	
	public String exprAffichable(String s){
		if (curseur <= 19){
			return s.substring(0, Math.min(20, s.length()));
		}
		return s.substring(curseur - 19, curseur + 1);
	}
}