package Visual;

import javafx.scene.Parent;

public class Tuile extends Parent {

	private int posX = 0;
	private int posY = 0;

	private static int taille = 40;

	public Tuile(int X, int Y) {
		posX = X;
		posY = Y;

		this.setTranslateX(posX);
		this.setTranslateY(posY);
	}

	public static int getTaille() {
		return taille;
	}

	public static void setTaille(int t) {
		taille = t;
	}

}
