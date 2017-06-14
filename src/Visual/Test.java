package Visual;

import java.util.Date;

import Engine.Timer;
import UserInterface.Keyboard;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Test extends Application {

	static int marge = Tuile.getTaille() / 5;
	private static Timer game;
	private static boolean inPause = false, inMenu = true, inParam = false;
	private static Group root, jeu, pause, menu, param;
	private static Rectangle pauseScreen = new Rectangle();
	private static ImageView pauseText;
	private static Scene scene;
	private static FinalScreen finalscreen;

	public static void main(String[] args) {
		Application.launch(Test.class, args);
	}

	public void start(Stage primaryStage) {
		restart(primaryStage);
	}

	public static void restart(Stage primaryStage) {

		finalscreen = new FinalScreen(primaryStage);
		System.out.println("Le programme se lance");
		marge = Tuile.getTaille() / 5;

		int dimX = Terrain.getTuileX() * Tuile.getTaille() + 2 * Barre.getDimX() + 3 * marge;
		int dimY = Barre.getDimX() + Boite.getHeight() + 4 * marge;

		System.out.println("DimX = " + dimX + " DimY = " + dimY);

		primaryStage.setTitle("ARF - Autonomous Robot Fight");

		root = new Group();

		scene = new Scene(root, dimX, dimY);

		/**
		 * Creation d'un group pause regroupant tous les elements affiches quand
		 * le jeu est en pause.
		 */

		pause = new Group();

		pauseScreen.setHeight(dimY);
		pauseScreen.setWidth(dimX);
		pauseScreen.setFill(Color.rgb(200, 200, 200, 0.4));

		pauseText = new ImageView(new Image(FinalScreen.class.getResourceAsStream("images/Pause.png")));
		pauseText.setFitWidth(8*Tuile.getTaille());
		pauseText.setFitHeight(2*Tuile.getTaille());
		pauseText.setTranslateX(dimX / 2 - 4 * Tuile.getTaille());
		pauseText.setTranslateY(dimY / 2 - 2 * Tuile.getTaille());

		pause.getChildren().add(pauseScreen);
		pause.getChildren().add(pauseText);

		/**
		 * Creation d'un group jeu regroupant tous les elements affiches au
		 * cours du jeu.
		 */

		jeu = new Group();

		int tailleExpression = (Terrain.getTuileX() / 2) * Tuile.getTaille();

		Terrain monTerrain = new Terrain();

		Rectangle champBleu = new Rectangle();
		champBleu.setHeight(Tuile.getTaille());
		champBleu.setWidth((Terrain.getTuileX() / 2) * Tuile.getTaille());
		champBleu.setTranslateX(2 * marge + Barre.getDimX());
		champBleu.setTranslateY(2 * marge + Terrain.getTuileY() * Tuile.getTaille());
		jeu.getChildren().add(champBleu);

		Text expr_bleue = new Text("EXPRESSION");
		expr_bleue.setFont(new Font(Tuile.getTaille() - marge));
		expr_bleue.setFill(Color.rgb(72, 145, 220, 1.0));
		expr_bleue.setX(3 * marge + Barre.getDimX());
		expr_bleue.setY(marge + (Terrain.getTuileY() + 1) * Tuile.getTaille());
		jeu.getChildren().add(expr_bleue);

		Rectangle champRouge = new Rectangle();
		champRouge.setHeight(Tuile.getTaille());
		champRouge.setWidth((Terrain.getTuileX() / 2) * Tuile.getTaille());
		champRouge.setTranslateX(2 * marge + Barre.getDimX() + ((Terrain.getTuileX() + 1) / 2) * Tuile.getTaille());
		champRouge.setTranslateY(2 * marge + Terrain.getTuileY() * Tuile.getTaille());
		jeu.getChildren().add(champRouge);

		Text expr_rouge = new Text("EXPRESSION");
		expr_rouge.setFont(new Font(Tuile.getTaille() - marge));
		expr_rouge.setFill(Color.rgb(220, 41, 30, 1.0));
		expr_rouge.setX(3 * marge + Barre.getDimX() + ((Terrain.getTuileX() + 1) / 2) * Tuile.getTaille());
		expr_rouge.setY(marge + (Terrain.getTuileY() + 1) * Tuile.getTaille());
		jeu.getChildren().add(expr_rouge);

		Boite boiteGauche = new Boite(monTerrain.getpersonnage1());
		Boite boiteDroite = new Boite(monTerrain.getpersonnage2());
		jeu.getChildren().add(boiteDroite);
		jeu.getChildren().add(boiteGauche);

		Team team1 = new Team(0);
		Team team2 = new Team(1);
		jeu.getChildren().add(team1);
		jeu.getChildren().add(team2);

		Keyboard keyboard = new Keyboard(monTerrain.getpersonnage1(), monTerrain.getpersonnage2(), jeu, expr_bleue,
				expr_rouge, marge, tailleExpression, boiteGauche, boiteDroite, team1, team2);

		scene.setOnKeyPressed(keyboard);

		jeu.getChildren().add(monTerrain);

		ImageView PersoRouge = new ImageView(new Image(Test.class.getResourceAsStream("images/PersoRouge.png")));
		PersoRouge.setFitWidth(Barre.getDimX());
		PersoRouge.setFitHeight(Barre.getDimX());
		PersoRouge.setTranslateX(Tuile.getTaille() * Terrain.getTuileX() + Barre.getDimX() + 3 * marge);
		PersoRouge.setTranslateY(Boite.getHeight() + 4 * marge);
		jeu.getChildren().add(PersoRouge);

		ImageView PersoBleu = new ImageView(new Image(Test.class.getResourceAsStream("images/PersoBleu.png")));
		PersoBleu.setFitWidth(Barre.getDimX());
		PersoBleu.setFitHeight(Barre.getDimX());
		PersoBleu.setTranslateX(marge);
		PersoBleu.setTranslateY(Boite.getHeight() + 4 * marge);
		jeu.getChildren().add(PersoBleu);

		System.out.println("jeu est créé");

		/**
		 * Creation d'un group menu, contenant tous les elements visuels du
		 * menu.
		 */

		menu = new Group();

		ImageView iFondM = new ImageView(new Image(Test.class.getResourceAsStream("images/Menu.png")));
		iFondM.setFitWidth(Terrain.getGrilleWidth());
		iFondM.setFitHeight(Terrain.getGrilleHeight());
		iFondM.setTranslateX(2 * marge + Barre.getDimX());
		iFondM.setTranslateY(marge);

		menu.getChildren().add(iFondM);

		ImageView iTitre = new ImageView(new Image(Test.class.getResourceAsStream("images/GameTitle.png")));
		iTitre.setFitWidth(12.8 * Tuile.getTaille());
		iTitre.setFitHeight(4 * Tuile.getTaille());
		iTitre.setTranslateX(
				2 * marge + Barre.getDimX() + Terrain.getGrilleWidth() / 2 - (12.8 * Tuile.getTaille()) / 2);
		iTitre.setTranslateY(marge + Terrain.getGrilleHeight() / 5 - (4 * Tuile.getTaille()) / 2);

		menu.getChildren().add(iTitre);

		ImageView iJouer = new ImageView(new Image(Test.class.getResourceAsStream("images/BoutonJouer.png")));
		iJouer.setFitWidth(6 * Tuile.getTaille());
		iJouer.setFitHeight((3 * Tuile.getTaille()) / 2);
		iJouer.setTranslateX(2 * marge + Barre.getDimX() + Terrain.getGrilleWidth() / 2 - (6 * Tuile.getTaille()) / 2);
		iJouer.setTranslateY(marge + (3 * Terrain.getGrilleHeight()) / 6 - ((3 * Tuile.getTaille()) / 2) / 2);
		iJouer.setOnMouseMoved(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				iJouer.setImage(new Image(Test.class.getResourceAsStream("images/BoutonJouerSurvol.png")));
			}
		});
		iJouer.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				iJouer.setImage(new Image(Test.class.getResourceAsStream("images/BoutonJouer.png")));
			}
		});
		iJouer.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				MenuOff();
				game.start();
			}
		});
		menu.getChildren().add(iJouer);

		ImageView iParam = new ImageView(new Image(Test.class.getResourceAsStream("images/BoutonReglages.png")));
		iParam.setFitWidth(6 * Tuile.getTaille());
		iParam.setFitHeight((3 * Tuile.getTaille()) / 2);
		iParam.setTranslateX(2 * marge + Barre.getDimX() + Terrain.getGrilleWidth() / 2 - (6 * Tuile.getTaille()) / 2);
		iParam.setTranslateY(marge + (4 * Terrain.getGrilleHeight()) / 6 - ((3 * Tuile.getTaille()) / 2) / 2);
		iParam.setOnMouseMoved(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				iParam.setImage(new Image(Test.class.getResourceAsStream("images/BoutonReglagesSurvol.png")));
			}
		});
		iParam.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				iParam.setImage(new Image(Test.class.getResourceAsStream("images/BoutonReglages.png")));
			}
		});
		iParam.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				ParamOn();
			}
		});
		menu.getChildren().add(iParam);

		ImageView iQuitter = new ImageView(new Image(Test.class.getResourceAsStream("images/BoutonQuitter.png")));
		iQuitter.setFitWidth(6 * Tuile.getTaille());
		iQuitter.setFitHeight((3 * Tuile.getTaille()) / 2);
		iQuitter.setTranslateX(
				2 * marge + Barre.getDimX() + Terrain.getGrilleWidth() / 2 - (6 * Tuile.getTaille()) / 2);
		iQuitter.setTranslateY(marge + (5 * Terrain.getGrilleHeight()) / 6 - ((3 * Tuile.getTaille()) / 2) / 2);
		iQuitter.setOnMouseMoved(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				iQuitter.setImage(new Image(Test.class.getResourceAsStream("images/BoutonQuitterSurvol.png")));
			}
		});
		iQuitter.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				iQuitter.setImage(new Image(Test.class.getResourceAsStream("images/BoutonQuitter.png")));
			}
		});
		iQuitter.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				primaryStage.close();
			}
		});
		menu.getChildren().add(iQuitter);

		/**
		 * Creation d'un group param, contenant tous les elements visuels des
		 * paramtres.
		 */

		param = new Group();

		ImageView iFondP = new ImageView(new Image(Test.class.getResourceAsStream("images/Menu.png")));
		iFondP.setFitWidth(Terrain.getGrilleWidth());
		iFondP.setFitHeight(Terrain.getGrilleHeight());
		iFondP.setTranslateX(2 * marge + Barre.getDimX());
		iFondP.setTranslateY(marge);

		param.getChildren().add(iFondP);

		ImageView iRetour = new ImageView(new Image(Test.class.getResourceAsStream("images/BoutonRetour.png")));
		iRetour.setFitWidth(6 * Tuile.getTaille());
		iRetour.setFitHeight((3 * Tuile.getTaille()) / 2);
		iRetour.setTranslateX(2 * marge + Barre.getDimX() + Terrain.getGrilleWidth() / 2 - (6 * Tuile.getTaille()) / 2);
		iRetour.setTranslateY(marge + (5 * Terrain.getGrilleHeight()) / 6 - ((3 * Tuile.getTaille()) / 2) / 2);
		iRetour.setOnMouseMoved(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				iRetour.setImage(new Image(Test.class.getResourceAsStream("images/BoutonRetourSurvol.png")));
			}
		});
		iRetour.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				iRetour.setImage(new Image(Test.class.getResourceAsStream("images/BoutonRetour.png")));
			}
		});
		iRetour.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				MenuOn();
			}
		});
		ImageView iPetit = new ImageView(new Image(Test.class.getResourceAsStream("images/BoutonPetit.png")));
		iPetit.setFitWidth(4 * Tuile.getTaille());
		iPetit.setFitHeight(Tuile.getTaille());
		iPetit.setTranslateX(
				2 * marge + Barre.getDimX() + (3 * Terrain.getGrilleWidth()) / 10 - (4 * Tuile.getTaille()) / 2);
		iPetit.setTranslateY(marge + (4 * Terrain.getGrilleHeight()) / 6 - Tuile.getTaille() / 2);
		iPetit.setOnMouseMoved(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				iPetit.setImage(new Image(Test.class.getResourceAsStream("images/BoutonPetitSurvol.png")));
			}
		});
		iPetit.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				iPetit.setImage(new Image(Test.class.getResourceAsStream("images/BoutonPetit.png")));
			}
		});
		iPetit.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				Tuile.setTaille(30);
				refresh(primaryStage);
			}
		});
		ImageView iMoyen = new ImageView(new Image(Test.class.getResourceAsStream("images/BoutonMoyen.png")));
		iMoyen.setFitWidth(4 * Tuile.getTaille());
		iMoyen.setFitHeight(Tuile.getTaille());
		iMoyen.setTranslateX(
				2 * marge + Barre.getDimX() + (5 * Terrain.getGrilleWidth()) / 10 - (4 * Tuile.getTaille()) / 2);
		iMoyen.setTranslateY(marge + (4 * Terrain.getGrilleHeight()) / 6 - Tuile.getTaille() / 2);
		iMoyen.setOnMouseMoved(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				iMoyen.setImage(new Image(Test.class.getResourceAsStream("images/BoutonMoyenSurvol.png")));
			}
		});
		iMoyen.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				iMoyen.setImage(new Image(Test.class.getResourceAsStream("images/BoutonMoyen.png")));
			}
		});
		iMoyen.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {

				Tuile.setTaille(40);
				refresh(primaryStage);
			}
		});
		ImageView iGrand = new ImageView(new Image(Test.class.getResourceAsStream("images/BoutonGrand.png")));
		iGrand.setFitWidth(4 * Tuile.getTaille());
		iGrand.setFitHeight(Tuile.getTaille());
		iGrand.setTranslateX(
				2 * marge + Barre.getDimX() + (7 * Terrain.getGrilleWidth()) / 10 - (4 * Tuile.getTaille()) / 2);
		iGrand.setTranslateY(marge + (4 * Terrain.getGrilleHeight()) / 6 - Tuile.getTaille() / 2);
		iGrand.setOnMouseMoved(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				iGrand.setImage(new Image(Test.class.getResourceAsStream("images/BoutonGrandSurvol.png")));
			}
		});
		iGrand.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				iGrand.setImage(new Image(Test.class.getResourceAsStream("images/BoutonGrand.png")));
			}
		});
		iGrand.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				Tuile.setTaille(50);
				refresh(primaryStage);
			}
		});
		param.getChildren().add(iRetour);
		param.getChildren().add(iPetit);
		param.getChildren().add(iMoyen);
		param.getChildren().add(iGrand);

		/**
		 * Lancement du jeu.
		 */

		scene.setFill(Color.rgb(210, 200, 190, 1.0));
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		root.getChildren().add(jeu);
		System.out.println(jeu.toString());

		game = new Timer(monTerrain);

		if (inMenu) {
			game.stop();
			jeu.setEffect(new GaussianBlur(4 * marge));
			root.getChildren().add(menu);
		}
		else
			game.start();
	}

	static public boolean enPause() {
		return inPause;
	}

	static public void PauseGame() {
		inPause = !inPause;
		if (inPause) {
			game.stop();
			jeu.setEffect(new GaussianBlur(2 * marge));
			root.getChildren().add(pause);
		} else {
			jeu.setEffect(new GaussianBlur(0));
			root.getChildren().remove(pause);
			game.setTime(new Date().getTime());
			game.start();
		}
	}

	public static void EndGame(int JoueurVictorieux) {
		inPause = !inPause;
		game.stop();
		root.getChildren().add(finalscreen.display(JoueurVictorieux));
	}

	public static void MenuOff() {
		jeu.setEffect(new GaussianBlur(0));
		root.getChildren().remove(menu);
		game.start();
		inMenu = false;
	}

	public static void MenuOn() {
		root.getChildren().remove(param);
		root.getChildren().add(menu);
	}

	public static void ParamOff() {
		root.getChildren().remove(param);
		root.getChildren().add(menu);
	}

	public static void ParamOn() {
		root.getChildren().remove(menu);
		root.getChildren().add(param);
	}

	static public boolean getMenu() {
		return inMenu;
	}
	
	static public void setMenu(boolean t){
		inMenu = t;
	}

	static public boolean getParam() {
		return inParam;
	}

	public static void refresh(Stage stage) {
		inPause = false;
		finalscreen.undisplay();
		Case.set();
		Barre.set();
		Boite.set();
		Mate.set();
		Team.set();
		Portrait.set();
		Terrain.set();
		restart(stage);
	}

}
