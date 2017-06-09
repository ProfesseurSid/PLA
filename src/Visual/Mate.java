package Visual;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Mate extends Parent {

	private int posX;
	private int posY;

	private static int taille = Tuile.getTaille() * 3;

	ImageView rob;
	Rectangle fond_mate;

	public Mate(int X, int Y, int equipe) {
		posX = X;
		posY = Y;

		fond_mate = new Rectangle(taille, taille, Color.rgb(220, 220, 220, 1.0));

		this.getChildren().add(fond_mate);

		ImageView robB = new ImageView(new Image(Portrait.class.getResourceAsStream("images/TeteRobotBleu.png")));
		robB.setFitWidth(taille);
		robB.setFitHeight(taille);
		ImageView robR = new ImageView(new Image(Portrait.class.getResourceAsStream("images/TeteRobotRouge.png")));
		robR.setFitWidth(taille);
		robR.setFitHeight(taille);
		if (equipe == 0) {
			this.getChildren().add(robB);
		} else {
			this.getChildren().add(robR);
		}

		Jauge jauge = new Jauge(Test.marge, (3 * taille) / 4, (taille - (2 * Test.marge))/5, equipe);
		this.getChildren().add(jauge);

		this.setTranslateX(posX);
		this.setTranslateY(posY);
	}

	public static int getTaille() {
		return taille;
	}

}
