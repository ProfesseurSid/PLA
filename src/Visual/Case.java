package Visual;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Case extends Parent {

	private int posX;
	private int posY;

	private static int taille = 2 * Tuile.getTaille();

	Rectangle fond_case;

	public Case(int X, int Y) {
		posX = X;
		posY = Y;

		fond_case = new Rectangle(taille, taille, Color.rgb(220, 220, 220, 1.0));
		fond_case.setStroke(Color.rgb(150, 150, 150, 1.0));
		fond_case.setStrokeWidth(2);
		fond_case.setArcHeight(10);
		fond_case.setArcWidth(10);

		this.getChildren().add(fond_case);

		this.setTranslateX(posX);
		this.setTranslateY(posY);
	}

	public static int getTaille() {
		return taille;
	}

}
