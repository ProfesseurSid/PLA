package Visual;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Barre extends Parent {

	private static int nb = 3;
	private static int dimX = nb * Case.getTaille() + (nb - 1) * Tuile.getTaille();

	public Barre() {

		Rectangle fond_barre = new Rectangle();
		fond_barre.setWidth(dimX);
		fond_barre.setHeight(Case.getTaille());
		fond_barre.setFill(Color.rgb(150, 150, 150, 1.0));
		fond_barre.setArcHeight(10);
		fond_barre.setArcWidth(10);

		Case Cases[] = new Case[nb];
		for (int i = 0; i < nb; i++) {
			Cases[i] = new Case(i * (Case.getTaille() + Tuile.getTaille()), 0);
		}

		this.getChildren().add(fond_barre);

		for (Case cases : Cases) {
			this.getChildren().add(cases);
		}
	}

	public static int getDimX() {
		return dimX;
	}

}
