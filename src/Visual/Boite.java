package Visual;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Boite extends Parent {

	private static int Nb = 5;
	private static int Height = Case.getTaille() * Nb + (Tuile.getTaille() / 2) * (Nb - 1);
	private Barre Barres[];

	public Boite(Engine.Personnages p) {

		Rectangle fond_boite = new Rectangle();
		fond_boite.setWidth(Barre.getDimX());
		fond_boite.setHeight(Height);
		fond_boite.setFill(Color.rgb(150, 150, 150, 1.0));
		fond_boite.setArcHeight(10);
		fond_boite.setArcWidth(10);

		Barres = new Barre[Nb];
		for (int i = 0; i < Nb; i++) {
			Barres[i] = new Barre(i, p);
			Barres[i].setTranslateY(i * (Case.getTaille() + Tuile.getTaille() / 2));
		}

		// this.getChildren().add(fond_boite);

		for (Barre barres : Barres) {
			this.getChildren().add(barres);
		}
		if (p.getEquipe() == 0) {
			this.setTranslateX(Test.marge);
			this.setTranslateY(Test.marge);
		} else if (p.getEquipe() == 1) {
			this.setTranslateX(Tuile.getTaille() * Terrain.getTuileX() + Barre.getDimX() + 3 * Test.marge);
			this.setTranslateY(Test.marge);
		}
	}

	public static int getNb() {
		return Nb;
	}

	public static int getHeight() {
		return Height;
	}

	public void visible(int i) {
		this.getChildren().remove(Barres[i]);
		Barres[i].visible(true);
		this.getChildren().add(Barres[i]);
	}

	public void invisible(int i) {
		this.getChildren().remove(Barres[i]);
		Barres[i].visible(false);
		this.getChildren().add(Barres[i]);
	}
	
	public int focused(){
		if(Barres[0].getVisible()){
			return 0;
		}
		if(Barres[1].getVisible()){
			return 1;
		}
		if(Barres[2].getVisible()){
			return 2;
		}
		if(Barres[3].getVisible()){
			return 3;
		}
		else
			return 4;
	}
}
