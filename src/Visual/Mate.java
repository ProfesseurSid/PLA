package Visual;

import javafx.scene.Parent;
import javafx.scene.effect.GaussianBlur;
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
	private boolean visible;
	Jauge jauge;

	public Mate(int X, int Y) {
		posX = X;
		posY = Y;

		fond_mate = new Rectangle(taille, taille, Color.rgb(220, 220, 220, 1.0));
		fond_mate.setArcHeight(Test.marge);
		fond_mate.setArcWidth(Test.marge);

		this.rob = new ImageView(new Image(Portrait.class.getResourceAsStream("images/TeteRobot.png")));
		rob.setFitWidth(taille);
		rob.setFitHeight(taille);
		this.getChildren().add(rob);

		this.jauge = new Jauge(Test.marge, (3 * taille) / 4, (taille - (2 * Test.marge)) / 5);
		this.getChildren().add(jauge);

		this.setEffect(new GaussianBlur(Test.marge));
		this.setTranslateX(posX);
		this.setTranslateY(posY);
	}

	public Mate(int X, int Y, int equipe) {
		posX = X;
		posY = Y;

		fond_mate = new Rectangle(taille, taille, Color.rgb(220, 220, 220, 1.0));

		if (equipe == 0) {
			this.rob = new ImageView(new Image(Portrait.class.getResourceAsStream("images/TeteRobotBleu.png")));
			rob.setFitWidth(taille);
			rob.setFitHeight(taille);
		} else {
			this.rob = new ImageView(new Image(Portrait.class.getResourceAsStream("images/TeteRobotRouge.png")));
			rob.setFitWidth(taille);
			rob.setFitHeight(taille);
		}
		this.getChildren().add(rob);
		this.jauge = new Jauge(Test.marge, (3 * taille) / 4, (taille - (2 * Test.marge)) / 5, equipe);
		this.getChildren().add(jauge);

		this.setTranslateX(posX);
		this.setTranslateY(posY);
	}

	public static int getTaille() {
		return taille;
	}

	public void visible(boolean t) {
		if (t) {
			this.getChildren().remove(rob);
			this.getChildren().remove(jauge);
			this.getChildren().add(fond_mate);
			this.visible = true;
			this.getChildren().add(this.rob);
			this.getChildren().add(this.jauge);
		} else {
			this.getChildren().remove(fond_mate);
			this.visible = false;
		}
	}

	public boolean getVisible() {
		return visible;
	}

	public static void set() {
		taille = Tuile.getTaille() * 3;
	}
}
