package Visual;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Jauge extends Parent {

	private int posX;
	private int posY;

	Rectangle jauge;

	public Jauge(int X, int Y, int hauteur) {
		posX = X;
		posY = Y;

		jauge = new Rectangle(5 * hauteur, hauteur);
		jauge.setTranslateX(posX);
		jauge.setTranslateY(posY);
		jauge.setStroke(Color.WHITE);
		jauge.setStrokeWidth(5);
		jauge.setArcHeight(10);
		jauge.setArcWidth(10);
		jauge.setFill(Color.rgb(0, 0, 0, 0.));
		this.getChildren().add(jauge);
	}

	public Jauge(int X, int Y, int hauteur, int equipe) {
		posX = X;
		posY = Y;

		jauge = new Rectangle(5 * hauteur, hauteur);
		jauge.setTranslateX(posX);
		jauge.setTranslateY(posY);
		jauge.setStroke(Color.WHITE);
		jauge.setStrokeWidth(2);
		jauge.setArcHeight(10);
		jauge.setArcWidth(10);

		if (equipe == 0) {
			jauge.setFill(Color.rgb(72, 145, 220, 1.0));
		} else {
			jauge.setFill(Color.rgb(220, 41, 30, 1.0));

		}
		this.getChildren().add(jauge);
	}
}
