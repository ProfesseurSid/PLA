package Visual;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Boite extends Parent {

	private static int Nb = 5;
	private static int Height = Case.getTaille() * Nb + (Tuile.getTaille() / 2) * (Nb - 1);

	public Boite() {

		Rectangle fond_boite = new Rectangle();
		fond_boite.setWidth(Barre.getDimX());
		fond_boite.setHeight(Height);
		fond_boite.setFill(Color.rgb(150, 150, 150, 1.0));
		fond_boite.setArcHeight(10);
		fond_boite.setArcWidth(10);

		Barre Barres[] = new Barre[Nb];
		for (int i = 0; i < Nb; i++) {
			Barres[i] = new Barre();
			Barres[i].setTranslateY(i * (Case.getTaille() + Tuile.getTaille() / 2));
		}

		this.getChildren().add(fond_boite);

		for (Barre barres : Barres) {
			this.getChildren().add(barres);
		}
	}

	public static int getNb() {
		return Nb;
	}

	public static int getHeight() {
		return Height;
	}
}
