package Visual;

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
	static ImageView op = new ImageView(new Image(PersonnagesVisual.class.getResourceAsStream("images/AccoladeOuvrante.png")));

	Plateau plateau = new Plateau();
	PersonnagesVisual personnage1 = new PersonnagesVisual(p1, 0, plateau);
	PersonnagesVisual personnage2 = new PersonnagesVisual(p2, 1, plateau);
	
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

	public OperateursVisual getoperateur(){
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
