package Visual;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FinalScreen {

	private int marge = Tuile.getTaille() / 5;
	private int dimX = Terrain.getTuileX() * Tuile.getTaille() + 2 * Barre.getDimX() + 3 * marge;
	private int dimY = Barre.getDimX() + Boite.getHeight() + 4 * marge;
	private Group Screen;
	private ImageView background;
	private ImageView Rreset;
	private ImageView Rquit;
	private ImageView Message;
	private static boolean isFinish = false;

	public FinalScreen(Stage ps) {
		Screen = new Group();
		// background = new Rectangle(dimX, dimY, Color.rgb(255, 255, 255,
		// 0.8));
		background = new ImageView(new Image(FinalScreen.class.getResourceAsStream("images/FinalScreen.png")));
		background.setFitWidth(dimX);
		background.setFitHeight(dimY);

		Rreset = new ImageView(new Image(FinalScreen.class.getResourceAsStream("images/BoutonRejouer.png")));
		Rreset.setFitWidth(dimX / 3);
		Rreset.setFitHeight(dimY / 5);
		Rreset.setTranslateX(dimX / 4 - (0.5 * dimX / 3));
		Rreset.setTranslateY(3 * dimY / 5 - (0.5 * dimY / 5));
		Rreset.setOnMouseMoved(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				Rreset.setImage(new Image(Test.class.getResourceAsStream("images/BoutonRejouerSurvol.png")));
			}
		});
		Rreset.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				Rreset.setImage(new Image(Test.class.getResourceAsStream("images/BoutonRejouer.png")));
			}
		});
		Rreset.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				Test.refresh(ps);
			}
		});

		Rquit = new ImageView(new Image(FinalScreen.class.getResourceAsStream("images/BoutonQuitter.png")));
		Rquit.setFitWidth(dimX / 3);
		Rquit.setFitHeight(dimY / 5);
		Rquit.setTranslateX(3 * dimX / 4 - (0.5 * dimX / 3));
		Rquit.setTranslateY(3 * dimY / 5 - (0.5 * dimY / 5));
		Rquit.setOnMouseMoved(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				Rquit.setImage(new Image(Test.class.getResourceAsStream("images/BoutonQuitterSurvol.png")));
			}
		});
		Rquit.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				Rquit.setImage(new Image(Test.class.getResourceAsStream("images/BoutonQuitter.png")));
			}
		});
		Rquit.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				System.exit(0);
			}
		});

		Message = new ImageView(new Image(FinalScreen.class.getResourceAsStream("images/Player1Dead.png")));
		Message.setFitWidth(dimX / 2);
		Message.setFitHeight(dimY / 5);
		Message.setTranslateX(dimX / 2 - (0.5 * dimX / 2));
		Message.setTranslateY(dimY / 5 - (0.5 * dimY / 5));

	}

	public Group display(int JoueurVictorieux) {
		Screen.getChildren().add(background);
		Screen.getChildren().add(Rreset);
		Screen.getChildren().add(Rquit);
		Screen.getChildren().add(Message);
		isFinish = true;

		// Gerer le background
		if (JoueurVictorieux == 0) {

		} else if (JoueurVictorieux == 1) {

		} else {

		}

		return Screen;
	}

	public void undisplay() {
		isFinish = false;
		Screen.getChildren().remove(background);
		Screen.getChildren().remove(Rreset);
		Screen.getChildren().remove(Rquit);
		Screen.getChildren().remove(Message);
	}
	
	public static boolean getIsFinish(){
		return isFinish;
	}
}
