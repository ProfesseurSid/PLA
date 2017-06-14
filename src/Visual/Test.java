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
	private static Text pauseText = new Text("PAUSE");
	private static Scene scene;
	private static FinalScreen finalscreen = new FinalScreen();

	public static void main(String[] args) {
		Application.launch(Test.class, args);
	}

	public void start(Stage primaryStage) {

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

		pauseText.setFont(new Font(Tuile.getTaille()));
		pauseText.setFill(Color.rgb(0, 0, 0, 1.0));
		pauseText.setX(dimX / 2 - 2 * Tuile.getTaille());
		pauseText.setY(dimY / 2 - 2 * Tuile.getTaille());

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

		ImageView iJouer = new ImageView(new Image(Test.class.getResourceAsStream("images/BoutonJouer.png")));
		iJouer.setFitWidth(8 * Tuile.getTaille());
		iJouer.setFitHeight(2 * Tuile.getTaille());
		iJouer.setTranslateX(dimX / 2 - 4 * Tuile.getTaille());
		iJouer.setTranslateY(dimY / 4 - Tuile.getTaille());
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
		iParam.setFitWidth(8 * Tuile.getTaille());
		iParam.setFitHeight(2 * Tuile.getTaille());
		iParam.setTranslateX(dimX / 2 - 4 * Tuile.getTaille());
		iParam.setTranslateY(dimY / 2 - Tuile.getTaille());
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

		/**
		 * Creation d'un group param, contenant tous les elements visuels des
		 * paramtres.
		 */

		param = new Group();

		ImageView iMenu = new ImageView(new Image(Test.class.getResourceAsStream("images/BoutonMenu.png")));
		iMenu.setFitWidth(8 * Tuile.getTaille());
		iMenu.setFitHeight(2 * Tuile.getTaille());
		iMenu.setTranslateX(dimX / 2 - 4 * Tuile.getTaille());
		iMenu.setTranslateY((5 * dimY) / 6 - Tuile.getTaille());
		iMenu.setOnMouseMoved(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				iMenu.setImage(new Image(Test.class.getResourceAsStream("images/BoutonMenuSurvol.png")));
			}
		});
		iMenu.setOnMouseExited(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				iMenu.setImage(new Image(Test.class.getResourceAsStream("images/BoutonMenu.png")));
			}
		});
		iMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent t) {
				MenuOn();
			}
		});
		ImageView iPetit = new ImageView(new Image(Test.class.getResourceAsStream("images/BoutonPetit.png")));
		iPetit.setFitWidth(6 * Tuile.getTaille());
		iPetit.setFitHeight((3 * Tuile.getTaille()) / 2);
		iPetit.setTranslateX(dimX / 4 - 3 * Tuile.getTaille());
		iPetit.setTranslateY((2 * dimY) / 3 - (3 * Tuile.getTaille()) / 4);
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
		iMoyen.setFitWidth(6 * Tuile.getTaille());
		iMoyen.setFitHeight((3 * Tuile.getTaille()) / 2);
		iMoyen.setTranslateX(dimX / 2 - 3 * Tuile.getTaille());
		iMoyen.setTranslateY((2 * dimY) / 3 - (3 * Tuile.getTaille()) / 4);
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
		iGrand.setFitWidth(6 * Tuile.getTaille());
		iGrand.setFitHeight((3 * Tuile.getTaille()) / 2);
		iGrand.setTranslateX((3 * dimX) / 4 - 3 * Tuile.getTaille());
		iGrand.setTranslateY((2 * dimY) / 3 - (3 * Tuile.getTaille()) / 4);
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
		param.getChildren().add(iMenu);
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
		game.stop();
		jeu.setEffect(new GaussianBlur(4 * marge));
		root.getChildren().add(menu);
	}

	static public boolean enPause() {
		return inPause;
	}

	static public void PauseGame() {
		inPause = !inPause;
		if (inPause) {
			game.stop();
			root.setEffect(new GaussianBlur(10));
			root.getChildren().add(pauseScreen);
			root.getChildren().add(pauseText);

		} else {
			root.getChildren().remove(pauseScreen);
			root.getChildren().remove(pauseText);
			root.setEffect(new GaussianBlur(0));
			game.setTime(new Date().getTime());
			game.start();
		}
	}

	public static void EndGame(int JoueurVictorieux) {
		inPause = !inPause;
		game.stop();
		root.getChildren().add(finalscreen.display(JoueurVictorieux));
	}

	public void MenuOff() {
		jeu.setEffect(new GaussianBlur(0));
		root.getChildren().remove(menu);
		game.start();
		inMenu = false;
	}

	public void MenuOn() {
		root.getChildren().remove(param);
		root.getChildren().add(menu);
	}

	public void ParamOff() {
		root.getChildren().remove(param);
		root.getChildren().add(menu);
	}

	public void ParamOn() {
		root.getChildren().remove(menu);
		root.getChildren().add(param);
	}

	static public boolean getMenu() {
		return inMenu;
	}

	static public boolean getParam() {
		return inParam;
	}

	public void refresh(Stage stage) {
		Case.set();
		Barre.set();
		Boite.set();
		Mate.set();
		Team.set();
		Portrait.set();
		Terrain.set();
		start(stage);
	}

}
