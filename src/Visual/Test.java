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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Test extends Application {

	static int marge = Tuile.getTaille() / 5;
	private static KeyCode haut1 = KeyCode.Z;
	private static KeyCode bas1 = KeyCode.S;
	private static KeyCode gauche1 = KeyCode.Q;
	private static KeyCode droite1 = KeyCode.D;
	private static KeyCode choix11 = KeyCode.DIGIT1;
	private static KeyCode choix12 = KeyCode.DIGIT2;
	private static KeyCode choix13 = KeyCode.DIGIT3;
	private static KeyCode haut2 = KeyCode.UP;
	private static KeyCode bas2 = KeyCode.DOWN;
	private static KeyCode gauche2 = KeyCode.LEFT;
	private static KeyCode droite2 = KeyCode.RIGHT;
	private static KeyCode choix21 = KeyCode.SEMICOLON;
	private static KeyCode choix22 = KeyCode.COLON;
	private static KeyCode choix23 = KeyCode.EXCLAMATION_MARK;

	private static Timer game;
	private static boolean inPause = false, inMenu = true, inParam = false;
	private static Group root, jeu, pause, menu, param;
	private static Rectangle pauseScreen = new Rectangle();
	private static Text pauseText = new Text("PAUSE");
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

		Rectangle tHaut1 = new Rectangle(Case.getTaille(), Case.getTaille(), Color.BLACK);
		tHaut1.setTranslateX(2 * marge + Barre.getDimX() + (4 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 2);
		tHaut1.setTranslateY(marge + (2 * Terrain.getGrilleHeight()) / 6 - Case.getTaille() / 2);
		tHaut1.setArcHeight(marge);
		tHaut1.setArcWidth(marge);
		Text textHaut1 = new Text(haut1.toString());
		textHaut1.setTranslateX(
				2 * marge + Barre.getDimX() + (4 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 5);
		textHaut1.setTranslateY(marge + (2 * Terrain.getGrilleHeight()) / 6 + Case.getTaille() / 5);
		textHaut1.setFill(Color.WHITE);
		textHaut1.setFont(new Font(Tuile.getTaille() - marge));
		tHaut1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tHaut1.requestFocus();
			}
		});
		tHaut1.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				haut1 = ke.getCode();
				textHaut1.setText(haut1.toString());
			}
		});
		textHaut1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tHaut1.requestFocus();
			}
		});
		param.getChildren().add(tHaut1);
		param.getChildren().add(textHaut1);

		Rectangle tBas1 = new Rectangle(Case.getTaille(), Case.getTaille(), Color.BLACK);
		tBas1.setTranslateX(2 * marge + Barre.getDimX() + (4 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 2);
		tBas1.setTranslateY(marge + (3 * Terrain.getGrilleHeight()) / 6 - Case.getTaille() / 2);
		tBas1.setArcHeight(marge);
		tBas1.setArcWidth(marge);
		Text textBas1 = new Text(bas1.toString());
		textBas1.setTranslateX(
				2 * marge + Barre.getDimX() + (4 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 5);
		textBas1.setTranslateY(marge + (3 * Terrain.getGrilleHeight()) / 6 + Case.getTaille() / 5);
		textBas1.setFill(Color.WHITE);
		textBas1.setFont(new Font(Tuile.getTaille() - marge));
		tBas1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tBas1.requestFocus();
			}
		});
		tBas1.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				bas1 = ke.getCode();
				textBas1.setText(bas1.toString());
			}
		});
		textBas1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tBas1.requestFocus();
			}
		});
		param.getChildren().add(tBas1);
		param.getChildren().add(textBas1);

		Rectangle tGauche1 = new Rectangle(Case.getTaille(), Case.getTaille(), Color.BLACK);
		tGauche1.setTranslateX(
				2 * marge + Barre.getDimX() + (3 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 2);
		tGauche1.setTranslateY(marge + (3 * Terrain.getGrilleHeight()) / 6 - Case.getTaille() / 2);
		tGauche1.setArcHeight(marge);
		tGauche1.setArcWidth(marge);
		Text textGauche1 = new Text(gauche1.toString());
		textGauche1.setTranslateX(
				2 * marge + Barre.getDimX() + (3 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 5);
		textGauche1.setTranslateY(marge + (3 * Terrain.getGrilleHeight()) / 6 + Case.getTaille() / 5);
		textGauche1.setFill(Color.WHITE);
		textGauche1.setFont(new Font(Tuile.getTaille() - marge));
		tGauche1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tGauche1.requestFocus();
			}
		});
		tGauche1.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				gauche1 = ke.getCode();
				textGauche1.setText(gauche1.toString());
			}
		});
		textGauche1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tGauche1.requestFocus();
			}
		});
		param.getChildren().add(tGauche1);
		param.getChildren().add(textGauche1);

		Rectangle tDroite1 = new Rectangle(Case.getTaille(), Case.getTaille(), Color.BLACK);
		tDroite1.setTranslateX(
				2 * marge + Barre.getDimX() + (5 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 2);
		tDroite1.setTranslateY(marge + (3 * Terrain.getGrilleHeight()) / 6 - Case.getTaille() / 2);
		tDroite1.setArcHeight(marge);
		tDroite1.setArcWidth(marge);
		Text textDroite1 = new Text(droite1.toString());
		textDroite1.setTranslateX(
				2 * marge + Barre.getDimX() + (5 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 5);
		textDroite1.setTranslateY(marge + (3 * Terrain.getGrilleHeight()) / 6 + Case.getTaille() / 5);
		textDroite1.setFill(Color.WHITE);
		textDroite1.setFont(new Font(Tuile.getTaille() - marge));
		tDroite1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tDroite1.requestFocus();
			}
		});
		tDroite1.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				droite1 = ke.getCode();
				textDroite1.setText(droite1.toString());
			}
		});
		textDroite1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tDroite1.requestFocus();
			}
		});
		param.getChildren().add(tDroite1);
		param.getChildren().add(textDroite1);

		Rectangle tChoix11 = new Rectangle(Case.getTaille(), Case.getTaille(), Color.BLACK);
		tChoix11.setTranslateX(
				2 * marge + Barre.getDimX() + (3 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 2);
		tChoix11.setTranslateY((1 * Terrain.getGrilleHeight()) / 6 - Case.getTaille() / 2);
		tChoix11.setArcHeight(marge);
		tChoix11.setArcWidth(marge);
		Text textChoix11 = new Text(choix11.toString());
		textChoix11.setTranslateX(
				2 * marge + Barre.getDimX() + (3 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 5);
		textChoix11.setTranslateY((1 * Terrain.getGrilleHeight()) / 6 + Case.getTaille() / 5);
		textChoix11.setFill(Color.WHITE);
		textChoix11.setFont(new Font(Tuile.getTaille() - marge));
		tChoix11.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tChoix11.requestFocus();
			}
		});
		tChoix11.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				choix11 = ke.getCode();
				textChoix11.setText(choix11.toString());
			}
		});
		textChoix11.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tChoix11.requestFocus();
			}
		});
		param.getChildren().add(tChoix11);
		param.getChildren().add(textChoix11);

		Rectangle tChoix12 = new Rectangle(Case.getTaille(), Case.getTaille(), Color.BLACK);
		tChoix12.setTranslateX(
				2 * marge + Barre.getDimX() + (4 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 2);
		tChoix12.setTranslateY((1 * Terrain.getGrilleHeight()) / 6 - Case.getTaille() / 2);
		tChoix12.setArcHeight(marge);
		tChoix12.setArcWidth(marge);
		Text textChoix12 = new Text(choix12.toString());
		textChoix12.setTranslateX(
				2 * marge + Barre.getDimX() + (4 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 5);
		textChoix12.setTranslateY((1 * Terrain.getGrilleHeight()) / 6 + Case.getTaille() / 5);
		textChoix12.setFill(Color.WHITE);
		textChoix12.setFont(new Font(Tuile.getTaille() - marge));
		tChoix12.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tChoix12.requestFocus();
			}
		});
		tChoix12.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				choix12 = ke.getCode();
				textChoix12.setText(choix12.toString());
			}
		});
		textChoix12.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tChoix12.requestFocus();
			}
		});
		param.getChildren().add(tChoix12);
		param.getChildren().add(textChoix12);

		Rectangle tChoix13 = new Rectangle(Case.getTaille(), Case.getTaille(), Color.BLACK);
		tChoix13.setTranslateX(
				2 * marge + Barre.getDimX() + (5 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 2);
		tChoix13.setTranslateY((1 * Terrain.getGrilleHeight()) / 6 - Case.getTaille() / 2);
		tChoix13.setArcHeight(marge);
		tChoix13.setArcWidth(marge);
		Text textChoix13 = new Text(choix13.toString());
		textChoix13.setTranslateX(
				2 * marge + Barre.getDimX() + (5 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 5);
		textChoix13.setTranslateY((1 * Terrain.getGrilleHeight()) / 6 + Case.getTaille() / 5);
		textChoix13.setFill(Color.WHITE);
		textChoix13.setFont(new Font(Tuile.getTaille() - marge));
		tChoix13.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tChoix13.requestFocus();
			}
		});
		tChoix13.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				choix13 = ke.getCode();
				textChoix13.setText(choix13.toString());
			}
		});
		textChoix13.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tChoix13.requestFocus();
			}
		});
		param.getChildren().add(tChoix13);
		param.getChildren().add(textChoix13);

		Rectangle tHaut2 = new Rectangle(Case.getTaille(), Case.getTaille(), Color.BLACK);
		tHaut2.setTranslateX(2 * marge + Barre.getDimX() + (8 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 2);
		tHaut2.setTranslateY(marge + (2 * Terrain.getGrilleHeight()) / 6 - Case.getTaille() / 2);
		tHaut2.setArcHeight(marge);
		tHaut2.setArcWidth(marge);
		Text textHaut2 = new Text(haut2.toString());
		textHaut2.setTranslateX(
				2 * marge + Barre.getDimX() + (8 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 5);
		textHaut2.setTranslateY(marge + (2 * Terrain.getGrilleHeight()) / 6 + Case.getTaille() / 5);
		textHaut2.setFill(Color.WHITE);
		textHaut2.setFont(new Font(Tuile.getTaille() - marge));
		tHaut2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tHaut2.requestFocus();
			}
		});
		tHaut2.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				haut2 = ke.getCode();
				textHaut2.setText(haut2.toString());
			}
		});
		textHaut2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tHaut2.requestFocus();
			}
		});
		param.getChildren().add(tHaut2);
		param.getChildren().add(textHaut2);

		Rectangle tBas2 = new Rectangle(Case.getTaille(), Case.getTaille(), Color.BLACK);
		tBas2.setTranslateX(2 * marge + Barre.getDimX() + (8 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 2);
		tBas2.setTranslateY(marge + (3 * Terrain.getGrilleHeight()) / 6 - Case.getTaille() / 2);
		tBas2.setArcHeight(marge);
		tBas2.setArcWidth(marge);
		Text textBas2 = new Text(bas2.toString());
		textBas2.setTranslateX(
				2 * marge + Barre.getDimX() + (8 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 5);
		textBas2.setTranslateY(marge + (3 * Terrain.getGrilleHeight()) / 6 + Case.getTaille() / 5);
		textBas2.setFill(Color.WHITE);
		textBas2.setFont(new Font(Tuile.getTaille() - marge));
		tBas2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tBas2.requestFocus();
			}
		});
		tBas2.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				bas2 = ke.getCode();
				textBas2.setText(bas2.toString());
			}
		});
		textBas2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tBas2.requestFocus();
			}
		});
		param.getChildren().add(tBas2);
		param.getChildren().add(textBas2);

		Rectangle tGauche2 = new Rectangle(Case.getTaille(), Case.getTaille(), Color.BLACK);
		tGauche2.setTranslateX(
				2 * marge + Barre.getDimX() + (7 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 2);
		tGauche2.setTranslateY(marge + (3 * Terrain.getGrilleHeight()) / 6 - Case.getTaille() / 2);
		tGauche2.setArcHeight(marge);
		tGauche2.setArcWidth(marge);
		Text textGauche2 = new Text(gauche2.toString());
		textGauche2.setTranslateX(
				2 * marge + Barre.getDimX() + (7 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 5);
		textGauche2.setTranslateY(marge + (3 * Terrain.getGrilleHeight()) / 6 + Case.getTaille() / 5);
		textGauche2.setFill(Color.WHITE);
		textGauche2.setFont(new Font(Tuile.getTaille() - marge));
		tGauche2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tGauche2.requestFocus();
			}
		});
		tGauche2.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				gauche2 = ke.getCode();
				textGauche2.setText(gauche2.toString());
			}
		});
		textGauche2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tGauche2.requestFocus();
			}
		});
		param.getChildren().add(tGauche2);
		param.getChildren().add(textGauche2);

		Rectangle tDroite2 = new Rectangle(Case.getTaille(), Case.getTaille(), Color.BLACK);
		tDroite2.setTranslateX(
				2 * marge + Barre.getDimX() + (9 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 2);
		tDroite2.setTranslateY(marge + (3 * Terrain.getGrilleHeight()) / 6 - Case.getTaille() / 2);
		tDroite2.setArcHeight(marge);
		tDroite2.setArcWidth(marge);
		Text textDroite2 = new Text(droite2.toString());
		textDroite2.setFont(new Font(Tuile.getTaille() - marge));
		textDroite2.setTranslateX(
				2 * marge + Barre.getDimX() + (9 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 5);
		textDroite2.setTranslateY(marge + (3 * Terrain.getGrilleHeight()) / 6 + Case.getTaille() / 5);
		textDroite2.setFill(Color.WHITE);

		tDroite2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tDroite2.requestFocus();
			}
		});
		tDroite2.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				droite2 = ke.getCode();
				textDroite2.setText(droite2.toString());
			}
		});
		textDroite2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tDroite2.requestFocus();
			}
		});
		param.getChildren().add(tDroite2);
		param.getChildren().add(textDroite2);

		Rectangle tChoix21 = new Rectangle(Case.getTaille(), Case.getTaille(), Color.BLACK);
		tChoix21.setTranslateX(
				2 * marge + Barre.getDimX() + (7 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 2);
		tChoix21.setTranslateY((1 * Terrain.getGrilleHeight()) / 6 - Case.getTaille() / 2);
		tChoix21.setArcHeight(marge);
		tChoix21.setArcWidth(marge);
		Text textChoix21 = new Text(choix21.toString());
		textChoix21.setTranslateX(
				2 * marge + Barre.getDimX() + (7 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 5);
		textChoix21.setTranslateY((1 * Terrain.getGrilleHeight()) / 6 + Case.getTaille() / 5);
		textChoix21.setFill(Color.WHITE);
		textChoix21.setFont(new Font(Tuile.getTaille() - marge));
		tChoix21.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tChoix21.requestFocus();
			}
		});
		tChoix21.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				choix21 = ke.getCode();
				textChoix21.setText(choix21.toString());
			}
		});
		textChoix21.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tChoix21.requestFocus();
			}
		});
		param.getChildren().add(tChoix21);
		param.getChildren().add(textChoix21);

		Rectangle tChoix22 = new Rectangle(Case.getTaille(), Case.getTaille(), Color.BLACK);
		tChoix22.setTranslateX(
				2 * marge + Barre.getDimX() + (8 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 2);
		tChoix22.setTranslateY((1 * Terrain.getGrilleHeight()) / 6 - Case.getTaille() / 2);
		tChoix22.setArcHeight(marge);
		tChoix22.setArcWidth(marge);
		Text textChoix22 = new Text(choix22.toString());
		textChoix22.setTranslateX(
				2 * marge + Barre.getDimX() + (8 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 5);
		textChoix22.setTranslateY((1 * Terrain.getGrilleHeight()) / 6 + Case.getTaille() / 5);
		textChoix22.setFill(Color.WHITE);
		textChoix22.setFont(new Font(Tuile.getTaille() - marge));
		tChoix22.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tChoix22.requestFocus();
			}
		});
		tChoix22.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				choix22 = ke.getCode();
				textChoix22.setText(choix22.toString());
			}
		});
		textChoix22.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tChoix22.requestFocus();
			}
		});
		param.getChildren().add(tChoix22);
		param.getChildren().add(textChoix22);

		Rectangle tChoix23 = new Rectangle(Case.getTaille(), Case.getTaille(), Color.BLACK);
		tChoix23.setTranslateX(
				2 * marge + Barre.getDimX() + (9 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 2);
		tChoix23.setTranslateY((1 * Terrain.getGrilleHeight()) / 6 - Case.getTaille() / 2);
		tChoix23.setArcHeight(marge);
		tChoix23.setArcWidth(marge);
		Text textChoix23 = new Text(choix23.toString());
		textChoix23.setTranslateX(
				2 * marge + Barre.getDimX() + (9 * Terrain.getGrilleWidth()) / 12 - Case.getTaille() / 5);
		textChoix23.setTranslateY((1 * Terrain.getGrilleHeight()) / 6 + Case.getTaille() / 5);
		textChoix23.setFill(Color.WHITE);
		textChoix23.setFont(new Font(Tuile.getTaille() - marge));
		tChoix23.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tChoix23.requestFocus();
			}
		});
		tChoix23.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				choix23 = ke.getCode();
				textChoix23.setText(choix23.toString());
			}
		});
		textChoix23.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				tChoix23.requestFocus();
			}
		});
		param.getChildren().add(tChoix23);
		param.getChildren().add(textChoix23);

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
		} else
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

	static public void setMenu(boolean t) {
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

	public static KeyCode getHaut1() {
		return haut1;
	}

	public static KeyCode getBas1() {
		return bas1;
	}

	public static KeyCode getGauche1() {
		return gauche1;
	}

	public static KeyCode getDroite1() {
		return droite1;
	}

	public static KeyCode getChoix11() {
		return choix11;
	}

	public static KeyCode getChoix12() {
		return choix12;
	}

	public static KeyCode getChoix13() {
		return choix13;
	}

	public static KeyCode getHaut2() {
		return haut2;
	}

	public static KeyCode getBas2() {
		return bas2;
	}

	public static KeyCode getGauche2() {
		return gauche2;
	}

	public static KeyCode getDroite2() {
		return droite2;
	}

	public static KeyCode getChoix21() {
		return choix21;
	}

	public static KeyCode getChoix22() {
		return choix22;
	}

	public static KeyCode getChoix23() {
		return choix23;
	}
}
