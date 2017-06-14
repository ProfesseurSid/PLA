package Visual;

import javafx.scene.Parent;
import javafx.scene.image.ImageView;

public class Portrait extends Parent {

	private int indX;
	private int indY;

	static int taille = Tuile.getTaille();

	public Portrait(ImageView personnage, int x, int y) {
		this.indX = x;
		this.indY = y;

		personnage.setTranslateX(indX * taille);
		personnage.setTranslateY(indY * taille);
		personnage.setFitWidth(taille);
		personnage.setFitHeight(taille);

		this.getChildren().add(personnage);
	}

	public int getX() {
		return this.indX;
	}

	public int getY() {
		return this.indY;
	}

	public void Haut(ImageView personnage) {
		if (indY > 0) {
			indY = indY - 1;
			personnage.setTranslateX(indX * taille);
			personnage.setTranslateY(indY * taille);
			personnage.setFitWidth(taille);
			personnage.setFitHeight(taille);
		}
	}

	public void Bas(ImageView personnage) {
		if (indY < Terrain.getTuileY() - 1) {
			indY = indY + 1;
			personnage.setTranslateX(indX * taille);
			personnage.setTranslateY(indY * taille);
			personnage.setFitWidth(taille);
			personnage.setFitHeight(taille);
		}
	}

	public void Gauche(ImageView personnage) {
		if (indX > 0) {
			indX = indX - 1;
			personnage.setTranslateX(indX * taille);
			personnage.setTranslateY(indY * taille);
			personnage.setFitWidth(taille);
			personnage.setFitHeight(taille);
		}
	}

	public void Droite(ImageView personnage) {
		if (indX < Terrain.getTuileX() - 1) {
			indX = indX + 1;
			personnage.setTranslateX(indX * taille);
			personnage.setTranslateY(indY * taille);
			personnage.setFitWidth(taille);
			personnage.setFitHeight(taille);
		}
	}

	public static void set(){
		taille = Tuile.getTaille();
	}
}
