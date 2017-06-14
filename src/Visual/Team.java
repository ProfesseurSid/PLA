package Visual;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Team extends Parent {

	private static int nb = 3;
	private static int dimX = nb * Mate.getTaille() + ((nb - 1) / 2) * Tuile.getTaille();
	Mate Mates[];

	public Team(int equipe) {

		Rectangle fond_team = new Rectangle(dimX, Mate.getTaille());
		fond_team.setFill(Color.rgb(150, 150, 150, 1.0));

		Mates = new Mate[nb];
		for (int i = 0; i < nb; i++) {
			Mates[i] = new Mate(i * (Mate.getTaille() + Tuile.getTaille() / 2), 0);
		}
		if (equipe == 0) {
			this.setTranslateX(2 * Test.marge + Barre.getDimX());
			this.setTranslateY(4 * Test.marge + Terrain.getTuileY() * Tuile.getTaille() + Tuile.getTaille());
		} else {
			this.setTranslateX(2 * Test.marge + Barre.getDimX() + ((Terrain.getTuileX() + 1) / 2) * Tuile.getTaille());
			this.setTranslateY(4 * Test.marge + Terrain.getTuileY() * Tuile.getTaille() + Tuile.getTaille());
		}

		// this.getChildren().add(fond_team);

		for (Mate mates : Mates) {
			this.getChildren().add(mates);
		}
	}

	public static int getDimX() {
		return dimX;
	}

	public void visible(int i) {
		this.getChildren().remove(Mates[i]);
		Mates[i].visible(true);
		this.getChildren().add(Mates[i]);
	}

	public void invisible(int i) {
		this.getChildren().remove(Mates[i]);
		Mates[i].visible(false);
		this.getChildren().add(Mates[i]);
	}

	public boolean getVisible(int i) {
		return Mates[i].getVisible();
	}

	public int focused() {
		if (Mates[0].getVisible())
			return 0;
		if (Mates[1].getVisible())
			return 1;
		if (Mates[2].getVisible())
			return 2;
		else
			return 3;
	}

	public static void set() {
		dimX = nb * Mate.getTaille() + ((nb - 1) / 2) * Tuile.getTaille();
	}
}
