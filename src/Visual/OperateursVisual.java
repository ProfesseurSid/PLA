package Visual;

import Engine.Operateurs;
import javafx.animation.*;
import javafx.scene.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class OperateursVisual extends Parent {

	Plateau plateau;
	int indX; // colonne
	int indY; // ligne
	int taille = Tuile.getTaille();
	ImageView operateur;
	Operateurs op;

	public OperateursVisual(int indX, int indY, ImageView operateur, Plateau plateau) {
		this.plateau = plateau;
		this.operateur = operateur;
		this.indX = indX;
		this.indY = indY;

		operateur.setTranslateX(indX * taille);
		operateur.setTranslateY(indY * taille);
		operateur.setFitWidth(taille);
		operateur.setFitHeight(taille);

		this.getChildren().add(operateur);
	}

	public Timeline Blinker(ImageView operateur) {
		Timeline blink = new Timeline(
				new KeyFrame(Duration.seconds(0), new KeyValue(operateur.opacityProperty(), 1, Interpolator.DISCRETE)),
				new KeyFrame(Duration.seconds(0.5),
						new KeyValue(operateur.opacityProperty(), 0, Interpolator.DISCRETE)),
				new KeyFrame(Duration.seconds(1), new KeyValue(operateur.opacityProperty(), 1, Interpolator.DISCRETE)));
		blink.setCycleCount(10);

		return blink;
	}

	public FadeTransition Fader(ImageView operateur) {
		FadeTransition fade = new FadeTransition(Duration.seconds(3), operateur);
		fade.setFromValue(1);
		fade.setToValue(0);
		plateau.plateau[indY][indX] = null;
		return fade;
	}

	public void remove() {
		this.getChildren().remove(operateur);
	}

}
