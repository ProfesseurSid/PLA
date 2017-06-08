package Visual;

import Engine.*;
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
	// Operateurs op;

	AccoladeO accoladeO;

	public OperateursVisual(ImageView operateur, Plateau plateau) {

		this.plateau = plateau;
		this.operateur = operateur;
		indX = (int) (Math.random() * (Terrain.getTuileX() - 1));
		indY = (int) (Math.random() * (Terrain.getTuileY() - 1));
		while (plateau.verification(indX, indY) == 1) {
			indX = (int) (Math.random() * (Terrain.getTuileX() - 1));
			indY = (int) (Math.random() * (Terrain.getTuileY() - 1));
		}

		operateur.setTranslateX(indX * taille);
		operateur.setTranslateY(indY * taille);
		operateur.setFitWidth(taille);
		operateur.setFitHeight(taille);

		this.getChildren().add(operateur);

		accoladeO = new AccoladeO(indX, indY);

		plateau.put(indX, indY, accoladeO);
	}

	public Timeline Blinker(ImageView image) {
		Timeline blink = new Timeline(
				new KeyFrame(Duration.seconds(0), new KeyValue(image.opacityProperty(), 1, Interpolator.DISCRETE)),
				new KeyFrame(Duration.seconds(0.5), new KeyValue(image.opacityProperty(), 0, Interpolator.DISCRETE)),
				new KeyFrame(Duration.seconds(1), new KeyValue(image.opacityProperty(), 1, Interpolator.DISCRETE)));
		blink.setCycleCount(10);

		return blink;
	}

	public FadeTransition Fader(ImageView image) {
		FadeTransition fade = new FadeTransition(Duration.seconds(3), image);
		fade.setFromValue(1);
		fade.setToValue(0);
		// plateau.remove(indX, indY);
		return fade;
	}

	public void remove() {
		this.getChildren().remove(operateur);
	}

	public ImageView getImageOperateur() {
		return operateur;
	}

}
