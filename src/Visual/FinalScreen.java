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
	private ImageView Rmenu;
	private ImageView Message;
	private ImageView Message2;
	private ImageView Message3;
	private static boolean isFinish = false;

	public FinalScreen(Stage ps) {
		Screen = new Group();
		// background = new Rectangle(dimX, dimY, Color.rgb(255, 255, 255,
		// 0.8));
		background = new ImageView(new Image(FinalScreen.class.getResourceAsStream("images/FinalScreen.png")));
		background.setFitWidth(dimX);
		background.setFitHeight(dimY);

		Rreset = new ImageView(new Image(FinalScreen.class.getResourceAsStream("images/BoutonRejouer.png")));
		Rreset.setFitWidth(8*Tuile.getTaille());
		Rreset.setFitHeight(2*Tuile.getTaille());
		Rreset.setTranslateX(dimX / 4 - 0.5*8*Tuile.getTaille());
		Rreset.setTranslateY(1 * dimY / 4 - (0.5*2*Tuile.getTaille()));
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
		Rquit.setFitWidth(8*Tuile.getTaille());
		Rquit.setFitHeight(2*Tuile.getTaille());
		Rquit.setTranslateX(dimX / 4 - 0.5*8*Tuile.getTaille());
		Rquit.setTranslateY(3 * dimY / 4 - (0.5*2*Tuile.getTaille()));
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
		
		Rmenu = new ImageView(new Image(FinalScreen.class.getResourceAsStream("images/BoutonMenu.png")));
		Rmenu.setFitWidth(8*Tuile.getTaille());
		Rmenu.setFitHeight(2*Tuile.getTaille());
		Rmenu.setTranslateX(dimX / 4 - 0.5*8*Tuile.getTaille());
		Rmenu.setTranslateY(dimY / 2 - (0.5*2*Tuile.getTaille()));
		Rmenu.setOnMouseMoved(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				Rmenu.setImage(new Image(Test.class.getResourceAsStream("images/BoutonMenuSurvol.png")));
			}
		});
		Rmenu.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				Rmenu.setImage(new Image(Test.class.getResourceAsStream("images/BoutonMenu.png")));
			}
		});
		Rmenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				Test.refresh(ps);
			}
		});

		Message = new ImageView(new Image(FinalScreen.class.getResourceAsStream("images/Player1Dead.png")));
		Message.setFitWidth(dimX / 3);
		Message.setFitHeight((dimX/3) / 5.6 );
		Message.setTranslateX(8*dimX / 11 - (0.5 * dimX / 3));
		Message.setTranslateY(2*dimY / 6 - ((dimX/3) / 5.6));
		
		Message2 = new ImageView(new Image(FinalScreen.class.getResourceAsStream("images/Player2Dead.png")));
		Message2.setFitWidth(dimX / 3);
		Message2.setFitHeight((dimX/3) / 5.6 );
		Message2.setTranslateX(8*dimX / 11 - (0.5 * dimX / 3));
		Message2.setTranslateY(2*dimY / 6 - ((dimX/3) / 5.6));

	}

	public Group display(int JoueurVictorieux) {
		Screen.getChildren().add(background);
		Screen.getChildren().add(Rreset);
		Screen.getChildren().add(Rquit);
		Screen.getChildren().add(Rmenu);
		isFinish = true;

		// Gerer le background
		if (JoueurVictorieux == 0) {
			
		} else if (JoueurVictorieux == 1) {
			Screen.getChildren().add(Message);
		} else {
			Screen.getChildren().add(Message2);
		}

		return Screen;
	}

	public void undisplay() {
		isFinish = false;
		Screen.getChildren().remove(background);
		Screen.getChildren().remove(Rreset);
		Screen.getChildren().remove(Rquit);
		Screen.getChildren().remove(Message);
		Screen.getChildren().remove(Message2);
	}
	
	public static boolean getIsFinish(){
		return isFinish;
	}
}
