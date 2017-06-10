package Visual;

import Engine.Personnages;
import Engine.Robots;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;

//import javafx.event.EventHandler;

public class Terrain extends Parent {

	private static int tuileX = 21;
	private static int tuileY = 11;

	private static int grilleWidth = Tuile.getTaille() * tuileX;
	private static int grilleHeight = Tuile.getTaille() * tuileY;

	static ImageView p1 = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/Robot.png")));
	static ImageView p2 = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/Robot.png")));
	static ImageView r1 = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/Robot.png")));
	static ImageView op = new ImageView(
			new Image(PersonnagesVisual.class.getResourceAsStream("images/AccoladeOuvrante.png")));

	Plateau plateau = new Plateau();
	PersonnagesVisual visuel1 = new PersonnagesVisual(p1, 0, plateau);
	Personnages personnage1 = new Personnages(plateau, 0, visuel1);
	PersonnagesVisual visuel2 = new PersonnagesVisual(p2, 1, plateau);
	Personnages personnage2 = new Personnages(plateau, 1, visuel2);
	RobotVisual visuel3 = new RobotVisual(r1, 0, plateau);
	Robots robot1 = new Robots(plateau, personnage1, 0, visuel3, "*{K>O}");
	
	OperateursVisual operateur = new OperateursVisual(op, plateau);

	public Terrain() {

		Rectangle fond_grille = new Rectangle();
		fond_grille.setWidth(grilleWidth);
		fond_grille.setHeight(grilleHeight);
		fond_grille.setFill(Color.rgb(150, 150, 150, 1.0));

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
		personnage1.addRobot(robot1, 1);
		
		
		
		this.getChildren().add(visuel1);
		this.getChildren().add(visuel3);
		this.getChildren().add(visuel2);
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

	public Personnages getpersonnage1() {
		return personnage1;
	}

	public Personnages getpersonnage2() {
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
}
