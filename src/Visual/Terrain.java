package Visual;

import Engine.Personnages;
import Engine.Robots;
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
	
	static ImageView r1P1 = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/Robot.png")));
	static ImageView r1P2 = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/Robot.png")));

	public Plateau plateau = new Plateau(this);

	private PersonnagesVisual visuel1 = new PersonnagesVisual(p1, 0, plateau);
	public Personnages personnage1 = new Personnages(plateau, 0, visuel1);

	private PersonnagesVisual visuel2 = new PersonnagesVisual(p2, 1, plateau);
	public Personnages personnage2 = new Personnages(plateau, 1, visuel2);

	RobotVisual visuelRobot1P1 = new RobotVisual(r1P1, 0, plateau);
	Robots robot1P1 = new Robots(plateau, personnage1, 0, visuelRobot1P1);

	RobotVisual visuelRobot1P2 = new RobotVisual(r1P2, 1, plateau);
	Robots robot1P2 = new Robots(plateau, personnage2, 1, visuelRobot1P2);

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

		robot1P1.setBehavior("*{K>O}");
		personnage1.addRobot(robot1P1, 1);

		robot1P2.setBehavior("*{P:;H>O}");
		personnage2.addRobot(robot1P2, 1);

		this.getChildren().add(visuel1);
		this.getChildren().add(visuelRobot1P1);
		this.getChildren().add(visuel2);
		this.getChildren().add(visuelRobot1P2);
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

	public Personnages getpersonnage1() {
		return personnage1;
	}

	public Personnages getpersonnage2() {
		return personnage2;
	}

	public ImageView getImagePersonnage1() {
		return p1;
	}

	public ImageView getImagePersonnage2() {
		return p2;
	}

	public void addVisual(Parent p) {
		this.getChildren().add(p);
	}

	public Plateau getPlateau() {
		return plateau;
	}
}
