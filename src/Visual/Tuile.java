package Visual;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tuile extends Parent {

	private int posX = 0;
	private int posY = 0;

	private static int taille = 40;

	Rectangle fond_tuile;

	public Tuile(int X, int Y) {
		posX = X;
		posY = Y;

		fond_tuile = new Rectangle(taille, taille, Color.rgb(220, 220, 220, 1.0));
		fond_tuile.setStroke(Color.rgb(150, 150, 150, 1.0));
		fond_tuile.setStrokeWidth(taille / 500);
		fond_tuile.setArcHeight(taille / 5);
		fond_tuile.setArcWidth(taille / 5);

		this.getChildren().add(fond_tuile);

		this.setTranslateX(posX);
		this.setTranslateY(posY);
	}

	public static int getTaille() {
		return taille;
	}

}
