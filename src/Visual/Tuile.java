package Visual;

import javafx.scene.Parent;
import javafx.scene.shape.Rectangle;

public class Tuile extends Parent {

	private int posX = 0;
	private int posY = 0;

	private static int taille = 25;

	Rectangle fond_tuile;

	public Tuile(int X, int Y) {
		posX = X;
		posY = Y;

		this.setTranslateX(posX);
		this.setTranslateY(posY);
	}

	public static int getTaille() {
		return taille;
	}

}
