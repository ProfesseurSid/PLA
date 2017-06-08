package Visual;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class Terrain extends Parent {

	private static int tuileX = 21;
	private static int tuileY = 11;

	private static int grilleWidth = Tuile.getTaille() * tuileX;
	private static int grilleHeight = Tuile.getTaille() * tuileY;

	static ImageView p1 = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/FaceRobotBleu.png")));
	static ImageView p2 = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/FaceRobotRouge.png")));
	static ImageView op = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/AccoladeOuvrante.png")));

	Plateau plateau = new Plateau();

	OperateursVisual operateur = new OperateursVisual(op, plateau);

	PersonnagesVisual personnage1 = new PersonnagesVisual(p1, 0, plateau, operateur);
	PersonnagesVisual personnage2 = new PersonnagesVisual(p2, 1, plateau, operateur);

	public Terrain() {

		ImageView fond_grille = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/Terrain.png")));
		fond_grille.setFitWidth(grilleWidth);
		fond_grille.setFitHeight(grilleHeight);

		Tuile Tuiles[] = new Tuile[21 * 11];
		for (int i = 0; i < Terrain.tuileX; i++) {
			for (int j = 0; j < Terrain.tuileY; j++) {
				Tuiles[i + j * 21] = new Tuile(i * Tuile.getTaille(), j * Tuile.getTaille());
			}
		}

		this.getChildren().add(fond_grille);

		for (Tuile tuile : Tuiles) {
			this.getChildren().add(tuile);
		}

		this.getChildren().add(personnage1);
		this.getChildren().add(personnage2);
		this.getChildren().add(operateur);

	}

	public static int getTuileX() {
		return tuileX;
	}

	public static int getTuileY() {
		return tuileY;
	}

	public static int getGrilleWidth() {
		return grilleWidth;
	}

	public static int getGrilleHeight() {
		return grilleHeight;
	}

	public PersonnagesVisual getpersonnage1() {
		return personnage1;
	}

	public PersonnagesVisual getpersonnage2() {
		return personnage2;
	}

	public OperateursVisual getoperateur() {
		return operateur;
	}

	public ImageView getImagePersonnage1() {
		return p1;
	}

	public ImageView getImagePersonnage2() {
		return p2;
	}

	public ImageView getImageOperateur() {
		return op;
	}

	public Plateau getPlateau() {
		return plateau;

	}
}
