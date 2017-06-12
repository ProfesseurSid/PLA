package Visual;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Barre extends Parent {

	private static int nb = 3;
	private static int dimX = nb * Case.getTaille() + ((nb - 1) / 2) * Tuile.getTaille();

	Case Cases[];
	Text commandes[];

	Color couleur;

	Rectangle fond_barre;

	public Barre(int rg, Engine.Personnages p) {

		fond_barre = new Rectangle();
		fond_barre.setWidth(dimX);
		fond_barre.setHeight(Case.getTaille());
		fond_barre.setFill(Color.rgb(150, 150, 150, 1.0));
		fond_barre.setArcHeight(10);
		fond_barre.setArcWidth(10);

		fond_barre.setVisible(true);

		Cases = new Case[nb];
		commandes = new Text[nb];

		if (p.getEquipe() == 0) {
			commandes[0] = new Text("1");
			commandes[1] = new Text("2");
			commandes[2] = new Text("3");
			couleur = Color.rgb(72, 145, 220);
		} else if (p.getEquipe() == 1) {
			commandes[0] = new Text(";");
			commandes[1] = new Text(":");
			commandes[2] = new Text("!");
			couleur = Color.rgb(220, 41, 30);
		}

		switch (rg) {
		case (0):
			Cases[0] = new Case(0, 0, '*', p);
			Cases[1] = new Case(Case.getTaille() + Tuile.getTaille() / 2, 0, '{', p);
			Cases[2] = new Case(2 * (Case.getTaille() + Tuile.getTaille() / 2), 0, '}', p);
			break;
		case (1):
			Cases[0] = new Case(0, 0, ';', p);
			Cases[1] = new Case(Case.getTaille() + Tuile.getTaille() / 2, 0, '|', p);
			Cases[2] = new Case(2 * (Case.getTaille() + Tuile.getTaille() / 2), 0, ':', p);
			break;
		case (2):
			Cases[0] = new Case(0, 0, '>', p);
			Cases[1] = new Case(Case.getTaille() + Tuile.getTaille() / 2, 0, 'H', p);
			Cases[2] = new Case(2 * (Case.getTaille() + Tuile.getTaille() / 2), 0, 'K', p);
			break;
		case (3):
			Cases[0] = new Case(0, 0, 'O', p);
			Cases[1] = new Case(Case.getTaille() + Tuile.getTaille() / 2, 0, 'J', p);
			Cases[2] = new Case(2 * (Case.getTaille() + Tuile.getTaille() / 2), 0, 'P', p);
			break;
		case (4):
			Cases[0] = new Case(0, 0, '<');
			Cases[1] = new Case(Case.getTaille() + Tuile.getTaille() / 2, 0, 'C');
			Cases[2] = new Case(2 * (Case.getTaille() + Tuile.getTaille() / 2), 0, '>');
			break;
		}

		for (Case cases : Cases) {
			this.getChildren().add(cases);
		}

	}

	public static int getDimX() {
		return dimX;
	}

	public void visible(boolean t) {
		if (t) {

			for (Case cases : Cases) {
				this.getChildren().remove(cases);
			}
			this.getChildren().add(fond_barre);
			for (Case cases : Cases) {
				this.getChildren().add(cases);
			}
			int i = 0;
			for (Text commande : commandes) {
				commande.setTranslateX(i * (Case.getTaille() + Tuile.getTaille() / 2) + Test.marge / 2);
				commande.setTranslateY((Test.marge * 5) / 2);
				commande.setFont(Font.font("Verdana", Tuile.getTaille() / 2));
				commande.setFill(couleur);
				this.getChildren().add(commande);
				i++;
			}
		} else {
			this.getChildren().remove(fond_barre);
			for (Text commande : commandes) {
				this.getChildren().remove(commande);
			}
		}
	}

}
