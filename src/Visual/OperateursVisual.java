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

	public OperateursVisual(ImageView operateur, Plateau plateau) {
		this.plateau = plateau;
		this.operateur = operateur;
		indX = (int) (Math.random() * (Terrain.getTuileX() - 1));
		indX = (int) (Math.random() * (Terrain.getTuileY() - 1));
		while (plateau.verification(indX, indY) == 1) {
			indX = (int) (Math.random() * (Terrain.getTuileX() - 1));
			indX = (int) (Math.random() * (Terrain.getTuileY() - 1));
		}

		// TODO
		// plateau.put(indX, indY, op);

		operateur.setTranslateX(indX * taille);
		operateur.setTranslateY(indY * taille);
		operateur.setFitWidth(taille);
		operateur.setFitHeight(taille);

		this.getChildren().add(operateur);
	}

	public Timeline Blinker(Node node) {
		Timeline blink = new Timeline(
				new KeyFrame(Duration.seconds(0), new KeyValue(node.opacityProperty(), 1, Interpolator.DISCRETE)),
				new KeyFrame(Duration.seconds(0.5), new KeyValue(node.opacityProperty(), 0, Interpolator.DISCRETE)),
				new KeyFrame(Duration.seconds(1), new KeyValue(node.opacityProperty(), 1, Interpolator.DISCRETE)));
		blink.setCycleCount(10);
		return blink;
	}

	public FadeTransition Fader(Node node) {
		FadeTransition fade = new FadeTransition(Duration.seconds(3), node);
		fade.setFromValue(1);
		fade.setToValue(0);
		return fade;
	}

	public void manger() {
		FadeTransition fade = new FadeTransition(Duration.seconds(0), operateur);
		fade.setFromValue(1);
		fade.setToValue(0);
		// TODO
		// plateau.remove(indX, indY, op);
	}
}
