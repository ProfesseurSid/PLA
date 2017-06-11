package Visual;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Team extends Parent {

	private static int nb = 3;
	private static int dimX = nb * Mate.getTaille() + ((nb - 1) / 2) * Tuile.getTaille();

	public Team(int equipe) {

		Rectangle fond_team = new Rectangle(dimX, Mate.getTaille());
		fond_team.setFill(Color.rgb(150, 150, 150, 1.0));

		Mate Mates[] = new Mate[nb];
		for (int i = 0; i < nb; i++) {
			Mates[i] = new Mate(i * (Mate.getTaille() + Tuile.getTaille() / 2), 0, equipe);
		}

		this.getChildren().add(fond_team);

		for (Mate mates : Mates) {
			this.getChildren().add(mates);
		}
	}

	public static int getDimX() {
		return dimX;
	}

}
